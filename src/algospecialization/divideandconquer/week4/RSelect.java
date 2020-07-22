package algospecialization.divideandconquer.week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RSelect {

  private int[] array;

  public static void main(String[] args) throws IOException {
    int[] input =
        Files.lines(Paths.get(args[0]))
            .mapToInt(
                value -> {
                  return Integer.valueOf(value);
                })
            .toArray();
    final RSelect rSelect = new RSelect(input);
    int ith = Integer.valueOf(args[1]).intValue();
    long startTime = System.nanoTime();
    System.out.println(rSelect.rSelect(0, input.length - 1, ith - 1));
    long endTime = System.nanoTime();
    System.out.println((endTime - startTime));
  }

  public RSelect(int[] array) {
    this.array = new int[array.length];
    for (int i = 0; i < array.length; i++) this.array[i] = array[i];
  }

  public int rSelect(int low, int high, int ith) {
    if (low >= high) return array[high];
    int position = partition(low, high);
    if (position == ith) {
      return array[position];
    } else if (position > ith) {
      return rSelect(low, position - 1, ith);
    } else {
      return rSelect(position + 1, high, ith);
    }
  }

  public int partition(int low, int high) {
    int i = low + 1;
    int pivot = array[low];
    for (int j = i; j <= high; j++) {
      if (array[j] < pivot) {
        swap(i, j);
        i++;
      }
    }
    swap(low, i - 1);
    return i - 1;
  }

  private void swap(int from, int to) {
    int temp = array[from];
    array[from] = array[to];
    array[to] = temp;
  }
}
