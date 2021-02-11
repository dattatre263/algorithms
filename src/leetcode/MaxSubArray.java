package leetcode;

public class MaxSubArray {

  private int maxSubArray(int[] nums){
    int max = Integer.MIN_VALUE;
    Integer[][] cache = new Integer[nums.length][nums.length];
    for(int i = 0; i < nums.length; i++) {
      cache[i][i] = nums[i];
      max = cache[i][i] > max ? cache[i][i] : max;
    }
    for (int i = 0; i <nums.length; i++) {
      for (int j = i+1 ; j < nums.length ; j++) {
        cache[i][j]  = cache[i][j-1] + nums[j];
        max = cache[i][j] > max ? cache[i][j] : max;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    MaxSubArray maxSubArray = new MaxSubArray();
    int max = maxSubArray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    System.out.println(max);
  }
}
