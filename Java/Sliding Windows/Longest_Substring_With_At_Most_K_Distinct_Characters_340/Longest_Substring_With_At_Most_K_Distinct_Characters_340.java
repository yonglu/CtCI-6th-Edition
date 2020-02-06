package Longest_Substring_With_At_Most_K_Distinct_Characters_340;

import java.util.*;

/*
 * 
 * Leetcode # 34. Substring with Concatenation of All Words


Given a string, find the length of the longest substring in it with no more than K distinct characters.

Example 1:

Input:
  input = "araaci",
  k = 2
Output: 4

Example 2:

Input:
  s = "cbbebi",
  k = 3
Output: 5


*/


public class Longest_Substring_With_At_Most_K_Distinct_Characters_340 {

	  public static int LongestSubstringKDistinct(String s, int k) {
		    int result = 0;
            if (s == null || s.isEmpty() || k <= 0) {
                return result;
            }
            
            Map<Character, Integer> map = new HashMap<Character, Integer>();

            int begin = 0;
            int end = 0;
            while (end < s.length()) {
            	char cur = s.charAt(end);
            	if (map.containsKey(cur)) {
            		map.put(cur, map.get(cur) + 1);
            	} else {
            		map.put(cur, 1);
            	}
            	if (map.size() <= k) {
            		result = Math.max(result, end - begin + 1);
            	}
            	end++;
            	
            	while (map.size() > k) {
            		char cur2 = s.charAt(begin);
            		if (map.containsKey(cur2)) {
            			map.put(cur2, map.get(cur2) - 1);
            			if (map.get(cur2) == 0) {
            				map.remove(cur2);
            			}
            		}
            		begin++;
            	}            	
            }

		    return result;
		  }
    

	public static void main(String[] args) {
		
	    int result = LongestSubstringKDistinct("araaci", 2);
	    System.out.println(result); // 4
	    result = LongestSubstringKDistinct("araaci", 1);
	    System.out.println(result); // 2
	    result = LongestSubstringKDistinct("cbbebi", 3);
	    System.out.println(result); // 5
	}

}
