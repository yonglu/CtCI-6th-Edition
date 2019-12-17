package Rotate_Matrix_48;

import java.util.*;


/* Leetcode #48. Rotate Image
Medium

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

 */

/*
 * https://www.cnblogs.com/grandyang/p/4389572.html
 * http://buttercola.blogspot.com/2014/08/leetcode-rotate-image.html
 * buttercola.blogspot.com/2014/08/leetcode-rotate-image.html
 */

public class Rotate_Matrix_48 {

	/*

  [1,2,3] flip along diagnoal line	[1,4,7] reverse each row [7,4,1]
  [4,5,6] 			-> 				[2,5,8]					 [8,5,2]
  [7,8,9]	 [i][j = [j][i]			[3,6,9]					 [9,6,3]
	*/
	public static void rotate90(int[][] matrix)  {
		
		if (matrix == null || matrix.length == 0 ) {
			return;
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[0].length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			reverse(matrix, true, i);
		}
		return;
	}

	/*

	  [1,2,3] Reverse each col	[7,8,9] reverse each row [9,8,7]
	  [4,5,6] 		-> 			[4,5,6]					 [6,5,4]
	  [7,8,9]	 				[1,2,3]					 [3,2,1]
		*/
		public static void rotate180(int[][] matrix)  {
			
			if (matrix == null || matrix.length == 0 ) {
				return;
//				throw new java.lang.IllegalArgumentException("bad parameters");
			}

			for (int i = 0; i < matrix[0].length; i++) {
				reverse(matrix, false, i);
			}
			for (int i = 0; i < matrix.length; i++) {
				reverse(matrix, true, i);
			}
			
			return;
		}

	/*

	  [1,2,3] flip along diagnoal line	[1,4,7] reverse each col [3,6,9]
	  [4,5,6] 			-> 				[2,5,8]					 [2,5,8]
	  [7,8,9]	 [i][j = [j][i]			[3,6,9]					 [1,4,7]
		*/
		public static void rotate270(int[][] matrix)  {
			
			if (matrix == null || matrix.length == 0 ) {
				return;
//				throw new java.lang.IllegalArgumentException("bad parameters");
			}
			
			for (int i = 0; i < matrix.length; i++) {
				for (int j = i + 1; j < matrix[0].length; j++) {
					int temp = matrix[i][j];
					matrix[i][j] = matrix[j][i];
					matrix[j][i] = temp;
				}
			}
			
			for (int i = 0; i < matrix.length; i++) {
				reverse(matrix, false, i);
			}
			return;
		}
	
	private static void reverse(int[][] matrix, boolean isRow, int k) {
		int l = 0;
		int r = 0;
		if (isRow) {
			r = matrix.length - 1;
			while (l < r) {
				int temp = matrix[k][l];
				matrix[k][l] = matrix[k][r];
				matrix[k][r] = temp;		
				l++;
				r--;
			}
		} else {
			r = matrix[0].length - 1;
			while (l < r) {
				int temp = matrix[l][k];
				matrix[l][k] = matrix[r][k];
				matrix[r][k] = temp;
				l++;
				r--;
			}			
		}
	}
		
	public static void main(String[] args) {
		
		int[][] matrix1 = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		rotate90(matrix1);	
		System.out.println("Rotate Matrix1 90 degrees is " + Arrays.deepToString(matrix1));

		int[][] matrix2 = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 16}
		};
		
		rotate90(matrix2);	
		System.out.println("Rotate Matrix2 90 degress is " + Arrays.deepToString(matrix2));

		int[][] matrix3 = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		rotate180(matrix3);	
		System.out.println("Rotate Matrix3 180 degrees is " + Arrays.deepToString(matrix3));

		int[][] matrix4 = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 16}
		};
		
		rotate180(matrix4);	
		System.out.println("Rotate Matrix4 180 degress is " + Arrays.deepToString(matrix4));
				
		int[][] matrix5 = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		rotate270(matrix5);	
		System.out.println("Rotate Matrix5 270 degrees is " + Arrays.deepToString(matrix5));

		int[][] matrix6 = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 16}
		};
		
		rotate270(matrix6);	
		System.out.println("Rotate Matrix6 270 degress is " + Arrays.deepToString(matrix2));
		
	}
}
