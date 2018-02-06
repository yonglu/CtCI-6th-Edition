package A_LongestPalindrome;

import java.util.*;

public class LongestPalindrome {

   // https://leetcode.com/problems/longest-palindromic-substring/solution/
   
   /*
    * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded 
    * from its center, and there are only 2n−1 such centers.
    * You might be asking why there are 2n - 1but not n centers? The reason is the center of a 
    * palindrome can be in between two letters. Such palindromes have even number of letters 
    * (such as \textrm{''abba''}”abba”) and its center are between the two \textrm{'b'}’b’s.
    * 
    * Time complexity : O(n^2).  Since expanding a palindrome around its center could take O(n) time, 
    * the overall complexity is O(n^2).
    *
    * Space complexity : O(1).
    */
   public static String longestPalindrome(String s) {
      int start = 0, end = 0;
      for (int i = 0; i < s.length(); i++) {
          int len1 = expandAroundCenter(s, i, i);
          int len2 = expandAroundCenter(s, i, i + 1);
          int len = Math.max(len1, len2);
          if (len > end - start) {
              start = i - (len - 1) / 2;
              end = i + len / 2;
          }
      }
      return s.substring(start, end + 1);
  }

  private static int expandAroundCenter(String s, int left, int right) {
      int L = left, R = right;
      while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
          L--;
          R++;
      }
      return R - L - 1;
  }

   
   // Not efficient O(2^N).  Can be done with matrix like Subset Sum
   public static String longestPalindrome2(String word) {

      int len = word.length();
      if (isPalindrome(word)) {
          return word;
      }
      
      String word2 = longestPalindrome2(word.substring(0, len - 1));        
      String word3 = longestPalindrome2(word.substring(1, len ));
      
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

      String result2 = longestPalindrome2(s);
      System.out.println("Longest Palindrome2 is:");
      System.out.println(result2);
	}

}
