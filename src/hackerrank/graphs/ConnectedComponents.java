package hackerrank.graphs;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConnectedComponents {
  static int N = Integer.MIN_VALUE;
  private static boolean[] visited = null;
  private static int MIN = Integer.MAX_VALUE;
  private static int MAX = Integer.MIN_VALUE;

  public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
    if (gb == null) return null;
    Map<Integer, Set<Integer>> graph = buildGraph(gb);
    visited = new boolean[N + 1];
    for (Integer integer : graph.keySet()) {
      if (!visited[integer]) {
        BFS(graph, integer);
      }
    }
    List<Integer> ans = new ArrayList<>();
    ans.add(MIN);
    ans.add(MAX);
    return ans;
  }

  private static void BFS(Map<Integer, Set<Integer>> graph, Integer integer) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(integer);
    int count = 0;
    while (!queue.isEmpty()) {
      int item = queue.poll();
      if(!visited[item]){
        visited[item] = true;
        for (Integer entry : graph.get(item)) {
          if (!visited[entry]) queue.add(entry);
        }
        count++;
      }
    }
    if (count > 1) {
      MIN = Math.min(MIN, count);
    }
    MAX = Math.max(MAX, count);
  }

  private static Map<Integer, Set<Integer>> buildGraph(List<List<Integer>> gb) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (List<Integer> pairs : gb) {
      N = Math.max(pairs.get(0), N);
      if (graph.containsKey(pairs.get(0))) {
        graph.get(pairs.get(0)).add(pairs.get(1));
      } else {
        Set<Integer> set = new HashSet<>();
        set.add(pairs.get(1));
        graph.put(pairs.get(0), set);
      }
      N = Math.max(pairs.get(1), N);
      if (graph.containsKey(pairs.get(1))) {
        graph.get(pairs.get(1)).add(pairs.get(0));
      } else {
        Set<Integer> set = new HashSet<>();
        set.add(pairs.get(0));
        graph.put(pairs.get(1), set);
      }
    }
    return graph;
  }

  //  public static void main(String[] args) {
  //    List<List<Integer>> list = new ArrayList<>();
  //    List<Integer> a = new ArrayList<>();
  //    a.add(1);
  //    a.add(17);
  //    list.add(a);
  //    List<Integer> b = new ArrayList<>();
  //    b.add(5);
  //    b.add(13);
  //    list.add(b);
  //    List<Integer> c = new ArrayList<>();
  //    c.add(7);
  //    c.add(12);
  //    list.add(c);
  //    List<Integer> d = new ArrayList<>();
  //    d.add(5);
  //    d.add(17);
  //    list.add(d);
  //    List<Integer> e = new ArrayList<>();
  //    e.add(5);
  //    e.add(12);
  //    list.add(e);
  //    List<Integer> ans = componentsInGraph(list);
  //    System.out.println(ans);
  //  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> gb = new ArrayList<>();

    IntStream.range(0, n)
        .forEach(
            i -> {
              try {
                gb.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList()));
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            });

    List<Integer> result = ConnectedComponents.componentsInGraph(gb);

    bufferedWriter.write(result.stream().map(Object::toString).collect(joining(" ")) + "\n");

    bufferedReader.close();
    bufferedWriter.close();
  }
}
