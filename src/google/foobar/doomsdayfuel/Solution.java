package google.foobar.doomsdayfuel;
import java.util.ArrayList;
import java.util.List;
public class Solution {

  public static int[] solution(int[][] input){
    return standardForm(input);
  }

  private static int[] standardForm(int[][] input){
    int[] order = new int[input.length];
    boolean[] terminal = new boolean[input.length];
    for (int i = 0; i < order.length; i++) order[i] = i;
    int current = 0;
    int i = input.length - 1;
    while (i > current){
      if(isTerminal(input, i,terminal)){
        // the row is a terminal row and needs to move up
        swap(input, i, current, true );
        // move the correspondig column over
        swap(input,i,current,false);
        //set the order
        swap(terminal,order,current,i);
        //increment the current row to swap
        current++;
      }else {
        i--;
      }
    }
    //check if the last swap was terminal row
    isTerminal(input, current,terminal);

    int termSize = 0;
    int x = 0;
    while (terminal[x]){
      termSize++;
      x++;
    }
    int noTermSize = terminal.length - termSize;
    sortTerminal(input,terminal,order,0,termSize);
    sortTerminal(input,terminal,order,termSize ,termSize + noTermSize);

    List<List<Fraction>> RL = extractR(input,terminal);
    List<List<Fraction>> QL = extractQ(input,terminal);
    List<List<Fraction>> IL  = extractI(noTermSize);

    Matrix Q = new Matrix(QL, noTermSize, noTermSize);
    Matrix R = new Matrix(RL, noTermSize, termSize);
    Matrix I = new Matrix(IL, noTermSize, termSize);
    
    Matrix IminusQ = I.minus(Q);

    Matrix F = IminusQ.getInverseMatrix();
    Matrix FR = F.multiply(R);
    List<Fraction> FRRow = FR.getRow(0);
    List<Fraction> numeratorList = new ArrayList<>(); // numeratorList
    int[] denomList = new int[FRRow.size()]; // denomList
    // Find the numerators and the common denominator, make it an array
    for (int j = 0; j < FRRow.size(); j++) {
      denomList[j] = FRRow.get(j).getDenominator();
      numeratorList.add(FRRow.get(j));
    }
    int lcm = getLcm(denomList);
    int[] result = new int[FRRow.size()+1];
    for (int j = 0; j < result.length-1; j++) {
      numeratorList.set(j, numeratorList.get(j).multiply(new Fraction(lcm)));
      result[j] = numeratorList.get(j).getNumerator();
    }
    result[FRRow.size()] = lcm;
    //system.out.println(Arrays.toString(result));

    return result;
  }

  public static int getLcm(int arr[]) {
    int max = 0;
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      if (max < arr[i]) {
        max = arr[i];
      }
    }
    int res = 1;
    int factor = 2;
    while (factor <= max) {
      ArrayList<Integer> arrIndex = new ArrayList<Integer>();
      for (int j = 0; j < n; j++) {
        if (arr[j] % factor == 0) {
          arrIndex.add(arrIndex.size(), j);
        }
      }
      if (arrIndex.size() >= 2) {
        // Reduce all array elements divisible
        // by factor.
        for (int j = 0; j < arrIndex.size(); j++) {
          arr[arrIndex.get(j)] /= factor;
        }

        res *= factor;
      } else {
        factor++;
      }
    }

    // Then multiply all reduced array elements
    for (int i = 0; i < n; i++) {
      res *= arr[i];
    }

