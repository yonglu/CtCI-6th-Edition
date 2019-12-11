package Remove_Invalid_Parentheses_301;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public static List<String> removeInvalidParentheses(String s) {
    	
    	List<String> lists = new ArrayList<String>();
    	
    	if (s == null || s.length() == 0) {
    		return lists;
    	}
    	
    	// if we have more '(' than ')', then removing 
    	
    	int index = isValid(s);
    	if (index == -1) {
    		
    	}
    	
    	return lists;
    	
    }

    private static int isValid(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	
    	boolean isValidFlag = true;
    	int i = 0;
    	for (i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (c == '(' ) {
        		stack.push(c);
        	} else if (c == ')') {
        		if (!stack.isEmpty() && stack.peek() == '(') {
        			stack.pop();
        		} else {
        			isValidFlag = false;
        			break;
        		}
        	}    		
    	}
    	
    	if (isValidFlag) {
    		return -1;
    	} else {
    		return i;
    	}
    }
    
    // remove the invalid parenthesees before index.  This can result a list of new string
    private static List<String> removeInvalidParentheses(String s, int index) {
    	List<String> lists = new ArrayList<String>();
    	
    	if (index > s.length()) {
    		index = s.length();
    	}
    	    	
    	for (int i = 0; i < index; i++) {
    		char c = s.charAt(i);
    		if (c == ')') {
    			lists.add(s.substring(0, i) + s.substring(i+1));
    		}
    	}
    	
    	return lists;
    }
    
	public static void main(String[] args) throws IOException {
		boolean result;		
		
		result = isValid("()");
		System.out.println(result);

		result = isValid("(){}[]");
		System.out.println(result);
		
		result = isValid("(]");
		System.out.println(result);
		
		result = isValid("([)]");
		System.out.println(result);
		
		result = isValid("([])");
		System.out.println(result);
	}
}