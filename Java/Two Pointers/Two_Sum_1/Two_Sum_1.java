package Two_Sum_1;

import java.util.*;

/* Leetcode # 1. Two Sum
 * 
 * 
 * Given an array of integers, return indices of the two numbers such that they add up 
 * to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may not 
 * use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 */

public class Two_Sum_1 {


	public static int[] twoSum(int[] nums, int target)  {
		int[] results = new int[2];
		
		if (nums == null || nums.length < 2 ) {
			return results;
		}
		
		// a hashmap of <number, index>
		Map<Integer, Integer> numsMap = new HashMap<Integer, Integer>();
		
		for (int i=0; i < nums.length; i++) {
			int matchingInt = target - nums[i];
			if (numsMap.containsKey(matchingInt)) {
				results[0] = numsMap.get(matchingInt);
				results[1] = i;
				return results;
			} else {
				numsMap.put(nums[i], i);
			}
		}
		
		return results;
	}


	public static void main(String[] args) {
		
		int[] results = twoSum(new int[] { 2, 7, 11, 15 }, 9);
		
		System.out.println("Two Sum of [2, 7, 11, 15] with target 9 :");
		for (int i : results) {
			System.out.println(i);
		}
		
	}
}
