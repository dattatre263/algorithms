package leetcode.amzn.mock.five;

import java.util.HashMap;
import java.util.Map;

public class Twosum {

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] ans = new int[2];
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
      int x = nums[i];
      int y = target - x;
      if(map.containsKey(y) && map.get(y).intValue() != i){
        ans[0] = i;
        ans[1] = map.get(y);
        break;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    //
    Twosum twoSum = new Twosum();
    int[] ans =  twoSum.twoSum(new int[]{2,7,11,15}, 9);
    System.out.println();
  }
}
