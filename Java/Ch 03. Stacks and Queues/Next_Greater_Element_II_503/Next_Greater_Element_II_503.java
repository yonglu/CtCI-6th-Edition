package Next_Greater_Element_II_503;

import java.util.*;



/*
 * Leetcode # 503. Next Greater Element II

Given a circular array (the next element of the last element is the first element of the array), 
print the Next Greater Number for every element. The Next Greater Number of a number x is the 
first greater number to its traversing-order next in the array, which means you could search 
circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:

Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.

*/

/*
 * Different from Next Greater Element i are:
 * 		1. nums has duplicate number
 * 		2. nums is circular array
 * 
 * Solution for different 1 is that: Our old map from x to next greater element of x needs to be
 * map from Pair of X and X index to next greater element of x.
 * 
 * Solution to different 2 is that: loop through nums twice, but don't erase the first finding.
 */

public class Next_Greater_Element_II_503 {

	static class Pair {
		int key;
		int value;
		public Pair(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		public int getValue() {
			return value;
		}
		
	}

	public static int[] nextGreaterElementII(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return new int[0];
        }
        int[] ret = new int[nums.length];
        Arrays.fill(ret, -1);
        Stack<Pair> stack = new Stack<Pair>();
        
        // loop 2 times for circular array
        for (int i = 0; i < 2 * nums.length; i++) {
        	
        	int idx = i % nums.length;
        	
    		while (!stack.isEmpty() && stack.peek().getValue() < nums[idx]) {
    			Pair temp = stack.pop();
    			// Don't overwrite the next greater element if it is found already.
    			if (ret[temp.getKey()] == -1) {
        			ret[temp.getKey()] = nums[idx];    				
    			} 
    		} 
    		
    		// Don't push to stack if we have found the next greater element.
    		if (ret[idx] == -1) {
    			stack.push(new Pair(idx, nums[idx]));
    		}
    	}
        
        return ret;
   }

	public static void main(String[] args) {
		
		int[] result = nextGreaterElementII(new int[] { 1, 2, 1 });		
		System.out.println("Next Greater Element II : " + Arrays.toString(result));

		result = nextGreaterElementII(new int[] { 2, 6, 4 });		
		System.out.println("Next Greater Element II : " + Arrays.toString(result));

	}
}
