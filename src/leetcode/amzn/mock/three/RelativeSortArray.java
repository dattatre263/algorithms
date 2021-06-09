package leetcode.amzn.mock.three;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelativeSortArray {

  public static int[] relativeSortArray(int[] arr1, int[] arr2) {
    Map<Integer, Integer> map = getMap(arr1);
    int[] copy = new int[arr1.length];
    int count = 0;
    for (int i = 0; i < arr2.length; i++){
      int copies =  map.get(arr2[i]);
      for(int j = 0; j < copies; j++){
        copy[count] = arr2[i];
        count++;
      }
      map.remove(arr2[i]);
    }
    int[] temp = new int[arr1.length - count];
    int newCount = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet() ) {
      int key = entry.getKey();
      int value = entry.getValue();
      for (int i = 0; i < value; i++) {
        temp[newCount] = key;
        newCount++;
      }
    }
    Arrays.sort(temp);
    for (int i = 0; i <temp.length; i++) {
      copy[count] = temp[i];
      count++;
    }
    return copy;
  }

  private static Map<Integer, Integer> getMap(int[] arr1){
    Map<Integer, Integer>  map = new HashMap<>();
    for (int i = 0; i < arr1.length; i++) {
      if(map.containsKey(arr1[i])){
        map.put(arr1[i], map.get(arr1[i]) + 1);
      }else {
        map.put(arr1[i], 1);
      }
    }
    return map;
  }

  public static void main(String[] args) {
    int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
    int[] arr2 = new int[]{2,1,4,3,9,6};
    int[] ans = relativeSortArray(arr1,arr2);


  }
}
