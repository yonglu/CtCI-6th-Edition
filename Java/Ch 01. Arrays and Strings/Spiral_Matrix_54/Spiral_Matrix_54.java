package Spiral_Matrix_54;

import java.util.*;


/* Leetcode #54. Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

 */

public class Spiral_Matrix_54 {


	public static List<Integer> spiralOrder(int[][] matrix)  {
		List<Integer> ans = new ArrayList<Integer>();
		
		if (matrix == null || matrix.length == 0 ) {
			return ans;
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		// do it layer-by-layer.  Suppose the current outer layer has top-left coordinates (r1, c1) 
		// and bottom-right coordinates (r2, c2).
		int r1 = 0;
		int c1 = 0;
		int r2 = matrix.length - 1;
		int c2 = matrix[0].length - 1;
		
		while (r1 <= r2 && c1 <= c2) {
			for (int i = c1; i <= c2; i++) 	ans.add(matrix[r1][i]);  // top side
			for (int i = r1+1; i <= r2; i++) ans.add(matrix[i][c2]); // right side
			
			//if r1 == r2 or c1 == c2, then no need to output the bottom and left side.
			if (r1 < r2 && c1 < c2) {
				for (int i = c2 - 1; i > c1; i--) ans.add(matrix[r2][i]);  // bottom side
				for (int i = r2; i > r1; i--) ans.add(matrix[i][c1]);  // left side
			}
			r1++;
			c1++;
			r2--;
			c2--;
		}
		

		return ans;
	}

	public static void main(String[] args) {
		
		int[][] matrix1 = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		List<Integer> list1 = spiralOrder(matrix1);	
		System.out.println("Spirla Matrix of matrix1 is " + list1.toString());

		int[][] matrix2 = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12}
		};
		
		List<Integer> list2 = spiralOrder(matrix2);	
		System.out.println("Spirla Matrix of matrix2 is " + list2.toString());

		int[][] matrix3 = new int[][] {
			{3},
			{2}
		};
		
		List<Integer> list3 = spiralOrder(matrix3);	
		System.out.println("Spirla Matrix of matrix3 is " + list3.toString());
		
	}
}
