package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveZeroSumSublists {

  public static class ListNode {
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

  public static class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
      Map<Integer, List<Integer>> map = new HashMap<>();
      removeZeroSumSublists(head,map, 1);
      ListNode newHead = new ListNode(0,head);
      removeUnwantedNodes(newHead,map,1);
      return newHead.next;
    }

    private void removeUnwantedNodes(ListNode newHead, Map<Integer, List<Integer>> map, int i) {
      if(map.isEmpty() || newHead == null || newHead.next == null) return;
      if(map.containsKey(i)){
        removeUnwantedNodes(newHead.next,map,++i);
        map.remove(i);
      }else {
        newHead.next = newHead.next.next;
        removeUnwantedNodes(newHead.next,map,++i);
      }
    }


    private void removeZeroSumSublists(ListNode head, Map<Integer, List<Integer>> map, int counter) {
      if(head == null) return;
      if(map.containsKey(counter -1)){
        List<Integer> prev = map.get(counter - 1);
        List<Integer> current = new ArrayList<>();
        current.add(head.val);
        map.put(counter,current);
        for (Integer key : prev ) {
            map.get(counter).add(head.val + key);
        }
        int internalCounter = 0;
        for (Integer  pos: prev ) {
          internalCounter++;
          if(head.val + pos == 0){
            for( int j = 1; j <= internalCounter; j++){
              map.remove(counter - j);
            }
            map.remove(counter);
            break;
          }
        }
        removeZeroSumSublists(head.next,map,++counter);
      }else {
        // this is the first entry
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        map.put(counter,list);
        removeZeroSumSublists(head.next,map,++counter);
      }
    }
  }

  public static void main(String[] args) {
    ListNode listNode =
        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(-3, new ListNode(4)))));
    Solution solution = new Solution();
    solution.removeZeroSumSublists(listNode);
  }
}
