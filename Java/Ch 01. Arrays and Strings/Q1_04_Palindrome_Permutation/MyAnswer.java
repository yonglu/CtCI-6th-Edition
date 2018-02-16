package Q1_04_Palindrome_Permutation;

//Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
//A palindrome is a word or phrase that is the same forwards and backwards. A permutation
//is a rea rrangement of letters. The palindrome does not need to be limited to just dictionary words.
//EXAMPLE
//Input: Tact Coa
//Output: True (permutations: "taco cat". "atco cta". etc.)

public class MyAnswer {	
	// TODO
	// Two issues in my implementation:
	// 1. Need to only count the Alphabetic
	// 2. May need to convert all to lower case as Palindrome ignore case.
	// 3. In the solution, Character.getNumericValue() returns the same value for uppercase and lowercase character.
	public static boolean isPermutationOfPalindrome(String phrase) {
		if (phrase == null) {
			return false;
		}
		int[] charTable = new int[128];
		
		char[] charPhrase = phrase.toLowerCase().toCharArray();
		for (int i = 0; i < charPhrase.length; i++) {
			if (Character.isAlphabetic(charPhrase[i])) {
				charTable[charPhrase[i]]++;
			}
		}
		int oddCount = 0;
		for (int i = 0; i < charTable.length; i++) {
			if ((charTable[i]%2) == 1) {
				oddCount++;
				if (oddCount > 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isPermutationOfPalindrome2(String phrase) {
		if (phrase == null) {
			return false;
		}
		int[] charTable = new int[26];
		
		char[] charPhrase = phrase.toCharArray();
		
		// Note: Character.getNumericValue() returns the same value for uppercase and lowercase character.
		// range 10 to 35
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		
		for (int i = 0; i < charPhrase.length; i++) {
			int numValue = Character.getNumericValue(charPhrase[i]);
			if (a <= numValue && numValue <= z) {
				charTable[numValue-a]++;
			}
		}
		int oddCount = 0;
		for (int i = 0; i < charTable.length; i++) {
			if ((charTable[i]%2) == 1) {
				oddCount++;
				if (oddCount > 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String pali = "Rats live on no evil star";
		System.out.println(isPermutationOfPalindrome(pali));
		
		System.out.println(isPermutationOfPalindrome2(pali));
	}


}
