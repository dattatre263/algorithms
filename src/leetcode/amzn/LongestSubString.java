package leetcode.amzn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubString {

//  public int lengthOfLongestSubstring(String s) {
//    String longest = "";
//    StringBuilder temp = new StringBuilder();
//    for(int i = 0; i < s.length(); i++){
//      int j = backOfLength(temp, s.charAt(i));
//      if( j != -1){
//        longest = longest.length() > temp.length() ? longest :temp.toString();
//        temp = new StringBuilder();
//        i = i - j + 1;
//        temp.append(s.charAt(i));
//      }else{
//        temp.append(s.charAt(i));
//      }
//    }
//    return longest.length() > temp.length() ? longest.length() :temp.toString().length();
//  }

  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
      // try to extend the range [i, j]
      if (!set.contains(s.charAt(j))){
        set.add(s.charAt(j++));
        ans = Math.max(ans, j - i);
      }
      else {
        set.remove(s.charAt(i++));
      }
    }
    return ans;
  }

  private int backOfLength(StringBuilder current, char c){
    if(current != null && current.length() != 0){
      for(int i = 0; i < current.length(); i++){
        if(current.charAt(i) == c){
          return current.length()  - i;
        }
      }
    }
    return -1;
  }


  public static void main(String[] args) {
    LongestSubString longestSubString = new LongestSubString();
    //    System.out.println(longestSubString.lengthOfLongestSubstring("dvdf"));
    //    System.out.println(longestSubString.lengthOfLongestSubstring("pwwkew"));
    //    System.out.println(longestSubString.lengthOfLongestSubstring("xyzabcabcbb"));
    System.out.println(Runtime.getRuntime().availableProcessors());
  }
}
