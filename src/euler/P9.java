package euler;

public class P9 {

  public static void main(String[] args) {
    System.out.println(calculate());
  }

  private static int calculate() {
    int[] ans = new int[3];
    for (int i = 293; i < 500; i++){
      for(int j = 293; j > 1; j--){
        if(i + j < 500){
          break;
        }
        int k = 1000 - (i +j);
        double kSquare = i*i + j*j;
        if(Math.sqrt(kSquare) % k == 0){
          ans[0] = i;
          ans[1] = j;
          ans[2] = k;
          break;
        }
        if(Math.sqrt(kSquare) + i + j < 1000){
          break;
        }
      }
    }
    return ans[0]*ans[1]*ans[2];
  }

}
