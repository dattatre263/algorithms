package euler;

import org.omg.CORBA.INTERNAL;

public class P12 {

  public static void main(String[] args) {
    long t1 = System.nanoTime();
    System.out.println(calculate(500));
    long t2 = System.nanoTime();
    System.out.println(t2 - t1);
    long t3 = System.nanoTime();
    System.out.println(calculate2(500));
    long t4 = System.nanoTime();
    System.out.println(t4 - t3);

  }

  private static long calculate2(int limit) {
    long end = Integer.MAX_VALUE;
    long start = 2;
    long triangle = 3;
    boolean done = false;
    while(!done){
      long test = (start + end)/2;
      triangle = test*(test+1)/2;
      int factors = getFactors(test);
      if(factors > limit){
        end = test;
      }else {
        start = test;
      }
      if(start == end){
        done = true;
      }
    }
    return triangle;
  }

  private static long calculate(int limit) {
    int end = Integer.MAX_VALUE;
    int start = 2;
    int triangleNumber = 3;
    boolean done = false;
    while (!done) {
      triangleNumber = start*(start +1)/2;
      int factors = getFactors(triangleNumber);
      if(factors > 500){
        done = true;
      }else {
        start++;
      }
    }
    return triangleNumber;
  }



  private static int getFactors(long number) {
    int count = 2;
    for (long i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        count = count + 2;
        if (count > 500) {
          break;
        }
      }
    }
    return count;
  }
}
