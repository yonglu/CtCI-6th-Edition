package Palindrome_Pairs_336;

import java.util.*;


/* Leetcode #336. Palindrome Pairs

Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]

 */

/*
 * https://zhuhan0.blogspot.com/2017/07/leetcode-336-palindrome-pairs.html
 * http://buttercola.blogspot.com/2016/06/leetcode-336-palindrome-pairs.html
 * https://www.cnblogs.com/Dylan-Java-NYC/p/6359809.html
 */

public class Palindrome_Pairs_336 {


	public static List<List<Integer>> palindromePairs(String[] words)   {
		List<List<Integer>> ans = new ArrayList<>();
		
		if (words == null || words.length == 0 ) {
			return ans;
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		
		return ans;		
	}

	public static void main(String[] args) {
		
		List<List<Integer>> ans = palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"});	
		System.out.println("Palindrome Pairs of [\"abcd\",\"dcba\",\"lls\",\"s\",\"sssll\"] : ");
		for (List<Integer> list : ans) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}

	}
}
