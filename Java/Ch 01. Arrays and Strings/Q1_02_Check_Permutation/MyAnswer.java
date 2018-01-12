package Q1_02_Check_Permutation;

import java.util.Arrays;

public class MyAnswer {	
	
	public static boolean permutation(final String s, final String t) {
		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}
		return sort(s).equals(sort(t));
	}	
	
	private static String sort (final String s) {
		char[] charS = s.toCharArray();
		Arrays.sort(charS);
		return new String(charS);
	}
	
	public static boolean permutation2(final String s, final String t) {
		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}
		int [] sFlag = new int[128];
		char[] charS = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			sFlag[charS[i]]++;
		}		
		char[] charT = t.toCharArray();
		for (int i = 0; i < t.length(); i++) {			
			sFlag[charT[i]]--;
			if (sFlag[charT[i]] < 0) {
				return false;
			}
		}

		return true;
	}	
	
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = permutation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram);
		}
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = permutation2(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram);
		}

	}
}
