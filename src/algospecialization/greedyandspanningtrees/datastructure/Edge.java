package algospecialization.greedyandspanningtrees.datastructure;

public class Edge implements Comparable<Edge> {
  public int v1;
  public int v2;
  public int len;

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
    if (this.len < that.len) return -1;
    if (this.len > that.len) return 1;
    return 0;
  }
}
