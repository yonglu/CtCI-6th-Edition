package Q8_08_Permutations_With_Dups;

import java.util.*;


public class MyAnswer {

   // This is just take the solution in Q8_07_Permutations_Without_Dups QuestionA.java, and
   // change the result as ArrayList<String> to HashSet<String> to filter out the dups.
   // There are better and more efficient way to do it with map to count the dups.  
   // See Question.java in this package.
	public static Set<String> getPermsWithDups(String str) {
		if (str == null) {
			return null;
		}
		Set<String> permutations = new HashSet<String>();
		if (str.length() == 0) { // base case
			permutations.add("");
			return permutations;
		}
	            
		char first = str.charAt(0); // get the first character
		String remainder = str.substring(1); // remove the first character
		Set<String> words = getPermsWithDups(remainder);
		for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				String s = insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}
	
	public static String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}
	
	public static void main(String[] args) {
		Set<String> list = getPermsWithDups("abb");
		System.out.println("There are " + list.size() + " permutations.");
		for (String s : list) {
			System.out.println(s);
		}
	}

}
