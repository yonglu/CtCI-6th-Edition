package Coin_Change_322;

import java.util.*;

/*
 * Leetcode # 322. Coin Change
 * 
 * https://leetcode.com/problems/coin-change

You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to 
make up that amount. If that amount of money cannot be made up by any 
combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.
*/


/*
 * Note, if ask minimum steps for path, it is kind of messy to do the backtracking.  
 * Mistakes made:
 * 		* set return from coinChange to 0 if no solution, should return 
 * 				MAX_VALUE to make it easier to process (line 64)
 * 		* add one before checking if the value is MAX_VALUE. (line 776)
 */
public class Coin_Change_322 {

	/*
	 * Backtracking Decision Tree:
	 * 		1. Order doesn't matter
	 * 		2. Coin can be used multiple times
	 */
	public static int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		} 		
        if (amount == 0) {
            return 0;
        }
		
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

		int minSteps = coinChange(coins, memo, amount, 0);
		
		if (minSteps == Integer.MAX_VALUE) {
			minSteps = -1;
		}
		return minSteps;
	}

	private static int coinChange(int[] coins, Map<Integer, Integer> memo, int rem, int start) {
		if (rem < 0) {
			// no solution
			return Integer.MAX_VALUE;
		} else if (rem == 0) {
			return 0;
		}
		
		if (memo.containsKey(rem)) {
			return memo.get(rem);
		}
		
		// Remember not initialize it to -1 or 0
		int minSteps = Integer.MAX_VALUE;
		int numSteps = Integer.MAX_VALUE;
		
		for (int i = start; i < coins.length; i++) {
			numSteps = coinChange(coins, memo, rem - coins[i], i);
			// Remember not to increase numSteps if no solution
			if (numSteps != Integer.MAX_VALUE) {
				numSteps++;
				if (numSteps < minSteps) {
					minSteps = numSteps;
				}
			}			
		}
		memo.put(rem, minSteps);
		
		return minSteps;
	}
	
	public static int coinChange2(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		} 		
        if (amount == 0) {
            return 0;
        }
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		
		int minSteps = coinChange2(coins, memo, amount);
		
		if (minSteps == Integer.MAX_VALUE) {
			minSteps = -1;
		}
		return minSteps;
	}

	private static int coinChange2(int[] coins, Map<Integer, Integer> memo, int rem) {
		if (rem < 0) {
			// no solution
			return Integer.MAX_VALUE;
		} else if (rem == 0) {
			return 0;
		}
		
		if (memo.containsKey(rem)) {
			return memo.get(rem);
		}
		
		// Remember not initialize it to -1 or 0
		int minSteps = Integer.MAX_VALUE;
		int numSteps = Integer.MAX_VALUE;
		
		for (int coin : coins) {
			numSteps = coinChange2(coins, memo, rem - coin);
			// Remember not to increase numSteps if no solution
			if (numSteps != Integer.MAX_VALUE) {
				numSteps++;
				if (numSteps < minSteps) {
					minSteps = numSteps;
				}
			}			
		}
		
		memo.put(rem, minSteps);
		
		return minSteps;
	}	
	
    // coin change number of ways, see problem leetcode # 518.	

	
	public static void main(String[] args) {
		int result = 0;
		
		result = coinChange(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange [1,2, 3], 4 : " + result);

		result = coinChange(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange [1,2,5], 11 : " + result);
		
		result = coinChange(new int[] { 2 }, 3);		
		System.out.println("coinChange [2], 3 : " + result);
		
		result = coinChange(new int[] { 357,239,73,52 }, 9832);		
		System.out.println("coinChange [357,239,73,52], 9832 : " + result);
		
		result = coinChange2(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange2 [1,2, 3], 4 : " + result);

		result = coinChange2(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange2 [1,2,5], 11 : " + result);
		
		result = coinChange2(new int[] { 2 }, 3);		
		System.out.println("coinChange2 [2], 3 : " + result);
		
		result = coinChange2(new int[] { 357,239,73,52 }, 9832);		
		System.out.println("coinChange2 [357,239,73,52], 9832 : " + result);
		
	}
}
