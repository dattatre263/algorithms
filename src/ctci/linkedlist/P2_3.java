package ctci.linkedlist;

import ctci.library.LinkedListNode;
import java.util.Random;

public class P2_3 {

  public static void main(String[] args) {
    LinkedListNode first = new LinkedListNode((0+ 10), null,
        null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;
    for (int i = 1; i < 10; i++) {
      second = new LinkedListNode(i*10 + 10, null, null);
      first.setNext(second);
      second.setPrevious(first);
      first = second;
    }
    System.out.println(head.printForward());
    LinkedListNode node = new LinkedListNode(60);
    deleteNode(node,head);
    System.out.println(head.printForward());

  }

  private static LinkedListNode deleteNode(LinkedListNode node, LinkedListNode head) {
    if(head == null){
      return null;
    }
    if(node.data == head.data){
      return head.next;
    }else{
      head.next = deleteNode(node, head.next);
      return head;
    }
  }
}
