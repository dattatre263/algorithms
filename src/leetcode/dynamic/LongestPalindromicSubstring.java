package leetcode.dynamic;

public class LongestPalindromicSubstring {
  Boolean[][] matrix;
  String s;
  String ans = "";

  public String longestPalindrome(String s) {
    this.s = s;
    matrix = new Boolean[s.length()][s.length()];
    if(s == null || s.length() == 0) return ans;
    ans = s.substring(0,1);
    for (int i = 0; i < s.length(); i++) {
      matrix[i][i] = true;
    }
    isPal(0, s.length() -1);
    return ans;
  }

  private boolean isPal(int i, int j) {
    if (matrix[i][j] != null) return matrix[i][j];
    if((j - i) == 0 ) {
      return matrix[i][j];
    }
    if((j-i) == 1){
      if (s.charAt(i) == s.charAt(j)){
        if(s.substring(i,j+1).length() > ans.length()){
          ans = s.substring(i,j+1);
        }
        matrix[i][j] = true;
      }else{
        matrix[i][j] = false;
      }
      return matrix[i][j];
    }
    matrix[i][j] = s.charAt(i) == s.charAt(j) & isPal(i+1, j-1);
    if(matrix[i][j] && s.substring(i,j+1).length() > ans.length()){
      ans = s.substring(i,j+1);
    }
    isPal(i,j-1);
    isPal(i+1, j);
    return matrix[i][j];
  }

  public static void main(String[] args) {
    LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
    String ans = longestPalindromicSubstring.longestPalindrome("abcdefedcba");
    System.out.println(ans);
  }
}
