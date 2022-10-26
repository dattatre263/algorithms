package algospecialization.greedyandspanningtrees.week1;

import algospecialization.greedyandspanningtrees.datastructure.Edge;
import algospecialization.greedyandspanningtrees.datastructure.Graph;
import algospecialization.greedyandspanningtrees.datastructure.Key;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST {
  private Graph graph;
  private PriorityQueue<Key> priorityQueue = new PriorityQueue<>();
  Boolean[] X;
  int MST = 0;

  public PrimMST(Graph graph) {
    this.graph = graph;
    X = new Boolean[graph.getV().size()];
  }

  private void calculate() {
    int startVertex = this.pickStartVertex();
    X[startVertex] = true;
    this.populateHeap(startVertex);
    while (!this.priorityQueue.isEmpty()){
      Key key = this.priorityQueue.remove();
      X[key.getAttribute()] = true;
      MST = MST + key.getLen();
      this.populateHeap(key.getAttribute());
    }
  }

  private void populateHeap(int startVertex) {
    // first get all edgeIds of the vertex
    List<Integer> edges =  this.graph.getV().get(startVertex);
    // iterate over edgeIds
    for (Integer edgeId: edges ) {
      // get the edge based on Id.
      Edge edge = this.graph.getE().get(edgeId);
      // get the other vertex.
      int otherVertex = this.getOtherVertex(edge,startVertex);
      // check if the vertex is in X
      if(X[otherVertex] == null){
        // then this other vertex is NOT in X.
        //check if already in heap
        Key key = new Key(otherVertex,edge.len);
        boolean removed = this.priorityQueue.removeIf(key1 ->
              key1.getAttribute() == key.getAttribute() && key1.getLen() > key.getLen());
        if(removed){
          this.priorityQueue.add(key);
        }else if(!this.priorityQueue.contains(key)) {
          this.priorityQueue.add(key);
        }
      }
    }
  }

  private int getOtherVertex(Edge edge, int startVertex) {
    return edge.v1 == startVertex ? edge.v2 :edge.v1;
  }

  private int pickStartVertex() {
    return 1;
  }

  public static void main(String[] args) {
    Path path = Paths.get(args[0]);
    try {
      String[] sizes = Files.lines(path).findFirst().get().split("\t| ");
      Graph graph = new Graph(Integer.valueOf(sizes[0]) + 1, Integer.valueOf(sizes[1]));
      Files.lines(path)
          .skip(1)
          .forEach(
              line -> {
                String[] attributes = line.split("\t| ");
                graph.addEdge(
                    Integer.valueOf(attributes[0]),
                    Integer.valueOf(attributes[1]),
                    Integer.valueOf(attributes[2]));
              });
      PrimMST primMST = new PrimMST(graph);
      primMST.calculate();
      System.out.println(primMST.MST);

    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
