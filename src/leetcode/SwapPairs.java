package leetcode;

public class SwapPairs {
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
    public ListNode swapNodes(ListNode head, int k) {
      ListNode node = recurse(head,k,1);
      swapPairs(head,node,node);
      return head;
    }
    private ListNode recurse(ListNode node, int k, int counter) {
      if(k == counter)return node;
      return recurse(node.next,k, ++counter);
    }
    private void swapPairs(ListNode head, ListNode node, ListNode last) {
      if(last.next == null){
        swap(head,node);
      }else {
        swapPairs(head.next, node, last.next);
      }
    }
    private void swap(ListNode a, ListNode b) {
      int temp = a.val;
      a.val = b.val;
      b.val = temp;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ListNode listNode =
        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    solution.swapNodes(listNode, 2);
    System.out.println();
  }
}
