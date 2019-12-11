package Search_in_Rotated_Sorted_Array_33;

import java.util.*;

/* Leetcode # 33. Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/

public class Search_in_Rotated_Sorted_Array_33 {

    public static int search(int[] nums, int target)  {
    	int ans = -1;
    	
    	if (nums == null || nums.length == 0 ) {
    		return ans;
    	}
    	
    	ans = search(nums, target, 0, nums.length - 1);
    	
    	return ans;
    }

    private static int search(int[] nums, int target, int low, int high)  {
    	
    	while (low <= high) {
        	int mid = (low + high) / 2;
        	if (nums[mid] == target) {
        		return mid;
        	}
        	
        	// Mistake in using "<" instead of "<=".  Failed the case of ([3, 1], 1)
        	// because mid is round down already. if we don't include "=" then fails the corner case. 
        	if (nums[low] <= nums[mid]) { // means the first half is in order
        		if (nums[low] <= target && nums[mid] > target) { // check if target is in the in-order half
        			high = mid - 1;
        		} else {
        			low = mid + 1;
        		}
        	} else { // means the second half is in order
        		if (nums[mid] < target && nums[high] >= target) { // check if target is in the in-order half
        			low = mid + 1;
        		} else {
        			high = mid - 1;
        		}
        	}
    		
    	}
    	
    	return -1;
 /*   	
    	int ans = -1;
    	
    	if (low > high) {
    		return -1;
    	}
    	
    	int mid = (low + high) / 2;
    	if (nums[mid] == target) {
    		return mid;
    	}
    	
    	if (nums[low] < nums[mid]) { // means the first half is in order
    		if (nums[low] <= target && nums[mid] > target) {
    			return search(nums, target, low, mid - 1);
    		} else {
    			return search(nums, target, mid + 1, high);
    		}
    	} else { // means the second half is in order
    		if (nums[mid] < target && nums[high] >= target) {
    			return search(nums, target, mid + 1, high);
    		} else {
    			return search(nums, target, low, mid - 1);
    		}
    	}
    */
    }
    
    public static void main(String[] args) {
		int ans;
		ans = search(new int[] {4,5,6,7,0,1,2}, 0);		
		System.out.println("Search [4,5,6,7,0,1,2] for 0 is : " + ans);

		ans = search(new int[] {4,5,6,7,0,1,2}, 3);		
		System.out.println("Search [4,5,6,7,0,1,2] for 0 is : " + ans);

		ans = search(new int[] {3,1}, 1);		
		System.out.println("Search [3,1] for 1 is : " + ans);		
	}	
}
