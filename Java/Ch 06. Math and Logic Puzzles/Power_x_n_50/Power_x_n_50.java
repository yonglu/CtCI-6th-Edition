package Power_x_n_50;

import java.util.*;

/* Leetcode # 50. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (x ^ n).

Example 1:

Input: 2.00000, 10
Output: 1024.00000

Example 2:

Input: 2.10000, 3
Output: 9.26100

Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2 ^ -2 = 1/22 = 1/4 = 0.25
 
*/

public class Power_x_n_50 {

	public static double myPow(double x, int n) {
		double result = 0.0;
		boolean isNeg = false;
		
		// Convert int n to long longN to handle the overflow edge cases
		long longN = (long)n;
		if (longN < 0) {
			isNeg = true;
			longN = -longN;
		}		
		result = myPow2(x, longN);
		
/*  
  		// Handle Integer.MIN_VALUE edge case, it's because the absolute value of Integer.MIN_VALUE is 
		// Integer.MIN_VALUE, due to the limitations of bits. To compensate for this just add an if 
		// statement handling Integer.MIN_VALUE.
		// https://stackoverflow.com/questions/18565485/why-is-absolute-of-integer-min-value-equivalent-to-integer-min-value
		if (n < 0) {
			isNeg = true;
			if (n == Integer.MIN_VALUE) {
				n = Integer.MAX_VALUE - 1;
			} else {
				n = -n;
			}
		}		
		result = myPow2(x, n);
*/		
		if (isNeg) {
			result = 1/result;
		}

		return result;
//		int decimalPlace = 6;
//	    double scale = Math.pow(10, decimalPlace);
//	    return Math.round(result * scale) / scale;
	}

	private static double myPow2(double x, long n) {
		if (x == 0.0) {
			return 0.0;
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		
		double result = myPow2(x, n/2);
		if ((n%2) == 1) {
			result =  x * result * result;
		} else {
			result = result * result;
		}	
		
		return result;
	}
	
    
	public static void main(String[] args) {
		double result;

		result = myPow(2.0, 10);		
		System.out.println("myPow(2.0, 10) is (expected: 1024.0): " + result);		

		result = myPow(2.1, 3);		
		System.out.println("myPow(2.1, 3) is (expected: 9.261): " + result);		

		result = myPow(2.0, -2);		
		System.out.println("myPow(2.0, -2) is (expected: 0.25): " + result);		

		result = myPow(2.0, 0);		
		System.out.println("myPow(2.0, 0) is (expected: 1.0): " + result);		

		result = myPow(0.0, 10);		
		System.out.println("myPow(0.0, 10) is (expected: 0.0): " + result);		
	
		result = myPow(0.44894, -5);		
		System.out.println("myPow(0.44894, -5) is (expected: 54.83508): " + result);		
		
		result = myPow(2.000000, -2147483648);		
		System.out.println("myPow(2.000000, -2147483648) is (expected: 0.0): " + result);		
	}
}
