package algospecialization.greedyandspanningtrees.week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MaxWeightIndependentSet {
  int[] pathGraph;
  List<Integer> set = new ArrayList<>();
  int[] weights;

  public MaxWeightIndependentSet(int[] list){
    pathGraph = list;
    weights = new int[list.length];
  }

  private void calculateMWIS(){
    weights[0] = 0;
    weights[1] = pathGraph[1];
    for(int i = 2; i < pathGraph.length; i++){
      int case1 = weights[i-2] + pathGraph[i];
      int case2 = weights[i-1];
      weights[i] = Math.max(case1,case2);
    }
    //reconstruct
    int i = weights.length - 1 ;
    while (i > 1){
      int x = pathGraph[i];
      int y = weights[i-2];
      int xy = x + y;
      int z = weights[i-1];
      if(weights[i-1] >= weights[i-2] + pathGraph[i]){
        i--;
      }else {
        set.add(i);
        i = i - 2;
      }
    }
  }

  public static void main(String[] args) {
    try {
      Path path = Paths.get(args[0]);
      int numberOfVertices = Integer.parseInt(Files.lines(path).findFirst().get());
      int[] list = new int[numberOfVertices + 1];
      AtomicInteger vertex = new AtomicInteger(1);
      Files.lines(path).skip(1).forEach(line -> {
        list[vertex.intValue()] = Integer.parseInt(line);
        vertex.incrementAndGet();
      });
      MaxWeightIndependentSet maxWeightIndependentSet = new MaxWeightIndependentSet(list);
//      int max = maxWeightIndependentSet.compute(list.size() - 1);
      maxWeightIndependentSet.calculateMWIS();
      List<Integer> set = maxWeightIndependentSet.set;
      if(!set.contains(2)){
        set.add(1);
      }
      StringBuilder builder = new StringBuilder();
      Integer[] keys = {1,2,3,4,17,117,517,997};
      for (Integer key : keys ) {
          buildAnswer(set,key,builder);
      }
      System.out.println(builder.toString());
    }catch (IOException exception){
      throw new RuntimeException(exception);
    }
  }

  static void buildAnswer(List<Integer> set,Integer key, StringBuilder builder){
    if(set.contains(key)){
      builder.append("1");
    }else {
      builder.append("0");
    }
  }
}
