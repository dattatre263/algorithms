package leetcode.amzn.mock.five;

public class Prison {

  public int[] prisonAfterNDays(int[] cells, int n) {
    String original = build(cells);
    int prev = -1;
    for (int i = 1; i <=n; i++) {
      for (int j = 0; j < cells.length; j++) {
        if(j == 0 || j == cells.length - 1) {
          prev = cells[j];
          cells[j] = 0;
        }else {
          if((prev == 0 && cells[j+1] == 0) || (prev == 1 && cells[j+1] == 1)){
            prev = cells[j];
            cells[j] = 1;
          }else {
            prev = cells[j];
            cells[j] = 0;
          }
        }
      }
      String current = build(cells);
      if(current.equals(original)){
        n = n%i;
      }
    }
    return cells;
  }

  private String build(int[] cells) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < cells.length; i++){
      builder.append(cells[i]);
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    Prison prison = new Prison();
    int[] ans = prison.prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000);
      System.out.println();
  }
}