    return res;
  }

  private static int getZeroIndex(boolean[] terminal,int[] order) {
    int x = 0;
    while (terminal[x]){
      x++;
    }
    int index = 0;
    for (int i = x; i < order.length; i++){
      if(order[i] == 0){
        break;
      }else {
        index++;
      }
    }
    return index;
  }

  private static List<List<Fraction>> extractI(int noTermSize) {
    Fraction one = new Fraction(1);
    Fraction zero = new Fraction(0);
    List<List<Fraction>> IList = new ArrayList<>();
    for (int i = 0; i < noTermSize; i++) {
      ArrayList<Fraction> IRow = new ArrayList<Fraction>();
      for (int j = 0; j < noTermSize; j++) {
        if (i==j) {
          IRow.add(one);
        } else {
          IRow.add(zero);
        }
      }
      IList.add(IRow);
    }
    return IList;
  }

  private static void sortTerminal(int[][] input, boolean[] terminal,  int[] order, int start, int end) {
    for (int i = start; i <end; i++  ){
      int min = i;
      for (int j = i+1; j < end; j++ ){
        if(order[j] < order[min]){
          min = j;
        }
      }
      if(i != min){
        swap(input, i, min, true);
        swap(input, i, min, false);
        swap(terminal,order,i,min);
      }
    }
  }


  private static List<List<Fraction>> extractQ(int[][] input, boolean[] terminal) {
    List<List<Fraction>> Q = new ArrayList<>();
    int fIndex = 0;
    while (terminal[fIndex]){
      fIndex++;
    }
    List<Integer> denominator = getDenominator(input, fIndex);
    for (int i = fIndex, k = 0; i < input.length; i++, k++ ){
      List<Fraction> list = new ArrayList<>();
      for (int j = fIndex, l = 0; j < input.length; j++, l++){
        Fraction fraction = new Fraction(input[i][j],denominator.get(k));
        list.add(fraction);
      }
      Q.add(list);
    }
    return Q;
  }

  private static List<List<Fraction>> extractR(int[][] input, boolean[] terminal) {
    List<List<Fraction>> R = new ArrayList<>();
    int fIndex = 0;
    while (terminal[fIndex]){
      fIndex++;
    }
    List<Integer> denominator = getDenominator(input, fIndex);
    for (int i = fIndex, k = 0; i < input.length; i++, k++ ){
      List<Fraction> list = new ArrayList<>();
      for (int j = 0; j < fIndex; j++){
        Fraction fraction = new Fraction(input[i][j],denominator.get(k));
        list.add(fraction);
      }
      R.add(list);
    }
    return R;
  }

  private static List<Integer> getDenominator(int[][] input, int fIndex) {
    List<Integer> denominator = new ArrayList<>();
    for(int i = fIndex;i < input.length; i++ ){
      int denom = 0;
      for (int j = 0; j < input[i].length ; j++){
        denom = denom + input[i][j];
      }
      denominator.add(denom);
    }
    return denominator;
  }


  private static void swap(boolean[] terminal,int[] order, int i , int j){
    int temp = order[i];
    boolean tempb = terminal[i];
    order[i] = order[j];
    terminal[i] = terminal[j];
    order[j] = temp;
    terminal[j] = tempb;
  }

  private static void swap(int[][] input, int x, int y, boolean isRow){
    int[] temp = new int[input[y].length];
    //copy from source to temp
    for (int i = 0; i < input[y].length; i++){
      if(isRow){
        temp[i] = input[y][i];
      }else {
        temp[i] = input[i][y];
      }
    }
    //copy from x to y
    for (int i = 0; i < input[x].length; i++){
      if(isRow){
        input[y][i] = input[x][i];
      }else {
        input[i][y] = input[i][x];
      }
    }
    //copy from temp to x
    for (int i = 0; i < temp.length; i++) {
      if(isRow){
        input[x][i] = temp[i];
      }else {
        input[i][x] = temp[i];
      }
    }
  }

  private static boolean isTerminal(int[][] input, int xindex, boolean[] terminal){
    boolean isTerminal = true;
    for (int i = 0; i < input[xindex].length; i++){
      if(input[xindex][i] != 0){
        isTerminal = false;
        break;
      }
    }
    if(isTerminal)terminal[xindex] = true;
    return isTerminal;
  }

  private static class Matrix {

    private final int M;
    private final int N;
    private final Fraction det;
    private List<List<Fraction>> matrix;
    private List<List<Fraction>> inverseMatrix;

    public Matrix(List<List<Fraction>> mat, int m, int n) {
      this.matrix = mat;
      this.M = m;
      this.N = n;
      this.det = this.determinant(mat, n);
      this.inverseMatrix = this.inverse();
    }

    private void getCofactor(List<List<Fraction>> mat, List<List<Fraction>> tempMat, int p, int q, int n) {
      int i = 0;
      int j = 0;
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          if (row != p && col != q) {
            tempMat.get(i).set(j++, mat.get(row).get(col));
            if (j == n - 1) {
              j = 0;
              i++;
            }
          }
        }
      }
    }

    private Fraction determinant(List<List<Fraction>> mat, int n) {
      Fraction ans = new Fraction(0, 1);
      if (this.M != this.N) {
        return ans;
      }
      if (n == 1) {
        return mat.get(0).get(0);
      }
      List<List<Fraction>> tempMat = new ArrayList<>();
      // Init 2d fraction arraylist
      for (int i = 0; i < this.M; i++) {
        ArrayList<Fraction> tempMatRow = new ArrayList<Fraction>();
        for (int j = 0; j < this.N; j++) {
          tempMatRow.add(new Fraction(0, 1));
        }
        tempMat.add(tempMatRow);
      }

      int sign = 1;
      Fraction signFraction = new Fraction(sign, 1);
      for (int k = 0; k < n; k++) {
        this.getCofactor(mat, tempMat, 0, k, n);
        ans = ans.plus(signFraction.multiply(mat.get(0).get(k).multiply(determinant(tempMat, n - 1))));
        sign = -sign;
        signFraction = new Fraction(sign, 1);
      }
      return ans;
    }

    private void adjoint(List<List<Fraction>> mat, List<List<Fraction>> adj) {
      if (this.N == 1) {
        adj.get(0).set(0, new Fraction(1, 1));
        return;
      }
      int sign = 1;

      List<List<Fraction>> tempMat = new ArrayList<>();
      // Init 2d fraction arraylist
      for (int i = 0; i < this.N; i++) {
        ArrayList<Fraction> tempMatRow = new ArrayList<Fraction>();
        for (int j = 0; j < this.N; j++) {
          tempMatRow.add(new Fraction(0, 1));
        }
        tempMat.add(tempMatRow);
      }

      for (int p = 0; p < this.N; p++) {
        for (int q = 0; q < this.N; q++) {
          this.getCofactor(mat, tempMat, p, q, this.N);
          sign = ((p + q) % 2 == 0) ? 1 : -1;
          Fraction signFraction = new Fraction(sign, 1);
          adj.get(q).set(p, signFraction.multiply((this.determinant(tempMat, this.N - 1))));
        }
      }
    }

    private List<List<Fraction>> inverse() {
      List<List<Fraction>> inv = new ArrayList<>();
      // Init 2d fraction arraylist
      for (int i = 0; i < this.M; i++) {
        ArrayList<Fraction> invRow = new ArrayList<Fraction>();
        for (int j = 0; j < this.N; j++) {
          invRow.add(new Fraction(0, 1));
        }
        inv.add(invRow);
      }

      if (this.det.equals(new Fraction(0))) {
        return inv;
      }

      List<List<Fraction>> adj = new ArrayList<>();
      // Init 2d fraction arraylist
      for (int i = 0; i < this.M; i++) {
        ArrayList<Fraction> adjRow = new ArrayList<Fraction>();
        for (int j = 0; j < this.N; j++) {
          adjRow.add(new Fraction(0, 1));
        }
        adj.add(adjRow);
      }

      adjoint(this.matrix, adj);
      for (int p = 0; p < this.N; p++) {
        for (int q = 0; q < this.N; q++) {
          Fraction temp = adj.get(p).get(q).dividedBy(this.det);
          inv.get(p).set(q, temp);
        }
      }
      return inv;
    }

    public Solution.Matrix getInverseMatrix() {
      if (this.M != this.N) {
        //system.out.println("No inverse matrix for non-square matrices");
      }
      return new Solution.Matrix(this.inverseMatrix, this.M, this.N);
    }

    public Fraction getElement(int m, int n) {
      return this.matrix.get(m).get(n);
    }

    public List<Fraction> getRow(int m) {
      if (m <= this.M) {
        return this.matrix.get(m);
      }
      return new ArrayList<Fraction>();
    }

    public Solution.Matrix plus(Solution.Matrix mat) {
      int M_m = mat.getDimension()[0];
      int N_m = mat.getDimension()[1];
      if (this.M != M_m || this.N != N_m) {
        //system.out.println("Error in plus: Dimensions of two matrices are not equal!"); // Debug
        return mat;
      } else {
        List<List<Fraction>> sum = new ArrayList<>();
        // Init 2d fraction arraylist
        for (int i = 0; i < this.M; i++) {
          ArrayList<Fraction> sumRow = new ArrayList<Fraction>();
          for (int j = 0; j < this.N; j++) {
            sumRow.add(new Fraction(0, 1));
          }
          sum.add(sumRow);
        }
        for (int i = 0; i < this.M; i++) {
          for (int j = 0; j < this.N; j++) {
            // sum[i][j] = this.matrix[i][j] + mat.getElement(i, j);
            sum.get(i).set(j, this.matrix.get(i).get(j).plus(mat.getElement(i, j)));
          }
        }
        return new Solution.Matrix(sum, this.M, this.N);
      }
    }

    public Solution.Matrix minus(Solution.Matrix mat) {
      int M_m = mat.getDimension()[0];
      int N_m = mat.getDimension()[1];
      if (this.M != M_m || this.N != N_m) {
        //system.out.println("Error in minus: Dimensions of two matrices are not equal!"); // Debug
        return mat;
      } else {
        List<List<Fraction>> difference = new ArrayList<>();
        // Init 2d fraction arraylist
        for (int i = 0; i < this.M; i++) {
          ArrayList<Fraction> differenceRow = new ArrayList<Fraction>();
          for (int j = 0; j < this.N; j++) {
            differenceRow.add(new Fraction(0, 1));
          }
          difference.add(differenceRow);
        }
        for (int i = 0; i < this.M; i++) {
          for (int j = 0; j < this.N; j++) {
            // difference[i][j] = this.matrix[i][j] + mat.getElement(i, j);
            difference.get(i).set(j, this.matrix.get(i).get(j).minus(mat.getElement(i, j)));
          }
        }
        return new Solution.Matrix(difference, this.M, this.N);
      }
    }

    public Solution.Matrix multiply(Solution.Matrix mat) {
      // M N M N
      // X(m, n) x Y(n, p) = Z(m, p)
      int M_m = mat.getDimension()[0];
      int p_m = mat.getDimension()[1];
      if (this.N != M_m) {
        //system.out.println("Error in multiply: Dimensions of two matrices are valid for cross multiplication!"); // Debug
        return mat;
      } else {
        List<List<Fraction>> product = new ArrayList<>();
        // Init 2d fraction arraylist
        for (int i = 0; i < this.M; i++) {
          ArrayList<Fraction> productRow = new ArrayList<Fraction>();
          for (int j = 0; j < p_m; j++) {
            productRow.add(new Fraction(0, 1));
          }
          product.add(productRow);
        }
        for (int i = 0; i < this.M; i++) {
          for (int j = 0; j < p_m; j++) {
            for (int k = 0; k < this.N; k++) {
              // product[i][j] += matrix[i][k] * mat.getElement(k, j);
              Fraction temp = product.get(i).get(j);
              product.get(i).set(j, temp.plus(this.matrix.get(i).get(k).multiply(mat.getElement(k, j))));
            }
          }
        }
        return new Solution.Matrix(product, this.M, p_m);
      }

    }

    public int[] getDimension() {
      return new int[] { this.M, this.N };
    }

    public void print() {
      for (int i = 0; i < this.M; i++) {
        for (int j = 0; j < this.N; j++) {
          //system.out.print(this.matrix.get(i).get(j).toString() + "  ");
        }
        //system.out.println();
      }
    }

    public void printInverse() {
      if (this.M != this.N) {
        //system.out.println("No inverse matrix for non-square matrices");
        return;
      }
      if (this.det.equals(new Fraction(0))) {
        //system.out.println("Singular matrix, can't find its inverse");
        return;
      }
      for (int i = 0; i < this.M; i++) {
        for (int j = 0; j < this.N; j++) {
          //system.out.print(this.inverseMatrix.get(i).get(j).toString() + "  ");
        }
        //system.out.println();
      }
    }

  }

  private static class Fraction {

    private int numerator;
    private int denominator = 1;
    private boolean sign = false; // true = negative, false = positive

    public Fraction(int num, int denom) {
      this.numerator = num;
      if (denom == 0) {
        //system.out.println("Denominator cannot be 0. Setting it to 1");
      } else {
        this.denominator = denom;
      }
      this.simplify();
    }

    public Fraction(int num) {
      this.numerator = num;
      this.simplify();
    }

    private int getGcm(int num1, int num2) {
      return num2 == 0 ? num1 : this.getGcm(num2, num1 % num2);
    }

    // Simplify fraction to simplest form, runs in constructor
    public void simplify() {
      this.sign = !(this.numerator <= 0 && this.denominator <= 0) && !(this.numerator >= 0 && this.denominator >= 0);

      this.numerator = Math.abs(this.numerator);
      this.denominator = Math.abs(this.denominator);

      int gcm = this.getGcm(this.numerator, this.denominator);
      this.numerator = this.numerator / gcm;
      this.denominator = this.denominator / gcm;
      // When fraction is zero, make sure denominator is one and no negative sign
      if (this.numerator == 0 && this.denominator != 0) {
        this.denominator = 1;
        this.sign = false;
      }
    }

    public Solution.Fraction plus(Solution.Fraction f1) {
      int num = 0;
      if (this.sign) { // this fraction is negative
        if (f1.getSign()) { // f1 is negative
          num = (-1) * this.numerator * f1.denominator + this.denominator * (-1) * f1.numerator;
        } else { // f1 is positive
          num = (-1) * this.numerator * f1.denominator + this.denominator * f1.numerator;
        }
      } else { // this fraction is positive
        if (f1.getSign()) { // f1 is negative
          num = this.numerator * f1.denominator + this.denominator * (-1) * f1.numerator;
        } else { // f1 is positive
          num = this.numerator * f1.denominator + this.denominator * f1.numerator;
        }
      }
      int denom = this.denominator * f1.getDenominator();
      return new Solution.Fraction(num, denom);
    }

    public Solution.Fraction minus(Solution.Fraction f1) {
      int num = 0;
      if (this.sign) { // this fraction is negative
        if (f1.getSign()) { // f1 is negative
          num = (-1) * this.numerator * f1.denominator + this.denominator * f1.numerator;
        } else { // f1 is positive
          num = (-1) * this.numerator * f1.denominator - this.denominator * f1.numerator;
        }
      } else { // this fraction is positive
        if (f1.getSign()) { // f1 is negative
          num = this.numerator * f1.denominator + this.denominator * f1.numerator;
        } else { // f1 is positive
          num = this.numerator * f1.denominator - this.denominator * f1.numerator;
        }
      }
      int denom = this.denominator * f1.getDenominator();
      return new Solution.Fraction(num, denom);
    }

    public Solution.Fraction multiply(Solution.Fraction f1) {
      int signInt = 1;
      // Either one fraction is negative will make the product fraction negative, but not for both fractions are negative.
      if (this.sign && !f1.getSign() || !this.sign && f1.getSign()) {
        signInt = -1;
      }
      return new Solution.Fraction(signInt * this.numerator * f1.getNumerator(), this.denominator * f1.getDenominator());
    }

    public Solution.Fraction dividedBy(Solution.Fraction f1) {
      int signInt = 1;
      // Either one fraction is negative will make the product fraction negative, but not for both fractions are negative.
      if (this.sign && !f1.getSign() || !this.sign && f1.getSign()) {
        signInt = -1;
      }
      return new Solution.Fraction(signInt *this.numerator * f1.getDenominator(), this.denominator * f1.getNumerator());
    }

    public boolean equals(Solution.Fraction f1) {
      return this.numerator == f1.getNumerator() && this.denominator == f1.getDenominator() && this.sign == f1.getSign();
    }

    public int getNumerator() {
      return this.numerator;
    }

    public int getDenominator() {
      return this.denominator;
    }

    public boolean getSign() {
      return this.sign;
    }

    public String toString() {
      String signStr = "";
      String fractionStr = "";
      if (this.sign) {
        signStr = "-";
      }
      if (numerator == denominator) {
        fractionStr = "1";
      } else if (denominator == 1) {
        fractionStr = Integer.toString(numerator);
      } else {
        fractionStr = numerator + "/" + denominator;
      }
      return signStr + fractionStr;
    }
  }

  public static void main(String[] args) {
    int[] ans = solution(new int[][]{{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}});
    for (int i = 0; i < ans.length; i++){
      System.out.println(ans[i]);
    }
  }
}
