package leetcode.amzn;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MostCommonWord {

  public String mostCommonWord(String paragraph, String[] banned) {
    Map<String, Integer> map = new HashMap();
    Map<String, String> bannedMap = buildBannedMap(banned);
    StringBuilder builder = new StringBuilder();
    for (char c : paragraph.toCharArray()) {
      if (c == ' ' && builder.length() != 0) {
        String word = builder.toString();
        checkBanned(word, bannedMap, map);
        builder = new StringBuilder();
      } else if (!(c == '!' || c == '?' || c == ',' || c == ';' || c == '.')) {
        builder.append(Character.toLowerCase(c));
      }
    }
    return mostCommonWord(map);
  }

  private void checkBanned(String word, Map<String, String> bannedMap, Map<String, Integer> map) {
    if (!bannedMap.containsKey(word)) {
      if (map.containsKey(word)) {
        int i = map.get(word);
        map.put(word, ++i);
      } else {
        map.put(word, 1);
      }
    }
  }

  private Map<String, String> buildBannedMap(String[] banned) {
    Map<String, String> map = new HashMap();
    for (int i = 0; i < banned.length; i++) {
      map.put(banned[i], banned[i]);
    }
    return map;
  }

  private String mostCommonWord(Map<String, Integer> map) {
    String common = "";
    int length = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() > length) {
        common = entry.getKey();
        length = entry.getValue();
      }
    }
    return common;
  }

  public static void main(String[] args) {
    MostCommonWord mostCommonWord = new MostCommonWord();
    System.out.println(
        mostCommonWord.mostCommonWord(
            "Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}));
  }
}
