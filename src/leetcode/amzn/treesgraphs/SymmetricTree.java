package leetcode.amzn.treesgraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class SymmetricTree {

  public boolean isSymmetric(TreeNode root) {
    return isSymmetric(root,root);

  }

  private boolean isSymmetric(TreeNode node1, TreeNode node2) {
    if(node1 == null && node2 == null) return true;
    if(node1 == null || node2 == null) return false;
    if(node1.val != node2.val) return false;
    boolean left = isSymmetric(node1.left,node2.right);
    boolean right = isSymmetric(node1.right, node2.left);
    return left && right;
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
            1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3)));
    SymmetricTree symmetricTree = new SymmetricTree();
    System.out.println(symmetricTree.isSymmetric(treeNode));
  }
}
