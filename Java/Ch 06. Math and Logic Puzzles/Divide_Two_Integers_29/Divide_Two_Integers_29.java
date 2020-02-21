package Divide_Two_Integers_29;

import java.util.*;

/* Leetcode # 29. Divide Two Integers

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3

Example 2:

Input: dividend = 7, divisor = -3
Output: -2

Note:

    Both dividend and divisor will be 32-bit signed integers.
    The divisor will never be 0.
    Assume we are dealing with an environment which could only store integers within the 32-bit 
    signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your 
    function returns 231 − 1 when the division result overflows.
 
*/

public class Divide_Two_Integers_29 {

	public static int divide(int dividend, int divisor) {
		// Not checking a lot of special cases as in divide2 as that time limit exceed as the limit is 1ms.
		if (divisor == 0) {
			return Integer.MAX_VALUE;
		}
		if (divisor == -1 && dividend == Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		
		long pDividend = Math.abs((long) dividend);
		long pDivisor = Math.abs((long) divisor);
		// it seems should work with int, but time limit exceed
//		int pDividend = Math.abs( dividend);
//		int pDivisor = Math.abs( divisor);
		
		int result = 0;
		while (pDividend >= pDivisor) {
			int shift = 1;
			while (pDividend >= (pDivisor << shift)) {
				shift++;
			}
			shift--;

			result += 1 << shift;
			pDividend -= pDivisor << shift;

		}

		if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) {
			return result;
		} else {
			return -result;
		}
	}
	
    public static int divide2(int dividend, int divisor) {
    	int result = 0;
    	if (dividend == 0) {
    		return 0;
    	} else if (divisor == 0) {
    		return -1;
        // Mistake 3: need to put in the special case if divisor = 1 or -1 otherwise time limit exceeded
    	} else if (divisor == 1 ) {
    		return dividend;
        // Mistake 2: Need to handle this special edge case
    	} else if (divisor == -1 && dividend == Integer.MIN_VALUE) {
    		return Integer.MAX_VALUE;
    	} else if (divisor == -1 ) {
    		return -dividend;
    	} else if ( (dividend + divisor) == 0) {
            return -1;
        // Mistakes 1: in not consider it both dividend and divisor are negative number
    	} else if (Math.abs(dividend) < Math.abs(divisor)) {
    		return 0;
    	} else if ( dividend == divisor) {
    		return 1;
        }
    	
    	int pDividend = Math.abs(dividend);
    	int pDivisor = Math.abs(divisor);
    	
    	while (pDividend >= pDivisor) {
    		int shift = 1;
    		while (pDividend >= (pDivisor << shift)) {
    			shift++;
    		}
    		shift--;
    		result += 1 << shift;
    		pDividend -= pDivisor << shift;
    	}
    	
    	if ((dividend > 0 && divisor < 0) ||
    		(dividend < 0 && divisor > 0)) {
    		result = result * -1;
    	}
    	
    	return result;
    }
    
    
	public static void main(String[] args) {
		int result;

		result = divide(10, 3);		
		System.out.println("divide(10, 3) is : " + result);		

		result = divide(7, -3);		
		System.out.println("divide(7, -3) : " + result);		

		result = divide(-4, 4);		
		System.out.println("divide(-4, 4) is : " + result);		

		result = divide(-4, -1);		
		System.out.println("divide(-4, -1) is : " + result);		

		result = divide(-2147483648, -1);		
		System.out.println("divide(-2147483648, -1) is : " + result);		

		result = divide(3, 4);		
		System.out.println("divide(3, 4) is : " + result);		

		result = divide(0, 4);		
		System.out.println("divide(0, 4) is : " + result);		

		result = divide(4, 0);		
		System.out.println("divide(4, 0) is : " + result);		
	
	}
}
