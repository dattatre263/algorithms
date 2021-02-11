package leetcode.dynamic;

public class UniquePaths {
  public int uniquePaths(int m, int n) {
    Integer[][] D = new Integer[m][n];
    for(int i = 1; i < m; i++){
      D[i][0] = 1;
    }
    for(int i = 1; i < n; i++){
      D[0][i] = 1;
    }
    D[0][0] = 0;
    return uniquePaths(D,m-1,n-1);
  }

  public int uniquePaths(Integer[][] D, int m, int n) {
    if(D[m][n] != null) return D[m][n];
    D[m][n] =  uniquePaths(D,m -1, n) + uniquePaths(D,m, n-1);
    return D[m][n];
  }

  public static void main(String[] args) {
    UniquePaths uniquePaths = new UniquePaths();//
    int ans = uniquePaths.uniquePaths(3,3);
    System.out.println(ans);
  }
}
