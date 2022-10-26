package algospecialization.graphsearch.week1;

import algospecialization.graphsearch.datastructure.Graph;
import algospecialization.graphsearch.datastructure.Graph.Edge;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static algospecialization.graphsearch.datastructure.Graph.buildGraph;
import static algospecialization.graphsearch.datastructure.Graph.getNumberOfVertices;

public class Kosaraju {
  private boolean[] explored;
  private int[] order;
  private Graph graph;
  private int finishingTimes = 1;
  private Map<Integer, Integer> leader = new HashMap<>();
  private int leaderVertex;

  public Kosaraju(Graph graph, int N) {
    explored = new boolean[N + 1];
    order = new int[N + 1];
    this.graph = graph;
    DFSloop();
  }

  public void DFSloop() {
    for (int i = order.length - 1; i > 0; i--) {
      if (!explored[i]) {
        dfs(this.graph, i, 1);
      }
    }
    // reset the array
    explored = new boolean[explored.length];
    for (int i = graph.V().length - 1; i > 0; i--) {
      int vertex = order[i];
      this.leaderVertex = vertex;
      if (!explored[vertex]) {
        dfs(this.graph, vertex, 2);
      }
    }
    Object[] array = this.leader.values().toArray();
    Arrays.sort(array, Collections.reverseOrder());
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      if (array.length > i) {
        builder.append(array[i].toString());
      } else {
        builder.append("0");
      }
      builder.append(",");
    }
    builder.replace(builder.lastIndexOf(","), builder.lastIndexOf(",") + 1, "");
    System.out.print(builder.toString());
  }

  public void dfs(Graph graph, int vertex, int pass) {
    explored[vertex] = true;
    List<Integer> edges = graph.V()[vertex];
    if (edges != null && !edges.isEmpty()) {
      for (Integer j : edges) {
        Edge edge = graph.E().get(j);
        // reverse graph pass
        if (pass == 1) {
          if (edge.v2 == vertex && !explored[edge.v1]) {
            dfs(graph, edge.v1, pass);
          }
        } else {
          if (edge.v1 == vertex && !explored[edge.v2]) {
            dfs(graph, edge.v2, pass);
          }
        }
      }
    }
    if (pass == 1) this.order[finishingTimes++] = vertex;
    if (pass == 2) {
      if (leader.get(leaderVertex) != null) {
        leader.put(leaderVertex, leader.get(leaderVertex) + 1);
      } else {
        leader.put(leaderVertex, 1);
      }
    }
  }

  public static void main(String[] args) {
    long start =  System.nanoTime();
    final Path path = Paths.get(args[0]);
    int n = args.length > 1 ? Integer.valueOf(args[1]) : getNumberOfVertices(path);
    Graph graph;
    try {
      graph = buildGraph(path, n + 1);
      Kosaraju kosaraju = new Kosaraju(graph, n);
      long end =  System.nanoTime();
      System.out.println();
      System.out.println(end - start);
    } catch (IOException e) {
      throw new RuntimeException("Exception", e);
    }
  }
}
