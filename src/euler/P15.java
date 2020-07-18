package euler;

import java.util.Stack;

public class P15 {

  public static void main(String[] args) {
    System.out.println(solution(100000  ));
  }

  private static int solution(int i) {
    int sum = 0;
    String s = "2";
    for (int j = 2; j <= i; j++) {
      Stack<Integer> stack = new Stack<>();
      int carry = 0;
      for (int k = s.length() - 1; k >= 0; k--) {
        int digit = Integer.valueOf(s.substring(k,k+1));
        int newdigit = 2 * digit + carry;
        carry = newdigit > 8 ? newdigit / 10 : 0;
        stack.push(newdigit % 10);
      }
      if(carry> 0){
        stack.push(carry);
      }
      StringBuilder builder = new StringBuilder();
      while (!stack.empty()) {
        builder.append(stack.pop());
      }
      s = builder.toString();
    }
    return calculateTotal(s);
  }

  private static int calculateTotal(String s) {
    int total = 0;
    for (int i = 0; i < s.length(); i++) {
      total = total + Integer.valueOf(s.substring(i,i+1));
    }
    return total;
  }
}
