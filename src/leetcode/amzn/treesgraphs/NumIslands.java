package leetcode.amzn.treesgraphs;

import java.util.AbstractMap.SimpleEntry;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class NumIslands {
  private boolean[][] visited = null;
  private int cnt = 0;
  public int numIslands(char[][] grid) {
    visited = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++){
        if(!visited[i][j] && grid[i][j] == '1'){
          cnt++;
          visited[i][j] = true;
          BFS(grid, i, j);
        }
      }
    }
    return cnt;
  }

  private void BFS(char[][] grid, int a, int b){
    Deque<Map.Entry<Integer, Integer>> queue = new LinkedList<>();
    queue.add(new SimpleEntry<>(a,b));
    while (!queue.isEmpty()){
      Entry<Integer, Integer> entry = queue.remove();
      int i = entry.getKey();
      int j = entry.getValue();
      if(i-1 >= 0 && !visited[i-1][j] && grid[i-1][j] == '1') {
        visited[i-1][j] = true;
        queue.add(new SimpleEntry<>(i-1,j));
      }
      if(i+1 < grid.length && !visited[i+1][j] && grid[i+1][j] == '1') {
        visited[i+1][j] = true;
        queue.add(new SimpleEntry<>(i+1,j));
      }
      if(j+1 < grid[i].length && !visited[i][j+1] && grid[i][j+1] == '1') {
        visited[i][j+1] = true;
        queue.add(new SimpleEntry<>(i,j+1));
      }
      if(j-1 >= 0 && !visited[i][j-1] && grid[i][j-1] == '1') {
        visited[i][j-1] = true;
        queue.add(new SimpleEntry<>(i,j-1));
      }
    }
  }

  public static void main(String[] args) {
    NumIslands numIslands = new NumIslands();
    numIslands.numIslands(
        new char[][] {
          {'1', '1', '1', '1', '0'},
          {'1', '1', '0', '1', '0'},
          {'1', '1', '0', '0', '0'},
          {'0', '0', '0', '0', '0'}
        });
  }
}
