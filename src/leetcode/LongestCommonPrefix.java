//package leetcode;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class LongestCommonPrefix {
//   static String longestCommonPrefix(String[] strs) {
//     Map<Integer,Integer> map = new HashMap<>();
//     int most = 0;
//     for (Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
//
//     }
//
//     if(strs == null && strs.length < 2){
//       return "";
//     }
//     StringBuilder longestCommon = new StringBuilder();
//     String s1 = strs[0];
//     String s2 = strs[2];
//     int i = 0;
//     for(char c : s1.toCharArray()){
//       if(c == s2.charAt(0)){
//         longestCommon.append(c);
//         i++;
//       }else{
//         break;
//       }
//     }
//     if(strs.length < 3){
//       return longestCommon.toString();
//     }
//     for(int j = 2; j < strs.length; j++){
//       int k = 0;
//       for(char ch : strs[j].toCharArray()){
////         if(){
////
////         }
//       }
//
//     }
//
//
//  }
//
//  public static void main(String[] args){
//    System.out.println(
//    longestCommonPrefix(new String[] {"flower","flow","flight"}));
//
//  }
//
//}
