package leetcode.amzn.mock.six;

import java.util.*;

public class CriticalConnections {

  private Map<Integer,List<Integer>> E = new HashMap<>();
  private Map<Integer,List<Integer>> V = new HashMap<>();
  private boolean[] visited = null;
  private Integer count = 0;
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    List<List<Integer>> ans = new ArrayList<>();
    buildGraph(n,connections);
    for (Map.Entry<Integer, List<Integer>> entry : E.entrySet() ) {
      Integer currentEdge = entry.getKey();
      count = 0;
      visited = new boolean[V.size()];
      dfs(currentEdge,E.get(currentEdge).get(0));
      if(count < n){
        ans.add(E.get(currentEdge));
      }
    }
    return ans;
  }

  private void dfs(int currentEdge, int vertex) {
    if(visited[vertex]) return;
    visited[vertex] = true;
    count++;
    List<Integer> edges = V.get(vertex);
    for (int edge : edges) {
      if(edge == currentEdge){
        // ignore
      }else {
        if(E.get(edge).get(0).equals(vertex)){
          dfs(currentEdge,E.get(edge).get(1));
        }else {
          dfs(currentEdge,E.get(edge).get(0));
        }
      }
    }
  }

  private void buildGraph(int n, List<List<Integer>> connections){
    int i = 1;
    for (List<Integer> connection : connections) {
      E.put(i++,connection);
    }
    for (Map.Entry<Integer,List<Integer>> entry: E.entrySet() ) {
      List<Integer> values = entry.getValue();
      for (Integer value : values) {
        if(V.containsKey(value)){
          V.get(value).add(entry.getKey());
        }else {
          List<Integer> edges = new ArrayList<>();
          edges.add(entry.getKey());
          V.put(value,edges);
        }
      }
    }
  }

  public static void main(String[] args) {
    //
    CriticalConnections criticalConnections = new CriticalConnections();
    criticalConnections.criticalConnections(4,Arrays.asList(Arrays.asList(0,1),Arrays.asList(1,2),Arrays.asList(2,0),Arrays.asList(1,3)));
  }
}
