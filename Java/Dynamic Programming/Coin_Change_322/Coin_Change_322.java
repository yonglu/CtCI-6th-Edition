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
 * Note, if ask minimum, it is kind of messy to do the backtracking.  Mistakes made:
 * 		* set return from coinChange to 0 if no solution, should return 
 * 				MAX_VALUE to make it easier to process (line 67)
 * 		* add one before checking if the value is MAX_VALUE. (line 78)
 * 
 */
public class Coin_Change_322 {

	public static int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		} 		
        if (amount == 0) {
            return 0;
        }
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		
		int minSteps = Integer.MAX_VALUE;
		int numSteps = Integer.MAX_VALUE;
		for (int i = 0; i < coins.length; i++ ) {
			numSteps = coinChange(coins, memo, coins[i], amount);
			if (numSteps < minSteps) {
				minSteps = numSteps;
			}
		}
		if (minSteps == Integer.MAX_VALUE) {
			minSteps = -1;
		}
		return minSteps;
	}

	private static int coinChange(int[] coins, Map<Integer, Integer> memo, int coinValue, 
			int amount) {
		if (amount-coinValue < 0) {
			// no solution
			return Integer.MAX_VALUE;
		} else if (amount-coinValue == 0) {
			return 1;
		}
		// Remember not initialize it to -1 or 0
		int minSteps = Integer.MAX_VALUE;
		int numSteps = Integer.MAX_VALUE;
		if (memo.containsKey(amount-coinValue)) {
			minSteps = memo.get(amount-coinValue);
		} else {
			for (int i = 0; i < coins.length; i++ ) {
				numSteps = coinChange(coins, memo, coins[i], amount - coinValue);
				// Remember not to increase numSteps if no solution
				if (numSteps != Integer.MAX_VALUE) {
					numSteps++;
					if (numSteps < minSteps) {
						minSteps = numSteps;
					}
				}
			}
			memo.put(amount-coinValue, minSteps);
		}
		return minSteps;
	}
	
	
	public static int coinChangeNumberOfWays(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		} 		
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		
		int result = 0;
		for (int i = 0; i < coins.length; i++ ) {
			result += coinChangeNumberOfWays(coins, memo, coins[i], amount);
		}
		if (result == 0) {
			result = -1;
		}
		return result;
	}
	
	private static int coinChangeNumberOfWays(int[] coins, Map<Integer, Integer> memo, int coinValue, int amount) {
		if (amount-coinValue < 0) {
			return 0;
		} else if (amount-coinValue == 0) {
			return 1;
		}
		int result = 0;
		if (memo.containsKey(amount-coinValue)) {
			result = memo.get(amount-coinValue);
		} else {
			for (int i = 0; i < coins.length; i++ ) {
				result += coinChangeNumberOfWays(coins, memo, coins[i], amount - coinValue);
			}
			memo.put(amount-coinValue, result);
		}
		return result;
	}


	
	public static void main(String[] args) {
		
		int result = coinChangeNumberOfWays(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways [1,2,3], 4 : " + result);

		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange number of ways [1,2,5], 11 : " + result);
		
		result = coinChangeNumberOfWays(new int[] { 2 }, 3);		
		System.out.println("coinChange number of ways [2], 3 : " + result);

		result = coinChange(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange [1,2, 3], 4 : " + result);

		result = coinChange(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange [1,2,5], 11 : " + result);
		
		result = coinChange(new int[] { 2 }, 3);		
		System.out.println("coinChange [2], 3 : " + result);
	}
}
