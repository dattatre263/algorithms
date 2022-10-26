package algospecialization.greedyandspanningtrees.week1;

import algospecialization.greedyandspanningtrees.datastructure.Job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class GreedyAlgorithm {

  PriorityQueue<Job> priorityQueue;
  long weightedSum = 0;

  public GreedyAlgorithm(int numberOfJobs, int algorithm) {
    if (algorithm == 0) {
      priorityQueue =
          new PriorityQueue<Job>(
              numberOfJobs,
              (Job o1, Job o2) -> {
                int ans = o2.getDifference().compareTo(o1.getDifference());
                if (ans == 0) {
                  return o2.getWeight().compareTo(o1.getWeight());
                } else {
                  return ans;
                }
              });
    } else {
      priorityQueue =
          new PriorityQueue<Job>(
              numberOfJobs,
              (Job o1, Job o2) -> {
                int ans = o2.getRatio().compareTo(o1.getRatio());
                if (ans == 0) {
                  return o2.getWeight().compareTo(o1.getWeight());
                } else {
                  return ans;
                }
              });
    }
  }

  public static void main(String[] args) {
    Path path = Paths.get(args[0]);
    try {
      int numberOfJobs = Integer.valueOf(Files.lines(path).findFirst().get());
      GreedyAlgorithm greedyAlgorithm1 = new GreedyAlgorithm(numberOfJobs, 0);
      GreedyAlgorithm greedyAlgorithm2 = new GreedyAlgorithm(numberOfJobs, 1);
      Files.lines(path)
          .skip(1)
          .forEach(
              line -> {
                String[] attributes = line.split("\t| ");
                Job job = new Job(Integer.valueOf(attributes[0]), Integer.valueOf(attributes[1]));
                greedyAlgorithm1.addJob(job);
                greedyAlgorithm2.addJob(job);
              });
      greedyAlgorithm1.calculate();
      greedyAlgorithm2.calculate();
      System.out.println(greedyAlgorithm1.weightedSum);
      System.out.println(greedyAlgorithm2.weightedSum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void calculate() {
    long acuumLength = 0;
    while (priorityQueue.size() != 0){
      Job job = priorityQueue.remove();
      acuumLength = acuumLength + job.getLength();
      weightedSum = weightedSum + acuumLength * job.getWeight();
    }
  }

  private void addJob(Job job) {
    this.priorityQueue.add(job);
  }
}
