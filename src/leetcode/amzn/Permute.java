package leetcode.amzn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Permute {
  Set<List<Integer>> collection = new HashSet<>();
  public List<List<Integer>> permute(int[] nums) {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(nums[0]);
    collection.add(list);
    for (int i = 1; i <nums.length; i++) {
      calculatePermute(nums,i);
    }
    List<List<Integer>> ans = new ArrayList<>();
    for (List<Integer> integers : collection) {
      ans.add(integers);
    }
    return ans;
  }

  private void calculatePermute(int[] nums, int index) {
    Set<List<Integer>> currentCollection = new HashSet<>();
    for (List<Integer>  list: collection ) {
      int i = 0;
      while (i <= list.size()){
        LinkedList<Integer> current = new LinkedList<>(list);
        current.add(i,nums[index]);
        currentCollection.add(current);
        i++;
      }
    }
    collection = currentCollection;
  }

  public static void main(String[] args) {
    Permute permute = new Permute();
    List<List<Integer>> ans =  permute.permute(new int[]{1,1,2});
    System.out.println(ans);
  }
}
