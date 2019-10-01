package LongestSubStringWithoutRepeatingCharacters_3;

import java.util.*;

/*
 * Leetcode # 3. Longest Substring Without Repeating Characters

https://leetcode.com/problems/longest-substring-without-repeating-characters/

Given a string, find the length of the longest substring without repeating characters.

Example 1:
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/


public class LongestSubStringWithoutRepeatingCharacters_3 {

    public static int lengthOfLongestSubstring(String s) { 
    	int result = Integer.MIN_VALUE;
    	
    	if (s == null || s.isEmpty()) {
    		return 0;
    	}
    	   
    	// Record the String p in HaskSet
    	Set<Character> wordSet = new HashSet<Character>();
    	
        int begin = 0;
        int end = 0;

        while (end < s.length() && begin < s.length()) {
        	char eChar = s.charAt(end);
        	if (!wordSet.contains(eChar)) {
        		wordSet.add(eChar);
        		end++;
        		if (result < end - begin) {
        			result = end-begin;
        		}
        	} else {
        		wordSet.remove(s.charAt(begin));
        		begin++;
         	}
        }      
    	
    	return result;
    }

	public static void main(String[] args) {
		
		int result = lengthOfLongestSubstring("abcabcbb");		
		System.out.println("Length of longest substring \"abcabcbb\" is: ");
		System.out.println(result);

		result = lengthOfLongestSubstring("bbbbb");		
		System.out.println("Length of longest substring \"bbbbb\" is: ");
		System.out.println(result);

		result = lengthOfLongestSubstring("pwwkew");
		System.out.println("Length of longest substring \"pwwkew\" is: ");
		System.out.println(result);

	}
}
