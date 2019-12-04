package Trapping_Rain_Water_42;

import java.util.*;

/* Leetcode 42. Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 
units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6


 * 
 * 
 * # https://leetcode.com/problems/trapping-rain-water/
 */

public class Trapping_Rain_Water_42 {

	/*
	 *  For each element in the array, we find the maximum level of water it can trap after the rain, 
	 *  which is equal to the minimum of maximum height of bars on both the sides minus its own height.
	 *  
	 *  trapLeftRightMaxArray() method is O(N) in time and space.  It calculates the leftMaxArray and rightMaxArray,
	 *  then calculates the trap water in each location as min⁡(meftmaxArray[i],rightMaxArray[i])−height[i].
	 *  
	 *  trapTwoPointers() method is O(N) in time and O(1) in space.  Instead of calculating the leftMaxArray or 
	 *  rightMaxArray, it maintains two pointers of leftMax and rightMax.
	 */

	/*
	 * Time complexity: O(n).
	 * Space complexity: O(1). 
	 *  
	 *  Instead of computing the left and right parts separately, notice that water trapped depends upon the minimum
	 *  of leftMax and rightMax.  So, we can say that if there is a larger bar at one end (say right), we are 
	 *  assured that the water trapped would be dependent on height of bar in current direction (from left to right). 
	 *  As soon as we find the bar at other end (right) is smaller, we start iterating in opposite direction 
	 *  (from right to left). We must maintain leftMax and rightMaxduring the iteration, 
	 *  but now we can do it in one iteration using 2 pointers, switching between the two.
	 */
	
	public static int trapTwoPointers(int[] height) {
		int ans = 0;
		if (height == null || height.length == 0) {
			return ans;
		}
		
		int leftIndex = 0;
		int rightIndex = height.length - 1;
		int leftMax = height[leftIndex];
		int rightMax = height[rightIndex];
		
		while (leftIndex <= rightIndex) {
			leftMax = Math.max(leftMax, height[leftIndex]);
			rightMax = Math.max(rightMax, height[rightIndex]);
			if (leftMax < rightMax) {
				ans += leftMax - height[leftIndex];
				leftIndex++;
			} else {
				ans += rightMax - height[rightIndex];
				rightIndex--;
			}
		}
		
		return ans;
	}

	/*
	 * Time complexity: O(n).
	 * Space complexity: O(n). 
	 *  
	 * Find maximum height of bar from the left end up to an index i in the array leftMaxArray.
	 * Find maximum height of bar from the right end up to an index i in the array rightMaxArray.
	 * Iterate over the height array and update ans:
	 * 		Add min⁡(meftmaxArray[i],rightMaxArray[i])−height[i] to ans.
	 */
	public static int trapLeftRightMaxArray(int[] height) {
		int ans = 0;
		if (height == null || height.length == 0) {
			return ans;
		}
		
		int[] leftMaxArray = new int[height.length];
		int[] rightMaxArray = new int[height.length];
		int leftMax = 0;
		int rightMax = 0;
		
		// calculate left max array
		for (int i = 0; i < height.length; i++) {
			leftMax = Math.max(leftMax, height[i]);
			leftMaxArray[i] = leftMax;
		}
		
		// calculate right max array
		for (int j = height.length - 1; j >= 0; j--) {
			rightMax = Math.max(rightMax, height[j]);
			rightMaxArray[j] = rightMax;
		}
		
		// calculate answer
		for (int k = 0; k < height.length; k++) {
			ans += Math.min(leftMaxArray[k], rightMaxArray[k]) - height[k];
		}
		
		return ans;
	}


	public static void main(String[] args) {
		
		int result; 
		
		result = trapTwoPointers(new int[] { 0,1,0,2,1,0,1,3,2,1,2,1});		
		System.out.println("Trapping rain water of [0,1,0,2,1,0,1,3,2,1,2,1] (answer should be 6) : " + result);

		result = trapLeftRightMaxArray(new int[] { 0,1,0,2,1,0,1,3,2,1,2,1});		
		System.out.println("Trapping rain water of [0,1,0,2,1,0,1,3,2,1,2,1] (answer should be 6) : " + result);
		
	}
}
