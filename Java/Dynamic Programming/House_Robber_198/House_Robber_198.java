package House_Robber_198;

import java.util.*;

/*
 * Leetcode # 198. House Robber
 * 
 * https://leetcode.com/problems/house-robber/

You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping 
you from robbing each of them is that adjacent houses have security system 
connected and it will automatically contact the police if two adjacent houses 
were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
*/

public class House_Robber_198 {


	public static int robBacktracking(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		} else if (nums.length == 1) {
			return nums[0];
		}
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		return Math.max(robBacktracking(nums, memo, 0), robBacktracking(nums, memo, 1));
	}

	private static int robBacktracking(int[] nums, Map<Integer, Integer> memo, int index) {
		if (index >= nums.length) {
			return 0;
		}
		if (memo.containsKey(index)) {
			return memo.get(index);
		}
		int result = Math.max(robBacktracking(nums, memo, index + 2), robBacktracking(nums, memo, index + 3)) + nums[index];
		memo.put(index, result);
		return result;
	}

	public static int robRecursive(int[] nums, int index) {
		if (nums == null || nums.length == 0) {
			return 0;
		} 
		if (index < 0) {
			return 0;
		}

		return Math.max( robRecursive(nums, index - 2), robRecursive(nums, index - 3)) + nums[index] ;
	}

	public static int robIterative(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		} 
		
        int currHouse = 0, oneHouseAgo = 0, twoHousesAgo = 0;

        for (int i = 0; i < nums.length; i++) {
            currHouse = Math.max(twoHousesAgo + nums[i], oneHouseAgo);
            twoHousesAgo = oneHouseAgo;
            oneHouseAgo = currHouse;
        }

        return currHouse;
	}
	
	
	public static void main(String[] args) {
		
		int result = robBacktracking(new int[] { 1,2,3,1 });		
		System.out.println("Rob Backtracking [1,2,3,1] : " + result);
		
		result = robBacktracking(new int[] { 2,7,9,3,1 });		
		System.out.println("Rob Backtracking [2,7,9,3,1] : " + result);

		result = robBacktracking(new int[] { 2,7,3,9,1 });		
		System.out.println("Rob Backtracking [2,7,3,9,1] : " + result);		
		
		result = robBacktracking(new int[] { 2,7,3,1,9 });		
		System.out.println("Rob Backtracking [2,7,3,1,9] : " + result);		

		result = robRecursive(new int[] { 2,7,9,3,1 }, 4);		
		System.out.println("Rob Recrusive [2,7,9,3,1] : " + result);

		result = robRecursive(new int[] { 2,7,3,1,9 }, 4);		
		System.out.println("Rob Recursive [2,7,3,1,9] : " + result);		

		result = robIterative(new int[] { 2,7,9,3,1 });		
		System.out.println("Rob Iterative [2,7,9,3,1] : " + result);

		result = robIterative(new int[] { 2,7,3,1,9 });		
		System.out.println("Rob Iterative [2,7,3,1,9] : " + result);		

	}
}
