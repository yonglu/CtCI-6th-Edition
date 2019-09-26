package CountNumberOfIslands;

import java.util.*;

// Leetcode # 200
// https://leetcode.com/problems/number-of-islands/
//
// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
// An island is surrounded by water and is formed by connecting adjacent lands horizontally 
// or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//Example 1:
//
//Input:
//11110
//11010
//11000
//00000
//
//Output: 1
//
//Example 2:
//
//Input:
//11000
//11000
//00100
//00011
//
//Output: 3

public class NumberOfIslands_09_25_2019 {

	private static int[][] deltas = {
			{-1,0}, {1,0}, {0,-1},{0,1}
	};
	
	public static class Point {
		public int row;
		public int col;
		Point(int r, int c) {
			row = r;
			col = c;
		}
	}
	
    public static int numIslands(char[][] grid) {
		int numOfIslands = 0;
		if(grid == null || (grid.length == 0 && grid[0].length == 0)) {
			return 0;
		}
		
		return numOfIslands;   	
    }
	
	public static int countNumberOfIslands(int[][] matrix) {
		if(matrix == null || (matrix.length == 0 && matrix[0].length == 0)) {
			return 0;
		}
		
		boolean[] visited = new boolean[matrix.length * matrix[0].length];
		Arrays.fill(visited, false);
		int numOfIslands = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				// If a cell with value 1, and not visited yet, then new island found 
				// Visit all cells in this island and increment island count
				if (!visited[i*matrix.length + j] && matrix[i][j] == 1) {
					numOfIslands++;
					markNeighborIslandsDFS(matrix, i, j, visited);
				}
			}
		}
		
		return numOfIslands;
	}
   
	public static void markNeighborIslandsDFS(int [][] matrix, int row, int col, boolean[] visited) {
		if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
			return;
		}
		visited[row * matrix.length + col] = true;
		if (matrix[row][col] == 0) {
			return;
		}
		
		for (int[] delta : deltas) {
			int newRow = row + delta[0];
			int newCol = col + delta[1];
			if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && 
					!visited[newRow * matrix.length + newCol]) {
				markNeighborIslandsDFS(matrix, newRow, newCol, visited);
			}
		}
		return;
	}
	
	public static void markNeighborIslandsBFS(int [][] matrix, int row, int col, boolean[] visited) {
		if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
			return;
		}
		
		Queue<Point> queue = new LinkedList<Point>(); 
		queue.add(new Point(row, col));
		visited[row * matrix.length + col] = true;
		
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			for (int[] delta : deltas) {
				int newRow = point.row + delta[0];
				int newCol = point.col + delta[1];
				if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && 
						!visited[newRow * matrix.length + newCol]) {
					visited[newRow * matrix.length + newCol] = true;
					if (matrix[newRow][newCol] == 1) {
						queue.add(new Point(newRow, newCol));
					}
				}
			}			
		}
		
		return;
	}
	
	
	public static void main(String[] args) {
      int matrix[][]=  new int[][] {
    	    {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
           };
      int numIsland = countNumberOfIslands(matrix);
      System.out.println(numIsland);   
      
      char grid[][]=  new char[][] {
  	      {'1', '1', '0', '0', '0'},
          {'0', '1', '0', '0', '1'},
          {'1', '0', '0', '1', '1'},
          {'0', '0', '0', '0', '0'},
          {'1', '0', '1', '0', '1'}
         };
         numIsland = numIslands(grid);
         System.out.println(numIsland);   
	}

}
