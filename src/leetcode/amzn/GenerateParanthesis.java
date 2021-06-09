package leetcode.amzn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GenerateParanthesis {
  Set<List<String>> collection = new HashSet<>();

  public List<String> generateParenthesis(int n) {
    LinkedList linkedList = new LinkedList();
    linkedList.add("(");
    linkedList.add(")");
    collection.add(linkedList);
    int i = 1;
    while (i < n) {
      Set<List<String>> currentCollection = new HashSet<>();
      for (List<String> previous : collection) {
        int s = previous.size();
        int index = 0;
        while (index <= s/2) {
          LinkedList<String> current = new LinkedList<>(previous);
          current.add(index, "(");
          current.add(++index, ")");
          currentCollection.add(current);
        }
      }
      collection = currentCollection;
      i++;
    }
    List<String> ans = new ArrayList<>();
    for (List<String> li : collection) {
      StringBuilder builder = new StringBuilder();
      for (String st : li) {
        builder.append(st);
      }
      ans.add(builder.toString());
    }
    return ans;
  }

  public static void main(String[] args) {
    //
    GenerateParanthesis generateParanthesis = new GenerateParanthesis();
    List<String> ans = generateParanthesis.generateParenthesis(3);
    System.out.println();
  }
}
