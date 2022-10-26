package udemy.mastery.linkedlist;

public class ReverseBetween {
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
    static int counter = 1;

    private  static ListNode reverseBetween(ListNode head, int left, int right){
//        if(right < 2 ) return head;
//        if(head == null) return head;
        if(left == 1){
            return reverse(head,right);
        }
        ListNode prev = head;
        ListNode curr = head;
        while (counter < left){
            prev = curr;
            curr = curr.next;
            counter++;
        }
        ListNode listNode = reverse(curr,right);
        prev.next = listNode;
        return head;
    }

    private static ListNode reverse(ListNode head, int right){
        if (counter == right) return head;
        counter++;
        ListNode p = reverse(head.next, right);
        ListNode temp = head.next.next;
        head.next.next = head;
        head.next = temp;
        return p;
    }


    public static void main(String[] args) {
        ListNode node = buildListNode(5);
        ListNode resp = reverseBetween(node,2,4);


    }
    private static ListNode buildListNode(int depth) {
        ListNode listNode = null;
        if (depth == 0) {
            return listNode;
        }
        listNode = new ListNode(depth);
        listNode.next = buildListNode(--depth);
        return listNode;
    }
}
