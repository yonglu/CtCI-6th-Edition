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
    	return "";
    }
    
	public static void main(String[] args) {
		String ans;
		ans = longestPalindrome("babad");		
		System.out.println("Longest palindrome of babad is : " + ans);

		ans = longestPalindrome("cbbd");		
		System.out.println("Longest palindrome of cbbd is : " + ans);
	}
}
