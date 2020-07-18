package euler;

public class P4 {

  public static void main(String[] args) {
    P4 p4 = new P4();
    int[] ans = p4.calculate();
    System.out.println(ans[0]+ "," + ans[1]);
    System.out.println(ans[0]*ans[1]);
//    System.out.println(four.checkPalindrome(456654));
  }

  private int[] calculate() {
    int[] ans = new int[2];
    boolean found = false;
    int palin = 999999;
    while (!found){
      palin = this.getNextPalindrome(palin);
      for(int i = 999; i > 99; i--){
        if(i*i < palin || palin/i > 999){
          break;
        }
        if(palin%i == 0){
          found = true;
          ans[0] = i;
          ans[1] = palin/i;
          return ans;
        }
      }
    }
    return ans;
  }

  private int getNextPalindrome(int number) {
    String string = Integer.valueOf(number).toString();
    String full = null;
    if (string.length() % 2 == 0) {
      String half = string.substring(0, string.length() / 2);
      Integer halfInt = Integer.valueOf(half);
      Integer nextHalf = halfInt - 1;
      full = nextHalf.toString() + reverseString(nextHalf.toString());
    }
    return Integer.valueOf(full);
  }

  private String reverseString(String string) {
    char[] array = string.toCharArray();
    StringBuilder builder = new StringBuilder();
    for (int i = array.length - 1; i >= 0; i--) {
      builder.append(array[i]);
    }
    return builder.toString();
  }

  private boolean checkPalindrome(int test) {
    int initial = 1;
    while (test / initial > 10) {
      initial = initial * 10;
    }
    int num = test;
    int units = 1;
    int rev = 0;

    for (int i = initial; num > 0; i /= 10) {
      rev = rev + (num / i) * units;
      num = num % i;
      units = units * 10;
    }
    return rev == test;
  }

}
