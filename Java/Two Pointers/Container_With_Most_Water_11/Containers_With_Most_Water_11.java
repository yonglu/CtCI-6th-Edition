package Container_With_Most_Water_11;

import java.util.*;

/* Leetcode 11. Container With Most Water

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2. 

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water 
(blue section between index 1 and 8, area = 7 * 7 = 49) the container can contain is 49.


Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */

public class Containers_With_Most_Water_11 {

	/*
	 * The area formed between the lines will always be limited by the height of the shorter line. 
	 * Further, the farther the lines, the more will be the area obtained.
	 * 
	 * We take two pointers, one at the beginning and one at the end of the array constituting 
	 * the length of the lines. Further, we maintain a variable areaMax to store the maximum 
	 * area obtained till now. At every step, we find out the area formed between them, 
	 * update areaMax and move the pointer pointing to the shorter line towards the other end by one step.
	 */
	public static int maxArea(int[] height) {
		int areaMax = 0;
		if (height == null || height.length == 0) {
			return areaMax;
		}
		
		int leftIndex = 0;
		int rightIndex = height.length - 1;
		
		while (leftIndex < rightIndex) {
			int area = (rightIndex - leftIndex) * Math.min(height[leftIndex], height[rightIndex]);
			if (area > areaMax) {
				areaMax = area;
			}
			if (height[leftIndex] > height[rightIndex]) {
				rightIndex--;
			} else {
				leftIndex++;
			}
		}
		
		return areaMax;
	}

	public static void main(String[] args) {
		
		int result; 
		
		result = maxArea(new int[] {1,8,6,2,5,4,8,3,7});		
		System.out.println("Containers with most water of [1,8,6,2,5,4,8,3,7] (answer should be 49) : " + result);

	}
}
