package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianSortedArrays {

  PriorityQueue<Integer> right = new PriorityQueue<>();
  PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());


  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    for (int i = 0; i < nums1.length; i++) {
      left.add(nums1[i]);
    }
    for (int i = 0; i < nums2.length; i++) {
      right.add(nums2[i]);
    }
    if (left.size() == right.size() && left.size() == 0) return 0;
    rebalance();
    //edge case
    if(left.size() == 0) return right.peek();
    if(right.size() == 0) return left.peek();
    if(left.size() == right.size()){
      return (left.peek() + right.peek())/2.0;
    }else {
      return left.peek();
    }
  }

  private void rebalance() {
    if((left.size() + right.size()) % 2 == 0){
      while (left.size() > right.size()){
        right.add(left.remove());
      }
      while (right.size() > left.size()){
        left.add(right.remove());
      }
      //each have to be equal
    }else {
      //left should be greater than right
      while (left.size()  > right.size() + 1){
        right.add(left.remove());
      }
      while (right.size() + 1 > left.size()){
        left.add(right.remove());
      }
    }
    if(left.size() != 0 && right.size() !=0){
      while (left.peek() > right.peek()){
        int temp = left.remove();
        left.add(right.remove());
        right.add(temp);
      }
    }

  }

  public static void main(String[] args) {
    int[] a = new int[]{};
    int[] b = new int[]{1};
    MedianSortedArrays medianSortedArrays = new MedianSortedArrays();
    System.out.println(
    medianSortedArrays.findMedianSortedArrays(a,b));
  }
}
