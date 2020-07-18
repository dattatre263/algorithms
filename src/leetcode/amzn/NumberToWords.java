package leetcode.amzn;

class NumberToWords {

  public String numberToWords(int num) {
    StringBuilder builder = new StringBuilder();
    int x = num / 1000000000;
    if (x > 0) {
      builder.append(x);
      builder.append("billion ");
      num = num % 1000000000;
    }
    x = num / 1000000;
    if (x > 0) {
      builder.append(numberToWords(x, builder, 100));
      builder.append("million ");
      num = num % 1000000;
    }
    x = num / 1000;
    if (x > 0) {
      builder.append(numberToWords(x, builder, 100));
      builder.append("thousand ");
      num = num % 1000000;
    }

    return numberToWords(num, builder, 100).toString();
  }

  public StringBuilder numberToWords(int num, StringBuilder buffer, int div) {
    if (div == 1) {
      return buffer;
    }
    int rem = num % div;
    int q = num / div;
    if (div == 100 && q > 0) {
      buffer.append(returnWord(q));
      buffer.append("hundred ");
    } else if (div == 10 && q > 0) {
      if (num > 19) {
        int x = num % div;
        buffer.append(returnWord(num - x));
      } else {
        buffer.append(returnWord(num));
      }
    }
    return numberToWords(rem, buffer, div / 10);
  }

  private String returnWord(int index) {
    String[] words =
        new String[] {
          "one ",
          "two ",
          "three ",
          "four ",
          "five ",
          "six ",
          "seven ",
          "eight ",
          "nine ",
          "ten ",
          "eleven ",
          "twelve ",
          "thirteen ",
          "fourteen ",
          "fifteen ",
          "sixteen ",
          "seventeen ",
          "eighteen ",
          "nineteen ",
          "twenty ",
          "thirty ",
          "forty ",
          "fifty ",
          "sixty ",
          "seventy ",
          "eighty ",
          "ninty "
        };
    int[] ints =
        new int[] {
          1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70,
          80, 90
        };

    for (int i = 0; i < ints.length; i++) {
      if (ints[i] == index) {
        return words[i];
      }
    }
    throw new IllegalArgumentException();
  }

  public static void main(String[] args) {
    NumberToWords numberToWords = new NumberToWords();
    System.out.println(numberToWords.numberToWords(1234567));
  }
}
