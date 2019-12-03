package Max_Area_Of_Islands_695;

import java.util.*;

/*
 * Leetcode # 695. Max Area of Island

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's 
(representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, 
the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. 

Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.*/


public class Max_Area_Of_Islands_696_12_2_2019 {
    
    public static int maxAreaOfIsland(int[][] grid) {
    	int maxArea = 0;
    	if (grid == null || (grid.length == 0 && grid[0].length == 0)) {
    		return maxArea;
    	}
    	
    	// default is false
    	boolean[][] visited = new boolean[grid.length][grid[0].length];
    	
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[0].length; j++) {
    			if (!visited[i][j])  {
    				maxArea = Math.max(maxArea, countArea(grid, visited, i, j));
    			}
    		}
    	}
    	
    	return maxArea;
    }
    
    private static int countArea(int[][] grid, boolean[][] visited, int row, int col) {

    	if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0 || visited[row][col]) {
    		return 0;
    	}

    	visited[row][col] = true;
    	
    	return 1 + countArea(grid, visited, row - 1, col) + countArea(grid, visited, row, col - 1 ) +
    				countArea(grid, visited, row + 1, col) + countArea(grid, visited, row, col + 1);
    }

	public static void main(String[] args) {
	           
	      int matrix[][] = new int [][] {
	    	    {0,0,1,0,0,0,0,1,0,0,0,0,0},
	            {0,0,0,0,0,0,0,1,1,1,0,0,0},
	            {0,1,1,0,1,0,0,0,0,0,0,0,0},
	            {0,1,0,0,1,1,0,0,1,0,1,0,0},
	            {0,1,0,0,1,1,0,0,1,1,1,0,0},
	            {0,0,0,0,0,0,0,0,0,0,1,0,0},
	            {0,0,0,0,0,0,0,1,1,1,0,0,0},
	            {0,0,0,0,0,0,0,1,1,0,0,0,0}
	       };
	       
	      int maxArea = maxAreaOfIsland(matrix);
	      System.out.println(maxArea);   

	      int matrix2[][] = new int[][] {
	    	  {0,0,0,0,0,0,0,0}
	      };
	      int maxArea2 = maxAreaOfIsland(matrix2);
	      System.out.println(maxArea2);   
	      
	      int matrix3[][] = new int[][] {
	    	  {0},
	    	  {1},
	    	  {1},
	    	  {1},
	    	  {0},
	    	  {0},
	    	  {1},
	    	  {1}
	      };
	      int maxArea3 = maxAreaOfIsland(matrix3);
	      System.out.println(maxArea3);   
	      
	}
}

