package euler;

public class P5 {

  private int calculate(int maxbound) {
    int ans = 20;
    for (int i = 19; i > 1; i--) {
      boolean done = false;
      int j = i;
      while (!done) {
        if (i % j == 0 && ((ans * (i / j)) % i == 0)) {
          ans = ans * (i / j);
          done = true;
        }
        j--;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    P5 p5 = new P5();
    System.out.println(p5.calculate(20));
  }

}
