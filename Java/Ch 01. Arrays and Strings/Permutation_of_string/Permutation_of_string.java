package Permutation_of_string;

import java.io.IOException;
import java.util.Arrays; 
 
public class Permutation_of_string {
	public static boolean checkInclusion(String s1, String s2) {
		if (s1 != null && s2 != null && (s1.length() <= s2.length())) {
			int[] s1Map = new int[128]; // ASCII
			for (int j = 0; j < s1.length(); j++) {
				if (s1Map[s1.charAt(j)] > 0) {
					s1Map[s1.charAt(j)] = s1Map[s1.charAt(j)] + 1;
				} else {
					s1Map[s1.charAt(j)] = 1;
				}
			}
			System.out.println(Arrays.toString(s1Map));
			
			// Check in the sliding windows of s1 length in s2 string
			for (int i = 0; i <= s2.length() - s1.length(); i++) {
				if (checkIfPermutation(s1Map, s2.substring(i, i + s1.length()))) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean checkIfPermutation(int[] s1Map, String s2Part) {
		System.out.println("Check this part of s2: " + s2Part);
		int matchCount = 0;
		int[] tempMap = Arrays.copyOf(s1Map, s1Map.length);
		for (int i = 0; i < s2Part.length(); i++) {
			if (tempMap[s2Part.charAt(i)] <= 0 ) {
				return false;
			}
			tempMap[s2Part.charAt(i)] = tempMap[s2Part.charAt(i)] - 1;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		boolean ret = checkInclusion(null, "abc");
		System.out.println(ret);

		ret = checkInclusion("abcd", "abc");
		System.out.println(ret);

		ret = checkInclusion("ba", "abc");
		System.out.println(ret);
	
		ret = checkInclusion("ccc", "ccccbbbbaaaa");
		System.out.println(ret);

		ret = checkInclusion("uwigd", "werhwekurhkdsjfgiuw");
		System.out.println(ret);

		ret = checkInclusion("uwig", "werhwekurhkdsjfgiuw");
		System.out.println(ret);
		
		ret = checkInclusion("abcdxabcee", "abcdeabcdx");
		System.out.println(ret);
		
		ret = checkInclusion("abcdxabcde", "abcdeabcdx");
		System.out.println(ret);
	}
}