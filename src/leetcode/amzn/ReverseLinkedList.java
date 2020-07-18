package leetcode.amzn;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
 * x) { val = x; } }
 */
class ReverseLinkedList {


  public static ListNode reverseList(ListNode head) {
    Stack<Integer> stack = new Stack();
    reverseList(head, stack);
    return populateList(stack);
  }

  private static ListNode populateList(Stack<Integer> stack) {
    if (stack.empty()) {
      return null;
    }
    ListNode node = new ListNode(stack.pop());
    node.next = populateList(stack);
    return node;
  }

  private static void reverseList(ListNode listNode, Stack<Integer> stack) {
    if (listNode == null) {
      return;
    }
    stack.push(listNode.val);
    reverseList(listNode.next, stack);
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    Stack<Integer> rev = new Stack<>();
    rev.push(5);
    rev.push(4);
    rev.push(3);
    rev.push(2);
    rev.push(1);
    ListNode node = populateList(rev);
    ListNode ret = reverseList(node);
    System.out.println();
  }


}
