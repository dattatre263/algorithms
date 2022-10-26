package algospecialization.divideandconquer.week2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CountInversions {
  private  int[] aux ;
  private  long inversions = 0;

  public CountInversions(Integer[] array){
    this.aux = new int[array.length];
  }

  public static void main(String[] args) throws IOException {
    String path = args[0];
    Integer[] array = Files.lines(Paths.get(path)).map(s -> {
      return new Integer(s);
    }).toArray(Integer[]:: new);
    CountInversions countInversions = new CountInversions(array);
    countInversions.mergeSort(array,0,array.length - 1);
    System.out.println(countInversions.inversions);
  }

  private void mergeSort(Integer[] input, int lo, int hi) {
    //base case
    if (lo == hi) return;
    int mid = (hi - lo)/2 + lo;
    mergeSort(input, lo, mid);
    mergeSort(input, mid + 1, hi);
    merge(input,lo,mid,hi );
  }

  private  void merge(Integer[] array, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) aux[k] = array[k];
    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        array[k] = aux[j++];
      } else if (j > hi) {
        array[k] = aux[i++];
      } else if (aux[i] < aux[j]) {
        array[k] = aux[i++];
      } else {
        inversions = inversions + (mid - i + 1);
        array[k] = aux[j++];
      }
    }
  }
}
