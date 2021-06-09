package leetcode.amzn.mock.six;

import java.util.Stack;

public class ReverseStr {
  public String reverseStr(String s, int k) {
    int cycles = s.length()/(2*k);
    int start = 0;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i <cycles; i++) {
      StringBuilder rev = reverse(s.substring(start,(start +2*k)),k);
      builder.append(rev);
      start = start+2*k;
    }
    if(start < s.length()){
      StringBuilder rev = reverse(s.substring(start),k);
      builder.append(rev);
    }
    return builder.toString();
  }

  private StringBuilder reverse(String s, int k){
    if(s.length() < k){
      return new StringBuilder(s).reverse();
    }else {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < k; i++) {
        stack.add(s.charAt(i));
        //
      }
      StringBuilder builder = new StringBuilder();
      while (!stack.isEmpty()){
        builder.append(stack.pop());
      }
      for (int i = k; i <s.length(); i++) {
        builder.append(s.charAt(i));
      }
      return builder;
    }
  }

  public static void main(String[] args) {
    //
    ReverseStr reverseStr = new ReverseStr();
    String ans = reverseStr.reverseStr("abcdefg", 2);
    System.out.println(ans);
  }
}
