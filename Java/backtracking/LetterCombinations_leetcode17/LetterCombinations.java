package LetterCombinations_leetcode17;

import java.util.*;

/*
 * Leetcode # 17
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 
 * Given a string containing digits from 2-9 inclusive, return all possible letter 
 * combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below. 
 * Note that 1 does not map to any letters.
 *
 * Example:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 */

public class LetterCombinations {

    private static final String[] KEYS = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv",
    "wxyz" };
	
    public static List<String> letterCombinations(String digits) {
    	
    	List<String> lists = new ArrayList<String>();  	
    	if (digits == null || digits.isEmpty()) {
//    		throw new IllegalArgumentException();
    		return lists;
    	}
    	
    	// mention that you assume the digits string are valid,
    	// otherwise will check
    	
    	letterCombinations(lists, new StringBuilder(), digits.toCharArray(), 0);
    	
    	return lists;
    }
    
	public static void letterCombinations(List<String> lists, StringBuilder tempSB, 
			char[] digitChars, int start) {
		
		if (start == digitChars.length) {
			lists.add(tempSB.toString());
			return;
		}
		
		// handle the digit of 1 or 0
		// no need for the original question
		if ( digitChars[start] == '0' || digitChars[start] == '1') {
			letterCombinations(lists, tempSB, digitChars, start+1);		
			return;
		}
		
		String values = KEYS[digitChars[start] - '0'];
		
		for (int i = 0; i<values.length(); i++) {
			tempSB.append(values.charAt(i));
			letterCombinations(lists, tempSB, digitChars, start+1);
			tempSB.deleteCharAt(tempSB.length()-1);
		}
		
		return;		
	}


	public static void main(String[] args) {
		List<String> results = letterCombinations("");
		System.out.println("Letter Combinations of empty string:");
		for (String str : results) {
			System.out.println(str);
		}

		results = letterCombinations("23");
		System.out.println("Letter Combinations 23:");
		for (String str : results) {
			System.out.println(str);
		}
		
		results = letterCombinations("2066508698");
		System.out.println("Letter Combinations 2066508698:");
		for (String str : results) {
			System.out.println(str);
		}

	}
}
