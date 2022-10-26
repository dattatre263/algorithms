//package leetcode.amzn;
//
//
//
//import org.apache.commons.lang3.tuple.Pair;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//public class WordSearch {
//  private Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
//  private boolean[][] visited;
//  public boolean exist(char[][] board, String word) {
//    List<Pair<Integer, Integer>> pairs = new ArrayList<>();
//    for (int i = 0; i < board.length; i++) {
//      for (int j = 0; j < board[i].length; j++) {
//        if (board[i][j] == word.charAt(0)) {
//          Pair<Integer, Integer> pair = new Pair<>(i, j);
//          pairs.add(pair);
//        }
//      }
//    }
//    for (Pair<Integer, Integer> start : pairs) {
//      visited = new boolean[board.length][board[0].length];
//      int level = 1;
//      int levelCount = 0;
//      int index = 1;
//      int count = 1;
//      queue.add(start);
//      while (index < word.length() && !queue.isEmpty()){
//        Pair<Integer, Integer> pair = queue.poll();
//        int i = pair.getKey();
//        int j = pair.getValue();
//        visited[i][j] = true;
//        while (count > 0){
//          if(i+1 < board.length && !visited[i+1][j]  && board[i+1][j] == word.charAt(index)){
//            queue.add(new Pair<>(i+1,j));
//            levelCount++;
//          }
//          if(i-1 >= 0 && !visited[i-1][j]  && board[i-1][j] == word.charAt(index)){
//            queue.add(new Pair<>(i-1,j));
//            levelCount++;
//          }
//          if(j+1 < board[i].length && !visited[i][j+1] && board[i][j+1] == word.charAt(index)){
//            queue.add(new Pair<>(i,j+1));
//            levelCount++;
//          }
//          if(j-1 >= 0 && !visited[i][j-1] && board[i][j-1] == word.charAt(index)){
//            queue.add(new Pair<>(i,j-1));
//            levelCount++;
//          }
//          count--;
//        }
//        count = levelCount;
//        levelCount = 0;
//        index++;
//      }
//      if(index == word.length() && !queue.isEmpty()) return true;
//    }
//    return false;
//  }
//
//  public static void main(String[] args) {
//
//    //
//    WordSearch wordSearch = new WordSearch();
//    boolean ans =
////        wordSearch.exist(
////            new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCB");
//        wordSearch.exist(
//            new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED");
//    System.out.println(ans);
//  }
//}
