package udemy.mastery.arrays;

public class TrappingRainWater {

    static int solution(int[] height) {
        int maxArea = 0;
        int maxLeft = 0;
        int maxRight = 0;
        int p1 = 0;
        int p2 = height.length - 1;
        while (p1 != p2){
            int current = 0;
            // left side moves
            if(height[p1] < height[p2]){
                if(maxLeft > height[p1]){
                    maxArea = maxArea + (maxLeft - height[p1]);
                }else {
                    maxLeft = height[p1];
                }
                p1++;
            }else {
                if(maxRight > height[p2]){
                    maxArea = maxArea + (maxRight - height[p2]);
                }else {
                    maxRight = height[p2];
                }
                p2--;
            }

        }
        return maxArea;

    }

    private static int getMaxLeft(int i, int[] height) {
        int maxL = 0;
        for (int j = i - 1; j >= 0; j--) {
            maxL = Math.max(maxL, height[j]);
        }
        return maxL;
    }

    private static int getMaxRight(int i, int[] height) {
        int maxR = 0;
        for (int j = i + 1; j < height.length; j++) {
            maxR = Math.max(maxR, height[j]);
        }
        return maxR;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 3, 1, 0, 1, 2};
        System.out.println(solution(height));

    }
}
