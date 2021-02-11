package leetcode;

public class HourGlassSum {

    public static int solution(int[][] arr){
      int rows = arr.length;
      int col = arr[0].length;
      int ans = Integer.MIN_VALUE;
      for (int i = 0; i <arr.length -2; i++) {
        for (int j = 0; j < arr[i].length -2; j++) {
          int currentans = calculate(arr,i,j);
          ans = ans > currentans ? ans : currentans;
        }
      }
      return ans;
    }

    private static int calculate(int[][] arr, int i, int j) {
      int ans = 0;
      ans = ans + arr[i][j] + arr[i][j+1] + arr[i][j+2];
      ans = ans + arr[i+1][j+1];
      ans = ans + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
      return ans;
    }

  public static void main(String[] args) {
    System.out.println(solution(new int[][]{{1 ,1, 1, 0, 0, 0},{0 ,1, 0, 0, 0, 0},{1, 1, 1, 0, 0, 0},{0, 0, 2, 4, 4, 0},{0, 0, 0, 2, 0, 0},{0, 0, 1, 2, 4, 0}}));
  }
}
