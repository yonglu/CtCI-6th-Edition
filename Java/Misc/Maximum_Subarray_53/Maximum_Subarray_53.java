package Maximum_Subarray_53;

import java.util.*;

/* Leetcode # 53. Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one 
number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
 
*/

public class Maximum_Subarray_53 {

    public static int maxSubArray(int[] nums)  {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	int max = 0;
    	int curSum = 0;
    	for ( int i = 0; i < nums.length; i++) {
    		
    		// reset the curSum to 0 if it is negative
    		curSum = curSum + nums[i] < 0 ? 0 : curSum + nums[i];
    		if (max < curSum) {
    			max = curSum;
    		}
    	}
    	
    	// handle the case if all number are negative
    	if (max == 0) {
    		max = nums[0];
    		for (int i = 1; i < nums.length; i++) {
    			if (max < nums[i]) {
    				max = nums[i];
    			}
    		}
    	}
    	
    	return max;
    }
    
	public static void main(String[] args) {
		int ans;
		ans = maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4});		
		System.out.println("Maximum Subarray of [-2,1,-3,4,-1,2,1,-5,4] is : " + ans);

		ans = maxSubArray(new int[] {-2});		
		System.out.println("Maximum Subarray of [-2] is : " + ans);
		
		ans = maxSubArray(new int[] {-2,1,-3,3,-1,2,1,-5,4});		
		System.out.println("Maximum Subarray of [-2,1,-3,3,-1,2,1,-5,4] is : " + ans);
		
		ans = maxSubArray(new int[] {-2,-1,-3,-4,-1,-2,-1,-5,-4});		
		System.out.println("Maximum Subarray of [-2,-1,-3,-4,-1,-2,-1,-5,-4] is : " + ans);
	}
}
