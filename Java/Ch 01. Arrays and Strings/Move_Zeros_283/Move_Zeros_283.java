package Move_Zeros_283;

import java.util.HashMap;
import java.util.Map;

/*
 * Leetcode # 283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the 
relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
 * 
 */

public class Move_Zeros_283 {
	
	/*
	 * 
			Input: [0,1,0,3,12]
			Output: [1,3,12,0,0]
					
			Two pointer approach
				- Read pointer reads the whole string from beginning
				- Write pointer write non zero character.
				- After finish reading in for loop, write zero to the rest starting from write pointer.
	 * 
	 */
	public static void moveZeroes(int[] nums) {
		
		if (nums == null || nums.length == 0 || nums.length == 1) {
			return;
		}
		
		int readPtr = 0;
		int writePtr = 0;
		
		while (readPtr < nums.length) {
			if (nums[readPtr] != 0) {
				nums[writePtr] = nums[readPtr];
				writePtr++;
			}
			readPtr++;
		}
		
		while (writePtr < nums.length) {
			nums[writePtr] = 0;
			writePtr++;
		}
		
		return;
	}


	/*
	 * 
			Input: [0,1,0,3,12]
			Output: [0,0,1,3,12]
				Similar to above algorithm but start from end.
	 * 
	 */
	public static void moveZeroesToFront(int[] nums) {
		
		if (nums == null || nums.length == 0 || nums.length == 1) {
			return;
		}
		
		int readPtr = nums.length - 1;
		int writePtr = nums.length - 1;
		
		while (0 <= readPtr) {
			if (nums[readPtr] != 0) {
				nums[writePtr] = nums[readPtr];
				writePtr--;
			}
			readPtr--;
		}
		
		while ( 0 <= writePtr) {
			nums[writePtr] = 0;
			writePtr--;
		}
		
		return;
	}
	
	
	/*
	 * 
			Input: [0,1,0,3,1,12]
			Output: [0,0,1,1,3,12]
				- Process the string from end and move non zero or one to end
				- Keep counting zero and one
				- After finish reading in for loop, write zero and one to the rest of array.
	 * 
	 */
	public static void moveZeroesAndOnesToFront(int[] nums) {
		
		if (nums == null || nums.length == 0 || nums.length == 1) {
			return;
		}
		
		int readPtr = nums.length - 1;
		int writePtr = nums.length - 1;
		
		int zeroCount = 0;
		int oneCount = 0;
		
		while (0 <= readPtr) {
			if (nums[readPtr] == 0) {
				zeroCount++;
				readPtr--;
				continue;
			}
			if (nums[readPtr] == 1) {
				oneCount++;
				readPtr--;
				continue;
			}
			nums[writePtr] = nums[readPtr];
			writePtr--;
			readPtr--;
		}
		
		for (int i = 0; i < oneCount; i++) {
			nums[writePtr] = 1;
			writePtr--;
		}
		
		for (int i = 0; i < zeroCount; i++) {
			nums[writePtr] = 0;
			writePtr--;
		}
		return;
	}
	
	public static void main(String[] args) {
		
		int[] nums = new int[] {0, 1, 0, 3, 12 };
		moveZeroes(nums);

		System.out.println("Move zeros to back, expect: 1, 3, 12, 0, 0"); 
		for (int i : nums) {
			System.out.print(i + ", "); 
		}

		System.out.println(" "); 

		nums = new int[] {0, 1, 0, 3, 12 };
		moveZeroesToFront(nums);
		
		System.out.println("Move zeros to front, expect: 0, 0, 1, 3, 12"); 
		for (int i : nums) {
			System.out.print(i + ", "); 
		}
		
		System.out.println(" "); 

		nums = new int[] {0,1,0,3,1,12 };
		moveZeroesAndOnesToFront(nums);
		
		System.out.println("Move zeros and Ones to front, expect: 0, 0, 1, 1, 3, 12"); 
		for (int i : nums) {
			System.out.print(i + ", "); 
		}
		
	}

}
