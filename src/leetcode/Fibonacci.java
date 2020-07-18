package leetcode;

public class Fibonacci {


  public static void main(String[] args) {
    Fibonacci fibonacci = new Fibonacci();
    long[] memo = new long[100+1];
    System.out.println(fibonacci.calculate(50, memo ));
    for(long l : memo){
      System.out.println(l);
    }
  }

  private long calculate(int i, long[] memo) {
    if(i == 0) return i;
    if(i == 1) return i;
    if(memo[i] == 0){
      memo[i] = calculate(i-1,memo) + calculate(i-2, memo);
    }
    return memo[i];
  }

}
