package udemy.mastery.linkedlist;

/**
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class Flatten {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    public static Node solution(Node node){


    }

    private static Node flatten(Node node, Node parent) {
        if(node.next == null) {
            node.next = parent;
            return node;
        }
        if(node.child == null){
            if(node.next != null){
                return flatten(node.next,parent);
            }else {
                return node;
            }
        }else {
            Node child =  flatten(node.child,node.next);
            Node temp = node.next;
            node.next = child;
            child.prev = node;
            return temp


        }

    }

    public static void main(String[] args) {

    }

}
