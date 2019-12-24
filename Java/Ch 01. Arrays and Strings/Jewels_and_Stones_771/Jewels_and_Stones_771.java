package Jewels_and_Stones_771;

import java.util.HashMap;
import java.util.Map;

/*
 * Leetcode # 771. Jewels and Stones

You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  
Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, 
so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3

Example 2:

Input: J = "z", S = "ZZ"
Output: 0

 * 
 */

public class Jewels_and_Stones_771 {
	public static int numJewelsInStones(String jewels, String stones) {
		if (jewels == null || stones == null || jewels.length() == 0 || stones.length() == 0) {
			return 0;
		}
		int ans = 0;
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		char[] stoneArray = stones.toCharArray();
		char[] jewelArray = jewels.toCharArray();
		for (char c : stoneArray) {
			if (countMap.containsKey(c)) {
				countMap.put(c, countMap.get(c) + 1);
			} else {
				countMap.put(c, 1);
			}
		}
		for (char c : jewelArray) {
			if (countMap.containsKey(c)) {
				ans += countMap.get(c);
			}
		}
		
		return ans;
	}


	public static void main(String[] args) {
		
		int i = numJewelsInStones("aA", "aAAbbbb");
		System.out.println(i);
		
		i = numJewelsInStones("z", "ZZ");
		System.out.println(i);
	}

}
