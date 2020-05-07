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

	// For counting in backtracking or DFS, it is easier to use global count instead of passing the count in parameter.
	// If need to pass the count parameter, remember, count++ will update the count in the current recursive level while
	// count+1 will not update the count in the current recursive level.
	static int loopCountGlobal;
	
	public static List<String> fillingStrings(int k)  {
		List<String> result = new ArrayList<String>();
		
		if (k < 2) {
			return result;
		} else if (k == 2) {
			result.add("aa");
			return result;
		}
		
		char[] charArray = new char[k];
		
		Set<Integer> visited = new HashSet<Integer>(); 
		
		// Setup the charArray with the 2 'a' characters filled in
		for (int i = 0; i < k-1; i++) {
			for (int j = i+1; j < k; j++) {
				charArray[i] = 'a';
				charArray[j] = 'a';
				loopCountGlobal = 0;
				visited.clear();
				visited.add(i);
				visited.add(j);
				// Call helper method to fill in the rest of characters.
				helper(result, charArray, visited, k, 0);				
			}
		}
		
		return result;
	}
	
	private static void helper(List<String> result, char[] charArray, Set<Integer> visited, int k, int start) {
		if (loopCountGlobal == k - 2) {
			result.add(new String(charArray));
			return;
		}
		
		// Use backtracking to fill the rest of characters
		for (int i = start; i < k; i++) {
			if (!visited.contains(i)) {
				// Loop through characters 'b' to 'z'
				// no need to update the visited set as we can only go forward in position.
//				visited.add(i);
				for (int j = 1; j < 26; j++) {
					charArray[i] = (char)('a' + j);
					loopCountGlobal++;
					helper(result, charArray, visited, k, i+1);
					loopCountGlobal--;
				}
//				visited.remove(i);
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
