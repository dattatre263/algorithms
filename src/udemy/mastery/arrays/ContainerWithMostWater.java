package udemy.mastery.arrays;

public class ContainerWithMostWater {

    static int solution(int[] input){
        int maxArea = 0;
        if(input == null || input.length < 2) return 0;
        int i = 0;
        int j = input.length-1;
        while (i != j){
            maxArea = Math.max(maxArea, Math.min(input[i], input[j]) * (j-i));
            if(input[i] < input[j]){
                i++;
            }else {
                j++;
            }
        }


        return maxArea;
    }

    public static void main(String[] args) {
        int[] testcase = new int[]{4,8,1,2,3,9};
        System.out.println(solution(testcase));

    }
}
