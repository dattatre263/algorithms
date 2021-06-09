package leetcode.amzn.oa;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
  public static List<String> doesCircleExist(List<String> commands) {
    List<String> ans = new ArrayList<>();
    validate(commands);

    if( commands != null && !commands.isEmpty()){
      for (String string : commands) {
        boolean a =isCircular(string.toCharArray());
        if(a){
          ans.add("YES");
        }else{
          ans.add("NO");
        }
      }
    }
  }

  private static void validate(List<String> commands) {
    for (String  s: commands ) {
        
    }
  }

  private static boolean isCircular(char path[]){
    int x = 0;
    int y = 0;
    int dir = 0;

    for(int i = 0; i < path.length; i++){
      char move = path[i];
      if(move == 'R'){
        dir = (dir + 1) % 4;
      }else if ( move == 'L') {
        dir = (4 + dir - 1) % 4;
      } else {
        if(dir == 0){
          y++;
        }
        else if( dir == 1){
          x++;
        }else if ( dir == 2) {
          y--;
        }else {
          x--;
        }
      }
    }
    return ( x == 0 && y ==0);
  }

}
