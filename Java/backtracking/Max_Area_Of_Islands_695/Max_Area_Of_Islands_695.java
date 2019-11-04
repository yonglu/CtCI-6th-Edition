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

public class Max_Area_Of_Islands_695 {
	
	static int[][] deltas = new int[][] {
		{-1, 0},
		{0, -1},
		{0, 1},
		{1, 0}
	}; 
	
    public static int maxAreaOfIsland(int[][] grid) {
    	if (grid == null || grid.length == 0 || grid[0].length == 0) {
    		return 0;
    	}
    	boolean visited[][] = new boolean[grid.length][grid[0].length];
    	for (int i=0; i < grid.length; i++) {
    		Arrays.fill(visited[i], false);
    	}
    	
    	int maxArea = Integer.MIN_VALUE;
    	
    	for (int i=0; i<grid.length; i++) {
    		for (int j=0; j<grid[0].length; j++) {
    			if(grid[i][j] == 1 && !visited[i][j]) {
    				List<Integer> tempArea = new ArrayList<Integer>();
    				tempArea.add(0);
    				countArea(grid, visited, i, j, tempArea);
    				if (tempArea.get(0) > maxArea) {
    					maxArea = tempArea.get(0);
    				}
    			}
    		}
    	}
    	
    	if (maxArea == Integer.MIN_VALUE) {
    		maxArea = 0;
    	}
    	
    	return maxArea;
    }

    private static void countArea(int[][] grid, boolean[][] visited,
    		int row, int col, List<Integer> tempArea) {
    	int temp = tempArea.get(0);
    	tempArea.clear();
    	tempArea.add(temp + 1);
    	
    	visited[row][col] = true;
    	
    	for (int[] delta : deltas) {
    		int newRow = row + delta[0];
    		int newCol = col + delta[1];
    		if (newCol >= 0 && newCol < grid[0].length &&
    			newRow >= 0 && newRow < grid.length &&
    			!visited[newRow][newCol] &&
    			grid[newRow][newCol] == 1)
    		countArea(grid, visited, newRow, newCol, tempArea); 			
    	}    	
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

