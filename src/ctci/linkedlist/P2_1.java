package ctci.linkedlist;

import ctci.library.LinkedListNode;
import java.util.HashSet;
import java.util.Set;

public class P2_1<Item> {


  public static void main(String[] args) {
    LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;
    for (int i = 1; i < 8; i++) {
      second = new LinkedListNode(i % 2, null, null);
      first.setNext(second);
      second.setPrevious(first);
      first = second;
    }
    System.out.println(head.printForward());
    deleteDupsA(head);
    System.out.println(head.printForward());
  }


  private static void deleteDupsA(LinkedListNode node) {
    Set<Integer> set = new HashSet<>();
    while (node != null) {
      if(set.add(node.data)){
        node = node.next;
      }else{
        node.prev.next = node.next;
        if(node.next != null){
          node.next.prev = node.prev;
        }
        node = node.next;
      }
    }
  }

}
