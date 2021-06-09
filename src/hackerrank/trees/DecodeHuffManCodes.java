package hackerrank.trees;

import java.util.HashMap;
import java.util.Map;

public class DecodeHuffManCodes {

  static Node root = null;
  static Map<String, Character> map = new HashMap<>();

  static class Node {
    public int frequency; // the frequency of this tree
    public char data;
    public Node left, right;
  }

  static void decode(String s, Node root) {
    DecodeHuffManCodes.root = root;
    decode(new StringBuilder(), root);
    StringBuilder ans = new StringBuilder();
    int t  = s.length();
    while (t > 0){
      int last = 0;
      for (int i = 1; i <= s.length(); i++) {
        if(map.containsKey(s.substring(0,i))){
          ans.append(map.get(s.substring(0,i)));
          last = i;
          break;
        }
      }
      s = s.substring(last,s.length());
      if(map.containsKey(s)){
        ans.append(map.get(s));
        break;
      }
      t = s.length();
    }
    s = ans.toString();

  }

  static void decode(StringBuilder builder, Node node) {
    if (node.left == null && node.right == null) {
      map.put(builder.toString(),node.data);
      return;
    }
    if (node.left != null) {
      builder.append("0");
      decode(builder, node.left);
      builder.deleteCharAt(builder.length() -1);
    }
    if(node.right != null){
      builder.append("1");
      decode(builder, node.right);
      builder.deleteCharAt(builder.length() -1);
    }

  }

  public static void main(String[] args) {
    Node C = new Node();
    C.data = 'C';
    Node B = new Node();
    B.data = 'B';
    Node BC = new Node();
    BC.left = B;
    BC.right = C;
    Node A = new Node();
    A.data = 'A';
    Node ABC = new Node();
    ABC.left = BC;
    ABC.right = A;
    decode("1001011", ABC);
  }
}
