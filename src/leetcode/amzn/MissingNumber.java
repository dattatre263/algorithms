package leetcode.amzn;

class MissingNumber {
  public int missingNumber(int[] nums) {
    int first = nums[0];
    int last = nums[0];
    int sum = 0;
    for(int i = 0; i < nums.length; i++){
      first = nums[i]<first?nums[i]:first;
      last = nums[i]>last?nums[i]:last;
      sum = sum+nums[i];
    }

    double actualSum = (nums.length+1)/2*(2*first+(nums.length));
    return (int) (actualSum - sum);

  }

  public static void main(String[] args) {
    MissingNumber missingNumber = new MissingNumber();
    System.out.println(missingNumber.missingNumber(new int[]{3,0,1}));
  }
}
