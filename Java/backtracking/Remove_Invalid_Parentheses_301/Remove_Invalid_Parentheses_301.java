package Remove_Invalid_Parentheses_301;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * 
 * Leetcode # 301. Remove Invalid Parentheses


Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:

Input: ")("
Output: [""]

 */
public class Remove_Invalid_Parentheses_301 {

	private static Set<String> validExpressions = new HashSet<String>();

	public static List<String> removeInvalidParentheses(String s) {

		int leftRem = 0, rightRem = 0;

		// First, we find out the number of misplaced left and right parentheses.
		for (int i = 0; i < s.length(); i++) {

			// Simply record the left one.
			if (s.charAt(i) == '(') {
				leftRem++;
			} else if (s.charAt(i) == ')') {
				
				// If we don't have a matching left, then this is a misplaced right, record it.
				if (leftRem == 0) {
					rightRem += 1;
				} else if (leftRem > 0) {
					// Decrement count of left parentheses because we have found a right
					// which CAN be a matching one for a left.
					leftRem -= 1;
				}
			}
		}

        dfs(s, 0, validExpressions, new StringBuilder(), leftRem, rightRem, 0);
        
        // dfs is much easier to understand!!
//		recurse(s, 0, 0, 0, leftRem, rightRem, new StringBuilder());
        
		return new ArrayList<String>(validExpressions);
	}

	private static void dfs(String s, int i, Set<String> res, StringBuilder sb, int leftRem, int rightRem, int open) {
		int len = sb.length();// decision point
		
		if (open < 0 || leftRem < 0 || rightRem < 0)
			return;// leftRem rightRem limit the max removal boundary,
		// else will return all possibilities["","()()","()","(())","()()()","(())()"]
		if (i == s.length()) {
			if (open == 0)
				res.add(sb.toString());// back tracking till the full length
			return;
		} else {
			// we don't need for loop since no ordering(not like subsets,permu problem)
			char c = s.charAt(i);
			if (c == '(') {// order matters here, once append c to sb, sb contains c when backtracking
				dfs(s, i + 1, res, sb, leftRem - 1, rightRem, open); // remove '('
				dfs(s, i + 1, res, sb.append('('), leftRem, rightRem, open + 1); // use '('
			} else if (c == ')') {
				dfs(s, i + 1, res, sb, leftRem, rightRem - 1, open); // remove ')'
				dfs(s, i + 1, res, sb.append(')'), leftRem, rightRem, open - 1); // use ')'
			} else {
				dfs(s, i + 1, res, sb.append(c), leftRem, rightRem, open);// append non '(',')' char
			}
		}
		sb.setLength(len);// reset back to decision point -- remove last char of sb
	}
	
	/*
	 * index - represents the current character that we have to process in the original string.
	 * left_count - represents the number of left parentheses that have been added to the 
	 * 		expression we are building.
	 * right_count - represents the number of right parentheses that have been added to the 
	 * 		expression we are building.
	 * left_rem is the number of left parentheses that remain to be removed.
	 * right_rem represents the number of right parentheses that remain to be removed. 
	 * 		Overall, for the final expression to be valid, left_rem == 0 and right_rem == 0.
	 */
	
	/*
	 * Solution: https://leetcode.com/articles/remove-invalid-parentheses/
	 */
	private static void recurse(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem,
			StringBuilder expression) {
		
		// If we reached the end of the string, just check if the resulting expression
		// is valid or not and also if we have removed the total number of left and right
		// parentheses that we should have removed.
		if (index == s.length()) {
			if (leftRem == 0 && rightRem == 0) {
				validExpressions.add(expression.toString());
			}

		} else {
			char character = s.charAt(index);

			int length = expression.length();

			// The discard case. Note that here we have our pruning condition.
			// We don't recurse if the remaining count for that parenthesis is == 0.
			if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
				recurse(s, index + 1, leftCount, rightCount, leftRem - (character == '(' ? 1 : 0),
						rightRem - (character == ')' ? 1 : 0), expression);
			}

			// Note: this is a special case for backtracking recursion that, we have 2 paths: skip/discard or
			// include.  For the skip/discard case, no need to backtrack.
			expression.append(character);

			// Simply recurse one step further if the current character is not a
			// parenthesis.
			if (character != '(' && character != ')') {

				recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);

			} else if (character == '(') {

				// Consider an opening bracket.
				recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);

			// don't recursive if it is '}' and the leftCount <= rightCount because it is
			// invalid expression, no need to go further.
			} else if (rightCount < leftCount) {

				// Consider a closing bracket.
				recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
			}

			// Delete for backtracking.
			expression.deleteCharAt(length);
		}
	}

	public static void main(String[] args) throws IOException {
		List<String> results;

		results = removeInvalidParentheses("()())()");
		System.out.println("removeInvalidParentheses(\"()())()\")");
		for (String result : results) {
			System.out.println(result);
		}

		validExpressions.clear();
		results = removeInvalidParentheses("(a)())()");
		System.out.println("removeInvalidParentheses(\"(a)())()\")");
		for (String result : results) {
			System.out.println(result);
		}

		validExpressions.clear();
		results = removeInvalidParentheses(")(");
		System.out.println("removeInvalidParentheses(\")(\")");
		for (String result : results) {
			System.out.println(result);
		}		
	}
}