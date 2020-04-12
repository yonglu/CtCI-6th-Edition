package Rotate_Array_189;

import java.util.*;


/* 
 * 
 * Leetcode 189. Rotate Array

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]


Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

 */

public class Rotate_Array_189 {

	// Time O(N) and space O(1)
	public static void rotateUseReverse(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return;
		}

		int len = nums.length;
		// Let's normalize rotations
		// if k > array size or k is negative.
		k = k % len;
		if (k < 0) {
			// calculate the positive rotations needed.
			k = k + len;
		}

		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);

		return;
	}
	
	private static void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

	// Time O(N) and space O(N)
	public static void rotateExtraArray(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return;
		}

		int[] newNums = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			newNums[i] = nums[(i+nums.length-k)%nums.length];
		}
		
		for (int j = 0; j < nums.length; j++) {
			nums[j] = newNums[j];
		}
	}

	public static void main(String[] args) {

		int[] nums = new int[] {
			1,2,3,4,5,6,7
		};
		
		rotateUseReverse(nums, 3);	
		System.out.println("3th rotate of [[1,2,3,4,5,6,7]] : " + Arrays.toString(nums));
		
		int[] nums2 = new int[] {
				1,2,3,4,5,6,7
		};
		
		rotateExtraArray(nums2, 3);	
		System.out.println("3th rotate of [[1,2,3,4,5,6,7]] : " + Arrays.toString(nums2));		
	}
}
