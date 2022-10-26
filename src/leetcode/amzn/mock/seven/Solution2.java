package leetcode.amzn.mock.seven;

import java.util.*;

public class Solution2 {
  int N = 0;
  int count = -1;
  boolean[][] visited = null;
  Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>();

  public int cutOffTree(List<List<Integer>> forest) {
    visited = new boolean[forest.size()][forest.get(0).size()];
    for (int i = 0; i < forest.size(); i++) {
      for (int j = 0; j < forest.get(i).size(); j++) {
        if (forest.get(i).get(j) > 1) {
          N = N + forest.get(i).get(j);
        }
      }
    }
    Map.Entry<Integer, Integer> pair = new AbstractMap.SimpleEntry<>(0, 0);
    queue.add(pair);
    BFS(forest);
    if (N == 0) {
      return count;
    } else {
      return -1;
    }
  }

  private void BFS(List<List<Integer>> forest) {
    while (!queue.isEmpty()) {
      Map.Entry<Integer, Integer> pair = queue.poll();
      int i = pair.getKey();
      int j = pair.getValue();
      if (!visited[pair.getKey()][pair.getValue()]) {
        visited[pair.getKey()][pair.getValue()] = true;
        count++;
        if(forest.get(pair.getKey()).get(pair.getValue()) > 1){
          N = N - forest.get(pair.getKey()).get(pair.getValue());
        }

        int top = Integer.MAX_VALUE, bottom = Integer.MAX_VALUE, left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        if (i - 1 >= 0 && !visited[i - 1][j] && forest.get(i - 1).get(j) > 1) {
          left = forest.get(i - 1).get(j);
        }
        if (i + 1 < forest.size() && !visited[i + 1][j] && forest.get(i + 1).get(j) > 1) {
          right = forest.get(i + 1).get(j);
        }
        if (j + 1 < forest.get(0).size() && !visited[i][j + 1] && forest.get(i).get(j + 1) > 1) {
          bottom = forest.get(i).get(j + 1);
        }
        if (j - 1 >= 0 && !visited[i][j - 1] && forest.get(i).get(j - 1) > 1) {
          top = forest.get(i).get(j - 1);
        }
        int min = Math.min(bottom, Math.min(top, Math.min(left, right)));
        if (min > 1 && min == top && min != Integer.MAX_VALUE) {
          queue.add(new AbstractMap.SimpleEntry<>(i, j - 1));
        } else if (min > 1 && min == bottom && min != Integer.MAX_VALUE) {
          queue.add(new AbstractMap.SimpleEntry<>(i, j + 1));
        } else if (min > 1 && min == left && min != Integer.MAX_VALUE) {
          queue.add(new AbstractMap.SimpleEntry<>(i - 1, j));
        } else if (min > 1 && min == right && min != Integer.MAX_VALUE) {
          queue.add(new AbstractMap.SimpleEntry<>(i + 1, j));
        }
      }
    }
  }

  public static void main(String[] args) {
    //
    List<Integer> list1 = new ArrayList<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    List<Integer> list2 = new ArrayList<>();
    list2.add(0);
    list2.add(0);
    list2.add(4);
    List<Integer> list3 = new ArrayList<>();
    list3.add(7);
    list3.add(6);
    list3.add(5);
    List<List<Integer>> input = new ArrayList<>();
    input.add(list1);
    input.add(list2);
    input.add(list3);
    Solution2 solution2 = new Solution2();
    solution2.cutOffTree(input);
  }
}
