package Product_of_Array_Except_Self_238;

import java.util.*;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;


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


	// Note, this can be O(1) space (ans array doesn't count) if
	// we use the ans array to keep the before the intermediate value
	public static int[] productExceptSelf(int[] nums) {
		
		if (nums == null || nums.length == 0 || nums.length == 1) {
			return new int[0];
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		int[] ans = new int[nums.length];
		int[] before = new int[nums.length];
		int[] after = new int[nums.length];
		
		// nums = 1, 2, 3, 4
		// before = 1, 1, 2, 6
		before[0] = 1;
		for (int i = 1; i < nums.length; i++ ) {
			before[i] = before[i-1] * nums[i-1];
		}
		
		// after = 24, 12, 4, 1
		after[nums.length-1] = 1;
		for (int j = nums.length - 2; j >= 0; j--) {
			after[j] = after[j+1] * nums[j+1];
		}
		
		// ans = 1*24, 1*12, 2*4, 6*1 (24, 12, 8, 6)
		for (int k = 0; k < nums.length; k++) {
			ans[k] = before[k] * after[k];
		}

		return ans;
	}

	public static void main(String[] args) {
		
		int[] results = productExceptSelf(new int[] {1,2,3,4});	
		System.out.println("Product Except Self in [1,2,3,4] is: " + Arrays.toString(results));

	}
}
