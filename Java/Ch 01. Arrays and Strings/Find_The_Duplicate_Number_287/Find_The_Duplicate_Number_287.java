package Find_The_Duplicate_Number_287;

import java.util.*;


/* Leetcode #287 Find the Duplicate Number
 * 
 * 
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
prove that at least one duplicate number must exist. Assume that there is only one duplicate 
number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once. */

public class Find_The_Duplicate_Number_287 {


	public static int findDuplicate(int[] nums) {

		if (nums == null || nums.length == 0) {
			throw new java.lang.IllegalArgumentException("bad parameters");
		}

		int floor = 1;
		int ceiling = nums.length - 1;
		while (floor < ceiling) {
			// divide our range 1..n into an upper range and lower range
			// (such that they don't overlap)
			// lower range is floor..midpoint
			// upper range is midpoint+1..ceiling
			int midpoint = floor + ((ceiling - floor) / 2);
			int lowerRangeFloor = floor;
			int lowerRangeCeiling = midpoint;
			int upperRangeFloor = midpoint + 1;
			int upperRangeCeiling = ceiling;
			// count number of items in lower range
			int itemsInLowerRange = 0;
			for (int item : nums) {
				// is it in the lower range?
				if (item >= lowerRangeFloor && item <= lowerRangeCeiling) {
					itemsInLowerRange += 1;
				}
			}
			int distinctPossibleIntegersInLowerRange = lowerRangeCeiling - lowerRangeFloor + 1;
			if (itemsInLowerRange > distinctPossibleIntegersInLowerRange) {
				// there must be a duplicate in the lower range
				// so use the same approach iteratively on that range
				floor = lowerRangeFloor;
				ceiling = lowerRangeCeiling;
			} else {
				// there must be a duplicate in the upper range
				// so use the same approach iteratively on that range
				floor = upperRangeFloor;
				ceiling = upperRangeCeiling;
			}
		}
		// floor and ceiling have converged
		// we found a number that repeats!
		return floor;

	}

	public static void main(String[] args) {
		
		int result = findDuplicate(new int[] {1,3,4,2,2});	
		System.out.println("Duplicate in [1,3,4,2,2] is: " + result);

		result = findDuplicate(new int[] {3,1,3,4,2});		
		System.out.println("Duplicate in [3,1,3,4,2] is: " + result);
	}
}
