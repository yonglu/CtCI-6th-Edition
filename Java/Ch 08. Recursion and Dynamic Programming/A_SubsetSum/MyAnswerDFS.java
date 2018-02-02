package A_SubsetSum;

import java.util.*;

public class MyAnswerDFS {

	public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if (candidates == null || candidates.length == 0)
			return result;

		ArrayList<Integer> current = new ArrayList<Integer>();
		
		// Sorting it can short cut the for loop below, but sort is a log(n) operation.
		// Arrays.sort(candidates);

		combinationSum(candidates, target, 0, current, result);
		return result;
	}

	// Depth First Search with Backtracking
	public static void combinationSum(int[] candidates, int target, int j, ArrayList<Integer> curr,
			ArrayList<ArrayList<Integer>> result) {
		if (target == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(curr);
			result.add(temp);
			return;
		}

		// Depth First Search with Backtracking
		for (int i = j; i < candidates.length; i++) {
			// if(target < candidates[i])
			// return;
			curr.add(candidates[i]);
			combinationSum(candidates, target - candidates[i], i + 1, curr, result);
			curr.remove(curr.size() - 1);
		}
	}

	
	// For algorithm, see https://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
	// or http://algorithms.tutorialhorizon.com/dynamic-programming-subset-sum-problem/	
	// Not efficient.  O(2^n*n)
	
	// Returns true if there is a subset of set[] with sum
	// equal to given sum
	static void isSubsetSum(int set[], int n, int sum, ArrayList<Integer> curr,
			ArrayList<ArrayList<Integer>> results) {
		// Base Cases
		if (sum == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(curr);
			results.add(temp);
			return;
		}
		if (n == 0 && sum != 0) {
			return;
		}

		/*
		 * else, check if sum can be obtained by any of the following (a) including the
		 * last element 
		 */
		curr.add(set[n-1]);
		isSubsetSum(set, n - 1, sum - set[n - 1], curr, results);
		curr.remove(curr.size() - 1);
	
		/*
		 * (b) excluding the last element.  When excluding the last element, no need to
		 * include it to the path
		 */
		isSubsetSum(set, n - 1, sum, curr, results); 
		
		return;
	}

	public static void main(String[] args) {
		int[] set = new int[] { 2, 3, 3, 6, 7 };
//  	int[] set = new int[] { -10, -8, -2, -1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		ArrayList<ArrayList<Integer>> results = combinationSum(set, 8);
		for (ArrayList<Integer> subset : results) {
			System.out.println(subset.toString());
		}
		
		ArrayList<ArrayList<Integer>> results2 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> current = new ArrayList<Integer>();
		isSubsetSum(set, set.length, 8, current, results2);
		for (ArrayList<Integer> subset : results2) {
			System.out.println(subset.toString());
		}
	}

}
