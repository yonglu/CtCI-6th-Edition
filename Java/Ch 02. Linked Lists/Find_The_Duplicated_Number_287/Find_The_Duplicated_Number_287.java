package Find_The_Duplicated_Number_287;

import java.util.*;

/* Leetcode 287. Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
prove that at least one duplicate number must exist. Assume that there is only one duplicate 
number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2

Example 2:

Input: [3,1,3,4,2]
Output: 3

Note:

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.

 
*/

public class Find_The_Duplicated_Number_287 {

    public static int findDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }
    
    public static int findDuplicateUseSet(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }
  
    public static int findDuplicateCycleDetecion(int[] nums) {
    	if (  nums == null || nums.length == 0 ) {
    		return -1;
    	}
        // Find the intersection point of the two runners.
        // This proofs that cycle exists
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        // Thi would be the duplicate number.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }    
    
	public static void main(String[] args) {
		int ans;
		ans = findDuplicateCycleDetecion(new int[] {1,3,4,2,2});		
		System.out.println("Duplicate number in [1,3,4,2,2] is : " + ans);
		ans = findDuplicateCycleDetecion(new int[] {3,1,3,4,2});		
		System.out.println("Duplicate number in [3,1,3,4,2] is : " + ans);
		ans = findDuplicateCycleDetecion(new int[] {2,5,9,6,9,3,8,9,7,1});		
		System.out.println("Duplicate number in [2,5,9,6,9,3,8,9,7,1] is : " + ans);

		ans = findDuplicateSorting(new int[] {1,3,4,2,2});		
		System.out.println("Duplicate number in [1,3,4,2,2] is : " + ans);
		ans = findDuplicateSorting(new int[] {3,1,3,4,2});		
		System.out.println("Duplicate number in [3,1,3,4,2] is : " + ans);
		
		ans = findDuplicateUseSet(new int[] {1,3,4,2,2});		
		System.out.println("Duplicate number in [1,3,4,2,2] is : " + ans);
		ans = findDuplicateUseSet(new int[] {3,1,3,4,2});		
		System.out.println("Duplicate number in [3,1,3,4,2] is : " + ans);
	}
}
