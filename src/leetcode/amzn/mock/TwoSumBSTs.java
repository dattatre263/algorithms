package leetcode.amzn.mock;

public class TwoSumBSTs {

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

  static class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
      return recurseBSTs(root1, root2, target);
    }

    private boolean recurseBSTs(TreeNode root1, TreeNode root2, int target) {
      if (root1 == null) return false;
      boolean case1 = checkSecondTree(root1.val, root2, target);
      if (case1) return case1;
      boolean case2 = recurseBSTs(root1.left, root2, target);
      if (case2) return case2;
      boolean case3 = recurseBSTs(root1.right, root2, target);
      return case3;
    }

    private boolean checkSecondTree(int first, TreeNode root2, int target) {
      if (root2 == null) return false;
      if (root2.val + first == target) return true;
      int toSearch = target - first;
      if (toSearch < root2.val) {
        return checkSecondTree(first, root2.left, target);
      } else {
        return checkSecondTree(first, root2.right, target);
      }
    }
  }

  public static void main(String[] args) {

    TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(4));
    TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(3));
    Solution solution = new Solution();
    solution.twoSumBSTs(left,right,5);
  }
}
