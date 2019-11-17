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

public class Two_Sum_1_11_16_2019 {


	public static int[] twoSum(int[] nums, int target)  {
		int[] result = new int[2];
		if (nums == null || nums.length <= 1) {
			return result;
		}
		
		// No need to use a set if you already have map????
		// If only ask for number not index, then use set only.
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		Set<Integer> numsSet = new HashSet<Integer>();
		
		for (int i = 0; i < nums.length; i++) {
			int pairNum = target-nums[i];
			if (numsSet.contains(pairNum)) {
				result[0] = indexMap.get(pairNum);
				result[1] = i;
				break;
			}
			indexMap.put(nums[i], i);
			numsSet.add(nums[i]);
		}
		
		return result;
	}


	public static void main(String[] args) {
		
		int[] results = twoSum(new int[] { 2, 7, 11, 15 }, 9);
		
		System.out.println("Two Sum of [2, 7, 11, 15] with target 9 :");
		for (int i : results) {
			System.out.println(i);
		}
		
	}
}
