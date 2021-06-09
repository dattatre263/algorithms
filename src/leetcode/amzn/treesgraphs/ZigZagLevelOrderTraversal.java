package leetcode.amzn.treesgraphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ZigZagLevelOrderTraversal {

  Deque<TreeNode> queue = new LinkedList<>();
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> temp = null;
  int level = 0;

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    queue.add(root);
    if (root == null) return new ArrayList<>();
    while (!queue.isEmpty()) {
      List<TreeNode> list = new ArrayList<>();
      while (!queue.isEmpty()) {
        list.add(queue.removeFirst());
      }
      temp = new ArrayList<>();
      for (TreeNode node : list) {
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }
      if(level%2 == 0){
        for (TreeNode node : list) {
          temp.add(node.val);
        }
      }else {
        for (int i = list.size() - 1; i >= 0 ; i--) {
          TreeNode node = list.get(i);
          temp.add(node.val);
        }
      }
      ans.add(temp);
      level++;
    }
    return ans;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static void main(String[] args) {
    TreeNode treeNode =
        new TreeNode(
            1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3, null, new TreeNode(5)));
    ZigZagLevelOrderTraversal levelOrderTraversal = new ZigZagLevelOrderTraversal();
    List<List<Integer>> ans = levelOrderTraversal.zigzagLevelOrder(treeNode);
    System.out.println();
  }
}
