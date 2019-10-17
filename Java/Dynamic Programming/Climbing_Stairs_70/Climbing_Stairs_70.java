package Climbing_Stairs_70;

import java.util.*;

/*
 * Leetcode # 70. Climbing Stairs
 * 
 * https://leetcode.com/problems/climbing-stairs/

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct 
ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Accepted
481,566

*/

public class Climbing_Stairs_70 {


	public static int climbStairs(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} 
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		return climbStairs(n, memo, 1, 0) + climbStairs(n, memo, 2, 0);
	}

	private static int climbStairs(int n, Map<Integer, Integer> memo, int steps, int index) {
		if ((index + steps) > n) {
			return 0;
		}
		if ((index + steps) == n) {
			return 1;
		}
		if (memo.containsKey(index + steps)) {
			return memo.get(index + steps);
		}
		int result = climbStairs(n, memo, 1, index + steps) + climbStairs(n, memo, 2, index + steps);
		memo.put(index + steps, result);
		return result;
	}

	public static void main(String[] args) {
		
		int result = climbStairs(2);		
		System.out.println("Ways to climb 2 stairs : " + result);
		
		result = climbStairs(3);		
		System.out.println("Ways to climb 3 stairs : " + result);
		
		result = climbStairs(4);		
		System.out.println("Ways to climb 4 stairs (expect 5): " + result);
		
		result = climbStairs(5);		
		System.out.println("Ways to climb 5 stairs (expect 8): " + result);
		
		result = climbStairs(1);
		System.out.println("Ways to climb 1 stairs : " + result);

	}
}
