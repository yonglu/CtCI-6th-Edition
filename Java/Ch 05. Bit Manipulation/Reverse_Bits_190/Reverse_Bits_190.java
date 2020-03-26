package Reverse_Bits_190;

import java.util.*;

/* Leetcode # 190. Reverse Bits

Reverse bits of a given 32 bits unsigned integer.


Example 1:

Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, 
so return 964176192 which its binary representation is 00111001011110000010100101000000.

Example 2:

Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, 
so return 3221225471 which its binary representation is 10111111111111111111111111111111. 

*/

public class Reverse_Bits_190 {

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
    	if ( n == 0) {
    		return 0;
    	}
    	for (int i = 0; i < 16; i++) {
    		n = swapBits(n, i, 32-i-1);
    	}
        
    	return n;
    }	
    
    private static int swapBits(int n, int i, int j) {
    	int a = (n >> i) & 1;
    	int b = (n >> j) & 1;
    	
        // only swap if they are different
    	if (a != b) {
            int bitMask = (1 << i) | (1 << j);
            // XOR ^ basically flip the bit
            return n = n ^ bitMask;  		
    	}
    	
    	return n;
    }
    
	public static void main(String[] args) {
		int result;

		result = reverseBits(43261596);		
		System.out.println("reverseBits(43261596) is (expected: 964176192): " + result);		

		result = reverseBits(0);		
		System.out.println("reverseBits(0) is (expected: 0): " + result);		

		result = reverseBits(Integer.MAX_VALUE);		
		System.out.println("reverseBits(Integer.MAX_VALUE) is (expected: 0): " + result);		

//		result = reverseBits(4294967293L);		
//		System.out.println("reverseBits(4294967293) is (expected: 3221225471): " + result);		

	}
}
