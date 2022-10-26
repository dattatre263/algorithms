package udemy.mastery.strings;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(solution("pwwkew"));
    }

    private static int solution(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int longest = 0;
        int i = 0;
        int j = 0;
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;
        int currLen = 0;
        while (j < s.length()){
            if(map.containsKey(s.charAt(j)) && (map.get(s.charAt(j)) >= i)) {
                i = map.get(s.charAt(j)) + 1;
            }else {
                map.put(s.charAt(j), j);
                j++;
                currLen = j-i;
                longest = Math.max(currLen,longest);
            }
        }
        return longest;
    }
}
