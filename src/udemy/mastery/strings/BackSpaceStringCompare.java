package udemy.mastery.strings;

public class BackSpaceStringCompare {


    static boolean solution(String s1, String s2) {

        return check(s1, s2, s1.length() - 1, s2.length() - 1, 0, 0);

    }

    private static boolean check(String s1, String s2, int i, int j, int b1, int b2) {
        if (i < 0 && j < 0) return true;
        if ((i >= 0 && s1.charAt(i) == '#')  ||  (j >= 0 && s2.charAt(j) == '#')) {
            if (i >= 0 && s1.charAt(i) == '#') {
                b1++;
                i--;
            }
            if (j >= 0 && s2.charAt(j) == '#') {
                b2++;
                j--;
            }
            return check(s1, s2, i, j, b1, b2);

        } else if (b1 > 0 || b2 > 0) {
            if (b1 > 0) {
                i--;
                b1--;
            }
            if (b2 > 0) {
                j--;
                b2--;
            }
            return check(s1, s2, i, j, b1, b2);
        } else if (s1.charAt(i) != s2.charAt(j)) {
            return false;
        } else {
            i--;
            j--;
            return check(s1, s2, i, j, b1, b2);
        }
    }


    public static void main(String[] args) {
        System.out.println(solution("a##c", "#a#c"));

    }
}
