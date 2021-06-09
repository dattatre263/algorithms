package leetcode.amzn;

import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {
    PriorityQueue<Key> map = new PriorityQueue<>();
    for (int i = 0; i <strs.length; i++) {
      //
      char[] array = strs[i].toCharArray();
      Arrays.sort(array);
      Key key = new Key(String.valueOf(array), i);
      map.add(key);
    }
    List<List<String>> ans = new ArrayList<>();
    while (!map.isEmpty()){
      boolean done = false;
      List<String> list = new ArrayList<>();
      Key key = map.poll();
      String k = key.key;
      list.add(strs[key.value]);
      while (!done && !map.isEmpty()){
        Key nextKey = map.peek();
        if(nextKey.key.equals(key.key)){
          map.poll();
          list.add(strs[nextKey.value]);
        }else {
          done = true;
        }
      }
      ans.add(list);
    }
    return ans;
  }

  private class Key implements Comparable<Key> {
     String key;
     Integer value;

    public Key(String key, Integer value){
      this.key = key;
      this.value = value;
    }


    @Override
    public int compareTo(Key o) {
      return this.key.compareTo(o.key);
    }
  }




  public static void main(String[] args) {
    GroupAnagrams groupAnagrams = new GroupAnagrams();
    List<List<String>> ans = groupAnagrams.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    System.out.println(ans);
  }
}
