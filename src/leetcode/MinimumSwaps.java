package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumSwaps {
  public static int solution(int[] arr){
    int count = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < arr.length; i++) {
      queue.add(arr[i]);
    }
    Integer min = queue.remove();
    for(int i = 0; i < arr.length - 1; i++){
      if(arr[i] != min){
        for (int j = i+ 1; j < arr.length; j++){
          if(arr[j] == min){
            swap(arr,i,j);
            count++;
            min++;
            break;
          }
        }
      }else {
        min++;
      }
    }
    return count;
  }

  private static void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    System.out.println(solution(new int[]{7, 1, 3, 2, 4, 5, 6}));
  }
}
