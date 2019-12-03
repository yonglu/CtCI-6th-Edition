package Longest_Palindromic_Substring_5;

import java.util.*;

/* Leetcode # 5. Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the 
maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
 
*/

public class Longest_Palindromic_Substring_5 {

    public static String longestPalindrome(String s)  {
    	
    	String longestPal = "";
    	if (s == null || s.length() == 0) {
    		return longestPal;
    	}
    	
    	
    	for (int i = 0; i < s.length(); i++) {
    		String tempPal = palindromeLength(s, i, i);
    		if (tempPal.length() > longestPal.length()) {
    			longestPal = tempPal;
    		}
    		
    		tempPal = palindromeLength(s, i, i+1);
    		if (tempPal.length() > longestPal.length()) {
    			longestPal = tempPal;
    		}
    	}
    	
    	return longestPal;
    }
    
    private static String palindromeLength(String s, int l, int r) {
    	while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
    		l--;
    		r++;
    	}
    	return s.substring(l+1, r);
    }
    
	public static void main(String[] args) {
		String ans;
		ans = longestPalindrome("babad");		
		System.out.println("Longest palindrome of babad is : " + ans);

		ans = longestPalindrome("cbbd");		
		System.out.println("Longest palindrome of cbbd is : " + ans);
		
		ans = longestPalindrome("abcdcba");		
		System.out.println("Longest palindrome of abcdcba is : " + ans);
		
	}
}
