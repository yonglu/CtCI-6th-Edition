package Sort_Squares_977;

import java.util.*;


/* Leetcode #977 - Squares of a Sorted Array
 * 
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * 
 * Given an array of integers A sorted in non-decreasing order, return an 
 * array of the squares of each number, also in sorted non-decreasing order.
 * 
 * Example 1:
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * 
 * Example 2:
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */

public class SortSquares_977 {


	public static int[] sortedSquares(int[] numbers)  {

		if (numbers == null || numbers.length == 0) {
			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		int [] result = new int[numbers.length];
		
		int begin = 0;
		int end = numbers.length - 1;
		int index = end;
		
		while (begin <= end) {			
			int beginValue = numbers[begin] * numbers[begin];
			int endValue = numbers[end] * numbers[end];
			
			if ( beginValue < endValue) {
				result[index--] = endValue;
				end--;
			} else {
				result[index--] = beginValue;
				begin++;
			}
		}
		
		return result;
	}


	public static void main(String[] args) {
		
		int[] results = sortedSquares(new int[] {-4,-1,0,3,10});
		
		System.out.println("Sorted Square [-4,-1,0,3,10] :");
		for (int i : results) {
			System.out.println(i);
		}

		results = sortedSquares(new int[] {-7,-3,2,3,11});
		
		System.out.println("Sorted Square [-7,-3,2,3,11] :");
		for (int i : results) {
			System.out.println(i);
		}		
	}
}
