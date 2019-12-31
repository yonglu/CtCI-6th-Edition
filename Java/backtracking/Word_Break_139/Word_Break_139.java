package Word_Break_139;

import java.util.*;


/*
 * Leetcode # 139. Word Break

Given a non-empty string s and a dictionary wordDict containing a list of 
non-empty words, determine if s can be segmented into a space-separated 
sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen 
apple". Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

public class Word_Break_139 {

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict.isEmpty()) {
        	return false;
        }
        
        Set<Integer> memo = new HashSet<Integer>();
        return wordBreak(memo, s, wordDict, 0);       
     }
   
    public static boolean wordBreak(Set<Integer> memo, String s, List<String> wordDict, int start) {
         if (start >= s.length()) {
        	return true;
        }
        
         int i = start;
         
         // Note here we don't need to do the for loop since we only need to start
         // from beginning no need to start the word break in the middle.
         while (i < s.length()) {
//        for (int i = start; i < s.length(); i++) {
         		String word = s.substring(start, i+1);
        		if (wordDict.contains(word)) {
        			// if we know it failed before, continue
        			if (memo.contains(i)) {
        				i++; // comment out if doing the for loop
        				continue;
        			}
        			
        			// if finish and find solution, return true to 
        			// short circuit it
        			if (wordBreak(memo, s, wordDict, i+1)) {
        				return true;
        			}
        			
        			// remember the failure
        			memo.add(i);
        		}
        		
        		i++;
        }
        
    	return false;
    }
    
	public static void main(String[] args) {
		boolean result;
		List<String> wordDict = new ArrayList<String>();
		wordDict.add("lee");
		wordDict.add("leet");
		wordDict.add("code");
		wordDict.add("and");
		wordDict.add("android");
		wordDict.add("apple");
		wordDict.add("pen");
		wordDict.add("cat");
		wordDict.add("cats");
		wordDict.add("dog");
		wordDict.add("sand");
		
		result = wordBreak("leetcode", wordDict);
		System.out.println(result); // expect true

		result = wordBreak("leetandroi", wordDict);
		System.out.println(result); // expect false

		result = wordBreak("leetandroid", wordDict);
		System.out.println(result); // expect true
		
		result = wordBreak("applepenapple", wordDict);
		System.out.println(result); // expect true
		
		result = wordBreak("catsandog", wordDict);
		System.out.println(result); // expect false
	}
}
