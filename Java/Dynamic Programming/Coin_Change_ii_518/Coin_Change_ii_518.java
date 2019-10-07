package Coin_Change_ii_518;

import java.util.*;


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
 * Note, the answer is order doesn't matter.  coinChangeNumberOfWaysBackTrackingOrderDoesntMatter 
 * has correct answer.
 * 
 */
public class Coin_Change_ii_518 {	
	
	
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
//			System.out.println(tempList);
			lists.add(tempList);
			return;
		}
		
		for (int i=start; i<coins.length; i++) {
			tempList.add(coins[i]);
//			System.out.print(coins[i] + ", ");
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
//			System.out.println(tempList);
			lists.add(tempList);
			return;
		}
		
		for (int i=0; i<coins.length; i++) {
			tempList.add(coins[i]);
//			System.out.print(coins[i] + ", ");
			coinChangeNumberOfWaysBackTrackingOrderMatter(coins, lists, tempList, amount-coins[i]);
			tempList.remove(tempList.size()-1);
		}
	}
		
	/*
	 * This is just a slightly different implementation of the above 
	 * coinChangeNumberOfWaysBackTrackingOrderMatter with memorization.
	 * 
	 * With memorization to decrease the big O from O(2^N) to O(N^2).
	 * 
	 * Note, if you are getting the all path combinations, it is hard to do memorization,
	 * but if it is for # of ways or minimum items in path, then it is easier to do
	 * memorization. 
	 */
	public static int coinChangeNumberOfWays(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return 0;
		} 		
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		
		int result = 0;
		result = coinChangeNumberOfWays(coins, memo, amount);
		return result;
	}
	
	private static int coinChangeNumberOfWays(int[] coins, Map<Integer, Integer> memo, int rem) {
		if (rem < 0) {
			return 0;
		} else if (rem == 0) {
//			System.out.println("Found a way");
			return 1;
		}
		int result = 0;
		if (memo.containsKey(rem)) {
			return memo.get(rem);
		}
			
		for (int coin : coins) {
//			System.out.print(coin + ", ");
			result += coinChangeNumberOfWays(coins, memo, rem - coin);
		}
		memo.put(rem, result);
		
		return result;
	}

	
	
    public static int coinChangeNUmberOfWaysIterative(int[] coins, int amount ) {
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
    
    /*
     * This is doing the backtracking, but just count the ways and do not build the set of valid conins.
     */
    public static int count=0;
    public static int coinChangeNumberOfWaysBackTrackingOrderDoesntMatterSimplified(int[] coins, int amount) {
        helper(amount, coins, 0, 0);
        return count;
    }
    public static void helper(int amount, int[] coins, int current, int start){
        if(current > amount){
            return;
        }
        if(current == amount){
            count++;
            return;
        }
        for(int i = start; i < coins.length; i++){
            helper(amount, coins, current + coins[i], i);
        }
    }

	
	public static void main(String[] args) {
		int result = 0;
		
/*
		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange number of ways [1,2,5], 11 : " + result);
		
		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 5);		
		System.out.println("coinChange number of ways [1,2,5], 5 : " + result);

		result = coinChangeNumberOfWays(new int[] { 2 }, 3);		
		System.out.println("coinChange number of ways [2], 3 : " + result);
*/

				
		System.out.println("Test the execution times...");
		
		long currentTime = System.currentTimeMillis();
		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways backtracking order matter with memorization [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);
		
		currentTime = System.currentTimeMillis();
		result = coinChangeNumberOfWaysBackTrackingOrderMatter(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways backtracking order matter [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);

		currentTime = System.currentTimeMillis();
		result = coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways backtracking order doesn't matter [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);
		
		currentTime = System.currentTimeMillis(); 
		result = coinChangeNUmberOfWaysIterative(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways iterative [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);

		currentTime = System.currentTimeMillis();
		result = coinChangeNumberOfWaysBackTrackingOrderDoesntMatterSimplified(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways backtracking order doesn't matter simplified [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);
		
/*
 		// Need to uncomment the System.out.println in code to show the order
		System.out.println("Show order of execution...");

		result = coinChangeNumberOfWays(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways [1,2,3], 4 : " + result);

		result = coinChangeNumberOfWaysBackTrackingOrderDoesntMatter(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways backtrackingn order doesn't matter [1,2,3], 4 : " + result);
		
		result = coinChangeNumberOfWaysBackTrackingOrderMatter(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways backtrackingn order matter [1,2,3], 4 : " + result);
*/		
	}
}
