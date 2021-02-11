package algospecialization.graphsearch.datastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Graph {
  private int N;
  private int M;
  private ArrayList<Integer>[] V;
  private List<Edge> edges = new ArrayList<>();

  public List<Integer>[] V() {
    return V;
  }

  public List<Edge> E() {
    return edges;
  }

  public void addEdge(int v1, int v2, int... len) {
    boolean edgeAlreadyConsidered = false;
    if (V[v2] != null) {
      for (Integer edge : V[v2]) {
        Edge e = edges.get(edge);
        if (e.v1 == v2 && e.v2 == v1) {
          edgeAlreadyConsidered = true;
        }
      }
    }
    if (!edgeAlreadyConsidered) {
      if (v1 != v2) {
        Edge e1 = new Edge(v1, v2, len[0]);
        if (V[v1] == null) {
          V[v1] = new ArrayList<>();
        }
        if (V[v2] == null) {
          V[v2] = new ArrayList<>();
        }
        V[v1].add(M);
        V[v2].add(M);
        edges.add(e1);
        M++;
      }
    }
  }

  public Graph(int N) {
    this.N = N;
    this.V = new ArrayList[N];
  }

  public static void main(String[] args) {
    final Path path = Paths.get(args[0]);
    int n = args.length > 1 ? Integer.valueOf(args[1]) : getNumberOfVertices(path);
    Graph graph;
    try {
      graph = buildGraph(path, n + 1);
    } catch (IOException e) {
      throw new RuntimeException("Exception", e);
    }
  }

  public static int getNumberOfVertices(Path path) {
    int index1 = path.toString().lastIndexOf("_");
    int index2 = path.toString().lastIndexOf(".txt");
    int length = Integer.valueOf(path.toString().substring(index1 + 1, index2));
    return length;
  }

  public static Graph buildGraph(Path path, int n) throws IOException {
    Graph graph = new Graph(n);
    Files.lines(path)
        .forEach(
            s -> {
              String[] vertices = s.split("\t| ");
              int v1 = Integer.valueOf(vertices[0]);
              int v2 = Integer.valueOf(vertices[1]);
              graph.addEdge(v1, v2);
            });
    return graph;
  }

  public static class Edge implements Comparable<Edge> {
    public int v1;
    public int v2;
    public int len;
    public int dijkstraLen;

    public void setDijkstraLen(int currentLen){
      this.dijkstraLen = currentLen + this.len;
    }

    public Edge(int v1, int v2) {
      this.v1 = v1;
      this.v2 = v2;
    }

    public Edge(int v1, int v2, int len) {
      this(v1, v2);
      this.len = len;
    }

    @Override
    public int compareTo(Edge that) {
      if(this.dijkstraLen <=0 || that.dijkstraLen <= 0) throw new RuntimeException();
      if (this.dijkstraLen < that.dijkstraLen) return -1;
      if (this.dijkstraLen == that.dijkstraLen) return 0;
      return 1;
    }
  }
}
