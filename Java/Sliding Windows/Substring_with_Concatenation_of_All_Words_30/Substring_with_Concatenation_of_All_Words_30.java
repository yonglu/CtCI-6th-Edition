package Substring_with_Concatenation_of_All_Words_30;

import java.util.*;

/*
 * 
 * Leetcode # 30. Substring with Concatenation of All Words


You are given a string, s, and a list of words, words, that are all of the same length. 
Find all starting indices of substring(s) in s that is a concatenation of each word in words 
exactly once and without any intervening characters.
 

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []


*/


public class Substring_with_Concatenation_of_All_Words_30 {

	  public static List<Integer> findWordConcatenation(String str, String[] words) {
		    List<Integer> resultIndices = new ArrayList<Integer>();
            if (str == null || words == null || str.isEmpty() || words.length == 0) {
                return resultIndices;
            }

            Map<String, Integer> wordFrequencyMap = new HashMap<>();
		    for (String word : words)
		      wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);

		    int wordsCount = words.length, wordLength = words[0].length();

		    for (int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
		      Map<String, Integer> wordsSeen = new HashMap<>();
		      for (int j = 0; j < wordsCount; j++) {
		        int nextWordIndex = i + j * wordLength;
		        // get the next word from the string
		        String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
		        if (!wordFrequencyMap.containsKey(word)) // break if we don't need this word
		          break;

		        wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1); // add the word to the 'wordsSeen' map

		        // no need to process further if the word has higher frequency than required 
		        if (wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word, 0))
		          break;

		        if (j + 1 == wordsCount) // store index if we have found all the words
		          resultIndices.add(i);
		      }
		    }

		    return resultIndices;
		  }
    

	public static void main(String[] args) {
		
	    List<Integer> result = findWordConcatenation("catfoxcat", new String[] { "cat", "fox" });
	    System.out.println(result);
	    result = findWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" });
	    System.out.println(result);
	}

}
