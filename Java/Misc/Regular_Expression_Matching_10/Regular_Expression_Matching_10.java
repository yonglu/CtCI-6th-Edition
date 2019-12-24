package Regular_Expression_Matching_10;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
 * Leetcode # 10. Regular Expression Matching

Given an input string (s) and a pattern (p), implement regular expression matching with 
support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
by repeating 'a' once, it becomes "aa".

Example 3:
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

public class Regular_Expression_Matching_10 {


	static boolean isMatch(String word, String pattern) {
    	if ((word == null) || (pattern == null)) {
    		return false;
    	}
    	if (pattern.length() == 0) {
    		return word.length() == 0;
    	}
    	
    	// Note: don't check word.length() == 0 here because 
    	// a char and * can stand for 0 element
      
    	// special case
    	if (pattern.length() == 1) {
     
    		// if the length of word is 0, return false
    		if (word.length() < 1) {
    			return false;
    		}
     
    		//if the first does not match, return false
    		else if ((pattern.charAt(0) != word.charAt(0)) && (pattern.charAt(0) != '.')) {
    			return false;
    		}
     
    		// otherwise, compare the rest of the string of s and p.
    		else {
    			return isMatch(word.substring(1), pattern.substring(1));
    		}
    	}
     
    	// case 1: when the second char of pattern is not '*'
    	if (pattern.charAt(1) != '*') {
    		if (word.length() < 1) {
    			return false;
    		}
    		if ((pattern.charAt(0) != word.charAt(0)) && (pattern.charAt(0) != '.')) {
    			return false;
    		} else {
    			return isMatch(word.substring(1), pattern.substring(1));
    		}
    	}
     
    	// case 2: when the second char of p is '*', complex case.
    	else {
    		//case 2.1: a char & '*' can stand for 0 element
    		if (isMatch(word, pattern.substring(2))) {
    			return true;
    		}
     
    		//case 2.2: a char & '*' can stand for 1 or more preceding element, 
    		//so try every sub string
    		int i = 0;
    		while (i<word.length() && (word.charAt(i)==pattern.charAt(0) || pattern.charAt(0)=='.')){
    			if (isMatch(word.substring(i + 1), pattern.substring(2))) {
    				return true;
    			}
    			i++;
    		}
    		return false;
    	}
    }
    
    public static void main(String[] args) throws IOException {

    	System.out.println("expect false : " + isMatch("aa", "a"));
    	System.out.println("expect true : " + isMatch("aa", "a*"));
    	System.out.println("expect true : " + isMatch("ab", ".*"));
    	System.out.println("expect true : " + isMatch("aab", "c*a*b"));
        System.out.println("expect false : " + isMatch("mississippi", "mis*is*p*."));
     }
}
