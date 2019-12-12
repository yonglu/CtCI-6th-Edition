package Product_of_Array_Except_Self_238;

import java.util.*;


/* Leetcode #238. Product of Array Except Self

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal 
to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the 
purpose of space complexity analysis.)
 */

public class Product_of_Array_Except_Self_238 {


	public static int[] productExceptSelf(int[] nums) {

		int[] ans = null;
		
		if (nums == null || nums.length == 0) {
			throw new java.lang.IllegalArgumentException("bad parameters");
		}

		return ans;
	}

	public static void main(String[] args) {
		
		int[] results = productExceptSelf(new int[] {1,2,3,4});	
		System.out.println("Product Except Self in [1,2,3,4] is: " + Arrays.toString(results));

	}
}
