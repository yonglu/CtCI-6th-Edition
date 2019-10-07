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
 * 				MAX_VALUE to make it easier to process (line 60)
 * 		* add one before checking if the value is MAX_VALUE. (line 77)
 * 
 * It is even harder to get the path order(e.g. 1->5->5 for 11) with memorization.
 * Need to remember the path from the backward not from beginning.  
 * 
 * During interview, just implement the backtracking algorithm without memorization first,
 * then mention that you can memorization to decrease the big O from O(2^N) to O(N^2).
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
		
		int minSteps = coinChange(coins, memo, amount);
		
		if (minSteps == Integer.MAX_VALUE) {
			minSteps = -1;
		}
		return minSteps;
	}

	private static int coinChange(int[] coins, Map<Integer, Integer> memo, int rem) {
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
			numSteps = coinChange(coins, memo, rem - coin);
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

	public static int coinChangePathWithoutMemory(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		} 		
        if (amount == 0) {
            return 0;
        }
		
		List<Integer> tempList = new ArrayList<Integer>();
		
		List<Integer> resultList = coinChangePathWithoutMemory(coins, amount, tempList);
		
		if (resultList.isEmpty()) {
			return -1;
		}
		for (int i : resultList) {
			System.out.print(i + ", ");
		}
		System.out.println();
		return resultList.size();
	}

	private static List<Integer> coinChangePathWithoutMemory(int[] coins, int rem,
			 List<Integer> tempList) {
		if (rem < 0) {
			// no solution
			return new ArrayList<Integer>();
		} else if (rem == 0) {
			return new ArrayList<Integer>(tempList);
		}
		
		List<Integer> minList = new ArrayList<Integer>();

		for (int coin : coins) {
			tempList.add(coin);
			List<Integer> numList = coinChangePathWithoutMemory(coins, rem - coin, tempList);
			// Remember to check if no solution or minList is empty
			if (!numList.isEmpty()) {
				if (minList.isEmpty()) {
					minList.addAll(numList);
				} else if (numList.size() < minList.size()) {
					minList.clear();
					minList.addAll(numList);
				}
			}	
			tempList.remove(tempList.size()-1);			
		}

		return minList;
	}
	
	
	
	public static void main(String[] args) {
		int result = 0;
		
		result = coinChange(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange [1,2, 3], 4 : " + result);

		result = coinChange(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange [1,2,5], 11 : " + result);
		
		result = coinChange(new int[] { 2 }, 3);		
		System.out.println("coinChange [2], 3 : " + result);
		
		result = coinChangePathWithoutMemory(new int[] { 1, 2, 3 }, 6);		
		System.out.println("coinChangePathWithouMemory [1,2, 3], 6 : " + result);
		
		result = coinChangePathWithoutMemory(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChangePathWithoutMemory [1,2, 5], 11 : " + result);
	}
}
