package Jump_Game_II_45;

import java.util.*;

/*
 * Leetcode # 45. Jump Game II

Given an array of non-negative integers, you are initially positioned at the 
first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

Note:

You can assume that you can always reach the last index.

 * 
 */

public class Jump_Game_II_45 {

	// Use greedy algorithm work
    public static boolean canJumpGreedy(int[] nums) {
        if ( nums == null ) {
        	return false;
        } else if ( nums.length == 0) {
        	return true;
        }

        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // Use greater than and equal to because it can always jump 1 step
            if (i + nums[i] == lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
    
	// Backtracking with memorization.
    // 91 / 92 test cases passed.  Status: Time Limit Exceeded
    public static int minJump(int[] nums) {
        if ( nums == null ) {
        	return -1;
        } else if ( nums.length == 0) {
        	return 0;
        }
        
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    	
    	int result = minJump(nums, memo, 0);
    	
    	if ( result == Integer.MAX_VALUE ) {
    		result = -1;
    	}
    	return result;
    }
 
    private static int minJump(int[] nums, Map<Integer, Integer> memo, int startIndex) {

    	if ( startIndex == nums.length - 1 ) {
    		return 0;
    	} else if ( startIndex > nums.length - 1) {
    		return Integer.MAX_VALUE;
    	}
    	
    	if ( memo.containsKey(startIndex)) {
    		return memo.get(startIndex);
    	}
    	
		int minJumps = Integer.MAX_VALUE;
    	// Optimization:  
    	// 		1. no sense to check pass the end.
    	//		2. try the furthestJump first
    	// Uses "for ( int i = 1; i <= nums[startIndex]; i++)" before. 
    	// However, still time over the limit in last Leetcode test case.
    	int furthestJump = Math.min(nums[startIndex], nums.length - 1 - startIndex);   	
    	for ( int i = furthestJump; i >= 1; i--) {
//    	for ( int i = 1; i <= nums[startIndex]; i++) {
    		int tempJumps = minJump(nums, memo, startIndex + i); 
    		if ( tempJumps != Integer.MAX_VALUE ) {
    			tempJumps++;
    			if ( tempJumps < minJumps ) {
    				minJumps = tempJumps;
    			}
    		}
    	}
    	
    	memo.put(startIndex, minJumps);
    	
    	return minJumps;
    }

    public static void main(String[] args) {
		boolean ans;
		int numJumps;
		numJumps = minJump(new int[] {2,3,1,1,4});
		System.out.println("minimum jump [2,3,1,1,4] : " + numJumps);

		numJumps = minJump(new int[] {3,2,1,0,4});
		System.out.println("minimum jump [3,2,1,0,4] : " + numJumps);
		
		numJumps = minJump(new int[] {2,1,3,2,2,1,2,1,2,5,3,2,1,3,2,1,1,1,3,2,1,8});
		System.out.println("minimum jump [2,1,3,2,2,1,2,1,2,5,3,2,1,3,2,1,1,1,3,2,1,8] (expected 10) : " + numJumps);

		ans = canJumpGreedy(new int[] {2,3,1,1,4});
		System.out.println("can jump [2,3,1,1,4] : " + ans);

		ans = canJumpGreedy(new int[] {3,2,1,0,4});
		System.out.println("can jump [3,2,1,0,4] : " + ans);
    
    }
}
