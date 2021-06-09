package leetcode.amzn.treesgraphs;


class ValidBST {
  /*
  recursively check if node left child is less than parent and
  right child is greater than parent

   */
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  private boolean isValidBST(TreeNode root, TreeNode low, TreeNode high){
    if(root == null) return true;
    if(low != null && low.val >= root.val) return false;
    if(high != null && high.val <= root.val) return false;
    boolean left =  isValidBST(root.left, low, root);
    boolean right = isValidBST(root.right, root, high);
    return left && right;
  }

  static public class TreeNode {
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
    TreeNode treeNode = new TreeNode(1, new TreeNode(1),null);
    ValidBST validBST = new ValidBST();
    boolean ans = validBST.isValidBST(treeNode);
    System.out.println(ans);
  }
}
