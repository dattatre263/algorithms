package leetcode.linkedlist;

public class MergeTwoLists {

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  static class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null && l2 == null) return null;
      ListNode ans = new ListNode();
      if (l1 == null && l2 != null) {
        ans = l2;
        return ans;
      }
      if (l2 == null && l1 != null) {
        ans = l1;
        return ans;
      }
      if (l1.val < l2.val) {
        ans.val = l1.val;
        ans.next = mergeTwoLists(l1.next, l2);
      } else {
        ans.val = l2.val;
        ans.next = mergeTwoLists(l1, l2.next);
      }
      return ans;
    }
  }

  public static void main(String[] args) {
    ListNode one = new ListNode(1, new ListNode(2, new ListNode(4)));
    ListNode two = new ListNode(1, new ListNode(3, new ListNode(4)));
    Solution solution = new Solution();
    ListNode listNode = solution.mergeTwoLists(one, two);
    System.out.println("done");
  }
}
