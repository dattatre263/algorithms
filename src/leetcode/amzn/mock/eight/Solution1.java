package leetcode.amzn.mock.eight;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

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

  Map<Integer, Integer> map = new HashMap<>();

  public boolean findTarget(TreeNode root, int k) {
    populateHashMap(root);
    boolean found = false;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int a = entry.getKey();
      int b = k - a;
      if (b == a) {
        if (map.get(a) > 1){
          return true;
        }else {
          return false;
        }
      }else if (map.containsKey(b)) {
        return true;
      }
    }
    return false;
  }

  private void populateHashMap(TreeNode root) {
    if (root == null) return;
    if (map.containsKey(root.val)) {
      map.put(root.val, map.get(root.val) + 1);
    } else {
      map.put(root.val, 1);
    }
    populateHashMap(root.left);
    populateHashMap(root.right);
  }

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(1);
    Solution1 solution1 = new Solution1();
    solution1.findTarget(treeNode, 2);
  }
}
