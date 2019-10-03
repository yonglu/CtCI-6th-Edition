package House_Robber_ii_213;

import java.util.*;

/*
 * Leetcode # 213. House Robber II
 * 
 * https://leetcode.com/problems/house-robber-ii/

You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed. All houses at this place 
are arranged in a circle. That means the first house is the neighbor of the 
last one. Meanwhile, adjacent houses have security system connected and 
it will automatically contact the police if two adjacent houses were broken 
into on the same night.

Given a list of non-negative integers representing the amount of money of 
each house, determine the maximum amount of money you can rob tonight 
without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.

Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

*/

public class House_Robber_ii_213 {


	public static int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		} else if (nums.length == 1) {
			return nums[0];
		}
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		return Math.max(rob(nums, memo, true, 0), rob(nums, memo, false, 1));
	}

	private static int rob(int[] nums, Map<Integer, Integer> memo, boolean startOfBeginning, int index) {
		if (index >= nums.length) {
			return 0;
		}
		// detect the condition where it is the end of the array and start from beginning 
		// (adjacent house in begin and end)
		if ((index == nums.length - 1) && startOfBeginning) {
			return 0;
		}
		if (memo.containsKey(index)) {
			return memo.get(index);
		}
		int result = Math.max(rob(nums, memo, startOfBeginning, index + 2), rob(nums, memo, startOfBeginning, index + 3)) + nums[index];
		memo.put(index, result);
		return result;
	}

	public static void main(String[] args) {
		
		int result = rob(new int[] { 1,2,3,1 });		
		System.out.println("Rob [1,2,3,1] : " + result);
		
		result = rob(new int[] { 2,7,9,3,1 });		
		System.out.println("Rob [2,7,9,3,1] : " + result);

		result = rob(new int[] { 2,7,3,9,1 });		
		System.out.println("Rob [2,7,3,9,1] : " + result);		
		
		result = rob(new int[] { 2,7,3,1,9 });		
		System.out.println("Rob [2,7,3,1,9] : " + result);		
		
		result = rob(new int[] { 2,3,2 });		
		System.out.println("Rob [2,3,2] : " + result);		

		result = rob(new int[] { 14,13,15 });		
		System.out.println("Rob [14,13,15] : " + result);		

	}
}
