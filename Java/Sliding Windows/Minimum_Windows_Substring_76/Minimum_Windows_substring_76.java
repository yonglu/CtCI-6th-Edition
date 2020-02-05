package Minimum_Windows_Substring_76;

import java.util.*;

/*
 * Leetcode # 76. Minimum Window Substring
 * 
 * https://leetcode.com/problems/minimum-window-substring/
 * 
 * Given a string S and a string T, find the minimum window in S which will contain 
 * all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * 
 * Note:
 * 
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only 
 * 		one unique minimum window in S.
 */


public class Minimum_Windows_substring_76 {


	public static String minWindow(String s, String t) {
		String result = "";
		int resultLength = Integer.MAX_VALUE;
		
		if (s == null || t == null || s.length() < t.length() || s.length() == 0) {
			return result;
		}
		
		int count = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c: t.toCharArray()) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
				count++;
			}
		}
		
		int begin = 0;
		int end = 0;
		while (end < s.length()) {
			char cur = s.charAt(end);
			if (map.containsKey(cur)) {
				map.put(cur, map.get(cur) - 1);
				if (map.get(cur) == 0) {
					count--;
				}
			}
			end++;
			
			while (count == 0) {
				String temp = s.substring(begin, end);
				if (temp.length() < resultLength) {
					result = temp;
					resultLength = result.length();
				}
				char cur2 = s.charAt(begin);
				if (map.containsKey(cur2)) {
					map.put(cur2, map.get(cur2) + 1);
					if (map.get(cur2) == 1) {
						count++;
					}
				}
				
				begin++;
			}
		}
		
		
		return result;
	}


	public static void main(String[] args) {
		
		String result = minWindow("ADOBECODEBANC", "ABC");
		
		System.out.println("Minimum windows for \"ADOBECODEBANC\", \"ABC\" is: ");
		System.out.println(result);
		
	}
	
	public static String minWindow_old(String s, String t) {
		
		String result = "";
		if (s == null || t == null || s.length() < t.length()) {
//			throw new java.lang.IllegalArgumentException("bad parameters");
			return result;
		}
		
		Map<Character, Integer> wordMap = new HashMap<Character, Integer>();
		int count = 0;
		for (char c : t.toCharArray()) {
			if (wordMap.containsKey(c)) {
				wordMap.put(c, wordMap.get(c) + 1);
			} else {
				wordMap.put(c, 1);
				count++;
			}
		}
		
		int begin = 0;
		int end = 0;
		int len = Integer.MAX_VALUE;
		
		while ( end < s.length()) {			
			char eCh = s.charAt(end);
			
			// update the count if find the character
			if (wordMap.containsKey(eCh)) {
				int eChCount = wordMap.get(eCh);
				wordMap.put(eCh, --eChCount);
				if (eChCount == 0) {
					count--;
				}
			}
			end++;
			
			// if find the whole string t, then adjust the begin to minimum the windows
			while (count == 0) {
				if ( len > end - begin) {
					len = end - begin;
					result = s.substring(begin, end);
				}
				char bCh = s.charAt(begin);
				if (wordMap.containsKey(bCh)) {
					int bChCount = wordMap.get(bCh);
					wordMap.put(bCh, ++bChCount);
					if (bChCount == 1) {
						count++;
					}
				}
				
				begin++;
			}
		}
		
		
		return result;
	}
	
}
