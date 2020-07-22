package algospecialization.divideandconquer.week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class QuickSortV1 {

  private int[] input = null;
  private int strategy = 0;
  private long comparisionCount = 0;

  public static void main(String[] args) throws IOException {
    String path = args[0];
    Integer[] array =
        Files.lines(Paths.get(path))
            .map(
                s -> {
                  return Integer.valueOf(s);
                })
            .toArray(Integer[]::new);
    int[] primitiveArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      primitiveArray[i] = array[i];
    }
    QuickSortV1 low = new QuickSortV1(primitiveArray, 0);
    System.out.println(low.getComparisionCount());

    QuickSortV1 high = new QuickSortV1(primitiveArray, 1);
    System.out.println(high.getComparisionCount());

    QuickSortV1 median = new QuickSortV1(primitiveArray, 2);
    long startTime = System.nanoTime();
    System.out.println(median.getComparisionCount());
    long endTime = System.nanoTime();
    System.out.println((endTime - startTime));
  }

  QuickSortV1(int[] input, int strategy) {
    //    Arrays.stream(input)
    //        .forEach(
    //            integer -> {
    //              System.out.print(integer);
    //            });
    this.input = new int[input.length];
    for (int i = 0; i < input.length; i++) {
      this.input[i] = input[i];
    }
    this.strategy = strategy;
  }

  public long getComparisionCount() {
    this.quickSort(0, input.length - 1);
    return this.comparisionCount;
  }

  private void quickSort(int low, int high) {
    // base case
    if (low >= high) return;
    setPivot(low, high);
    int pivot = partition(low, high);
    quickSort(low, pivot - 1);
    quickSort(pivot + 1, high);
  }

  private void setPivot(int low, int high) {
    if (this.strategy == 0) {
      // do nothing
      return;
    }
    if (this.strategy == 1) {
      swap(low, high);
      return;
    }
    if (this.strategy == 2) {
      findMedian(low, high);
      return;
    }
  }

  private void findMedian(int low, int high) {
    int b = low + ((high - low) / 2);
    if (b == low) {
      // do nothing
    } else {
      int median = middleOfThree(low, b, high);
      swap(low, median);
    }
  }

  public int middleOfThree(int A, int B, int C) {
    int a = input[A];
    int b = input[B];
    int c = input[C];
    // Checking for b
    if ((a < b && b < c) || (c < b && b < a)) return B;

    // Checking for a
    else if ((b < a && a < c) || (c < a && a < b)) return A;
    else return C;
  }

  private int partition(int low, int high) {
    int i = low + 1;
    int j = low + 1;
    while (j <= high) {
      if (input[j] < input[low]) {
        // an optimization can be added here. Basically no real swapping is done until the first
        // time
        // you see a number greater than the pivot input[low]
        swap(i++, j++);
      } else {
        j++;
      }
    }
    swap(low, i - 1);
    comparisionCount = comparisionCount + (high - low);
    return i - 1;
  }

  private void swap(int pos1, int pos2) {
    int temp = this.input[pos1];
    this.input[pos1] = this.input[pos2];
    this.input[pos2] = temp;
  }
}
