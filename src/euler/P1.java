package euler;

public class P1 {


  public static void main(String[] args) {
    P1 obj = new P1();
    System.out.println(obj.calculate(3, 5, 1000));
    System.out.println(obj.calculate2(3, 5, 1000));

  }

  private int calculate(int num1, int num2, int limit) {
    return this.progression(num1, limit) + this.progression(num2, limit) - this
        .progression(num1 * num2, limit);
  }

  private int progression(int num1, int limit) {
    limit--;
    int last = limit - limit % num1;
    int n = limit / num1;
    double avg = (num1 + last) / 2.0;
    int ans = (int) (n * avg);
    return ans;
  }

  private int calculate2(int num1, int num2, int limit) {
    int ans = 0;
    int multiple = num1 * num2;
    boolean done = false;
    int counter = 1;
    while (multiple < limit) {
      ans = ans + multiple;
      counter++;
      multiple = num1 * num2 * counter;
    }
    counter = 1;
    multiple = num1;
    while (multiple < limit) {
      if (multiple % num2 != 0) {
        ans = ans + multiple;
      }
      counter++;
      multiple = num1 * counter;
    }
    counter = 1;
    multiple = num2;
    while (multiple < limit) {
      if (multiple % num1 != 0) {
        ans = ans + multiple;
      }
      counter++;
      multiple = num2 * counter;
    }
    return ans;
  }

}
