package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneLetterCombination {

  Map<Integer, List<String>> map = new HashMap<>();

  public List<String> letterCombinations(String digits) {
    if(digits == null || digits.length() == 0) return new ArrayList<>();
    int num = Integer.parseInt(digits);
    for (int i = 2; i < 10; i++) {
      loadMap(i);
    }
    return split(num);
  }

  private  List<String> split(int num) {
    if (String.valueOf(num).length() == 1) {
      return map.get(num);
    } else {
      List<String> a = split(Integer.parseInt(String.valueOf(num).substring(0, 1)));
      List<String> b =
          split(Integer.parseInt(String.valueOf(num).substring(1, String.valueOf(num).length())));
      return combine(a,b);
    }
  }

  private  List<String> combine(List<String> a, List<String> b) {
    List<String> list = new ArrayList<>();
    for (String vala: a ) {
      for (String valb: b) {
        String com = vala + valb;
        list.add(com);
      }
    }
    return list;
  }


  private  void loadMap(int i) {
    List<String> list = new ArrayList<>();
    if (i == 2) {
      list.add("a");
      list.add("b");
      list.add("c");
    }
    if (i == 3) {
      list.add("d");
      list.add("e");
      list.add("f");
    }
    if (i == 4) {
      list.add("g");
      list.add("h");
      list.add("i");
    }
    if (i == 5) {
      list.add("j");
      list.add("k");
      list.add("l");
    }
    if (i == 6) {
      list.add("m");
      list.add("n");
      list.add("o");
    }
    if (i == 7) {
      list.add("p");
      list.add("q");
      list.add("r");
      list.add("s");
    }
    if (i == 8) {
      list.add("t");
      list.add("u");
      list.add("v");
    }
    if (i == 9) {
      list.add("w");
      list.add("x");
      list.add("y");
      list.add("x");
    }
    map.put(i,list);
  }

  public static void main(String[] args) {
    PhoneLetterCombination phoneLetterCombination = new PhoneLetterCombination();
    List<String> list = phoneLetterCombination.letterCombinations(String.valueOf(239));
    for (String x : list ) {
      System.out.print(x + " ");
    }
  }
}
