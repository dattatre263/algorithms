package algospecialization.greedyandspanningtrees.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
  int[] parentMapping;
  int count;
  int targetCLusters;
  Map<Integer, List<Integer>> map = new HashMap<>();
  private boolean done = false;
  private int maxDistance = 0;

  public UnionFind(int N, int targetCLusters) {
    this.parentMapping = new int[N];
    this.targetCLusters = targetCLusters;
    this.count = N;
    for (int i = 1; i < N ; i++) {
      map.put(i, new ArrayList<>());
    }
  }

  public void addEdge(Edge edge) {
    int v1 = edge.v1;
    int v2 = edge.v2;
    if (parentMapping[v1] == 0 && parentMapping[v2] == 0) {
      checkIfDone(edge);
      parentMapping[v1] = v1;
      parentMapping[v2] = v1;
      map.get(v1).add(v1);
      map.get(v1).add(v2);
      map.remove(v2);
    } else if (parentMapping[v1] == 0) {
      checkIfDone(edge);
      parentMapping[v1] = parentMapping[v2];
      map.get(parentMapping[v2]).add(v1);
      map.remove(v1);
    } else if (parentMapping[v2] == 0) {
      checkIfDone(edge);
      parentMapping[v2] = parentMapping[v1];
      map.get(parentMapping[v1]).add(v2);
      map.remove(v2);
    } else {
      // both vertices are not null
      checkCycle(edge);
    }
  }

  private void checkIfDone(Edge edge) {
    if(this.map.size() == targetCLusters){
      this.done = true;
      this.maxDistance = edge.len;
    }
  }

  private void checkCycle(Edge edge) {
    int v1 = edge.v1;
    int v2 = edge.v2;
    if (parentMapping[v1] == (parentMapping[v2])) {
      // ignore because this means this edge will create a cycle
      return;
    } else {
      // check and move the lower sized array to the larger sized array and change the parent.
      checkIfDone(edge);
      List<Integer> l1 = map.get(parentMapping[v1]);
      List<Integer> l2 = map.get(parentMapping[v2]);
      if (l1.size() < l2.size()) {
        unionAndChangeParent(v1, v2);
      } else {
        unionAndChangeParent(v2, v1);
      }
    }
  }

  private void unionAndChangeParent(int from, int to) {
    List<Integer> l1 = map.remove(parentMapping[from]);
    for (Integer vertex : l1) {
      map.get(parentMapping[to]).add(vertex);
      parentMapping[vertex] = parentMapping[to];
    }

  }

  public boolean isDone() {
    return done;
  }

  public int getMaxDistance() {
    return maxDistance;
  }
}
