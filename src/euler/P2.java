package euler;

public class P2 {

  public static void main(String[] args) {
    P2 p2 = new P2();
    System.out.println(p2.calculateRecursive(4000000));
  }

  private int calculate(int limit) {
    int sum = 0;
    int previous = 1;
    int next = 1;
    while(next < limit){
      int oldprevious = previous;
      previous = next;
      next = next +oldprevious;
      if(next%2 == 0){
        sum = sum + next;
      }
    }
    return sum;
  }

  private int calculateRecursive(int limit){
    int ans = calculate(1,1,limit,0);
    return ans;
  }

  private int calculate(int previous, int next, int limit, int ans) {
    if(next >= 4000000) return ans;
    if(next%2 == 0) ans = ans + next;
    int temp = previous;
    previous = next;
    next = next + temp;
    return calculate(previous,next,limit,ans);
  }


}
