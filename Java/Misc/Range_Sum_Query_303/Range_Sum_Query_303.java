package Range_Sum_Query_303;

import java.util.*;

/* Leetcode # 303. Range Sum Query - Immutable

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:

Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

Note:

    You may assume that the array does not change.
    There are many calls to sumRange function.
 
*/

public class Range_Sum_Query_303 {

	static int[] cummulativeSum;
	
	// We can pre-compute the cummulative sum from index 0 to k. 
	// Let us define sum[k] as the cumulative sum for nums[0 ⋯ k−1].
	// sumRange(i,j)=sum[j+1]−sum[i]
    public static void NumArray(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return;
    	}
    	
    	cummulativeSum = new int[nums.length + 1];
    	
    	cummulativeSum[0] = 0;
    	
    	// The trick here is that the cummulativeSum of i holds cummulative sum of all previous indexs.
    	// Given nums = [-2, 0, 3, -5, 2, -1]
    	// cummulativeSum =   [ 0, -2, -2, 1, -4, -2, -3]
    	if (nums != null) {
	        for (int i = 1; i<= nums.length; i++) {
	        	cummulativeSum[i] = cummulativeSum[i-1] + nums[i-1];
	        }
    	}
    }
    
    public static int sumRange(int i, int j) {
    	if (cummulativeSum == null) {
    		return 0;
    	}
    	if ( i > j || i+1 >= cummulativeSum.length || j+1 >= cummulativeSum.length ) {
    		return 0;
    	}
    	return cummulativeSum[j+1] - cummulativeSum[i];
    }
    
	public static void main(String[] args) {
		int sum;
		NumArray(new int[] {-2, 0, 3, -5, 2, -1});		

		sum = sumRange(0, 2);		
		System.out.println("Sum Range 0, 2 of [-2, 0, 3, -5, 2, -1] is : " + sum);

		sum = sumRange(2, 5);		
		System.out.println("Sum Range 2, 5 of [-2, 0, 3, -5, 2, -1] is : " + sum);
		
		sum = sumRange(0, 5);		
		System.out.println("Sum Range 0, 5 of [-2, 0, 3, -5, 2, -1] is : " + sum);
		
		sum = sumRange(0, 0);		
		System.out.println("Sum Range 0, 0 of [-2, 0, 3, -5, 2, -1] is : " + sum);
		
		sum = sumRange(5, 6);		
		System.out.println("Sum Range 0, 0 of [-2, 0, 3, -5, 2, -1] is : " + sum);
		
		NumArray(new int[] {-2});		

		sum = sumRange(0, 2);		
		System.out.println("Sum Range 0, 2 of [-2] is : " + sum);
		
	}
}
