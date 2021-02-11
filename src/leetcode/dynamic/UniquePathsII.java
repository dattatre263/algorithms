package leetcode.dynamic;


public class UniquePathsII {

  int[][] obstacleGrid;

  public int uniquePathsII(int[][] obstacleGrid) {
    this.obstacleGrid = obstacleGrid;
    if(obstacleGrid[0][0] == 1) return 0;
    if(obstacleGrid == null) return 0;
    if(obstacleGrid.length == 0 && obstacleGrid[0].length == 0) return 0;
    Integer[][] D = new Integer[obstacleGrid.length][obstacleGrid[0].length];
    boolean obstacle = false;
    for (int i = 0; i <D.length; i++) {
      if(obstacle){
        D[i][0] = 0;
      }else {
        if(obstacleGrid[i][0] == 1) {
          D[i][0] = 0;
          obstacle = true;
        }else {
          D[i][0] = 1;
        }
      }
    }
    obstacle = false;
    for (int i = 0; i <obstacleGrid[0].length; i++) {
      if(obstacle){
        D[0][i] = 0;
      }else {
        if(obstacleGrid[0][i] == 1) {
          D[0][i] = 0;
          obstacle = true;
        }else {
          D[0][i] = 1;
        }
      }
    }
    return uniquePaths(D,obstacleGrid.length -1 ,obstacleGrid[0].length -1 );
  }

  public int uniquePaths(Integer[][] D, int m, int n) {
    if(m == 0 || n == 0) return D[m][n];
    if(D[m][n] != null) return D[m][n];
    if(obstacleGrid[m][n] == 1) {
      D[m][n] = 0;
      return D[m][n];
    }
    D[m][n] =  uniquePaths(D,m -1, n) + uniquePaths(D,m, n-1);
    return D[m][n];
  }

  public static void main(String[] args) {
    UniquePathsII uniquePaths = new UniquePathsII();//
    int[][] test = new int[][]{{0,0},{1,0}};
    int ans = uniquePaths.uniquePathsII(test);
    System.out.println(ans);
  }
}
