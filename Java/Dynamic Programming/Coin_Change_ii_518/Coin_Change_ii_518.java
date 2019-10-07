package Coin_Change_ii_518;

import java.util.*;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

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
public class Coin_Change_ii_518 {	
	
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
			return 1;
		}
		int result = 0;
//		if (memo.containsKey(rem)) {
//			return memo.get(rem);
//		}
			
		for (int coin : coins) {
			System.out.print(coin + ", ");
			result += coinChangeNumberOfWays(coins, memo, rem - coin);
		}
		memo.put(rem, result);
		
		return result;
	}

	public static int coinChangeNumberOfWaysBackTracking(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return 0;
		} 		
		
		List<List<Integer>> lists = new ArrayList<>();
		coinChangeNumberOfWaysBackTracking(coins, lists, new ArrayList<Integer>(), amount, 0);
	
		return lists.size();
		
	}
	
	private static void coinChangeNumberOfWaysBackTracking(int[] coins, 
			List<List<Integer>> lists, ArrayList<Integer> tempList, int amount, int start) {
		if (amount < 0) {
			return;
		} else if (amount == 0) {
			lists.add(tempList);
		}
		
		for (int i=start; i<coins.length; i++) {
			tempList.add(coins[i]);
//			System.out.println();
			System.out.print(coins[i] + ", ");
//			System.out.println();
			coinChangeNumberOfWaysBackTracking(coins, lists, tempList, amount-coins[i], i);
			tempList.remove(tempList.size()-1);
		}
	}
	
    public static int changeDP(int[] coins, int amount ) {
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
    
    public static int count=0;
    public static int change(int[] coins, int amount) {
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
		
		int result = coinChangeNumberOfWays(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways [1,2,3], 4 : " + result);
/*
		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 11);		
		System.out.println("coinChange number of ways [1,2,5], 11 : " + result);
		
		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 5);		
		System.out.println("coinChange number of ways [1,2,5], 5 : " + result);

		result = coinChangeNumberOfWays(new int[] { 2 }, 3);		
		System.out.println("coinChange number of ways [2], 3 : " + result);
*/
		result = coinChangeNumberOfWaysBackTracking(new int[] { 1, 2, 3 }, 4);		
		System.out.println("coinChange number of ways backtracking [1,2,3], 4 : " + result);
		
/*		
		System.out.println("Test the execution times...");
		
		long currentTime = System.currentTimeMillis();
		result = coinChangeNumberOfWays(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);
		
		currentTime = System.currentTimeMillis();
		result = coinChangeNumberOfWaysBackTracking(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways backtracking [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);
		
		currentTime = System.currentTimeMillis(); 
		result = changeDP(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways DP [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);

		currentTime = System.currentTimeMillis();
		result = change(new int[] { 1, 2, 5 }, 30);		
		System.out.println("coinChange number of ways DP [1,2,5], 11 : " + result);
		System.out.println(System.currentTimeMillis() - currentTime);
*/
	}
}
