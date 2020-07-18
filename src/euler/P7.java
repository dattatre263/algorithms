package euler;

public class P7 {

  public static void main(String[] args) {
    P7 p7 = new P7();
    System.out.println(p7.calculate(10001));
  }

  private int calculate(int num) {
    int primeCount = 1;
    int latestPrime = 1;
    for (int i = 3; primeCount <num; i = i+2){
      boolean prime = true;
      for(int j = 2; j <= Math.sqrt(i); j++ ) {
        if( i%j == 0){
          prime = false;
          break;
        }
      }
      if(prime){
        latestPrime = i;
        primeCount++;
      }
    }
    return latestPrime;
  }

}
