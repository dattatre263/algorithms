package udemy.mastery.linkedlist;

public class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     *    2    ->    1      ->       null
     *   head    head.next      head.next.next
     *
     *   1    ->    2      ->       null
     *
     *
     *
     *
     * * * * * * *
     * @param listNode
     * @return
     */

    public static ListNode solution(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p =  solution(head.next);
        head.next = head.next.next;
        head.next.next = head;

        return p;

    }


    public static void main(String[] args) {
        ListNode listnode = buildListNode( 5);
        System.out.println();
    }

    private static ListNode buildListNode( int depth) {
        ListNode listNode = null;
        if (depth == 0) {
           return listNode;
        }
        listNode = new ListNode(depth);
        listNode.next = buildListNode(--depth);
        return listNode;
    }
}
