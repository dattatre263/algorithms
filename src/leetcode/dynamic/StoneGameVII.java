package leetcode.dynamic;

public class StoneGameVII {

  private Integer[][] score;
  private Integer[][] D;

  public int stoneGameVII(int[] stones){
    score = new Integer[stones.length][stones.length];
    D = new Integer[stones.length][stones.length];
    return stoneGameVII(stones, 0, stones.length - 1);
  }

  private int stoneGameVII(int[] stones, int i, int j) {
    if (j - i == 0) {
      return 0;
    }
    if(D[i][j] != null){
      return D[i][j];
    }
    int case1 = sum(stones, i, j - 1) - stoneGameVII(stones, i, j - 1);
    int case2 = sum(stones, i + 1, j) - stoneGameVII(stones, i + 1, j);
    D[i][j] = Math.max(case1, case2);
    return D[i][j];
  }

  private int sum(int[] stones, int i, int j) {
    if (j < i) {
      return 0;
    }
    if(score[i][j] != null) return score[i][j];
    score[i][j] = stones[j] + sum(stones, i, j - 1);
    return score[i][j];
  }

  public static void main(String[] args) {
    StoneGameVII gameVII = new StoneGameVII();
     int ans = gameVII.stoneGameVII(new int[]{7,90,5,1,100,10,10,2});
    System.out.println(ans);
  }
}
