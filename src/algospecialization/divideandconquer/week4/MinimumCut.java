package algospecialization.divideandconquer.week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MinimumCut {

  Graph graph;
  int seed;
  List<Integer> edgeIndex;

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(args[0]);
    long lineCount = Files.lines(path).count();
    int answer = Integer.MAX_VALUE;
    for (int j = 1; j < 100; j++) {
      Graph graph = new Graph(Math.toIntExact(lineCount));
      Files.lines(Paths.get(args[0]))
          .forEach(
              s -> {
                int[] array =
                    Stream.of(s.split("\t| ")).mapToInt(value -> Integer.valueOf(value)).toArray();
                for (int i = 1; i < array.length; i++) {
                  graph.addEdge(array[0], array[i]);
                }
              });
      MinimumCut minimumCut = new MinimumCut(graph, j);
      minimumCut.contract();
      answer = minimumCut.graph.m < answer ? minimumCut.graph.m : answer;
    }

    System.out.println(answer);
  }

  public MinimumCut(Graph graph, int seed) {
    this.seed = seed;
    this.graph = graph;
    this.edgeIndex = new ArrayList<>(this.graph.m);
    for (int i = 0; i < graph.m; i++) this.edgeIndex.add(i);
  }

  public void contract() {
    while (graph.n > 2) {
      int random = random(seed, graph.m);
      contract(random);
    }
  }

  static int random(int seed, int Max) {
    Random rand = new Random(seed);
    return rand.nextInt(Max);
  }

  public void contract(int index) {
    // Step 1 pick an edge at random
    int edgeNumber = this.edgeIndex.get(index);
    Edge edge = this.graph.edges.get(edgeNumber);
    int edgeId = edge.id;
    int v1 = edge.v1;
    int v2 = edge.v2;
    // step2 delete the edge and reduce the size of m
    deleteEdge(edge);
    // step 2a remove edge from v2 and v1
    this.graph.vertices.get(v2).remove(new Integer(edgeId));
    this.graph.vertices.get(v1).remove(new Integer(edgeId));
    // step 2.b remove the edge from edgeIndex List
    this.edgeIndex.remove(index);
    // step3 - move remaining edges from v1 to v2
    moveEdges(v1, v2, edgeId);
    // step 6 remove the vertex
    this.graph.vertices.remove(v1);
    this.graph.n--;
  }

  private void moveEdges(int v1, int v2, int contractionEdgeId) {
    List<Integer> edges = this.graph.vertices.get(v1);
    for (Integer edge : edges) {
      this.graph.vertices.get(v2).add(edge);
      // step4 - change vertex of the moved edges
      replaceVertexInedge(v1, v2, edge);
      // step 5 - check if selfloop and delete
      checkSelfLoopAndDelete(v2, edge);


    }
  }

  private void checkSelfLoopAndDelete(int v2, Integer edgeId) {
    Edge edge = this.graph.edges.get(edgeId);
    if(edge != null) {
      if (edge.v1 == edge.v2) {
        this.graph.edges.remove(edgeId);
        // Step 6 remove edge from the edgeIndex array
        this.edgeIndex.remove(new Integer(edgeId));
        this.graph.m--;
      }
    }
  }

  /**
   * @param v1
   * @param v2
   * @param edgeId
   */
  private void replaceVertexInedge(int v1, int v2, Integer edgeId) {
    Edge edge = this.graph.edges.get(edgeId);
    if(edge != null) {
      if (edge.v1 == v1) {
        edge.v1 = v2;
      } else {
        edge.v2 = v2;
      }
    }
  }

  /** @param edge */
  private void deleteEdge(Edge edge) {
    this.graph.edges.remove(edge.id);
    this.graph.m--;
    if (this.graph.edges.size() != this.graph.m) throw new IllegalArgumentException();
  }

  /** */
  static class Graph {
    private int n = 0;
    private int m = 0;

    private Map<Integer, List<Integer>> vertices;
    private Map<Integer, Edge> edges = new HashMap<>();

    public Graph(int n) {
      this.n = n;
      this.vertices = new HashMap<>(n);
    }

    public void addEdge(int v1, int v2) {
      if (v2 > v1) {
        Edge edge = new Edge(v1, v2, m);
        edges.put(m, edge);
        connectVertices(v1, v2, edge);
        m++;
      }
    }

    public void connectVertices(int v1, int v2, Edge edge) {
      if (this.vertices.get(v1) == null) this.vertices.put(v1, new ArrayList<>());
      this.vertices.get(v1).add(edge.id);
      if (this.vertices.get(v2) == null) this.vertices.put(v2, new ArrayList<>());
      this.vertices.get(v2).add(edge.id);
    }
  }

  /** */
  static class Vertex {
    private int v;
    private List<Integer> edges;

    public Vertex(int v, List<Integer> edges) {
      this.v = v;
      this.edges = new ArrayList<>();
      this.edges.addAll(edges);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Vertex vertex = (Vertex) o;

      return new EqualsBuilder().append(v, vertex.v).isEquals();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder(17, 37).append(v).toHashCode();
    }

    @Override
    public String toString() {
      return new ToStringBuilder(this).append("v", v).append("edges", edges).toString();
    }
  }

  static class Edge {
    private int id, v1, v2;

    public Edge(int v1, int v2, int id) {
      this.v1 = v1;
      this.v2 = v2;
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Edge edge = (Edge) o;
      return new org.apache.commons.lang3.builder.EqualsBuilder()
          .append(v1, edge.v1)
          .append(v2, edge.v2)
          .append(id, edge.id)
          .isEquals();
    }

    @Override
    public int hashCode() {
      return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
          .append(v1)
          .append(v2)
          .append(id)
          .toHashCode();
    }

    @Override
    public String toString() {
      return new ToStringBuilder(this)
          .append("id", id)
          .append("v1", v1)
          .append("v2", v2)
          .toString();
    }
  }
}
