package algospecialization.greedyandspanningtrees.week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Knapsack {

  long[] values;
  int[] weights;
  int capacity;
  Map<Key, Long> map = new HashMap<>();

  public Knapsack(long[] values, int[] weights) {
    this.values = values;
    this.weights = weights;
  }

  private long calculateOptimalSolution2(int c, int item) {
    // base case
    if (c <= 0 || item < 1) {
      this.map.put(new Key(item, c), 0l);
      return 0;
    }
//    if (c < this.weights[item]) {
//      this.map.put(new Key(item, c), 0l);
//      return 0;
//    }
    if (map.containsKey(new Key(item, c))) {
      return map.get(new Key(item, c));
    } else {
      if (this.weights[item] > c) {
        return calculateOptimalSolution2(c, item - 1);
      } else {
        long val =
            Math.max(
                calculateOptimalSolution2(c, item - 1),
                calculateOptimalSolution2(c - this.weights[item], item - 1) + this.values[item]);
        this.map.put(new Key(item, c), val);
        return val;
      }
    }
  }

  public static void main(String[] args) {
    try {
      Path path = Paths.get(args[0]);
      String[] first = Files.lines(path).findFirst().get().split(" |\t");
      int S = Integer.parseInt(first[0]);
      int N = Integer.parseInt(first[1]);
      long[] values = new long[N + 1];
      int[] weights = new int[N + 1];
      AtomicInteger item = new AtomicInteger(0);
      PriorityQueue<Entry> priorityQueue = new PriorityQueue();
      Files.lines(path)
          .skip(1)
          .forEach(
              line -> {
                String[] data = line.split(" |\t");
                int i = item.incrementAndGet();
                values[i] = Integer.parseInt(data[0]);
                weights[i] = Integer.parseInt(data[1]);
                Entry entry = new Entry(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                priorityQueue.add(entry);
              });
//      while (!priorityQueue.isEmpty()) {
//        int i = item.incrementAndGet();
//        Entry entry = priorityQueue.remove();
//        values[i] = entry.value;
//        weights[i] = entry.weight;
//      }

      Knapsack knapsack = new Knapsack(values, weights);
      long start = System.currentTimeMillis();
      System.out.println(knapsack.calculateOptimalSolution2(S, N));
      long end = System.currentTimeMillis();
      System.out.println(end - start);
//      System.out.println(knapsack.map.size());

    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private static class Key {
    int item;
    int capacity;

    public Key(int item, int capacity) {
      this.capacity = capacity;
      this.item = item;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Key key = (Key) o;
      return item == key.item && capacity == key.capacity;
    }

    @Override
    public int hashCode() {
      return Objects.hash(item, capacity);
    }
  }

  private static class Entry implements Comparable<Entry> {

    private int weight;
    private long value;

    public Entry(long value, int weight) {
      this.value = value;
      this.weight = weight;
    }

    @Override
    public int compareTo(Entry o) {
      if (this.weight < o.weight) return -1;
      if (this.weight > o.weight) return 1;
      return 0;
    }
  }
}
