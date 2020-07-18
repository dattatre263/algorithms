package euler;

/**
 * The sum of the squares of the first ten natural numbers is,
 *
 * 12 + 22 + ... + 102 = 385
 * The square of the sum of the first ten natural numbers is,
 *
 * (1 + 2 + ... + 10)2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class P6 {

  public static void main(String[] args) {
    P6 p6 = new P6();
    System.out.println(p6.calculate());
  }

  private int calculate() {
    int ans = 0;
    for(int i = 1; i <100; i++){
      for(int j= i+1; j <= 100; j++){
        ans = ans + i*j;
      }
    }
    return 2*ans;
  }

}
