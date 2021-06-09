package leetcode.linkedlist;

public class TwoPassRemoveNthNode {
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
      if(head == null) return null;
      ListNode first = head;
      int i = 1, j = 1;
      while ((j - i + 1) <= n ){
        j++;
        first = first.next;
      }
      if(first == null) return null;

      removeNthFromEnd(head, first);
      return head;
    }

    private void removeNthFromEnd(ListNode second, ListNode first){
      if (first.next == null){
        second.next = second.next.next;
      }else {
        removeNthFromEnd(second.next, first.next);
      }
    }
  }

  public static void main(String[] args) {
    ListNode node = new ListNode(1, new ListNode(2));
    Solution solution = new Solution();
    solution.removeNthFromEnd(node, 2);
    System.out.println();
  }
}
