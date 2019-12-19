package Palindrome_Pairs_336;

import java.util.*;


/* Leetcode #336. Palindrome Pairs

Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]

 */

/*
 * https://zhuhan0.blogspot.com/2017/07/leetcode-336-palindrome-pairs.html
 * http://buttercola.blogspot.com/2016/06/leetcode-336-palindrome-pairs.html
 * https://www.cnblogs.com/Dylan-Java-NYC/p/6359809.html
 */

public class Palindrome_Pairs_336 {


	public static List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> ans = new ArrayList<>();

		if (words == null || words.length == 0) {
			return ans;
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}

		Map<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			hm.put(reverse(words[i]), i);
		}

		for (int i = 0; i < words.length; i++) {
			// j is word[i].length() because it is used as endIndex for substring 
			// and the endIndex is exclusive
			for (int j = 0; j <= words[i].length(); j++) { 
				String left = words[i].substring(0, j);
				String right = words[i].substring(j);

				if (isPalindrome(left)) {
					if (hm.containsKey(right) && hm.get(right) != i) {
						List<Integer> item = new ArrayList<Integer>();
						item.add(hm.get(right));
						item.add(i);
						ans.add(item);
					}
				}
				if (isPalindrome(right)) {
					if (hm.containsKey(left) && hm.get(left) != i && right.length() != 0) {
						// Addition check is right.length() != 0
						// Or will add duplicate results, like ["abc", "cba"]
						List<Integer> item = new ArrayList<Integer>();
						item.add(i);
						item.add(hm.get(left));
						ans.add(item);
					}
				}
			}
		}
		return ans;
	}

	private static String reverse(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int l = 0;
		int r = s.length() - 1;
		char[] cArray = s.toCharArray();
		while (l < r) {
			char temp = cArray[l];
			cArray[l] = cArray[r];
			cArray[r] = temp;
			l++;
			r--;
		}
		
		return new String(cArray); 
	}
	
    private static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
         
        int start = 0;
        int end = s.length() - 1;
         
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
         
        return true;
    }	
	
	public static void main(String[] args) {
		
		List<List<Integer>> ans = palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"});	
		System.out.println("Palindrome Pairs of [\"abcd\",\"dcba\",\"lls\",\"s\",\"sssll\"] : ");
		for (List<Integer> list : ans) {
			System.out.print("[");
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.print("], ");
		}

		System.out.println("");
		System.out.println("");

		ans = palindromePairs(new String[] {"bat","tab","cat"});	
		System.out.println("Palindrome Pairs of [\"bat\",\"tab\",\"cat\"] : ");
		for (List<Integer> list : ans) {
			System.out.print("[");
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.print("], ");
		}
		
	}
}
