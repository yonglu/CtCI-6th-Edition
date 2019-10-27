package Coin_Change_i_322_and_ii_518;

import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.sun.org.glassfish.external.statistics.Statistic;

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

public class Coin_Change_i_322_and_ii_518 {	

	/*
	 * Backtracking Decision Tree:
	 * 		1. Order doesn't matter
	 * 		2. Coin can be used multiple times
	 *      3. No need to filter duplicated
	 */
	public static List<List<Integer>> coinChangeCombinations(int[] coins, int amount) {
		List<List<Integer>> lists = new ArrayList<>();
		if (coins == null || coins.length == 0 || amount == 0) {
			return lists;
		}
		coinChangeCombinations(lists, new ArrayList<Integer>(), coins, amount, 0);
		
		return lists;
	}

	private static void coinChangeCombinations(List<List<Integer>> lists, 
			ArrayList<Integer> tempList, int[] coins, int rem, int start) {
		if (rem < 0) {
			return;
		} else if ( rem == 0) {
			lists.add(new ArrayList<Integer>(tempList));
		}
		for (int i=start; i < coins.length; i++) {
			tempList.add(coins[i]);
			coinChangeCombinations(lists, tempList, coins, rem-coins[i], i);
			tempList.remove(tempList.size()-1);
		}	
	}
	
	// This is Leetcode # 518
	public static int coinChangeNumberOfWays(int[] coins, int amount) {
		int result = -1;
		if (coins == null || coins.length == 0 || amount == 0) {
			return result;
		}
		Map<String, Integer> memo = new HashMap<String, Integer>();
		result = coinChangeNumberOfWays(memo, coins, amount, 0);
		
		return result;
	}

	private static int coinChangeNumberOfWays(Map<String, Integer> memo,
			int[] coins, int rem, int start) {
		if (rem < 0) {
			return 0;
		} else if ( rem == 0) {
			return 1;
		}
		int result = 0;
		for (int i=start; i < coins.length; i++) {
			// Notice the memorization is based on the remind amount and the 
			// index of the coins array.  Made mistake to base on only remind amount.
			// With memorization to decrease the big O from O(2^N) to O(N^2).
			// 
			// Note, if you are getting the all path combinations, it is hard to 
			// do memorization, but if it is for # of ways or minimum items in path, 
			// then it is easier to do memorization. 
			int temp = 0;
			StringBuilder sb = new StringBuilder();
			sb.append(rem).append("+").append(i);
			if (memo.containsKey(sb.toString())) {
				temp = memo.get(sb.toString());
			} else {
				temp = coinChangeNumberOfWays(memo, coins, rem-coins[i], i);
				memo.put(sb.toString(), temp);
			}
			result += temp;
		}
		return result;
	}
		
    public static int coinChangeNumberOfWaysIterative(int[] coins, int amount ) {
        if (amount < 0 || coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }
 
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
 
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
 
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
 
        return dp[coins.length][amount];
 
    }	

	// This is Leetcode # 322
	public static int coinChangeMinimumSteps(int[] coins, int amount) {
		int result = -1;
		if (coins == null || coins.length == 0 || amount == 0) {
			return result;
		}
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		result = coinChangeMinimumSteps(memo, coins, amount, 0);
		
		if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		return result;
	}
	
	/*
	 * Note, if ask minimum steps for path, it is kind of messy to do the backtracking.  
	 * Mistakes made:
	 * 		* set return from coinChange to 0 if no solution, should return 
	 * 				MAX_VALUE to make it easier to process (line 201)
	 * 		* add one before checking if the value is MAX_VALUE. (line 212)
	 */
	private static int coinChangeMinimumSteps(Map<Integer, Integer> memo,
			int[] coins, int rem, int start) {
		if (rem < 0) {
			return Integer.MAX_VALUE;
		} else if ( rem == 0) {
			return 0;
		}
		if (memo.containsKey(rem)) {
			return memo.get(rem);
		}
		int minSteps = Integer.MAX_VALUE;
		for (int i=start; i < coins.length; i++) {
			int temp = 0;
				temp = coinChangeMinimumSteps(memo, coins, rem-coins[i], i);
				if (temp != Integer.MAX_VALUE) {
					temp++;
					if (temp < minSteps) {
						minSteps = temp;
					}
				}
		}
		memo.put(rem, minSteps);
		return minSteps;
	}
	
	
	public static void main(String[] args) {
		
		long currentTime;
		List<List<Integer>> result = new ArrayList<>();
		
		currentTime = System.currentTimeMillis();
		result = coinChangeCombinations(new int[] { 1, 2, 5 }, 11);		
		System.out.println("Time of execution : " + (System.currentTimeMillis() - currentTime));
		System.out.println("coinChangeCombinations [1,2,5], 11 : ");		
		for (List<Integer> tempList : result) {
			for ( Integer i : tempList) {
				System.out.print(i + ",");
			}
			System.out.println("");
		}

		currentTime = System.currentTimeMillis();
		int result2 = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 11);		
		System.out.println("Time of execution : " + (System.currentTimeMillis() - currentTime));
		System.out.println("coinChangeNumberOfWays [1,2,5], 11 : " + result2);
	
		currentTime = System.currentTimeMillis();
		int result4 = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 11);		
		System.out.println("Time of execution : " + (System.currentTimeMillis() - currentTime));
		System.out.println("coinChangeNumberOfWaysIterative [1,2,5], 11 : " + result4);

		currentTime = System.currentTimeMillis();
		int result3 = coinChangeMinimumSteps(new int[] { 1, 2, 5 }, 11);		
		System.out.println("Time of execution : " + (System.currentTimeMillis() - currentTime));
		System.out.println("coinChangeMinimumSteps [1,2,5], 11 : " + result3);
		
	}
}
