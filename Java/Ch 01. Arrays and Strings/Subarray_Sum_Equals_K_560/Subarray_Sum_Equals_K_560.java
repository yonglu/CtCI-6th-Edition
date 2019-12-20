package Subarray_Sum_Equals_K_560;

import java.util.*;


/* Leetcode #560. Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous 
subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

 */

public class Subarray_Sum_Equals_K_560 {

	
	// Time O(N) and Space O(N)
	// Subarray Sum before index i to index j is sum[j] - sum[i]. (Sum[j] - k = Sum[i].
	// Solve this similar to Two Sum problem, put Sum[i] as key and occurrences as value
	// to a map, then as we walk through the number array, we just need to count the occurrences. 
	public static int subarraySum(int[] nums, int k)  {
		int ans = 0;
		
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		// use to keep track of mapping of sum number to number of occurrences 
		Map<Integer, Integer> mymap = new HashMap<Integer, Integer>();
		
		// Remember to initialize 0 sum has at least 1 occurrence.
		mymap.put(0,  1);
		
		int sum = 0;
		for (int num : nums) {
			sum += num;
			int diff = sum - k;
			if (mymap.containsKey(diff)) {
				ans += mymap.get(diff);
			}
			
			if (mymap.containsKey(sum)) {
				mymap.put(sum, mymap.get(sum) + 1);
			} else {
				mymap.put(sum, 1);
			}			
		}
		
		return ans;
	}

	public static void main(String[] args) {
		
		int ans = subarraySum(new int[] {1, 1, 1}, 2);	
		System.out.println("Subarray Sum of [1, 1, 1] Equals to K=2 is: " + ans);

		
	}
}
