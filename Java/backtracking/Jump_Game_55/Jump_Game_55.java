package Jump_Game_55;

import java.util.*;

/*
 * Leetcode # 55. Jump Game

Given an array of non-negative integers, you are initially positioned 
at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

 * 
 */

public class Jump_Game_55 {

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
    // 74 / 75 test cases passed.  Status: Time Limit Exceeded
    public static boolean canJump(int[] nums) {
        if ( nums == null ) {
        	return false;
        } else if ( nums.length == 0) {
        	return true;
        }
        
        Map<Integer, Boolean> memo = new HashMap<Integer, Boolean>();
    	
    	return canJump(nums, memo, 0);
    }
 
    private static boolean canJump(int[] nums, Map<Integer, Boolean> memo, int startIndex) {

    	if ( startIndex == nums.length - 1 ) {
    		return true;
    	} else if ( startIndex > nums.length - 1) {
    		return false;
    	}
    	
    	if ( memo.containsKey(startIndex)) {
    		return memo.get(startIndex);
    	}
    	
    	// Optimization:  
    	// 		1. no sense to check pass the end.
    	//		2. try the furthestJump first
    	// Uses "for ( int i = 1; i <= nums[startIndex]; i++)" before. 
    	// However, still time over the limit in last Leetcode test case.
    	int furthestJump = Math.min(nums[startIndex], nums.length - 1 - startIndex);   	
    	for ( int i = furthestJump; i >= 1; i--) {
//    	for ( int i = 1; i <= nums[startIndex]; i++) {
    		if (canJump(nums, memo, startIndex + i)) {
    			memo.put(startIndex, true);
    			return true;
    		} else {
    			memo.put(startIndex, false);
    		}
    	}
    	
    	return false;
    }

    public static void main(String[] args) {
		boolean ans;
		ans = canJump(new int[] {2,3,1,1,4});
		System.out.println("can jump [2,3,1,1,4] : " + ans);

		ans = canJump(new int[] {3,2,1,0,4});
		System.out.println("can jump [3,2,1,0,4] : " + ans);

		ans = canJumpGreedy(new int[] {2,3,1,1,4});
		System.out.println("can jump [2,3,1,1,4] : " + ans);

		ans = canJumpGreedy(new int[] {3,2,1,0,4});
		System.out.println("can jump [3,2,1,0,4] : " + ans);
    
    }
}
