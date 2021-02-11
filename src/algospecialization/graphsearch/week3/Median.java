package algospecialization.graphsearch.week3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.PriorityQueue;

public class Median {
  private long answer = 0;
  private final PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
  private final PriorityQueue<Integer> high = new PriorityQueue<>();

  private void calculate(Integer digit) {
    addToHeap(digit);
    reBalanceHeaps();
    findMean();
  }

  private void findMean() {
    if(low.size() > high.size()){
      answer = answer + low.peek();
    }else if(low.size() < high.size()){
      answer = answer + high.peek();
    }else {
      answer = answer + low.peek();
    }
  }

  private void reBalanceHeaps() {
    int diff = low.size() - high.size();
    if(diff > 1){
      high.add(low.remove());
    }else if (diff < -1){
      low.add(high.remove());
    }
  }

  private void addToHeap(Integer digit){
    if(low.peek() == null) {
      low.add(digit);
      return;
    }
    if(low.peek() >= digit){
      low.add(digit);
    }else{
      high.add(digit);
    }
  }


  public static void main(String[] args) {
    Path path = Paths.get(args[0]);
    try{
      Median median = new Median();
      Files.lines(path).forEach(digit -> {
        median.calculate(Integer.valueOf(digit));
      });
      System.out.println(median.answer % 10000);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
}
