package Implement_strstr_28;

import java.util.HashMap;
import java.util.Map;

/*
 * Leetcode # 28. Implement strStr()


Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to 
C's strstr() and Java's indexOf().

 * 
 */

public class Implement_strstr_28 {
	public static int strStr(String haystack, String needle) {
        if ( needle == null || needle.isEmpty() ) {
            return 0;
        }
		else if (haystack == null || haystack.isEmpty() ) {
			return -1;
		}         
		int ans = -1;
		
		for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				if (ifEqual(haystack.substring(i+1, i + needle.length()), needle.substring(1))) {
					return i;
				}
			}
		}
		
		return ans;
	}

	private static boolean ifEqual(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}
		
		for (int i=0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		int i = strStr("hello", "ll");
		System.out.println(i); // expected 2
		
		i = strStr("hello", "o");
		System.out.println(i); // expect -1

		i = strStr("aaaaa", "bba");
		System.out.println(i); // expect -1

		i = strStr("aaa", "aaaa");
		System.out.println(i); // expect -1
		
		i = strStr("a", "a");
		System.out.println(i); // expect 0
	}

}
