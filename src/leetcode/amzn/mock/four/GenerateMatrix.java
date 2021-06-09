package leetcode.amzn.mock.four;

public class GenerateMatrix {
  private int next = 0;
  private int i = 0;
  private int j = 0;
  private String direction = "right";

  public int[][] generateMatrix(int n) {
    int[][]  ans = new int[n][n];
    dfs(ans);
    return ans;
  }

  private void dfs(int[][] ans) {
    if(next >= Math.pow(ans.length,2)) return;
    //go right
    right(ans);
    //reset i & j
    --j;++i;
    // go down
    down(ans);
    //reset i & j
    --i;--j;
    // go left
    left(ans);
    //reset i & j
    ++j;--i;
    //go up
    up(ans);
    //reset i & j
    ++i; ++j;
    dfs(ans);
  }

  private void right(int[][] ans){
    if(j < ans[i].length && ans[i][j] ==0){
      ans[i][j] = ++next;
      ++j;
      right(ans);
    }
  }

  private void down(int[][] ans){
    if(i < ans.length && ans[i][j] == 0){
      ans[i][j] = ++next;
      ++i;
      down(ans);
    }
  }
  private void left(int[][] ans){
    if(j >= 0 && ans[i][j] ==0){
      ans[i][j] = ++next;
      --j;
      left(ans);
    }
  }
  private void up(int[][] ans){
    if(i >= 0 && ans[i][j] ==0){
      ans[i][j] = ++next;
      --i;
      up(ans);
    }
  }

  public static void main(String[] args) {
    GenerateMatrix generateMatrix = new GenerateMatrix();
    generateMatrix.generateMatrix(4);
  }
}
