package euler;

import java.util.HashMap;
import java.util.Map;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3
 * + 5 + 4 + 4 = 19 letters used in total.
 *
 * <p>If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many
 * letters would be used?
 */
public class P17 {
  static Map<Integer, String> numberMap = new HashMap<>();
  static int count = 0;

  public static void main(String[] args) {
    solution();
    System.out.println(count - 27);
  }

  private static void solution() {
    String[] array = new String[1001];
    array[1] = "one";
    array[2] = "two";
    array[3] = "three";
    array[4] = "four";
    array[5] = "five";
    array[6] = "six";
    array[7] = "seven";
    array[8] = "eight";
    array[9] = "nine";
    array[10] = "ten";
    array[11] = "eleven";
    array[12] = "twelve";
    array[13] = "thirteen";
    array[14] = "fourteen";
    array[15] = "fifteen";
    array[16] = "sixteen";
    array[17] = "seventeen";
    array[18] = "eighteen";
    array[19] = "nineteen";
    array[20] = "twenty";
    array[30] = "thirty";
    array[40] = "forty";
    array[50] = "fifty";
    array[60] = "sixty";
    array[70] = "seventy";
    array[80] = "eighty";
    array[90] = "ninety";
    array[100] = "oneHundredand";
    array[200] = "twohundredand";
    array[300] = "threehundredand";
    array[400] = "fourhundredand";
    array[500] = "fivehundredand";
    array[600] = "sixhundredand";
    array[700] = "sevenhundredand";
    array[800] = "eighthundredand";
    array[900] = "ninehundredand";
    array[1000] = "onethousand";

    int k = 0;
    int resetNum = 1;
    for (int i = 1; i <= 1000; i++) {
      if(array[i] == null){
        array[i] = array[k] + array[resetNum];
        count += array[i].length();
        resetNum++;
      }else {
        count += array[i].length();
        k = i;
        resetNum = 1;
      }
    }
  }

  //

}
