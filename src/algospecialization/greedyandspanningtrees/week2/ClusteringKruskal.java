package algospecialization.greedyandspanningtrees.week2;

import algospecialization.greedyandspanningtrees.datastructure.Edge;
import algospecialization.greedyandspanningtrees.datastructure.Graph;
import algospecialization.greedyandspanningtrees.datastructure.Key;
import algospecialization.greedyandspanningtrees.datastructure.UnionFind;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class ClusteringKruskal {
  Graph graph;
  int numOfClusters;

  public ClusteringKruskal(Graph graph, int numOfClusters) {
    this.graph = graph;
    this.numOfClusters = numOfClusters;
  }

  public static void main(String[] args) {
    try {
      Path path = Paths.get(args[0]);
      int N = parseInt(Files.lines(path).findFirst().get());
      Graph graph = new Graph(N + 1, N);
      Files.lines(path)
          .skip(1)
          .forEach(
              line -> {
                String[] entry = line.split("\t| ");
                int v1 = parseInt(entry[0]);
                int v2 = parseInt(entry[1]);
                int cost = parseInt(entry[2]);
                graph.addEdge(v1, v2, cost);
              });
      ClusteringKruskal clusteringKruskal = new ClusteringKruskal(graph, 4);
      clusteringKruskal.buildCluster();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private void buildCluster() {
    PriorityQueue<Key> priorityQueue = this.graph.getPriorityQueue();
    UnionFind unionFind = new UnionFind(this.graph.getN(),numOfClusters);
    while (!unionFind.isDone()) {
      Key key = priorityQueue.remove();
      Edge edge = this.graph.getE().get(key.getAttribute());
      unionFind.addEdge(edge);
    }
    System.out.println(unionFind.getMaxDistance());
  }
}
