package hackerrank.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingStrings {

  public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
    // Write your code here
    List<Integer> ans = new ArrayList<>(queries.size());
    Map<String, Integer> map = new HashMap<>();
    for (String input : strings) {
      if (map.containsKey(input)) {
        map.put(input,map.get(input) + 1);
      }else {
        map.put(input,1);
      }
    }
    int i = 0;
    while (i < queries.size()){
      if(map.containsKey(queries.get(i))){
        ans.add(i, map.get(queries.get(i)));
      }else {
        ans.add(i,0);
      }
      i++;
    }
    return ans;

  }

  public static void main(String[] args) {
    //
  }
}
