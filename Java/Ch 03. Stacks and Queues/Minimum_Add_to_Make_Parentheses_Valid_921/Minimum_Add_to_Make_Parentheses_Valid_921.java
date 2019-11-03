package Minimum_Add_to_Make_Parentheses_Valid_921;

import java.io.IOException;
import java.util.Arrays;
import java.util.Stack; 
 
/*
 * 
 * Leetcode # 921 Minimum Add to Make Parentheses Valid

Given a string S of '(' and ')' parentheses, we add the minimum number of 
parentheses ( '(' or ')', and in any positions ) so that the resulting 
parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or It can be written as AB (A concatenated with B), 
where A and B are valid strings,  or It can be written as (A), where A is a 
valid string.

Given a parentheses string, return the minimum number of parentheses we must 
add to make the resulting string valid. 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4
 

Note:

S.length <= 1000
S only consists of '(' and ')' characters.

 */
public class Minimum_Add_to_Make_Parentheses_Valid_921 {
    public static int minAddToMakeValidWithStack(String S) {
         if (S == null || S.length() == 0) {
        	return 0;
        }
        
        Stack<Character> stack = new Stack<Character>();        
        for (int i = 0; i < S.length(); i++) {
        	char c = S.charAt(i);
        	if (c == '(') {
        		stack.push('(');
        	} else {
        		if (!stack.isEmpty() && stack.peek() == '(') {
        			stack.pop();
        		} else {
        			stack.push(')');
        		}
        	}
        }    
        
        return stack.size();
    }

	
    public static int minAddToMakeValid(String S) {
        int result = 0;
        int leftParentheses = 0;
        int rightParentheses = 0;
        if (S == null || S.length() == 0) {
        	return 0;
        }
        for (int i = 0; i < S.length(); i++) {
        	char c = S.charAt(i);
        	if (c == '(') {
        		leftParentheses++;
        		if (rightParentheses > 0) {
        			result += rightParentheses;
        			rightParentheses = 0;
        		}
        	} else {
        		rightParentheses++;
        		if (leftParentheses == rightParentheses) {
        			leftParentheses = 0;
        			rightParentheses = 0;
        		} else if (leftParentheses < rightParentheses) {
        			result += rightParentheses - leftParentheses;
        			rightParentheses = 0;
        			leftParentheses = 0;
        		} else {
        			leftParentheses -= rightParentheses;
        			rightParentheses = 0;
        		}
        	}
        }    
        
        if (leftParentheses > rightParentheses) {
        	result += (leftParentheses - rightParentheses);
        }
        return result;
    }
    
	public static void main(String[] args) throws IOException {
		int result;		
		
		result = minAddToMakeValid("())");
		System.out.println(result);

		result = minAddToMakeValid("()");
		System.out.println(result);
		
		result = minAddToMakeValid("(((");
		System.out.println(result);
		
		result = minAddToMakeValid("()))((");
		System.out.println(result);
		
		result = minAddToMakeValidWithStack("())");
		System.out.println(result);

		result = minAddToMakeValidWithStack("()");
		System.out.println(result);
		
		result = minAddToMakeValidWithStack("(((");
		System.out.println(result);
		
		result = minAddToMakeValidWithStack("()))((");
		System.out.println(result);
		
	}
}