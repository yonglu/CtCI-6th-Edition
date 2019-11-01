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
 *  
 *    Variation of the Base Backtracking Pattern:
 *    
 *    		Input contains duplicate items - 
 *    			Sort the given array beforehand and skip over duplicates while backtracking
 *
 *			The item from input can be chosen unlimited time - 
 *				use i instead of i + 1 as multiple using is permitted
 *
 *			If only one solution is needed, then use the boolean return in the 
 *				recursive calls to short circuit it.
 *
 * 		    Order matter and at least once - for loop starts from 0 instead of "start", 
 * 				no need "start" parameter.  For permutation, since order matter and
 * 				you need every number/character, you start the for loop with 0 and skip 
 * 				the number/char that is in the current solution.
 * 			
 * 			if can have multiple digits, use Long.parseLong(num.substring(start, i + 1))
 * 				instead of Long.parseLong(num.substring(start, start + 1)) or 
 * 				num.charAt[start] - "0")
 *
 *			For N-queens - loop through the columns and backtracking on rows 
 *				(it would work if loop through rows and backtracking on columns).  
 *				This is more efficient that DFS because only one queen per row and column.
 *
 *  Depth-First search is a specific form of backtracking related to searching tree structures.  
 *  Backtracking is DFS for implicit tree, while DFS is backtracking without pruning. 
 *  Usually, a depth-first-search is a way of iterating through an actual graph/tree structure 
 *  looking for a value, whereas backtracking is iterating through a problem space looking for 
 *  a solution.  (Here is a pretty good explainiation of the concept: 
 *  http://exceptional-code.blogspot.com/2012/09/generating-all-permutations.html)
 *  
 *  In the Subsets and Combinations cases, order doesn't matter, the DFS branches are just the rest of 
 *  characters/numbers in the array. 
 *  
 *  For the permutation case, order matter, the DFS branches are every characters/numbers that 
 *  have not been visited. 
 *  
 *  Decision tree:
 *  
 *  	1.  if order doesn't matter, 
 *              "for loop starts from "start". need "start" parameter
 *              	"for (int i = start; i < nums.length; i++) {"
 *              No need to do memorization because "i=start" is doing the 
 *              	pruning on the tree already.
 *  		else 
 *              "for loop starts from 0 instead of "start", no need "start" parameter."
 *              	"for (int i = 0; i < nums.length; i++) {"
 *              		OR
 *              	"for (int i : nums) {"
 *              Need memorization if it is not asking for the whole combinations.
 *              For example, # of ways, minimum number of inputs, etc...  I guess 
 *              can still do memorization if asking the whole combinations, but not easy.
 *              
 *      2.  if item from input can be chosen only once 
 *      		use i + 1 during recursive calls
 *          else
 *          	use i instead of i + 1 as multiple using is permitted
 *      3.  if input contains duplicate items 
 *    			a. Sort the given array beforehand 
 *              		Arrays.sort(nums);
 *              b. skip over duplicates while backtracking
 *                     	if (i > start && nums[i] == nums[i-1]) {
 *              			continue;
 *          			}
 *      4. if can have multiple digits, e.g. for "123", we can have "1, 2, 3", 
 *      	"12, 3", "1, 23", or "123", instead of just "1, 2, 3"
 *      	use 
 *      		* long currentDigitsValue = Long.parseLong(num.substring(start, i + 1));
 *    			* for (int i = start; i < s.length(); i++) {
					if (isPalindrome(s, start, i)) {
     	   else
 *          use 
 *          	* long currentDigitsValue = Long.parseLong(num.substring(start, start + 1)); or
 *          	  long currentDigitsValue = num.charAt[start] - "0");
 *          	* for (int i = start; i < s.length(); i++) {
					// no checking the multiple items. 

 *  
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
			subsets(lists, tempList, nums, i + 1);
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
	 * 
	 */
	
	// Approach: Sort the given array beforehand and skip over duplicates while backtracking,
	//           and it is the only different than subsets problem
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

	/*
	 * Leetcode # 131 
	 * https://leetcode.com/problems/palindrome-partitioning/ 
	 * 
	 * Given a string s, partition s such that every substring of the partition is a
	 * palindrome. Return all possible palindrome partitioning of s. 
	 * 
	 * For example,
	 * given s = "aab", 
	 * return: [ ["aa","b"], ["a","a","b"] ]
	 */

	public static List<List<String>> palindromePartition(String s) {
		List<List<String>> lists = new ArrayList<>();
		if (s == null || s.isEmpty()) {
			return lists;
		}
		palindromePartition(lists, new ArrayList<String>(), s, 0);
		return lists;
	}

	private static void palindromePartition(List<List<String>> lists, List<String> tempList, 
			String s, int start) {
		if ( start == s.length()) {
			lists.add(new ArrayList<String>(tempList));
		}
		for (int i = start; i < s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				tempList.add(s.substring(start, i+1));
				palindromePartition(lists, tempList, s, i+1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	private static boolean isPalindrome(String s, int low, int high) {
		while (low < high) {
			if (s.charAt(low++) != s.charAt(high--)) {
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * Leetcode # 46
	 * https://leetcode.com/problems/permutations/
	 * 
	 * Given a collection of distinct integers, return all possible permutations.
	 * Example:
	 * Input: [1,2,3]
	 * Output:
	 * [
	 *   [1,2,3],
  	 *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     * 
     * The permutation is similar as the last power set, the difference is we use 
	 * each element at least and only one time, and order matter. 
	 * 
	 * 		1. Order matter and at least once - for loop starts from 0 instead of "start", 
	 * 				no need "start" parameter.
	 * 		2. only one time - check if element already visited
	 * 
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
	
	public static List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		// Arrays.sort(nums); // not necessary
		permute2(list, new ArrayList<Integer>(), nums);
		return list;
	}

	private static void permute2(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<Integer>(tempList));
		}

		for (int i : nums) {
			if (tempList.contains(i))
				continue; // element already exists, skip
			tempList.add(i);
			permute2(list, tempList, nums);
			tempList.remove(tempList.size() - 1);
		}
	}
	
	
	/*
	 * Leetcode # 47
	 * https://leetcode.com/problems/permutations-ii/
	 * 
	 * Given a collection of numbers that might contain duplicates, 
	 * return all possible unique permutations.
	 * 
	 * Example:
	 * Input: [1,2,1]
	 * Output:
	 * [
	 *   [1,1,2],
  	 *   [1,2,1],
     *   [2,1,1],
     * ]
     * 
     * This is similar to the permutation one except it may contain duplicates. 
     * 
     * 		1. sort the number array
	 * 		2. have duplicate - skip the duplicate that has been visited
	 * 		3. Order matter and at least once - for loop starts from 0 instead of "start", 
	 * 				no need "start" parameter.
	 * 		4. only one time - check if element already visited
	 * 
	 */
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		boolean[] visited = new boolean[nums.length];
		Arrays.fill(visited, false);
		permuteUnique(list, visited, new ArrayList<Integer>(), nums);
		return list;
	}

	private static void permuteUnique(List<List<Integer>> list, boolean[] visited, List<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<Integer>(tempList));
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i] || (i > 0 && nums[i] == nums[i-1] && visited[i-1]))
				continue; // element already visited, skip
			visited[i] = true;
			tempList.add(nums[i]);
			permuteUnique(list, visited, tempList, nums);
			tempList.remove(tempList.size() - 1);
			visited[i] = false;
		}
	}
	
	/*
	 * Leetcode # 518. Coin Change 2
	 * 
	 * https://leetcode.com/problems/coin-change-2/
	 * 
	You are given coins of different denominations and a total amount of money. 
	Write a function to compute the number of combinations that make up that amount. 
	You may assume that you have infinite number of each kind of coin. 

	Example 1:

	Input: amount = 5, coins = [1, 2, 5]
	Output: 4
	Explanation: there are four ways to make up the amount:
	5=5
	5=2+2+1
	5=2+1+1+1
	5=1+1+1+1+1
	Example 2:

	Input: amount = 3, coins = [2]
	Output: 0
	Explanation: the amount of 3 cannot be made up just with coins of 2.
	Example 3:

	Input: amount = 10, coins = [10] 
	Output: 1
	 

	Note:

	You can assume that

	0 <= amount <= 5000
	1 <= coin <= 5000
	the number of coins is less than 500
	the answer is guaranteed to fit into signed 32-bit integer
	*/
	
	/*
	 * Decision Tree:
	 * 		1. order doesn't matter
	 * 		2. item from input can be used multiple times
	 * 		3. No need to filter duplicated
	 */
	public static int coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return 0;
		} 		
		
		List<List<Integer>> lists = new ArrayList<>();
		coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(coins, lists, new ArrayList<Integer>(), amount, 0);
	
		return lists.size();
		
	}
	
	private static void coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(int[] coins, 
			List<List<Integer>> lists, ArrayList<Integer> tempList, int amount, int start) {
		if (amount < 0) {
			return;
		} else if (amount == 0) {
			System.out.println(tempList);
			lists.add(tempList);
			return;
		}
		
		for (int i=start; i<coins.length; i++) {
			tempList.add(coins[i]);
			System.out.print(coins[i] + ", ");
			coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(coins, lists, tempList, amount-coins[i], i);
			tempList.remove(tempList.size()-1);
		}
	}
	
	/*
	 * Decision Tree:
	 * 		1. order matter
	 * 		2. item from input can be used multiple times
	 * 		3. No need to filter duplicated
	 */
	public static int coinChangeNumberOfWaysBackTrackingOrderMatter(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return 0;
		} 		
		
		List<List<Integer>> lists = new ArrayList<>();
		coinChangeNumberOfWaysBackTrackingOrderMatter(coins, lists, new ArrayList<Integer>(), amount);
	
		return lists.size();
		
	}
	
	private static void coinChangeNumberOfWaysBackTrackingOrderMatter(int[] coins, 
			List<List<Integer>> lists, ArrayList<Integer> tempList, int amount) {
		if (amount < 0) {
			return;
		} else if (amount == 0) {
			System.out.println(tempList);
			lists.add(tempList);
			return;
		}
		
		for (int i=0; i<coins.length; i++) {
			tempList.add(coins[i]);
			System.out.print(coins[i] + ", ");
			coinChangeNumberOfWaysBackTrackingOrderMatter(coins, lists, tempList, amount-coins[i]);
			tempList.remove(tempList.size()-1);
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

		
		List<List<Integer>> results = subsetsWithDup(new int[] { 1, 2, 2 });
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

		List<List<Integer>> results5 = permute(new int[] { 1, 2, 3 });
		System.out.println("Permute:");
		for (List<Integer> subset : results5) {
			System.out.println(subset.toString());
		}

		List<List<Integer>> results55 = permute2(new int[] { 1, 2, 3 });
		System.out.println("Permute2:");
		for (List<Integer> subset : results55) {
			System.out.println(subset.toString());
		}

		List<List<Integer>> results7 = permuteUnique(new int[] { 1, 2, 1});
		System.out.println("PermuteUnique:");
		for (List<Integer> subset : results7) {
			System.out.println(subset.toString());
		}
		
		int result8 = coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways backtracking order doesn't matter [1,2,3], 4 : " + result8);
	
		int result9 = coinChangeNumberOfWaysBackTrackingOrderMatter(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways backtracking order matter [1,2,3], 4 : " + result9);
	}

}
