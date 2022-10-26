package ctci.linkedlist;

import ctci.library.LinkedListNode;

import java.util.Random;

public class P2_2 {

  public static void main(String[] args) {
    LinkedListNode first = new LinkedListNode(0, null,
        null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;
    for (int i = 1; i < 10; i++) {
      second = new LinkedListNode(new Random().nextInt(100), null, null);
      first.setNext(second);
      second.setPrevious(first);
      first = second;
    }
    System.out.println(head.printForward());
    P2_2 p2_2 = new P2_2();
    Index obj = new Index();
    int index = p2_2.findKth(3,obj, head);
    System.out.println(obj.value);
  }

  private int findKth(int k, Index obj, LinkedListNode head) {
    if (head == null) return 0;
    int index = findKth(k,obj,head.next) + 1;
    if(index == k){
      obj.value = head.data;
    }
    return index;
  }
  private static class Index {
    int value = -1;
  }

}
