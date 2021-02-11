package leetcode.arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HourglassSum {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    //
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int[][] arr = new int[6][6];

    for (int i = 0; i < 6; i++) {
      String[] arrRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 6; j++) {
        int arrItem = Integer.parseInt(arrRowItems[j]);
        arr[i][j] = arrItem;
      }
    }
    int result = hourglassSum(arr);
    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();
    bufferedWriter.close();
    scanner.close();
  }

  private static int hourglassSum(int[][] arr, int i, int j) {
    int sum = 0;
    sum = sum + arr[i][j];
    sum = sum + arr[i][j+1];
    sum = sum + arr[i][j+2];
    sum = sum + arr[i+1][j+1];
    sum = sum + arr[i+2][j];
    sum = sum + arr[i+2][j+1];
    sum = sum + arr[i+2][j+2];
    return sum;
  }

  private static int hourglassSum(int[][] arr){
    int min = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length - 2; i++) {
      for (int j = 0; j < arr[0].length - 2; j++) {
        int sum = hourglassSum(arr,i,j);
        min = sum > min ? sum : min;
      }
    }
    return min;
  }
}
