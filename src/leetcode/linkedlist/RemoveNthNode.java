package leetcode.linkedlist;

public class RemoveNthNode {

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
      int size = lengthOfLinkedList(head);
      if(size == n) {
        head = head.next;
        return head;
      }
      removeNthFromEnd(head,size - n + 1, 1);
      return head;
    }

    public void removeNthFromEnd(ListNode node, int n, int m) {
      if(n == m+1){
        node.next = node.next.next;
      }else {
        removeNthFromEnd(node.next,n,++m);
      }
    }
    private int lengthOfLinkedList(ListNode node){
      ListNode temp = node;
      int size = 1;
      while (temp.next != null) {
        size++;
        temp = temp.next;
      }
      return size;
    }
  }



  public static void main(String[] args) {
    ListNode node = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4))));
    Solution solution = new Solution();
    solution.removeNthFromEnd(node,3);
    System.out.println();
  }
}
