package Search_a_2D_Matrix_74;

import java.util.*;


/* Leetcode #74. Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has 
the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */

public class Search_a_2D_Matrix_74 {


	public static boolean searchMatrix(int[][] matrix, int target)  {
		
		if (matrix == null || matrix.length == 0 ) {
			return false;
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		int r = 0;
		int c = matrix[0].length - 1;
		while (r < matrix.length && c >= 0) {
			if (matrix[r][c] == target) {
				return true;
			}
			if (matrix[r][c] < target) {
				r++;
			} else {
				c--;
			}
		}
		
		return false;		
	}

	public static void main(String[] args) {
		
		int[][] matrix = new int[][] {
			{1, 3, 5, 7},
			{10, 11, 16, 20},
			{23, 30, 34, 50}
		};

		boolean ans = searchMatrix(matrix, 3);	
		System.out.println("Found 3 in 2D matrix " + ans);

		ans = searchMatrix(matrix, 13);	
		System.out.println("Found 13 in 2D matrix " + ans);
		
	}
}
