package leetcode.amzn.mock.seven;

import java.util.*;

public class Solution1 {

  public String mostCommonWord(String paragraph, String[] banned) {
    String[] array = paragraph.split(" |,");
    Set<String> set = new HashSet<>();
    for (int i = 0; i < banned.length; i++) {
      set.add(banned[i]);
    }


    Map<String, Integer> map = new HashMap<>();

    for (String s : array) {
      if(!s.equals("")){
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if(!set.contains(s)){
          if(map.containsKey(s)){
            map.put(s,map.get(s) + 1);
          }else {
            map.put(s,1);
          }
        }
      }
    }
    PriorityQueue<Entity> queue = new PriorityQueue<>(Collections.reverseOrder());
    for (Map.Entry<String,Integer> entry : map.entrySet()) {
      queue.add(new Entity(entry.getKey(), entry.getValue()));
    }
    return queue.poll().value;

  }

  private class Entity implements Comparable<Entity> {
    String value;
    Integer count;
    public Entity(String value, int count){
      this.value = value;
      this.count = count;
    }

    @Override
    public int compareTo(Entity o) {
      return this.count.compareTo(o.count);
    }
  }

  public static void main(String[] args) {

    Solution1 solution1 = new Solution1();
    String ans = solution1.mostCommonWord(
        "Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"});
    System.out.println(ans);
  }
}
