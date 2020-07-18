package euler;

public class P14 {

  public static void main(String[] args) {
    long t1 = System.currentTimeMillis();
    System.out.println(calculate());
    long t2 = System.currentTimeMillis();
    System.out.println(t2-t1);
    long t3 = System.currentTimeMillis();
    System.out.println(calculateRecursively());
    long t4 = System.currentTimeMillis();
    System.out.println(t4 - t3);
  }

  private static int calculateRecursively() {
    int startNumber = 1;
    int largestCount = 1;
    int[] tracker = new int[1000000]; // this can changed based on memory available.
    tracker[1] = 1;
    for (int i = 2; i < 1000000; i++) {
      long temp = i; // this has to be long and not int
      int count = recursion(temp, tracker);
      if (count > largestCount) {
        largestCount = count;
        startNumber = i;
      }
    }
    return startNumber;
  }

  private static int recursion(long num, int[] tracker) {
    int count = 1;
    if (isTracked(num, tracker)) {
      return tracker[(int) num];
    }
    count = count + (num % 2 == 0 ? recursion(num / 2, tracker) : recursion(3 * num + 1, tracker));
    if (num < 1000000) {
      tracker[(int) num] = count;
    }
    return count;
  }

  private static boolean isTracked(long temp, int[] tracker) {
    if (temp < 1000000 && tracker[(int) temp] != 0) {
      return true;
    } else {
      return false;
    }
  }

  private static int calculate() {
    int index = 1;
    int largestCount = 1;
    int[] tracker = new int[1000000];
    tracker[1] = 1;
    long temp;
    for (int i = 2; i < 1000000; i++) {
      temp = i;
      int count = 1;
      while (temp != 1) {
        if (temp % 2 == 0) {
          temp = temp / 2;
        } else {
          temp = (3 * temp) + 1;
        }
        count++;
      }
      if (count > largestCount) {
        largestCount = count;
        index = i;
      }
    }
    return index;
  }

}
