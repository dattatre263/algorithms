package leetcode.dynamic;

public class ClimbStairs {

  public int climbStairs(int n) {
    int[] D = new int[n + 1];
    D[0] = 1;
    D[1] = 2;
    return climbStairs(D,n-1);
  }

  private int climbStairs(int[] D, int n) {
    if ( n > 1 && D[n] != 0) return D[n];
    if( n <= 1) return D[n];
    D[n] = climbStairs(D, n-1) + climbStairs(D, n-2);
    return D[n];
  }

  public static void main(String[] args) {
    ClimbStairs climbStairs = new ClimbStairs();
    int ans = climbStairs.climbStairs(4);
    System.out.println(ans);
  }
}
