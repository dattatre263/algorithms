package algospecialization.greedyandspanningtrees.week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class HuffmanCodes2 {
  private int N;
  TreeSet<Node> queue = new TreeSet<>();
  Node tree = null;
  int min = 0;
  int max = 0;
  boolean flag = false;

  public HuffmanCodes2(TreeSet<Node> priorityQueue, int numberOfSymbols) {
    this.N = numberOfSymbols;
    this.queue =  priorityQueue;
  }

  private static class Node implements Comparable<Node> {
    private long weight;
    private Node left;
    private Node right;
    public Node(long weight){
      this.weight = weight;
    }
    public Node(long weight, Node left, Node right){
      this.weight = weight;
      this.left = left;
      this.right = right;
    }

    @Override
    public int compareTo(Node o) {
      if(this.weight < o.weight ) return  -1;
      if(this.weight > o.weight) return 1;
      return 0;
    }
  }
  public static void main(String[] args) {
    try{
      Path path = Paths.get(args[0]);
      int numberOfSymbols =  Integer.parseInt(Files.lines(path).findFirst().get());
      TreeSet<Node> priorityQueue = new TreeSet<>();
      AtomicLong index = new AtomicLong(1);
      Files.lines(path).skip(1).forEach(line -> {
        Node node = new Node(Integer.parseInt(line));
        priorityQueue.add(node);
        index.incrementAndGet();
      });
      HuffmanCodes2 huffmanCodes = new HuffmanCodes2(priorityQueue, numberOfSymbols);
      huffmanCodes.buildTree();
      huffmanCodes.traverseTreeMin(huffmanCodes.tree, 0);
      System.out.println(huffmanCodes.min -1);
      System.out.println(huffmanCodes.max -1);
    }catch (IOException exception){
      throw new RuntimeException(exception);
    }
  }

  private void traverseTreeMin(Node tree, int level) {
    if(tree == null){
      if(!flag){
        flag = true;
        min = level;
      }else {
        max = level > max ? level : max;
      }
      return;
    }
    traverseTreeMin(tree.left, level + 1);
    traverseTreeMin(tree.right, level + 1);
  }

  private void buildTree() {
    while (this.queue.size() > 2){
      Node n1 = queue.pollFirst();
      Node n2 = queue.pollFirst();
      Node node = new Node(n1.weight + n2.weight, n1, n2);
      queue.add(node);
    }
    Node n1 = queue.pollFirst();
    Node n2 = queue.pollFirst();
    tree = new Node(n1.weight + n2.weight, n1, n2);
  }
}
