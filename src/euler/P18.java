package euler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the
 * maximum total from top to bottom is 23.
 *
 * <p>3 7 4 2 4 6 8 5 9 3
 *
 * <p>That is, 3 + 7 + 4 + 9 = 23.
 *
 * <p>Find the maximum total from top to bottom of the triangle below:
 *
 * <p>75 95 64 17 47 82 18 35 87 10 20 04 82 47 65 19 01 23 75 03 34 88 02 77 73 07 63 67 99 65 04
 * 28 06 16 70 92 41 41 26 56 83 40 80 70 33 41 48 72 33 47 32 37 16 94 29 53 71 44 65 25 43 91 52
 * 97 51 14 70 11 33 28 77 73 17 78 39 68 17 57 91 71 52 38 17 14 91 43 58 50 27 29 48 63 66 04 68
 * 89 53 67 30 73 16 69 87 40 31 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 *
 */
public class P18 {
  private int[][] grid;
  int[][] memo;
  public P18(int[][] grid) {
    this.grid = grid;
    memo = new int[grid.length][grid[0].length];
    for (int i = 0; i < memo.length ; i++) {
      for (int j = 0; j <memo[i].length; j++) {
        memo[i][j] = -1;
      }
    }
  }

  public static void main(String[] args) {
    try {
      Path path = Paths.get(args[0]);
      int size =  Files.readAllLines(path).size();
      int[][] grid = new int[size][size];
      AtomicInteger row = new AtomicInteger(-1);
      Files.lines(path).forEach(line -> {
        int i = row.incrementAndGet();
        String[] vals = line.split(" |\t");
        for (int j = 0; j < vals.length; j++){
          grid[i][j] = Integer.parseInt(vals[j]);
        }
      });
      P18 p18 = new P18(grid);
      System.out.println(p18.calculate());
    }catch (IOException exception){
      throw new RuntimeException(exception);
    }
  }

  private int calculate() {
    int ans = 0;
    for (int i = 0; i < grid[0].length ; i++) {
      int temp =  solve(grid.length -1, i);
      ans = ans > temp  ? ans :temp;
    }
    return ans;
  }

  private int solve(int r, int c){
    if(r == 0 ) return grid[r][c];
    if (memo[r][c] != -1){
      return memo[r][c];
    }
    int ans  = 0;
    int case1 = grid[r][c] + solve(r-1,c);
    if(c-1 > -1){
      int case2 = grid[r][c] + solve(r-1, c-1);
      ans =  Math.max(case1,case2);
    }else {
      ans = case1;
    }
    memo[r][c] = ans;
    return ans;
  }

}
