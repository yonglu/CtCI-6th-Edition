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
	
	public static List<String> generateParenthesisDFS(int n) {
		List<String> lists = new ArrayList<String>();

		generateParenthesisDFS(lists, new StringBuilder(), 0, 0 , n);
		
		return lists;		
	}

	private static void generateParenthesisDFS(List<String> lists,  
			StringBuilder tempResult, int lpCount, int rpCount, int n) {
		if (lpCount < rpCount) {
			return;
		}

		if (lpCount == n && rpCount == n) {
			lists.add(new String(tempResult.toString()));
			return;
		}
		
		if (lpCount < n) {
			tempResult.append('(');
			generateParenthesisDFS(lists, tempResult, lpCount+1, rpCount, n);
			tempResult.deleteCharAt(tempResult.length()-1);
		}
		
		if (rpCount < n) {
			tempResult.append(')');
			generateParenthesisDFS(lists, tempResult, lpCount, rpCount+1, n);
			tempResult.deleteCharAt(tempResult.length()-1);
		}
		
		return;
	}	
	public static List<String> generateParenthesisBacktracking(int n) {
		
		List<String> lists = new ArrayList<String>();
		
		if (n <= 0) return lists;
		
		StringBuilder inputSB = new StringBuilder();
		for (int i = 0; i < n; i++) {
			inputSB.append('(');
			inputSB.append(')');
		}
		char[] input = inputSB.toString().toCharArray();
		Arrays.sort(input);

		boolean[] visited = new boolean[input.length];
		
		
		generateParenthesisBacktracking(lists, visited, new StringBuilder(), input);
				
		return lists;
	}
	
	private static void generateParenthesisBacktracking(List<String> lists, boolean[] visited, 
			StringBuilder tempResult, char[] input) {
		if (tempResult.length() == input.length && doneParenthesis(tempResult)) {
			lists.add(new String(tempResult.toString()));
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			// Short circuit if we are going to have more right parenthesis
			if (!checkIfCanAdd(tempResult, input[i])) {
				continue;
			}
			if (visited[i] || (i > 0 && input[i] == input[i-1] && visited[i-1])) {
				continue;
			}
			visited[i] = true;
			tempResult.append(input[i]);
			generateParenthesisBacktracking(lists, visited, tempResult, input);
			tempResult.deleteCharAt(tempResult.length() - 1);
			visited[i] = false;
		}
		return;
	}
	
	// can add if it is not right parenthesis or if we have more left parenthesis than right
	private static boolean checkIfCanAdd(StringBuilder tempResult, char next) {
		if (next != ')') {
			return true;
		}
		
		int countLP = 0, countRP = 0;
		for (char c : tempResult.toString().toCharArray()) {
			if ( c == '(') {
				countLP++;
			} else {
				countRP++;
			}
		}
		return (countLP>countRP);
	}

	// check if we finish
	private static boolean doneParenthesis(StringBuilder tempResult) {
		boolean result = false;
		int count = 0;
		for (char c : tempResult.toString().toCharArray()) {
			if ( c == '(') {
				count++;
			}
		}
		if (count == tempResult.length()/2) {
			result = true;
		}		
		return result;
	}

	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		List<String> results = generateParenthesisBacktracking(4);
		System.out.println("Generate Parenthesis by Backtracking: 4");
		for (String str : results) {
			System.out.println(str);
		}
		System.out.println(System.currentTimeMillis() - currentTime);
		
		currentTime = System.currentTimeMillis();
		results = generateParenthesisDFS(4);
		System.out.println("Generate Parenthesis by DFS: 4");
		for (String str : results) {
			System.out.println(str);
		}
		System.out.println(System.currentTimeMillis() - currentTime);
		
		System.out.println("Backtracking is slower!!");
		System.out.println("Because it is a generalization form of DFS where it");
		System.out.println("branches on all combinations (unvisited nodes in '((()))',");
		System.out.println("while DFS will only branches on '(' and ')'");
	}
}
