package algospecialization.graphsearch.datastructure;

public class Heap<T extends Comparable<T>> {

  private T[] heapArray;
  private int heapsize = 0;

  public Heap(T[] heapArray) {
    this.heapArray = heapArray;
  }

  public void add(T key) {
    heapsize++;
    heapArray[heapsize] = key;
    swim(key, heapsize);
  }

  public T poll() {
    T min = heapArray[1];
    heapArray[1] = heapArray[heapsize];
    heapsize--;
    sink(1);
    return min;
  }

  private void swim(T key, int location) {
    int parentlocation = Math.floorDiv(location, 2);
    if(parentlocation == 0) return;
    T parent = heapArray[parentlocation];
    if ((parent.compareTo(key) > 0 )) {
      swap(location, parentlocation);
      swim(key, parentlocation);
    }
  }

  private void sink(int location) {
    T item = heapArray[location];
    if(2*location > heapsize || 2*location +1 > heapsize ){
      return;
    }
    T child1 = heapArray[2 * location];
    T child2 = heapArray[2 * location + 1];
    int childLoc =
        heapArray[2 * location].compareTo(heapArray[2 * location + 1]) <= 0  ? 2 * location : 2 * location + 1;
    if ((item.compareTo(heapArray[childLoc]) > 0)) {
      swap(location, childLoc);
      sink(childLoc);
    }
  }

  private void swap(int loc1, int loc2) {
    T temp = heapArray[loc1];
    heapArray[loc1] = heapArray[loc2];
    heapArray[loc2] = temp;
  }

  public int size() {
    return heapsize;
  }

}
