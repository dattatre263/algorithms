package euler;

public class P10 {

  public static void main(String[] args) {
    long t1 = System.nanoTime();
    System.out.println(calculate(2000000));
    System.out.println(System.nanoTime() - t1);
    long t2 = System.nanoTime();
    System.out.println(seive(2000000));
    System.out.println(System.nanoTime() - t2);
  }

  private static long seive(int limit) {
    long answer = 0;
    int crosslimit = (int) Math.floor(Math.sqrt(limit));
    boolean[] seive = new boolean[limit+1];
    for (int n = 4; n <= limit; n = n+2){
      seive[n] = true;
    }
    for (int n = 3; n <=crosslimit; n = n+2){
      if(!seive[n]){
        for(int m = n*n; m <limit; m = m + 2*n){
          seive[m] = true;
        }
      }
    }
    for(int n = 2; n <=limit; n++){
      if(!(seive[n])){
        answer = answer + n;
      }
    }
    return answer;
  }

  private static long calculate(int limit) {
    long ans = 0;
    for(int i = 1999999; i > 2; i = i -2){
      boolean prime = true;
      for(int j = 3; j <= Math.sqrt(i); j = j+2){
        if(i%j == 0){
          prime = false;
          break;
        }
      }
      if(prime){
        ans = ans + i;
      }
    }
    ans = ans + 2;
    return ans;
  }

}
