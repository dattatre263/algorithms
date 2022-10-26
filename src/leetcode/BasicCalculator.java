package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BasicCalculator {

  public List<String> letterCasePermutation(String S) {
    List<String> list = new ArrayList();
    list.add(S);
    recurseAdd(list,S,0);
    return list;
  }

  private void recurseAdd(List<String> list, String S, int index){
    for(int i = index; i < S.length(); i++) {
      if (Character.isAlphabetic(S.charAt(index))) {
        StringBuilder builder = new StringBuilder(S);
        builder.replace(i, i + 1, swap(S.charAt(index)));
        list.add(builder.toString());
        recurseAdd(list, builder.toString(), i+1);
        recurseAdd(list, S, i+1);
      }
    }
  }
  private String swap(char c){
    char ch = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    return String.valueOf(ch);
  }

  public static void main(String[] args) {
    BasicCalculator basicCalculator = new BasicCalculator();
    basicCalculator.letterCasePermutation("a1b2");
  }
}
