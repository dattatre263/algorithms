package leetcode.amzn.treesgraphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelOrderTraversal {

  Deque<TreeNode> queue = new LinkedList<>();
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> temp = null;
  int cnt = 0;

  public List<List<Integer>> levelOrder(TreeNode root) {
    queue.add(root);
    if(root == null) return new ArrayList<>();
    while (!queue.isEmpty()){
      List<TreeNode> list = new ArrayList<>();
      while (!queue.isEmpty()){
        list.add(queue.remove());
      }
      temp = new ArrayList<>();
      for (TreeNode node: list ) {
          temp.add(node.val);
          if(node.left != null) queue.add(node.left);
          if(node.right != null) queue.add(node.right);
      }
      ans.add(temp);
    }
    return ans;
  }


  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static void main(String[] args) {
    TreeNode treeNode =
        new TreeNode(
            1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3)));
    LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
    levelOrderTraversal.levelOrder(treeNode);
  }
}
