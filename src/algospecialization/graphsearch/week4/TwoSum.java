package algospecialization.graphsearch.week4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TwoSum {
  private final Map<Long, Integer> map = new HashMap<>();
  private int ans = 0;

  public static void main(String[] args) {
    try {
      Path path = Paths.get(args[0]);
      TwoSum twoSum = new TwoSum();
      Files.lines(path)
          .forEach(
              line -> {
                Integer entry = twoSum.map.getOrDefault(Long.valueOf(line), 0);
                twoSum.map.put(Long.valueOf(line), entry++);
              });

      twoSum.calculate();
      System.out.println(twoSum.ans);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  private  void calculate() {
    for (int target = -10000; target <= 10000; target++) {
      Set<Long> keys = map.keySet();
      for (Long key : keys) {
        long companion = target - key;
        if (map.containsKey(companion)) {
          ans++;
          mutateMap(companion);
          mutateMap(key);
          break;
        }
      }
    }
  }

  private void mutateMap(long item) {
    if (map.get(item) == 1) {
      map.remove(item);
    } else {
      map.put(item, map.get(item) - 1);
    }
  }
}
