package Find_First_And_Last_Position_Of_Element_In_Sorted_Array_34;

import java.util.*;

/* Leetcode # 34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in ascending order, find the starting and ending 
position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]


*/

public class Find_First_And_Last_Position_Of_Element_In_Sorted_Array_34 {

    public static int[] searchRange(int[] nums, int target)  {
    	int[] ans = new int[] {-1, -1};
    	
    	if (nums == null || nums.length == 0 ) {
    		return ans;
    	}
    	
    	int firstIndex = searchRange(nums, target, 0, nums.length - 1, true);
    	int lastIndex = searchRange(nums, target, 0, nums.length - 1, false);
    	
    	ans[0] = firstIndex;
    	ans[1] = lastIndex;
    	return ans;
    }

    public static int searchRange(int[] nums, int target, int start, int end, boolean isFirst)  {
    	int ans = -1;
    	if (start > end) {
    		return ans;
    	}
    	
    	int mid = (start + end) / 2;
    	
    	if (nums[mid] == target) {
    		if (((mid-1 < 0) || nums[mid-1] != target) && isFirst) {
    			return mid;
    		} else if (((mid+1 >= nums.length) || nums[mid+1] != target) && !isFirst) {
    			return mid;
    		} else if (isFirst) {
    			return searchRange(nums, target, start, mid-1, isFirst);
    		} else {
    			return searchRange(nums, target, mid + 1, end, isFirst);
    		}    		
    	} else if (nums[mid] < target) {
    		return searchRange(nums, target, mid+1, end, isFirst);
    	} else {
    		return searchRange(nums, target, start, mid-1, isFirst);    		
    	}
    }
    
    public static void main(String[] args) {
		int[] ans;
		ans = searchRange(new int[] {5,7,7,8,8,10}, 8);		
		System.out.println("Search [5,7,7,8,8,10] for 8 is (expected [3, 4]: " + ans[0] + ", " + ans[1]);

		ans = searchRange(new int[] {5,7,7,8,8,10}, 6);		
		System.out.println("Search [5,7,7,8,8,10] for 6 is (expected [-1, -1]: " + ans[0] + ", " + ans[1]);

	}	
}
