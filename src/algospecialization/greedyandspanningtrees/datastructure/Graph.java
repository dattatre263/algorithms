package algospecialization.greedyandspanningtrees.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
  private int N;
  private int M = 0;
  private List<List<Integer>> V;
  private List<Edge> E;
  // only used for kruskal's algorithm
  private PriorityQueue<Key> priorityQueue = new PriorityQueue();

  public Graph(int N, int M){
    this.N = N;
    this.V = new ArrayList<>(N);
    this.E = new ArrayList<>(M);
    init();
  }

  private void init(){
    V.add(0,null);
    for(int i = 1; i < N  ; i++) {
      List<Integer> list = new ArrayList<>();
      V.add(i,list);
    }
  }

  public PriorityQueue getPriorityQueue() {
    return priorityQueue;
  }

  public int getN() {
    return N;
  }

  public List<List<Integer>> getV() {
    return V;
  }

  public List<Edge> getE() {
    return E;
  }

  public void addEdge(int v1, int v2, int len){
    Edge edge = new Edge(v1,v2,len);
    this.V.get(v1).add(M);
    this.V.get(v2).add(M);
    this.E.add(edge);
    this.priorityQueue.add(new Key(M,len));
    M++;
  }
}
