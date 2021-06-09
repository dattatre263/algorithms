package leetcode;

public class MaxProfit1 {

  public static int solution(int[] prices) {
    int profit = 0;
    int current_max = 0;
    for (int i = prices.length - 1; i > 0; i--) {
      if(prices[i] > current_max){
        current_max = prices[i];
        for (int j = 0; j < i; j++) {
          int current_profit = prices[i] - prices[j];
          profit = current_profit > profit ? current_profit : profit;
        }
      }
    }
    return profit;
  }

  public static void main(String[] args) {
    MaxProfit1.solution(new int[] {7, 1, 5, 3, 6, 4});
  }
}
