package Edit_Distance_72;

import java.util.*;

/*
 * Leetcode # 72. Edit Distance
 * 
 * 
Given two words word1 and word2, find the minimum number of operations 
required to convert word1 to word2.

You have the following 3 operations permitted on a word:

	* Insert a character
	* Delete a character
	* Replace a character
	* 
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/

public class Edit_Distance_72 {


	public static int minDistance(String word1, String word2) {
		if (word1 == null || word1.length() == 0) {
			if (word2 != null) {
				return word2.length();
			} else {
				return 0;
			}
		} else if (word2 == null || word2.length() == 0) {
			return word1.length();
/*	checking word1 != null is not needed
			if (word1 != null) {
				return word1.length();
			} 
			else {
				return 0;
			}
*/
		}
		
		Map<String, Integer> memo = new HashMap<String, Integer>();
		return minDistance(word1, word2, word1.length(), word2.length(), memo);
	}
	
	// Note: Have tried to start from beginning instead of from end, it is kind
	// of messy when it ends because the word's length changes.  Much cleaner to
	// start from end.
	private static int minDistance(String word1, String word2, int i, int j,
			Map<String, Integer> memo) {
		if (i == 0 || j == 0 ) {
			return i + j;
		}
		int minSteps = 0;
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(i).append("+").append(j);
		if (memo.containsKey(sb.toString())) {
			return memo.get(sb.toString());
		}
		if (word1.charAt(i-1) == word2.charAt(j-1)) {
			minSteps = minDistance(word1,  word2, i-1, j-1, memo);
		} else {
			minSteps = 1 + min(
				minDistance(word1, word2, i, j-1, memo), 	// insert
				minDistance(word1,  word2, i-1, j, memo), // delete
				minDistance(word1,  word2, i-1, j-1, memo)// replacement
			);
		}
		memo.put(sb.toString(), minSteps);
		return minSteps;
	}


	private static int min (int i, int j, int k) {		
		return Math.min(i, Math.min(j,  k));
	}
	
	public static void main(String[] args) {
		
		int result = minDistance("horse", "ros");		
		System.out.println("Minimum Edition from hose to ros : " + result);

		result = minDistance("intention", "execution");		
		System.out.println("Minimum Edition from intention to execution  : " + result);

	}
}
