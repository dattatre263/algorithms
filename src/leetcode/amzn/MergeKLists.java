package leetcode.amzn;

import java.util.List;
import java.util.PriorityQueue;

public class MergeKLists {

   public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;
    ListNode head = null;
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i <lists.length; i++) {
      if(lists[i] != null){
        build(queue,lists[i]);
      }
    }
    if(!queue.isEmpty()){
      head  = new ListNode(queue.poll());
      rebuild(queue, head);
    }
    return head;
  }

  private void rebuild(PriorityQueue<Integer> queue, ListNode head) {
     if(queue.isEmpty()) return;
     ListNode node = new ListNode(queue.poll());
     head.next = node;
     rebuild(queue,head.next);
  }

  private void build(PriorityQueue queue, ListNode node){
     if(node == null) return;
     queue.add(node.val);
     build(queue,node.next);
  }

  public static void main(String[] args) {
    MergeKLists mergeKLists = new MergeKLists();
    ListNode head1 = new ListNode(1,new ListNode(4, new ListNode(5)));
    ListNode head2 = new ListNode(1,new ListNode(3, new ListNode(4)));
    ListNode head3 = new ListNode(2,new ListNode(6));
    ListNode[] array = new ListNode[3];
    array[0] = head1;
    array[1] = head2;
    array[2] = head3;
    mergeKLists.mergeKLists(array);
  }
}
