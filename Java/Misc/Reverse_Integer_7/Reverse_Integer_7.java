package Reverse_Integer_7;

import java.util.*;

/* Leetcode # 7. Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321

Example 2:

Input: -123
Output: -321

Example 3:

Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer 
range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 
when the reversed integer overflows.
 
*/

public class Reverse_Integer_7 {

    public static int reverse(int x) {
    	boolean isNegative = false;
    	
    	if (x < 0) {
    		isNegative = true;
    		x = -x;
    	}
    	
    	long ans = 0;    	
    	while (x > 0) {
    		int rem = x%10;
    		ans = ans * 10 + rem;
    		x = x/10;
    	}

    	if (isNegative) {
    		ans = -ans;
    	}
    	
    	// check if ans is overflow
    	if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
    		return 0;
    	}
    	
    	return (int)ans;
    }
    
    /*
     * Use Stack to reverse the digit.  Not necessary here but remember we can use Stack to reverse stuffs
     */
    public static int reverse2(int x) {
    	boolean isNegative = false;
    	
    	if (x < 0) {
    		isNegative = true;
    		x = -x;
    	}
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	while (x > 0) {
    		int rem = x%10;
    		stack.push(rem);
    		x = x/10;
    	}
    	
    	long ans = 0;
    	
    	// Note: mistake in use stack.size() in the loop, didn't count for that the stack.size() changes
    	// after pop.
    	int length = stack.size();
    	for (int i = 0; i < length; i++) {
    		ans += stack.pop() * Math.pow(10,i);
    	}
    	
    	if (isNegative) {
    		ans = -ans;
    	}
    	
    	// check if ans is overflow
    	if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
    		return 0;
    	}
    	
    	return (int)ans;
    }
    
    
	public static void main(String[] args) {
		int result;

		result = reverse(123);		
		System.out.println("Reverse integer of 123 is : " + result);		

		result = reverse(-321);		
		System.out.println("Reverse integer of -321 is : " + result);		

		result = reverse(1234567888);		
		System.out.println("Reverse integer of 1234567888 is : " + result);		

		result = reverse(-1234567888);		
		System.out.println("Reverse integer of -1234567888 is : " + result);		
	}
}
