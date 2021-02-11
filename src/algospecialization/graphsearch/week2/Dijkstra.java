package algospecialization.graphsearch.week2;

import algospecialization.graphsearch.datastructure.DiaGraph;
import algospecialization.graphsearch.datastructure.Graph;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
  Graph graph;
  int[] minDistance;
  List<Integer> doneList = new ArrayList<>();

  public Dijkstra(Graph graph) {
    this.graph = graph;
    minDistance = new int[graph.V().length];
  }

  public void calculateMinDistance() {
    minDistance[1] = 0;
    doneList.add(1);
    int currentDist = 0;
    int nextVertex = -1;
    // check if donelist has all the vertices
    while (doneList.size() < graph.V().length) {
      // for each of the vertices in done list,
      // find the shortes edge to the frontier
      int shortest = Integer.MAX_VALUE;
      for (Integer i : doneList) {
        currentDist = minDistance[i];
        // get all the edges from the graph for the given vertex
        List<Integer> arraylist = this.graph.V()[i];
        // iterate through every edge of the vertex
        for (Integer edge : arraylist) {
          // check of the edge is the shortest
          if (this.graph.E().get(edge).len + currentDist < shortest) {
            Graph.Edge e = this.graph.E().get(edge);
            //check if that vertex is already explored
            if(i == e.v1 && !doneList.contains(e.v2)) {
              //mark the new vertex as possible candidate
              nextVertex = e.v2;
              shortest = e.len + currentDist;
            }else if(i == e.v2 && !doneList.contains(e.v1)){
              //mark the new vertex as possible candidate
              nextVertex = e.v1;
              shortest = e.len + currentDist;
            }
          }
        }
        // done with all edges of a single vertex
      }
      // done with all vertices in done-list
      // add new vertex to the donelist
      doneList.add(nextVertex);
      // add the new distance from root to this vertext
      minDistance[nextVertex] = shortest;
    }
  }

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
