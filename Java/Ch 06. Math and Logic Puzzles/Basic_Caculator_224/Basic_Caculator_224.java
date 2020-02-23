package Basic_Caculator_224;

import java.util.*;

/* Leetcode # 224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2

Example 2:

Input: " 2-1 + 2 "
Output: 3

Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23 
*/

public class Basic_Caculator_224 {

	public static int calculate(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		
		int ans = 0;
		
		Deque<Object> stack = new ArrayDeque<>();
		
		int operend = 0;
		int n = 0;
		for (int i = s.length()-1; i>=0; i--) {
			char c = s.charAt(i);
						
			if (Character.isDigit(c)) {
                // Forming the operand - in reverse order.
				// remember we are reading backward as number 123 is "321"
				operend += (int)Math.pow(10, n) * (int)(c - '0');
				n++;
			} else {
				if (n != 0) {
                    // Save the operand on the stack
                    // As we encounter some non-digit.
					stack.push(operend);
				}
				operend = 0;
				n = 0;
				
				if (c == '(') {
					int res = evaluateExpr(stack);					
					// pop the ')'
					stack.pop();
					
                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
					stack.push(res);
				} else if (c == ')' || c == '+' || c == '-') {
                    // Push ')', '+', or '-' onto the stack, skip space
					stack.push(c);
				}
			}			
		}
		
	    //Push the last operand to stack, if any.
		if ( n != 0) {
			stack.push(operend);
		}
		
        // Evaluate any left overs in the stack.
		return evaluateExpr(stack);
	}
	
	private static int evaluateExpr(Deque stack) {
		int ans = 0;
		
		if (!stack.isEmpty()) {
			ans = (int) stack.pop();
		}
		
		while (!stack.isEmpty() && (char)stack.peek() != ')') {
            char sign = (char) stack.pop();

            if (sign == '+') {
                ans += (int) stack.pop();
            } else {
                ans -= (int) stack.pop();
            }			
		}
		
		return ans;
	}
    
	public static void main(String[] args) {
		int result;

		result = calculate("1 + 1");		
		System.out.println("calculate(\"1 + 1\") is (expected: 2): " + result);		

		result = calculate(" 2-1 + 2 ");		
		System.out.println("calculate(\" 2-1 + 2 \") is (expected: 3): " + result);		

		result = calculate("(1+ (4 +5   +2)-3)+(6+8)");		
		System.out.println("calculate(\"(1+ (4 +5   +2)-3)+(6+8)\") is (expected: 23): " + result);		
		
		result = calculate("    99  ");		
		System.out.println("calculate(\"    99  \") is (expected: 99): " + result);		

		result = calculate("123 + (456-123) ");		
		System.out.println("calculate(\"123 + (456-123) \") is (expected: 456): " + result);		
		
	}
}
