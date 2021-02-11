package algospecialization.graphsearch.week4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TwoSum2 {
  private final Map<Integer, PriorityQueue<Long>> map = new TreeMap<>();
  private int ans = 0;

  public static void main(String[] args) {
    try {
      Path path = Paths.get(args[0]);
      TwoSum2 twoSum = new TwoSum2();
      Files.lines(path)
          .forEach(
              line -> {
                twoSum.addToBuckets(Long.valueOf(line));
              });

      System.out.println(twoSum.ans);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  private void addToBuckets(long number){
    int bucket =  getBucket(number);
    if(this.map.containsKey(bucket)){
      this.map.get(bucket).add(number);
    }else {
      PriorityQueue<Long> queue = new PriorityQueue<>();
      queue.add(number);
      map.put(bucket,queue);
    }
  }

  private int getBucket(long number) {
    int bucket = (int) (Math.abs(number) % 10000);
    return bucket;

  }


}
