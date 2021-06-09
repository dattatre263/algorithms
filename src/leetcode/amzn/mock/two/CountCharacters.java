package leetcode.amzn.mock.two;

import java.util.HashMap;
import java.util.Map;

public class CountCharacters {

  public static int countCharacters(String[] words, String chars) {
    int ans = 0;
    for (String word : words) {
      Map<Character, Integer> map = getMap(chars);
      boolean in = true;
      for (char c : word.toCharArray()) {
        if (map.containsKey(new Character(c))) {
          int i = map.get(new Character(c));
          if (i == 1) {
            map.remove(new Character(c));
          } else {
            map.put(new Character(c), --i);
          }
        } else {
          in = false;
          break;
        }
      }
      if (in) {
        ans = ans + word.length();
      }
    }
    return ans;
  }

  private static Map<Character, Integer> getMap(String chars) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < chars.length(); i++) {
      if (map.containsKey(chars.charAt(i))) {
        map.put(chars.charAt(i), map.get(chars.charAt(i)) + 1);
      } else {
        map.put(chars.charAt(i), 1);
      }
    }
    return map;
  }

  public static void main(String[] args) {

    int count = countCharacters(new String[] {"cat", "bt", "hat", "tree"}, "atach");

    System.out.println();
  }
}
