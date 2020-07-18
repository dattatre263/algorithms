package euler;

public class P3 {


  public static void main(String[] args) {
    P3 p3 = new P3();
    System.out.println(p3.calculate(13195));
  }

  private long calculate(long num) {
    long factor = 2;
    long lastFactor = 1;
    while (num > 1){
      if(num % factor == 0){
        lastFactor = factor;
        num = num/factor;
      }
      factor = factor + 1;
    }
    return lastFactor;

  }

//  private long calculate(long num) {
//    for (long i = 1; i <= num / 2; i++) {
//      if (num % i == 0) {
//        long factor = num / i;
//        if (isPrime(factor)) {
//          return factor;
//        }
//      }
//    }
//    return 0;
//  }

  private boolean isPrime(long factor) {
    for (long i = 2; i <= Math.sqrt(factor); i++) {
      if (factor % i == 0) {
        return false;
      }
    }
    return true;
  }

}
