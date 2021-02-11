package google.foobar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KnightMoves {
  public static int solution(int src, int dest) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[64];
    int[] D = new int[64];
    D[dest] = 0;
    queue.add(dest);
    visited[dest] = true;
    while (!visited[src]) {
      int v = queue.remove();
      for (int w :getAdjacentVertices(v) ) {
        if(!visited[w]){
          visited[w] = true;
          D[w] = D[v] + 1;
          queue.add(w);
        }
      }
    }
    return D[src];
  }

  private static List<Integer> getAdjacentVertices(int v){
    int xSrc = v/8;
    int ySrc = v%8;
    List<Integer> list = new ArrayList<>();
    if (xSrc - 2 >= 0 && ySrc - 1 >= 0) {
      list.add(map(xSrc - 2, ySrc - 1));
    }
    if (xSrc - 2 >= 0 && ySrc + 1 < 8) {
      list.add(map(xSrc - 2, ySrc + 1));
    }
    if (xSrc + 2 < 8 && ySrc - 1 >= 0) {
      list.add(map(xSrc + 2, ySrc - 1));
    }
    if (xSrc + 2 < 8 && ySrc + 1 < 8) {
      list.add(map(xSrc + 2, ySrc + 1));
    }
    if (xSrc - 1 >= 0 && ySrc - 2 >= 0) {
      list.add(map(xSrc - 1, ySrc - 2));
    }
    if (xSrc + 1 < 8 && ySrc - 2 >= 0) {
      list.add(map(xSrc + 1, ySrc - 2));
    }
    if (xSrc + 1 < 8 && ySrc + 2 < 8) {
      list.add(map(xSrc + 1, ySrc + 2));
    }
    if (xSrc - 1 >= 0 && ySrc + 2 < 8) {
      list.add(map(xSrc - 1, ySrc + 2));
    }
    return list;

  }
  private static int map(int src, int dest) {
    return 8 * src + dest;
  }
  public static void main(String[] args) {
    System.out.println(solution(19, 36));
  }
}
