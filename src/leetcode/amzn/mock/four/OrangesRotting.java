package leetcode.amzn.mock.four;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrangesRotting {

  static boolean[][] visited;
  static Queue<Point> points = new LinkedList<>();
  public static int orangesRotting(int[][] grid) {
    visited = new boolean[grid.length][grid[0].length];
    int level = -1;
    List<Point> starts = getInitialPoint(grid);
    if (starts.isEmpty()) {
      boolean good = dfs(grid, 0, 0);
      if (good) {
        return -1;
      } else {
        return 0;
      }
    }
    for (Point point : starts) {
      points.add(point);
    }

    while (!points.isEmpty()) {
      int count = points.size();
      while (count-- > 0){
        Point point = points.poll();
        int x = point.x;
        int y = point.y;
        visited[x][y] = true;
        grid[x][y] = 2;
        if (x + 1 < grid.length && grid[x + 1][y] == 1 && !visited[x + 1][y]) {
          grid[x+1][y] = 2;
          Point p = new Point(x + 1, y);
          points.add(p);
        }
        if (x - 1 >= 0 && grid[x - 1][y] == 1 && !visited[x - 1][y]) {
          grid[x-1][y] = 2;
          Point p = new Point(x - 1, y);
          points.add(p);
        }
        if (y + 1 < grid[x].length && grid[x][y + 1] == 1 && !visited[x][y + 1]) {
          grid[x][y+1] = 2;
          Point p = new Point(x, y + 1);
          points.add(p);
        }
        if (y - 1 >= 0 && grid[x][y - 1] == 1 && !visited[x][y - 1]) {
          grid[x][y-1] = 2;
          Point p = new Point(x, y - 1);
          points.add(p);
        }
      }
      level++;
    }
    visited = new boolean[grid.length][grid[0].length];
    boolean foundGood = dfs(grid, 0, 0);
    if (foundGood) {
      return -1;
    } else {
      return level;
    }
  }

  private static boolean dfs(int[][] grid, int x, int y) {
    if (x >= grid.length || x < 0 || y >= grid[x].length || y < 0) return false;
    if (visited[x][y]) return false;
    if (grid[x][y] == 1) return true;
    visited[x][y] = true;
    if (dfs(grid, x + 1, y)) return true;
    if (dfs(grid, x - 1, y)) return true;
    if (dfs(grid, x, y + 1)) return true;
    if (dfs(grid, x, y - 1)) return true;
    return false;
  }

  private static List<Point> getInitialPoint(int[][] grid) {
    List<Point> starts = new ArrayList<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 2) {
          starts.add(new Point(i, j));
        }
      }
    }
    return starts;
  }

    private static class Point {
      private int x;
      private int y;

      public Point(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }

  public static void main(String[] args) {
    int[][] grid = new int[][] {{2, 2}, {1, 1}, {0, 0}, {2, 0}};
    int ans = orangesRotting(grid);
    System.out.println(ans);
  }
}
