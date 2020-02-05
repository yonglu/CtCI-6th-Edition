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
    	
    	if (s == null || p == null || s.length() < p.length() || s.isEmpty() || p.isEmpty()) {
    		return lists;
    	}
    	
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	
    	int count = 0;
    	for (char c: p.toCharArray()) {
    		if (map.containsKey(c)) {
    			map.put(c, map.get(c) + 1);
    		} else {
    			map.put(c,  1);
    			count++;
    		}
    	}
    	
    	int end = 0;
    	int begin = 0;
    	while (end < s.length()) {
    		char cur = s.charAt(end);
    		if (map.containsKey(cur)) {
    			map.put(cur,  map.get(cur) - 1);
    			if (map.get(cur) == 0) {
    				count--;
    			}
    		}
    		end++;
    		
    		while (count == 0) {
    			if (end - begin == p.length()) {
    				lists.add(begin);
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
    	   	
    	return lists;
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
	
    public static List<Integer> findAnagrams_old(String s, String p) {
    	List<Integer> lists = new ArrayList<Integer>();
    	
    	if (s == null || p == null || s.length() < p.length() || s.isEmpty() || p.isEmpty()) {
    		return lists;
    	}
    	
    	// Record the String p in HaskMap
    	Map<Character, Integer> wordMap = new HashMap<Character, Integer>();
    	
    	int count = 0;
    	for (char c : p.toCharArray()) {
    		if (wordMap.containsKey(c)) {
    			wordMap.put(c,  wordMap.get(c) + 1);
    		} else {
    			wordMap.put(c, 1);
    			count++;
    		}
    	}
    	
        int begin = 0;
        int end = 0;
        while (end < s.length()) {
        	char eChar = s.charAt(end);
        	if (wordMap.containsKey(eChar)) {
        		int eCharCount = wordMap.get(eChar);
        		wordMap.put(eChar,  --eCharCount);
        		if (eCharCount == 0) {
        			count--;
        		}
        	}
        	end++;
        	
        	while (count == 0) {
        		if (end-begin == p.length()) {
        			lists.add(begin);
        		}
        		
        		char bChar = s.charAt(begin);
        		if(wordMap.containsKey(bChar)) {
        			int bCharCount = wordMap.get(bChar);
        			wordMap.put(bChar, ++bCharCount);
        			if (bCharCount == 1) {
        				count++;
        			}
        		}
        		begin++;
        	}
        }      
    	
    	return lists;
    }
	
}
