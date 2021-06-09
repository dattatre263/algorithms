package leetcode.amzn.treesgraphs;

public class MaxPathSum {

  private int max = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    if(root == null) return 0;
    inOrderTraverseTree(root);
    return max;
  }

  private void inOrderTraverseTree(TreeNode root) {
    if (root == null) return;
    inOrderTraverseTree(root.left);
    inOrderTraverseTree(root.right);
    if(root.left != null){
      int x = root.left.val + root.val;
      if(x > root.val) root.val = x;
    }
    if(root.right != null){
      int x = root.right.val + root.val;
      if(x > root.val) root.val = x;
    }
    max = root.val > max ? root.val : max;
  }

  public static void main(String[] args) {
    MaxPathSum maxPathSum = new MaxPathSum();
    int ans = maxPathSum.maxPathSum(
        new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))));
    System.out.println(ans);
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
}
