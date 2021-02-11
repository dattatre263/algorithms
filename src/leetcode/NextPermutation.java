package leetcode;

import java.util.Arrays;

public class NextPermutation {

  public static void solution(int[] arr){
    boolean swapped = false;
    for (int i = arr.length - 1; i >=1; i--) {
      if(arr[i] > arr[i-1]){
        swap(arr,i,i-1);
        swapped = true;
        break;
      }
    }
    if(!swapped){
      Arrays.sort(arr);
    }
  }

  private static void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1,2,3};
    solution(arr);
    System.out.println(arr);
  }
}
