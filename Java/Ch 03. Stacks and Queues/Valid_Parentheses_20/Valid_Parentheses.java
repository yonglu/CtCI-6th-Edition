package Valid_Parentheses_20;

import java.io.IOException;
import java.util.Arrays;
import java.util.Stack; 
 
/*
 * 
 * Leetcode # 20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

 */
public class Valid_Parentheses {
    public static boolean isValid(String s) {
         if (s == null || s.length() == 0) {
        	return true;
        }
        
        Stack<Character> stack = new Stack<Character>();        
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (c == '(' || c == '{' || c == '[') {
        		stack.push(c);
        	} else if (c == ')') {
        		if (!stack.isEmpty() && stack.peek() == '(') {
        			stack.pop();
        		} else {
        			return false;
        		}
        	}  else if (c == '}') {
        		if (!stack.isEmpty() && stack.peek() == '{') {
        			stack.pop();
        		} else {
        			return false;
        		}
        	}  else if (c == ']') {
        		if (!stack.isEmpty() && stack.peek() == '[') {
        			stack.pop();
        		} else {
        			return false;
        		}
        	}
        }    
        
        return stack.isEmpty();
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