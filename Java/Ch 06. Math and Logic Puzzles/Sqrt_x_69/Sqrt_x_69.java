package Sqrt_x_69;

import java.util.*;

/* Leetcode # 69. Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the 
result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.

 
*/

public class Sqrt_x_69 {

	public static int mySqrt(int x) {
		if (x < 0) {
			return -1;
		} 
		if (x == 0) {
			return 0;
		}
		if (x == 1) {
			return 1;
		}
		
		int mid = 0;
		int l = 1;
		int r = x;
		
		while (l <= r) {
			mid = (l+r)/2;
			// Note: don't do "Mid * mid == x" as it can overflow.
			if (mid == x / mid) {
				return mid;
			} else if (mid > x / mid) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}

        // Note, have to use the r value nor l value. 
        // Maybe need to tile to right default Java integer division flooring
		return r;        
	}
	
    
	public static void main(String[] args) {
		int result;

		result = mySqrt(4);		
		System.out.println("mySqrt(4) is (expected: 2): " + result);		

		result = mySqrt(8);		
		System.out.println("mySqrt(8) is (expected: 2): " + result);		

		result = mySqrt(2);		
		System.out.println("mySqrt(2) is (expected: 1): " + result);		

		result = mySqrt(3);		
		System.out.println("mySqrt(3) is (expected: 1): " + result);		

		result = mySqrt(10);		
		System.out.println("mySqrt(10) is (expected: 3): " + result);		

		result = mySqrt(0);		
		System.out.println("mySqrt(0) is (expected: 0): " + result);		
		
		result = mySqrt(2147483647);		
		System.out.println("mySqrt(2147483647) is (expected: 46340): " + result);		
	}
}
