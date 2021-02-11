package algospecialization.greedyandspanningtrees.week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class HuffmanCodes {

  PriorityQueue<Node> priorityQueue;
  long N;
  Node tree = null;
  Map<Long, Boolean> map = null;
  int max = 0;
  int min = Integer.MAX_VALUE;

  public HuffmanCodes(PriorityQueue priorityQueue, int numberOfSymbols){
    this.priorityQueue = priorityQueue;
    this.N = numberOfSymbols;
  }

  public static void main(String[] args) {
    try{
      Path path = Paths.get(args[0]);
      int numberOfSymbols =  Integer.parseInt(Files.lines(path).findFirst().get());
      PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
      AtomicLong index = new AtomicLong(1);
      Files.lines(path).skip(1).forEach(line -> {
        Node node = new Node(index.get(),Integer.parseInt(line));
        priorityQueue.add(node);
        index.incrementAndGet();
      });
      HuffmanCodes huffmanCodes = new HuffmanCodes(priorityQueue, numberOfSymbols);
      huffmanCodes.buildTree();
      huffmanCodes.traverseTree();
      System.out.println(huffmanCodes.max);
      System.out.println(huffmanCodes.min);
    }catch (IOException exception){
      throw new RuntimeException(exception);
    }


  }

  private void traverseTree() {
   this.map = new HashMap<>();
    for (long i = 1; i < N + 1 ; i++) {
      map.put(i,false);
    }
   this.DFS(tree, new StringBuilder());
  }

  private void DFS(Node node, StringBuilder builder) {
    map.put(node.index, true);
    if(node.left == null){
      max = builder.length() > max ? builder.length() : max;
      min = builder.length() < min ? builder.length() : min;
    }else {
      if(!map.get(node.left.index)){
        DFS(node.left,builder.append("0"));
        builder.deleteCharAt(builder.length() - 1);
      }
      if(!map.get(node.right.index)){
        DFS(node.right,builder.append("1"));
        builder.deleteCharAt(builder.length() - 1);
      }
    }
  }


  private void buildTree() {
    if (priorityQueue.size() == 2){
      Node left = priorityQueue.remove();
      Node right = priorityQueue.remove();
      N++;
      this.tree = new Node(N, left.weight + right.weight);
      tree.left = left;
      tree.right = right;
    }else {
      Node left = priorityQueue.remove();
      Node right = priorityQueue.remove();
      N++;
      Node temp = new Node(N, left.weight + right.weight);
      temp.left = left;
      temp.right = right;
      priorityQueue.add(temp);
      buildTree();
    }

  }

  private static class Node implements Comparable<Node>{
    private long index;
    private long weight;
    private Node left;
    private Node right;
    public Node(long index, long weight){
      this.index = index;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node that) {
      if(this.weight < that.weight) return -1;
      if(this.weight == that.weight) return 0;
      return 1;
    }
  }
}
