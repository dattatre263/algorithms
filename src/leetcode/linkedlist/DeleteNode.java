package leetcode.linkedlist;

public class DeleteNode {
  static ListNode node = null;
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    ListNode(int x, ListNode next) {
      this.val = x;
      this.next = next;
    }
  }

  static class Solution {
    public void deleteNode(ListNode node) {
      if(node.next == null){
        return;
      }else {
        node.val = node.next.val;
        node.next = node.next.next;
      }
      deleteNode(node.next);
    }
  }

  public static void main(String[] args) {
    DeleteNode.node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
    Solution solution = new Solution();
    solution.deleteNode(node.next.next);
    System.out.println("done");
  }
}
