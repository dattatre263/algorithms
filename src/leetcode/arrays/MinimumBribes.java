package leetcode.arrays;

import java.util.Scanner;

public class MinimumBribes {
  static int[] aux;

  // Complete the minimumBribes function below.
  static void minimumBribes(int[] q) {
    int count = 0;
    boolean tooChaotic = false;
    for (int i = q.length -1; i >=0; i--){
      if((q[i] - (i+1)) > 2){
        System.out.println("Too chaotic");
        tooChaotic = true;
        break;
      }else {
        for (int j = Math.max(0, q[i] - 2); j < i; j++){
          if(q[j] > q[i]){
            count++;
          }
        }
      }
    }
    if (!tooChaotic) System.out.println(count);
  }





  public static void main(String[] args) {
    minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
    //1 2 5 3 7 8 6 4
    //1 2 3 4 5 6 7 8
    //1 2 5 3 4 6 7 8  2
    //1 2 5 3 7 4 6 8  2
    //1 2 5 3 7 8 4 6  2
    //1 2 5 3 7 8 6 4  1
  }
}
