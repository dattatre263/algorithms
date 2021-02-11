package leetcode.dynamic;

public class MinPathSum {

  Integer[][] D;

  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    D = new Integer[m][n];
    D[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) {
      D[i][0] = D[i-1][0] + grid[i][0];
    }
    for (int i = 1; i < n; i++) {
      D[0][i] = D[0][i-1] + grid[0][i];
    }
    return minPathSum(grid,m-1, n-1);

  }

  public int minPathSum(int[][] grid, int m ,int n){
    if (D[m][n] != null) return D[m][n];
    int case1 = grid[m][n] + minPathSum(grid,m-1,n);
    int case2 = grid[m][n] + minPathSum(grid,m,n-1);
    D[m][n] = case1 > case2 ? case2 : case1;
    return D[m][n];
  }

  public static void main(String[] args) {
    MinPathSum minPathSum = new MinPathSum();
    int[][] test = new int[][]{{1,2,3},{4,5,6}};
    int ans = minPathSum.minPathSum(test);
    System.out.println(ans);
  }
}
