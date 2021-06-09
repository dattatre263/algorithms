package leetcode.dynamic;

public class LongestPalindromicSubstring {
  Boolean[][] matrix;
  String ans = "";

  public String longestPalindrome(String s) {
    if(s == null) return null;
    if(s.length() == 1) return s;
    if(s.length() == 2){
      return s.charAt(0) == s.charAt(1) ? s : s.substring(0,1);
    }
    matrix = new Boolean[s.length()][s.length()];
    solveBaseCase(s);
    recurse(s,0,s.length() -1);
    return ans;
  }

  private void solveBaseCase(String s) {
    ans = s.substring(0,1);
    for (int i = 0, j = 0; i < s.length() ; i++, j++) {
      matrix[i][j] = true;
    }
    for (int i = 0, j = i+1; j < s.length(); i++,j++) {
      if(s.charAt(i) == s.charAt(j)){
        matrix[i][j] = true;
        ans = s.substring(i,j+1);
      }else {
        matrix[i][j] = false;
      }
    }
  }

  private boolean recurse(String s, int i, int j) {
    //base case
    if(matrix[i][j] != null) return matrix[i][j];
    // case 1
    boolean case1 = (j-1)-(i-1) > 0 && recurse(s,i+1, j-1) && s.charAt(i) == s.charAt(j) ;
    if(case1){
      String temp = s.substring(i,j+1);
      ans = temp.length() > ans.length() ? temp : ans;
      matrix[i][j] = true;
    }else {
      matrix[i][j] = false;
      if(j-1-i > 0 ){
        recurse(s,i, j-1);
      }
      if(j-i+1 > 0) {
        recurse(s,i+1, j);
      }
    }
    return matrix[i][j];
  }


  public static void main(String[] args) {
    LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
    String ans = longestPalindromicSubstring.longestPalindrome("aaaabbaa");
    System.out.println(ans);
  }
}
