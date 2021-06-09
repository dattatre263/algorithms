package leetcode.amzn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class RotateRight {
  int size = 0;
  int k = 0;
  ListNode tail = null;
  Stack<ListNode> stack = new Stack<>();
  public ListNode rotateRight(ListNode head, int k) {
    size(head);
    if(size == k) return head;
    if(k > size){
      k = k%size;
    }
    this.k = k;
    extract(head,1);
    while (!stack.isEmpty()){
      ListNode node = stack.pop();
      node.next = head;
      head = node;
    }
    return head;
  }

  private void addToStack() {
    while (tail != null){
      stack.add(new ListNode(tail.val));
      tail = tail.next;
    }
  }

  private void extract(ListNode head,int count){
    if((size - k) == count ){
      tail = head.next;
      addToStack();
      head.next = null;
    }else {
      extract(head.next, ++count);
    }
  }

  private void size(ListNode listNode){
    if(listNode == null)return;
    size++;
    size(listNode.next);
  }

   public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public static void main(String[] args) {
    ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
    RotateRight rotateRight = new RotateRight();
    rotateRight.rotateRight(listNode,2);
    //
  }
}
