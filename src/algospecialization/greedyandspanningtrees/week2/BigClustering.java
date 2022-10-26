package algospecialization.greedyandspanningtrees.week2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BigClustering {

  private Map<Integer, Integer> parentMapping;
  private Map<Integer, List<Integer>> cluster;
  private int numberOfBits;

  public BigClustering(
      Map<Integer, Integer> parentMapping, Map<Integer, List<Integer>> cluster, int numberOfBits) {
    this.parentMapping = parentMapping;
    this.cluster = cluster;
    this.numberOfBits = numberOfBits;
  }

  public void createClusters() {
    Set<Integer> set = parentMapping.keySet();
    int count = 0;
    for (Integer entry : set) {
      count++;
//      if(count % 10000 == 0){
//        System.out.println(count);
//      }
      /*
      If the mapping's parent is the same as the entry then it is it's own cluster
      else it has moved to another cluster and hence can be ignored
       */
      this.checkForOneAndTwoHammingDistance(entry);
      // check if this entry found another target that has hamming distance 1 || 2
    }
    System.out.println(this.cluster.size());
  }


  private void checkForOneAndTwoHammingDistance(Integer entry) {
    String[] xor = initialize();
    for (int j = 0; j < xor.length; j++) {
      xor[j] = "1";
      for (int i = j ; i < xor.length; i++) {
        if(i != j) xor[i] = "1";
        String xorString = toString(xor);
        Integer xorInt = Integer.parseInt(xorString, 2);
        int target = entry ^ xorInt;
        Integer parent = parentMapping.get(entry);
        if (parentMapping.containsKey(target)) {
          // need to check if target is in the same cluster; if it is move on
          // else add the target to the entry's parent cluster
          int targetParent = parentMapping.get(target);
          if(targetParent != parent) {
            // add the target as an entry in the entry's parent cluster
            if(cluster.get(parent).size() > cluster.get(targetParent).size()){
              cluster.get(parent).addAll(cluster.get(targetParent));
              for(Integer item : cluster.get(targetParent)){
                parentMapping.put(item,parent);
              }
              cluster.remove(targetParent);
            }else {
              cluster.get(targetParent).addAll(cluster.get(parent));
              for(Integer item : cluster.get(parent)){
                parentMapping.put(item,targetParent);
              }
              cluster.remove(parent);
            }
          }
        }
        // reset the bit to zero
        if(i != j) xor[i] = "0";
      }
      xor[j] = "0";
    }
  }

  private String[] initialize() {
    String[] xor = new String[numberOfBits];
    int i = 0;
    while (i < numberOfBits) {
      xor[i] = "0";
      i++;
    }
    return xor;
  }

  private static String toString(String[] stringArray) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < stringArray.length; i++) {
      builder.append(stringArray[i]);
    }
    return builder.toString();
  }

  public static void main(String[] args) {
//    long start = System.currentTimeMillis();
    Path path = Paths.get(args[0]);
    try {
      Map<Integer, Integer> mapping = new HashMap<>();
      Map<Integer, List<Integer>> cluster =
          new HashMap(Integer.valueOf(Files.lines(path).findFirst().get().split("\t| ")[0]));
      int numberOfBits = Integer.valueOf(Files.lines(path).findFirst().get().split("\t| ")[1]);
      final Integer duplicateCluster = -1;
      Files.lines(path)
          .skip(1)
          .forEach(
              line -> {
                int v = Integer.parseInt(line.replaceAll("\t| ", ""), 2);
                mapping.put(v, v);
                if (cluster.containsKey(v)) {
                  cluster.get(v).add(v);
                } else {
                  List<Integer> list = new ArrayList<>();
                  list.add(v);
                  cluster.put(v, list);
                }
              });
      BigClustering bigClustering = new BigClustering(mapping, cluster, numberOfBits);
      bigClustering.createClusters();
//      long end = System.currentTimeMillis();
//      System.out.println(end - start);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private void checkForSingleHammingDistance(Integer entry) {
    String[] xor = initialize();
    for (int i = 0; i < xor.length; i++) {
      xor[i] = "1";
      String xorString = toString(xor);
      Integer xorInt = Integer.parseInt(xorString, 2);
      int target = entry ^ xorInt;
      // basically don't mess with the parent
      Integer parent = parentMapping.get(entry);
      if (cluster.containsKey(target) && target != parent ) {
        // add the target as an entry in the entry's parent cluster
        cluster.get(parent).addAll(cluster.get(target));
        // remove the cluster that is formed by the single entry of the target
        cluster.remove(target);
        // set the leader of the target to the leader of the entry
        parentMapping.put(target, parent);
      }
      // reset the bit to zero
      xor[i] = "0";
    }
  }
}
