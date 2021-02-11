package leetcode;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

  public void setZeroes(int[][] matrix) {
    Set<Integer> rows = new HashSet<>();
    Set<Integer> columns = new HashSet<>();
    for (int i = 0; i <matrix.length; i++) {
      for (int j = 0; j < matrix[i].length ; j++) {
        if(matrix[i][j] == 0){
          rows.add(i);
          columns.add(j);
        }
      }
    }
    for (Integer  row: rows ) {
      for (int i = 0; i < matrix[row].length ; i++) {
        matrix[row][i] = 0;
      }
    }
    for (Integer  column: columns ) {
      for (int i = 0; i < matrix.length ; i++) {
        matrix[i][column] = 0;
      }
    }
  }

  public static void main(String[] args) {
    SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();
    setMatrixZeroes.setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
  }
}
