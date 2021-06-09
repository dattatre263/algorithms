package leetcode.amzn.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RankArray {

  public static int[] arrayRankTransform(int[] arr) {
    if(arr == null || arr.length == 0) return arr;
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < arr.length; i++){
      queue.add(arr[i]);
    }
    Map<Integer, Integer> map = new HashMap<>();
    int rank = 1;
    int prev = queue.remove();
    map.put(prev,rank);
    while (!queue.isEmpty()){
      int current = queue.remove();
      if(prev == current){
        // do nothing
      }else {
        rank++;
        map.put(current,rank);
        prev = current;
      }
    }
    for (int i = 0; i < arr.length; i++){
      arr[i] = map.get(arr[i]);
    }
    return arr;
  }



  public static void main(String[] args) {
    arrayRankTransform(new int[]{37,12,28,9,100,56,80,5,12});
    System.out.println();
  }
}
