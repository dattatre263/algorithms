package leetcode.amzn.mock.six;

import java.util.PriorityQueue;

public class RectangleOverlap {
  public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

    int x1 = rec1[0];
    int y1 = rec1[1];
    int x2 = rec1[2];
    int y2 = rec1[3];

    int p1 = rec2[0];
    int q1 = rec2[1];
    int p2 = rec2[2];
    int q2 = rec2[3];
      if(x1 >= x2) return false;
      if(y1 >= y2) return false;
      if(p1 >= p2) return false;
      if(q1 >= q2) return false;
    if(p1 < x2 && q1 < y2 && p2 > x1 && q2 > y1) return true;

    return false;
  }

  public static void main(String[] args) {
    //
    RectangleOverlap rectangleOverlap = new RectangleOverlap();
    boolean ans = rectangleOverlap.isRectangleOverlap(new int[]{-1,0,1,1}, new int[]{0,-1,0,1});
    System.out.println(ans);
  }
}
