package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        Integer val = map.get(nums[i]);
        val = val + 1;
      } else {
        map.put(nums[i], 1);
      }
    }
    List<Quad> temp = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int x = nums[i];
      int y = target - x;
      List<Quad> list = threeSum(map, nums, y);
      for (Quad quad : list) {
        quad.l = x;
      }
      int a = map.get(x);
      a--;
      if (a == 0) {
        map.remove(x);
      } else {
        map.put(x, a);
      }
      temp.addAll(list);
    }
    for (Quad quad : temp) {
      List list = new ArrayList();
      list.add(quad.i);
      list.add(quad.j);
      list.add(quad.k);
      list.add(quad.l);
      ans.add(list);
    }
    return ans;
  }

  private List<Quad> threeSum(Map<Integer, Integer> map, int[] nums, int target) {
    List<Quad> ans = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int x = nums[i];
      int y = target - x;
      List<Quad> list = twoSum(map, nums, y);
      for (Quad quad : list) {
        quad.k = x;
      }
      int a = map.get(x);
      a--;
      if (a == 0) {
        map.remove(x);
      } else {
        map.put(x, a);
      }
      ans.addAll(list);
    }
    return ans;
  }

  private List<Quad> twoSum(Map<Integer, Integer> map, int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      int x = nums[i];
      int y = target - x;
      List<Quad> innerList = new ArrayList();
      if (map.containsKey(y)) {
        Quad quad = new Quad(x, y, null, null);
        innerList.add(quad);
        int a = map.get(y);
        a--;
        if (a == 0) {
          map.remove(y);
        } else {
          map.put(y, a);
        }
        int b = map.get(x);
        b--;
        if (b == 0) {
          map.remove(x);
        } else {
          map.put(x, b);
        }
      }
    }
    return null;
  }

  private static class Quad {
    Integer i, j, k, l;

    public Quad(Integer i, Integer j, Integer k, Integer l) {
      this.i = i;
      this.j = j;
      this.k = k;
      this.l = l;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Quad quad = (Quad) o;
      return i == quad.i && j == quad.j && k == quad.k && l == quad.l;
    }

    @Override
    public int hashCode() {
      return Objects.hash(i, j, k, l);
    }
  }

  public static void main(String[] args) {
    FourSum fourSum = new FourSum();
    fourSum.fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0);
  }
}
