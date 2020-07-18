package euler;

public class P16 {

  public static void main(String[] args) {
    System.out.println(calculate());
  }

  private static long calculate() {
    long sum = 0;
    long power = 2l;
    int j =0;
    while(j < 3){
      long start = power;
      for( int i = 0; i <10; i++){
        power = power*start;
      }
      j++;
    }
    sum = calculateSum(power);
    return sum;
  }

  private static long calculateSum(long power) {
    boolean done = false;
    long sum = 0;
    while(!done){
      sum = sum + power%10;
      power = power/10;
      if(power == 0){
        done = true;
      }
    }
    return sum;
  }

}
