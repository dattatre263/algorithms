package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class MergeIntervals {

  private static class Interval implements Comparable<Interval>{
    int start;
    int end;
    public Interval(int start, int end){
      this.start = start;
      this.end = end;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Interval interval = (Interval) o;
      return start == interval.start;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start);
    }

    @Override
    public int compareTo(Interval o) {
      if(this.start < o.start) return -1;
      if(this.start > o.start) return 1;
      return 0;
    }
  }

  public static int[][] merge(int[][] intervals) {
    PriorityQueue<Interval> sortedList = getSortedIntervals(intervals);
    List<Interval> list = new ArrayList<>();
    list.add(sortedList.remove());
    int i = 1;
    while (!sortedList.isEmpty()){
      Interval current = sortedList.remove();
      Interval previous =  list.get(i-1);
      if(current.start <= previous.end){
        current.start = previous.start;
        current.end = current.end > previous.end ? current.end : previous.end;
        list.remove(i-1);
        list.add(i-1,current);
      }else {
        list.add(current);
        i++;
      }
    }
    int[][] response = new int[list.size()][2];
    int j = 0;
    for (Interval  interval: list ) {
      response[j][0] = interval.start;
      response[j][1] = interval.end;
      j++;
    }
    return response;
  }

  private static PriorityQueue<Interval> getSortedIntervals(int[][] intervals) {
    PriorityQueue<Interval> priorityQueue = new PriorityQueue<>();
    for(int i = 0; i < intervals.length ; i++) {
      Interval interval = new Interval(intervals[i][0],intervals[i][1]);
      priorityQueue.add(interval);
    }
    return priorityQueue;
  }

  public static void main(String[] args) {
    int[][] input = new int[][] {{1,3},{2,6},{8,10},{15,18}};
    int[][] output =  MergeIntervals.merge(input);
    System.out.println();
  }
}
