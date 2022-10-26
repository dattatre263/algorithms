package udemy.mastery.strings;

import org.apache.commons.lang3.StringUtils;

public class AlmostPalindrome {

    private static boolean solution(String s) {
        if (s == null || s.length() == 0) return false;
        if (s.length() < 3) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right -1);
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    private static boolean isPalindrome(String substring, int left, int right) {
        while (left < right) {
            if (substring.charAt(left) != substring.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
