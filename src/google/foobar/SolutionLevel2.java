package google.foobar;

public class SolutionLevel2 {
  public static void main(String[] args) {
    String s = "--->-><-><-->-";
    System.out.println(solution(s));
  }

  private static int solution(String s) {
    char[] charArray = s.toCharArray();
    int total = 0;
    int count = 0;
    for(int i = charArray.length -1; i >= 0; i-- ){
      if(charArray[i] == '<'){
        count++;
      }else if(charArray[i] == '>'){
        total += count*2;
      }
    }
    return total;
  }
}
