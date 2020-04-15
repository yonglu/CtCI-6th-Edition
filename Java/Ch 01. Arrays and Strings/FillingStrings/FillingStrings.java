package FillingStrings;

import java.util.*;


/*
 *  // f(int n)
// generate and return all possible strings of length n
// containing exactly two a's

// e.g.  f(4)
// returns aabb, aabc, ... gaua...  zzaa

 */

public class FillingStrings {

	public static List<String> fillingStrings(int k)  {
		List<String> result = new ArrayList<String>();
		
		if (k < 2) {
			return result;
		} else if (k == 2) {
			result.add("aa");
			return result;
		}
		
		char[] charArray = new char[k];
		
		// Setup the charArray with the 2 'a' characters filled in
		for (int i = 0; i < k-1; i++) {
			for (int j = i+1; j < k; j++) {
				charArray[i] = 'a';
				charArray[j] = 'a';
				// Call helper method to fill in the rest of characters.
				helper(result, charArray, i, j, 0, k, 0);				
			}
		}
		
		return result;
	}
	
	private static void helper(List<String> result, char[] charArray, int firstIndex, int secondIndex, int loopCount, int k, int start) {
		if (loopCount == k - 2) {
			result.add(new String(charArray));
			return;
		}
		
		// Use backtracking to fill the rest of characters
		for (int i = start; i < k; i++) {
			if (i != firstIndex && i != secondIndex) {
				// Loop through characters 'b' to 'z'
				for (int j = 1; j < 26; j++) {
					charArray[i] = (char)('a' + j);
					helper(result, charArray, firstIndex, secondIndex, loopCount+1, k, i+1);
				}
				loopCount++;
			}
		}
		
		return;
	}

	public static void main(String[] args) {
		
		int n = 0;
		
		List<String> ans = fillingStrings(4);	
		System.out.println("Filling Strings with 4: ");
		for (String s : ans) {
			System.out.print(s + " ");
			n++;
			if (n > 30) {
				System.out.println("");
				n = 0;
			}
		}

		
	}
}
