package Two_Sum_II_167;

import java.util.*;

/* Leetcode # 167 Two Sum II - Input array is sorted (different from Two Sum, problem #1)
 * 
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * 
 * Given an array of integers that is already sorted in ascending order, find two numbers 
 * such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they add up to 
 * the target, where index1 must be less than index2.
 * 
 * Note:
 * 
 *  Your returned answers (both index1 and index2) are not zero-based.
 *  You may assume that each input would have exactly one solution and you may not use the 
 *  same element twice.
 *  
 *  Example:
 *  
 *  Input: numbers = [2,7,11,15], target = 9
 *  Output: [1,2]
 *  Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */

public class Two_Sum_II_167 {


	public static int[] twoSumII(int[] numbers, int target) {

		if (numbers == null || numbers.length < 2) {
			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		// Note, there is no direct method to convert from ArrayList<Integer> to int[],
		// need to do it in for loop.
		int[] result = new int[2];
		
		int begin = 0;
		int end = numbers.length - 1;
		
		while (begin < end) {
			if (numbers[begin] + numbers[end] == target) {
				// they want answer index start from 1, so we need to add 1
				result[0] = begin + 1;
				result[1] = end + 1;
				return result;
			}
			
			if (numbers[begin] + numbers[end] < target) {
				begin++;
			} else {
				end--;
			}
		}
		
		return result;
	}


	public static void main(String[] args) {
		
		int[] results = twoSumII(new int[] { 2, 7, 11, 15 }, 9);
		
		System.out.println("Two Sum II of [2, 7, 11, 15] with target 9 :");
		for (int i : results) {
			System.out.println(i);
		}
		
	}
}
