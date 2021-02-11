package algospecialization.graphsearch.week2;

import algospecialization.graphsearch.datastructure.DiaGraph;
import algospecialization.graphsearch.datastructure.DiaGraph.Edge;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class DijkstraWithHeap {

  private static final class Key implements Comparable<Key> {
    private int vertex;
    private int shortestPath;

    public Key(int vertex) {
      this(vertex, 0);
    }

    public Key(int vertex, int shortestPath) {
      this.vertex = vertex;
      this.shortestPath = shortestPath;
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
      return vertex == key.vertex;
    }

    @Override
    public int hashCode() {
      return Objects.hash(vertex);
    }

    @Override
    public int compareTo(Key that) {
      if (this.shortestPath < that.shortestPath) return -1;
      if (this.shortestPath == that.shortestPath) return 0;
      return 1;
    }
  }

  private DiaGraph graph;
  public Integer[] shortestPaths;
  private List<Integer> X = new ArrayList<>();
  private PriorityQueue<Key> heap = new PriorityQueue<>();

  public DijkstraWithHeap(DiaGraph graph) {
    this.graph = graph;
    shortestPaths = new Integer[graph.V().length];
  }

  public void calculateMinDistance() {
    // add the source vertex with shortest distance as zero
    shortestPaths[1] = 0;
    addVertexToHeap(1, 0);
    while (!heap.isEmpty()) {
      Key key = heap.remove();
      shortestPaths[key.vertex] = key.shortestPath;
      updateShortestPaths(key);
    }
  }

  private void updateShortestPaths(Key key) {
    // get all the edges
    for (Integer id : this.graph.V()[key.vertex]) {
      // get the vertex
      int otherVertex = this.graph.E().get(id).v2;
      // check if this other vertex is in X( the list of vertices whose shortest paths have been
      // calculated)
      if (shortestPaths[otherVertex] == null) {
        if (this.heap.contains(new Key(otherVertex))) {
          if (this.heap.removeIf(
              // Execute the round-1
              // recalculate the shortest path of this vertex.
              key1 ->
                  (key1.vertex == otherVertex
                      && key1.shortestPath > (this.graph.E().get(id).len + key.shortestPath)))) {
            // execute round -2. add this new vertex to the heap to put this new vertex in it's
            // correct place.
            this.heap.add(new Key(otherVertex, this.graph.E().get(id).len + key.shortestPath));
          }
        } else {
          // Directly execute round - 2
          Key k = new Key(otherVertex, this.graph.E().get(id).len + key.shortestPath);
          this.heap.add(k);
        }
      }
    }
  }

  private void addVertexToHeap(int vertex, int currentShortest) {
    // get all the edge ids for the given vertex
    List<Integer> ids = this.graph.V()[vertex];
    for (Integer i : ids) {
      // get the edge of the id
      Edge edge = this.graph.E().get(i);
      // get the other vertex
      Integer othervertex = edge.v2;
      // if the head of the edge is in the frontier.
      if (shortestPaths[othervertex] == null) {
        Key key = new Key(othervertex, edge.len + currentShortest);
        this.heap.add(key);
      }
    }
  }

  // 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
  public static void main(String[] args) {
    Path path = Paths.get(args[0]);
    try {
      long start = System.currentTimeMillis();
      List<String> fileStream = Files.readAllLines(path);
      DiaGraph graph = new DiaGraph(fileStream.size() + 1);
      fileStream.forEach(
          s -> {
            String[] vertices = s.split("\t| ");
            for (int i = 1; i < vertices.length; i++) {
              String[] recs = vertices[i].split(",");
              graph.addEdge(
                  Integer.valueOf(vertices[0]), Integer.valueOf(recs[0]), Integer.valueOf(recs[1]));
            }
          });
      DijkstraWithHeap dijkstra = new DijkstraWithHeap(graph);
      dijkstra.calculateMinDistance();
      // 7,37,59,82,99,115,133,165,188,197
      System.out.print(dijkstra.shortestPaths[7] + ",");
      System.out.print(dijkstra.shortestPaths[37] + ",");
      System.out.print(dijkstra.shortestPaths[59] + ",");
      System.out.print(dijkstra.shortestPaths[82] + ",");
      System.out.print(dijkstra.shortestPaths[99] + ",");
      System.out.print(dijkstra.shortestPaths[115] + ",");
      System.out.print(dijkstra.shortestPaths[133] + ",");
      System.out.print(dijkstra.shortestPaths[165] + ",");
      System.out.print(dijkstra.shortestPaths[188] + ",");
      System.out.println(dijkstra.shortestPaths[197]);
      long end = System.currentTimeMillis();
      //      System.out.println(end - start);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
