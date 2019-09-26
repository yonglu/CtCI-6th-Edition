package GenerateParentheses_leetcode22;

import java.util.*;

/*
 * Leetcode # 22
 * https://leetcode.com/problems/generate-parentheses/
 * 
 * Given n pairs of parentheses, write a function to generate all combinations of 
 * well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * [
 *  "((()))",
 *  "(()())",
 *  "(())()",
 *  "()(())",
 *  "()()()"
 * ]
 */

public class GenerateParentheses {

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
		List<String> results = generateParenthesisBacktracking(3);
		System.out.println("Generate Parenthesis: 3");
		for (String str : results) {
			System.out.println(str);

		}
	}
}
