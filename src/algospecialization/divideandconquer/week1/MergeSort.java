package algospecialization.divideandconquer.week1;

public class MergeSort {
  private static int[] aux = null;

  public static void main(String[] args) {
    int[] array1 = new int[21];
    int min = -100;
    int max = 100;
    for (int i = 0; i < array1.length; i++) {
      array1[i] = (int) (Math.random() * (max - min) + min);
    }
    for (int i : array1) {
      System.out.print(i + ",");
    }
    System.out.println("*************");
    aux = new int[array1.length];
    mergeSort(array1,0,array1.length - 1);
    for (int i : array1) {
      System.out.print(i + ",");
    }
  }

  private static void mergeSort(int[] input, int lo, int hi) {
    //base case
    if (lo == hi) return;
    int mid = (hi - lo)/2 + lo;
    mergeSort(input, lo, mid);
    mergeSort(input, mid + 1, hi);
    merge(input,lo,mid,hi );
  }

  private static void merge(int[] array, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) aux[k] = array[k];
    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        // just copy right side
        array[k] = aux[j++];
      } else if (j > hi) {
        array[k] = aux[i++];
      } else if (aux[i] < aux[j]) {
        array[k] = aux[i++];
      } else {
        array[k] = aux[j++];
      }
    }
  }
}
