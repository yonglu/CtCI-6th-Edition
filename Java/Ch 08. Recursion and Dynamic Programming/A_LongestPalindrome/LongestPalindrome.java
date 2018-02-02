package A_LongestPalindrome;

import java.util.*;

public class LongestPalindrome {

   public static String longestPalindrome(String word) {

      int len = word.length();
      if (isPalindrome(word)) {
          return word;
      }
      
      String word2 = longestPalindrome(word.substring(0, len - 1));        
      String word3 = longestPalindrome(word.substring(1, len ));
      
      if (word2.length() >= word3.length()) {
          return word2;
      }
      else {
          return word3;
      }

  }

  public static boolean isPalindrome(String s){
     if (s == null) {
        return false;
     }
     
     int low = 0;
     int high = s.length() - 1;
     while(low < high)
        if(s.charAt(low++) != s.charAt(high--)) return false;
     return true;
  }   
  
	public static void main(String[] args) {      
      String s = "AppleelpApp";
      String result = longestPalindrome(s);
      System.out.println("Longest Palindrome is:");
      System.out.println(result);
	}

}
