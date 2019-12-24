package Merge_Sorted_Array_88;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Leetcode # 771. Jewels and Stones

You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  
Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, 
so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3

Example 2:

Input: J = "z", S = "ZZ"
Output: 0

 * 
 */

public class Merge_Sorted_Array_88 {
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 
				|| nums1.length < m + n) {
			return;
		}

		int index = m + n - 1;
		int nums1Idx = m - 1;
		int nums2Idx = n - 1;  
		
		while (nums1Idx >=0 && nums2Idx >= 0) {
			if (nums1[nums1Idx] > nums2[nums2Idx]) {
				nums1[index--] = nums1[nums1Idx--];
			} else {
				nums1[index--] = nums2[nums2Idx--];				
			}
		}
		
		// If nums2 has left over, copy to nums1 
		if (nums2Idx >= 0) {
			while (nums2Idx >=0) {
				nums1[index--] = nums2[nums2Idx--];
			}
		}
		
		// Don't need to worry if nums1 has left over, because they are in the right position already.
		
		return;
	}


	public static void main(String[] args) {
		
		int[] nums1 = new int[] {
			2, 3, 5, 7, 0, 0
		};
		
		int[] nums2 = new int[] {
			1, 4
		};

		merge(nums1, 4, nums2, 2);
		System.out.println(Arrays.toString(nums1));
		
	}

}
