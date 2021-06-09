package leetcode.dynamic;

public class LongestCommonSubsequence {

  Integer[][] P;
  int mismatchCost = 2;
  int gapcost = 1;
  int count = 0;

  public int longestCommonSubsequence(String text1, String text2) {
    P = new Integer[text1.length() + 1][text2.length() + 1];
    for (int i = 0; i <= text1.length(); i++) {
      P[i][0] = i * gapcost;
    }
    for (int j = 0; j <= text2.length(); j++) {
      P[0][j] = j * gapcost;
    }
    for (int i = 1; i <= text1.length(); i++) {
      for (int j = 1; j <= text2.length(); j++) {
        int mismatchcost = text1.charAt(i - 1) == text2.charAt(j - 1) ? 0 : mismatchCost;
        P[i][j] =
            Math.min(P[i - 1][j - 1] + mismatchcost, Math.min(P[i - 1][j] + gapcost, P[i][j - 1] + gapcost));
      }
    }
    reconstruct(text1,text2);
    return count;
  }

  private void reconstruct(String text1, String text2) {
    StringBuilder A = new StringBuilder();
    StringBuilder B = new StringBuilder();
    int m = P.length-1;
    int n = P[0].length -1;
    while (m > 0 && n > 0){
      int cost = text1.charAt(m-1) == text2.charAt(n-1) ? 0 : mismatchCost;
      if(P[m-1][n-1] + cost == P[m][n]){
        //case1
        A.append(text1.charAt(m-1));
        B.append(text2.charAt(n-1));
        if(cost == 0) count++;
        m--; n--;
      }else if(P[m-1][n] + gapcost == P[m][n]) {
        A.append(text1.charAt(m-1));
        B.append(" ");
        m--;
      }else {
        A.append(" ");
        B.append(text2.charAt(n-1));
        n--;
      }
    }
  }

  public static void main(String[] args) {
    LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
    int ans =
        longestCommonSubsequence.longestCommonSubsequence(
            "ezupkr",
            "ubmrapg");
    System.out.println(ans);
  }
}
