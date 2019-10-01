package Find_Anagrams_438;

import java.util.*;

/*
 * 
 * Leetcode # 438. Find All Anagrams in a String
https://leetcode.com/problems/find-all-anagrams-in-a-string/

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings 
s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

*/


public class Find_Anagrams_438 {

    public static List<Integer> findAnagrams(String s, String p) {
    	List<Integer> lists = new ArrayList<Integer>();
        
    	return lists;
    }

	public static String minWindow(String s, String t) {
		
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


	public static void main(String[] args) {
		
		List<Integer> lists = findAnagrams("cbaebabacd", "abc");
		
		System.out.println("Find Anagrams for \"cbaebabacd\", \"abc\" is: ");
		for (Integer i : lists) {
			System.out.println(i);
		}

		lists = findAnagrams("abab", "ab");
		
		System.out.println("Find Anagrams for \"abab\", \"ab\" is: ");
		for (Integer i : lists) {
			System.out.println(i);
		}		
	}
}
