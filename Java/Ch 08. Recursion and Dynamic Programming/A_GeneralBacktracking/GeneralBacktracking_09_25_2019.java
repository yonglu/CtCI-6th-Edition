package A_GeneralBacktracking;

import java.util.*;

/*
 *    A general approach to backtracking questions in Java.  This is good for
 *    walking down the array/string and find the combination of something. 
 *    (Subsets, Subset Sum, Palindrome Partitioning, etc...)
 *    
 *    It can work for Permutation too, but the code pattern is different and
 *    doesn't work if there is duplicate items in the array or string.  Use different 
 *    technique for permutation. 
 *    
 *    https://medium.com/leetcode-patterns/leetcode-pattern-3-backtracking-5d9e5a03dc26
 *    
 *    https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
public class GeneralBacktracking_09_25_2019 {
	
	/*
	 * Leetcode #78 
	 * https://leetcode.com/problems/subsets/ 
	 * 
	 * Given a set of distinct integers, nums, return all possible subsets (the power set).
	 * 
	 * Note: The solution set must not contain duplicate subsets.
	 * 
	 * Example:
	 * 
	 * Input: nums = [1,2,3] 
	 * Output: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		subsets(lists, new ArrayList<Integer>(), nums, 0);
		return lists;
	}

	public static void subsets(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int start) {
		lists.add(new ArrayList<Integer>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			// index @i is important, use i+1 instead of i as multiple using is not permitted
			subsetsWithDup(lists, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
		return;
	}
	
	/*
	 * Leetcode #90
	 * https://leetcode.com/problems/subsets-ii/
	 * 
	 * Given a collection of integers that might contain duplicates, nums, return
	 * all possible subsets (the power set).
	 * 
	 * Note: The solution set must not contain duplicate subsets.
	 * 
	 * Example:
	 * 
	 * Input: [1,2,2] 
	 * Output: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
	 */

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		// sort() for de-duplicates
		Arrays.sort(nums);
		subsetsWithDup(lists, new ArrayList<Integer>(), nums, 0);
		return lists;
	}

	public static void subsetsWithDup(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int start) {
		lists.add(new ArrayList<Integer>(tempList));
		for (int i = start; i < nums.length; i++) {
			// Check duplicate.  If duplicated elements exist in current loop, 
			// only use the first while skipping others
			if (i > start && nums[i] == nums[i - 1]) {
				continue;
			}
			tempList.add(nums[i]);
			// index @i is important, use i+1 instead of i as multiple using is not permitted
			subsetsWithDup(lists, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
		return;
	}

	/*
	 * 
	 * Given a set of candidate numbers (candidates) (without duplicates) and a
	 * target number (target), find all unique combinations in candidates where the
	 * candidate numbers sums to target.
	 * 
	 * The same repeated number may NOT be chosen from candidates.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. The solution set
	 * must not contain duplicate combinations.
	 * 
	 * Example 1:
	 * 
	 * Input: candidates = [2,3,6,7], target = 7, A solution set is: [ [7] ] Input:
	 * candidates = [2,3,5], target = 8, A solution set is: [[3,5]]
	 */
	public static List<List<Integer>> combinationSumSimple(int[] nums, int target) {
		List<List<Integer>> lists = new ArrayList<>();
		combinationSumSimple(lists, new ArrayList<Integer>(), nums, target, 0);
		return lists;
	}

	public static void combinationSumSimple(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int target,
			int start) {
		if (target < 0)
			return;
		if (target == 0) {
			lists.add(new ArrayList<Integer>(tempList));
		}
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			// if we CANNOT reuse same elements use i + 1
			// index @i is important, use i+1 instead of i as multiple using is not permitted
			combinationSumSimple(lists, tempList, nums, target - nums[i], i + 1);
			tempList.remove(tempList.size() - 1);
		}
		return;
	}

	/*
	 * Leetcode #39 // https://leetcode.com/problems/combination-sum/
	 * 
	 * Given a set of candidate numbers (candidates) (without duplicates) and a
	 * target number (target), find all unique combinations in candidates where the
	 * candidate numbers sums to target.
	 * 
	 * The same repeated number may be chosen from candidates unlimited number of
	 * times.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. The solution set
	 * must not contain duplicate combinations.
	 * 
	 * Example 1:
	 * 
	 * Input: candidates = [2,3,6,7], target = 7, A solution set is: [ [7], [2,2,3]]
	 * Input: candidates = [2,3,5], target = 8, A solution set is:
	 * [[2,2,2,2],[2,3,3],[3,5]]
	 */
	public static List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> lists = new ArrayList<>();
		combinationSum(lists, new ArrayList<Integer>(), nums, target, 0);
		return lists;
	}

	public static void combinationSum(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int target,
			int start) {
		if (target < 0)
			return;
		if (target == 0) {
			lists.add(new ArrayList<Integer>(tempList));
		}
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			// if we CAN reuse same elements use i.
			// This is the only different between combinationSumSimple and this
			// index @i is important, use i instead of i+1 as multiple using is permitted
			combinationSum(lists, tempList, nums, target - nums[i], i);
			tempList.remove(tempList.size() - 1);
		}
		return;
	}

	/*
	 * Leetcode #40
	 * https://leetcode.com/problems/combination-sum-ii/
	 * 
	 * Given a collection of candidate numbers (candidates) and a target number
	 * (target), find all unique combinations in candidates where the candidate
	 * numbers sums to target.
	 * 
	 * Each number in candidates may only be used once in the combination.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. The solution set
	 * must not contain duplicate combinations. 
	 * 
	 * Example 1:
	 * 
	 * Input: candidates = [10,1,2,7,6,1,5], target = 8, 
	 * A solution set is: [ [1,7], [1, 2, 5], [2, 6], [1, 1, 6] ] 
	 * 
	 * Example 2:
	 * 
	 * Input: candidates = [2,5,2,1,2], target = 5, 
	 * A solution set is: [ [1,2,2], [5] ]
	 */
	public static List<List<Integer>> combinationSum2(int[] nums, int target) {
		List<List<Integer>> lists = new ArrayList<>();
		// sort() for de-duplicates
		Arrays.sort(nums);
		combinationSum2(lists, new ArrayList<Integer>(), nums, target, 0);
		return lists;
	}

	public static void combinationSum2(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int target,
			int start) {
		if (target < 0)
			return;
		if (target == 0) {
			lists.add(new ArrayList<Integer>(tempList));
		}
		for (int i = start; i < nums.length; i++) {
			// Check duplicate.  If duplicated elements exist in current loop, 
			// only use the first while skipping others
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
			tempList.add(nums[i]);
			// index @i is important, use i+1 instead of i as multiple using is not permitted
			combinationSum2(lists, tempList, nums, target - nums[i], i + 1 );
			tempList.remove(tempList.size() - 1);
		}
		return;
	}

	// Given a string s, partition s such that every substring of the partition is a
	// palindrome.
	// Return all possible palindrome partitioning of s.
	// For example, given s = "aab",
	// return:
	// [
	// ["aa","b"],
	// ["a","a","b"]
	// ]

	public static List<List<String>> palindromePartition(String s) {
		List<List<String>> list = new ArrayList<>();
		return list;
	}

	/*
	 * It can work for Permutation too, but the code pattern is different and
	 * doesn't work if there is duplicate items in the array or string. Use
	 * different technique for permutation.
	 */
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		// Arrays.sort(nums); // not necessary
		permute(list, new ArrayList<Integer>(), nums);
		return list;
	}

	private static void permute(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<Integer>(tempList));
		}

		for (int i = 0; i < nums.length; i++) {
			if (tempList.contains(nums[i]))
				continue; // element already exists, skip
			tempList.add(nums[i]);
			permute(list, tempList, nums);
			tempList.remove(tempList.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] set = new int[] { 2, 3, 5, 6, 7 };
		// int[] set = new int[] { -10, -8, -2, -1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		List<List<Integer>> results0 = subsets(new int[] { 1, 2, 3 });
		System.out.println("Subset:");
		for (List<Integer> subset : results0) {
			System.out.println(subset.toString());
		}

		
		List<List<Integer>> results = subsetsWithDup(new int[] { 1, 2, 2, 2 });
		System.out.println("Subset with Dup:");
		for (List<Integer> subset : results) {
			System.out.println(subset.toString());
		}

		List<List<Integer>> results2 = combinationSumSimple(new int[] { 2, 3, 5 }, 8);
		System.out.println("Combination Sum Simple:");
		for (List<Integer> subset : results2) {
			System.out.println(subset.toString());
		}

		List<List<Integer>> results3 = combinationSum(new int[] { 2, 3, 5 }, 8);
		System.out.println("Combination Sum:");
		for (List<Integer> subset : results3) {
			System.out.println(subset.toString());
		}

		List<List<Integer>> results6 = combinationSum2(new int[] { 10,1,2,7,6,1,5 }, 8);
		System.out.println("Combination Sum2:");
		for (List<Integer> subset : results6) {
			System.out.println(subset.toString());
		}
		
		String s = "AppleApp";
		List<List<String>> results4 = palindromePartition(s);
		System.out.println("Palindrome Partition:");
		for (List<String> subset : results4) {
			System.out.println(subset.toString());
		}

		List<List<Integer>> results5 = permute(new int[] { 2, 3, 4 });
		System.out.println("Permute:");
		for (List<Integer> subset : results5) {
			System.out.println(subset.toString());
		}
	}

}
