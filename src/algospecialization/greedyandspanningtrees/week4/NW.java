package algospecialization.greedyandspanningtrees.week4;

public class NW {
  String X;
  String Y;
  int gapPenalty;
  int mismatchPenalty;
  int[][] matrix;

  public NW(String X, String Y, int gapPenalty, int mismatchPenalty) {
    this.X = X;
    this.Y = Y;
    this.gapPenalty = gapPenalty;
    this.mismatchPenalty = mismatchPenalty;
    matrix = new int[X.length() + 1][Y.length() + 1];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][0] = gapPenalty * i;
    }
    for (int j = 0; j < matrix[0].length; j++) {
      matrix[0][j] = gapPenalty * j;
    }
  }

  private int calculateScore() {
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[i].length; j++) {
        int case1 =
            matrix[i - 1][j - 1] + (X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : mismatchPenalty);
        int case2 = matrix[i - 1][j] + gapPenalty;
        int case3 = matrix[i][j - 1] + gapPenalty;
        int minimum = Math.min(case1, Math.min(case2, case3));
        matrix[i][j] = minimum;
      }
    }
    return matrix[X.length()][Y.length()];
  }

  private int calculateScoreRecursive(int i, int j) {
    if(i == 0 || j ==0){
      return matrix[i][j];
    }
    int case1 =
        calculateScoreRecursive(i-1, j-1) + (X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : mismatchPenalty);
    int case2 = calculateScoreRecursive(i-1, j) + gapPenalty;
    int case3 = calculateScoreRecursive(i, j-1) + gapPenalty;
    int minimum = Math.min(case1, Math.min(case2, case3));
    matrix[i][j] = minimum;
    return matrix[i][j];
  }

  private String[] reconstruct() {
    String[] ans = new String[2];
    StringBuilder A = new StringBuilder();
    StringBuilder B = new StringBuilder();
    int i = matrix.length - 1;
    int indexI = i - 1;
    int j = matrix[0].length - 1;
    int indexJ = j - 1;
    while (i != 0 && j != 0) {
      int case1 =
          matrix[i - 1][j - 1] + (X.charAt(indexI) == Y.charAt(indexJ) ? 0 : mismatchPenalty);
      int case2 = matrix[i - 1][j] + gapPenalty;
      int case3 = matrix[i][j - 1] + gapPenalty;
      if (matrix[i][j] == case1) {
        A.insert(0, X.charAt(indexI));
        B.insert(0, Y.charAt(indexJ));
        i--;
        j--;
        indexI--;
        indexJ--;
      } else if (case2 == matrix[i][j]) {
        A.insert(0, X.charAt(indexI));
        B.insert(0, " ");
        i--;
        indexI--;
      } else {
        A.insert(0, " ");
        B.insert(0, Y.charAt(indexJ));
        j--;
        indexJ--;
      }
    }
    while (indexI >= 0) {
      A.insert(0, X.charAt(indexI));
      B.insert(0, " ");
      indexI--;
    }
    while (indexJ >= 0) {
      B.insert(0, X.charAt(indexJ));
      A.insert(0, " ");
      indexJ--;
    }
    ans[0] = A.toString();
    ans[1] = B.toString();
    return ans;
  }



  public static void main(String[] args) {
    String X = "babad";
    String Y = new StringBuilder(X).reverse().toString();
    NW nw = new NW(X, Y, 2, 100);
//    int score = nw.calculateScore();
    int score = nw.calculateScoreRecursive(X.length(), Y.length());
    String[] ans = nw.reconstruct();
    String longest = nw.calculateLongestSubstring(ans);
    System.out.println(longest);
  }

  private String calculateLongestSubstring(String[] ans) {
    String O = ans[0];
    String R = ans[1];
    StringBuilder S = new StringBuilder();
    for(int i = 0; i < O.length(); i++){
      if(Character.isSpaceChar(O.charAt(i)) ||  Character.isSpaceChar(R.charAt(i))){
        S.append(" ");
      }else {
        S.append(O.charAt(i));
      }
    }
    String[] substrings = S.toString().split(" ");
    String finalAnswer = "";
    for (String substring : substrings) {
      if(substring.length() > finalAnswer.length()){
        finalAnswer = substring;
      }
    }
    return finalAnswer;
  }

  //  public static void main(String[] args) {
  //    try {
  //      Path path = Paths.get(args[0]);
  //      String line1 = Files.lines(path).findFirst().get();
  //      String line2 = Files.lines(path).skip(1).findFirst().get();
  //      String X = Files.lines(path).skip(2).findFirst().get();
  //      String Y = Files.lines(path).skip(3).findFirst().get();
  //      String[] lengths =  line1.split(" |\t");
  //      String[] penalties = line2.split(" |\t");
  //      int lenofX = Integer.parseInt(lengths[0]);
  //      int lenOfY = Integer.parseInt(lengths[1]);
  //      int mismatchPenalty = Integer.parseInt(penalties[1]);
  //      int gapPenalty = Integer.parseInt(penalties[0]);
  //      if(lenofX == X.length()){
  //        System.out.println("you are golden");
  //      }
  //      NW nw = new NW(X,Y,gapPenalty,mismatchPenalty);
  //      int score = nw.calculateScore();
  //      String[] ans = nw.reconstruct();
  //      System.out.println(ans[0]);
  //      System.out.println(ans[1]);
  //    }catch (IOException exception){
  //      throw new RuntimeException(exception);
  //    }
  //  }
}
