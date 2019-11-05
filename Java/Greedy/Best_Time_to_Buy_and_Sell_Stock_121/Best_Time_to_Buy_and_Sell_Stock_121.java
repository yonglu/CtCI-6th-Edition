package Best_Time_to_Buy_and_Sell_Stock_121;

import java.util.*;

/* Leetcode # 121. Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock 
on day i.

If you were only permitted to complete at most one transaction (i.e., buy one 
and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), 
			 profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 
*/

public class Best_Time_to_Buy_and_Sell_Stock_121 {


	public static int maxProfit(int[] prices)  {
		
		if (prices == null || prices.length < 2 ) {
			return 0;
		}
		
		int max = 0;
		int minPrice = prices[0];
		for (int i = 1; i < prices.length; i++) {
			int profit = prices[i] - minPrice;
			if (max < profit) {
				max = profit;
			}
			if (minPrice > prices[i]) {
				minPrice = prices[i];
			}
		}
		
		return max;
	}


	public static void main(String[] args) {
		
		int max = maxProfit(new int[] {7,1,5,3,6,4});		
		System.out.println("Max Profit of [7,1,5,3,6,4] is : " + max);

		max = maxProfit(new int[] {7,6,4,3,1});		
		System.out.println("Max Profit of [7,6,4,3,1] is : " + max);
		
	}
}
