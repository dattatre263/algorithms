package leetcode.amzn;

class AtoiSolution {
  public int myAtoi(String str) {
    str = trimLeadingWhiteSpaces(str);
    if(str.length() == 0){
      return 0;
    }
    char[] array = new char[12];
    boolean negative = false;
    StringBuilder numString = new StringBuilder();
    extractFirstChar(str,array,0);
    for(int i = 0; i <12;i++){
      if(array[i] == '\u0000'){
        break;
      }
      if(array[0] == '-'){
        negative = true;
      }
      numString.append(array[i]);
    }
    if(numString.length() == 0){
      return 0;
    }
    long num = Long.parseLong(numString.toString());
//    if(negative){
//      num = num * -1;
//    }
    if(num < Integer.MIN_VALUE){
      return Integer.MIN_VALUE;
    }else if(num > Integer.MAX_VALUE){
      return Integer.MAX_VALUE;
    }

    return (int)num;
  }

  private void extractFirstChar(String str, char[] array, Integer index ){
    if(index > 11){
      maxOutArray(array);
    }
    if(str.length() > 0){
      char c = str.charAt(0);
      if(index == 0 && c == '-'){
        array[index] = c;
        index++;
        extractFirstChar(str.substring(1),array,index);
      }else if((int)c >= 48 && (int)c <= 57 ) {
        array[index] = c;
        index++;
        extractFirstChar(str.substring(1),array,index);
      }else{
        return;
      }
    }
  }

  private String trimLeadingWhiteSpaces(String str){
    if(str == null || str.length() == 0){
      return "";
    }
    if(str.charAt(0) == ' '){
      return trimLeadingWhiteSpaces(str.substring(1));
    }else{
      return str;
    }
  }

  private void maxOutArray(char[] array){
    if(array[0] == '-'){
      array[1] = '9';
    }else{
      array[0] = '9';
    }
  }

  public static void main(String[] args) {
    AtoiSolution atoiSolution = new AtoiSolution();
    int i =  atoiSolution.myAtoi("   -42");
    System.out.println(i);
  }
}
