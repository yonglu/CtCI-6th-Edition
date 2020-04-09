package Max_Consecutive_Ones_485;

import java.util.*;


/* Leetcode #485. Max Consecutive Ones

Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:

Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.

Note:

    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000

*/

public class Max_Consecutive_Ones_485 {


	public static int findMaxConsecutiveOnes(int[] nums) {

		int result = 0;
		if (nums == null || nums.length == 0) {
			return result;
		}
		
		int temp = 0;
		for (int i : nums) {
			if (i == 1) {
				temp++;
			} else {
				if (temp > result) {
					result = temp;
				}
				temp = 0;
			}
		}

		// Take care of the condition if the max consecutive one is the end.
		if (temp > result) {
			result = temp;
		}
		
		return result;
	}

	public static void main(String[] args) {
		
		int result = findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1});	
		System.out.println("MaxConsecutiveOnes in [1,1,0,1,1,1] is: (expected 3) " + result);

		result = findMaxConsecutiveOnes(new int[] {1,1,1,1,1,1});		
		System.out.println("MaxConsecutiveOnes in [1,1,1,1,1,1] is: (expected 6) " + result);
		
		result = findMaxConsecutiveOnes(new int[] {0, 0, 0});		
		System.out.println("MaxConsecutiveOnes in [0, 0, 0] is: (expected 0) " + result);

		result = findMaxConsecutiveOnes(new int[] {});		
		System.out.println("MaxConsecutiveOnes in [] is: (expected 0) " + result);
		
	}
}
