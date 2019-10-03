package Decode_Ways_91;

import java.util.*;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

/*
 * Leetcode # 91. Decode Ways
 * 
 * https://leetcode.com/problems/decode-ways/
 * 
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

public class Decode_Ways_91 {


//	public static Map<String, String> decodeMap;
//	static {
//	    decodeMap = new HashMap<>();
//	    decodeMap.put("1", "A");
//	    decodeMap.put("2", "B");
//	    decodeMap.put("3", "C");
//	    decodeMap.put("4", "D");
//	    decodeMap.put("5", "E");
//	    decodeMap.put("6", "F");
//	    decodeMap.put("7", "G");
//	    decodeMap.put("8", "H");
//	    decodeMap.put("9", "I");
//	    decodeMap.put("10", "J");
//	    decodeMap.put("11", "K");
//	    decodeMap.put("12", "L");
//	    decodeMap.put("13", "M");
//	    decodeMap.put("14", "N");
//	    decodeMap.put("15", "O");
//	    decodeMap.put("16", "P");
//	    decodeMap.put("17", "Q");
//	    decodeMap.put("18", "R");
//	    decodeMap.put("19", "S");
//	    decodeMap.put("20", "T");
//	    decodeMap.put("21", "U");
//	    decodeMap.put("22", "V");
//	    decodeMap.put("23", "W");
//	    decodeMap.put("24", "X");
//	    decodeMap.put("25", "Y");
//	    decodeMap.put("26", "Z");
//	}
	
	public static int numDecodings(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		return numDecodings(s, memo, 1, 0) + numDecodings(s, memo, 2, 0);
	}

	private static int numDecodings(String s, Map<Integer, Integer> memo, 
			int len, int start) {
		if ((start + len) >  s.length()) {
			return 0;
		}
		
		String key = s.substring(start, start + len);
		if (Integer.valueOf(key) > 26 || Integer.valueOf(key) <= 0) {
			return 0;
		} 
		
		// Forgot to handle case of 0.  The leetcode expects return if there is 0 in the string.
		// Should ask question how to handle zero too
		if (key.charAt(0) == '0') {
			return 0;
		}
		
		// if we reach the end, that mean a valid solution, return 1.
		if ((start + len) ==  s.length()) {
			return 1;
		}
		
		int result = 0;
		if (memo.containsKey(start+len)) {
			result = memo.get(start+len);
		} else {
			result = numDecodings(s, memo, 1, start + len) + numDecodings(s, memo, 2, start + len);			
			memo.put(start+len, result);
		}	

		return result;
	}
	
	public static void main(String[] args) {
		
		int result = numDecodings(new String("12"));		
		System.out.println("numDecodings \"12\" : " + result);

		result = numDecodings(new String("238"));		
		System.out.println("numDecodings \"238\" : " + result);
		
		result = numDecodings(new String("226"));		
		System.out.println("numDecodings \"226\" : " + result);
		
		result = numDecodings(new String("12345"));		
		System.out.println("numDecodings \"12345\" : " + result);		
		
		result = numDecodings(new String("0"));		
		System.out.println("numDecodings \"0\" : " + result);		

		result = numDecodings(new String("01"));		
		System.out.println("numDecodings \"01\" : " + result);	// expect 0	
		
		result = numDecodings(new String("101"));		
		System.out.println("numDecodings \"101\" : " + result);	// expect 1
		
	}
}
