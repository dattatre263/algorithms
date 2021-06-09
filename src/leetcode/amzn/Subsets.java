package leetcode.amzn;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> collection = new ArrayList<>();
    for (int i : nums){
      List<List<Integer>> currentCollection = new ArrayList<>();
      currentCollection.add(new ArrayList<>());
      currentCollection.get(0).add(i);
      for (List<Integer>  previousEntry: collection ) {
        List<Integer> currentEntry = new ArrayList<>(previousEntry);
        currentEntry.add(i);
        currentCollection.add(currentEntry);
      }
      collection.addAll(currentCollection);
    }
    List<Integer> empty = new ArrayList<>();
    collection.add(empty);
    return collection;
  }

  public static void main(String[] args) {
    //
    Subsets subsets = new Subsets();
    subsets.subsets(new int[]{1,2,3});
  }
}
