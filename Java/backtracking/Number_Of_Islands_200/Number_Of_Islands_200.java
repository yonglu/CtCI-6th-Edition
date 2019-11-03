package Number_Of_Islands_200;

import java.util.*;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import FirstUniqueCharacterInString.FirstUniqueCharacterInString;
import sun.security.krb5.internal.rcache.MemoryCache;
import sun.security.util.Length;
import sun.security.x509.DeltaCRLIndicatorExtension;

/*
 * Leetcode # 200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are 
all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/

public class Number_Of_Islands_200 {

	private static int[][] delta = new int[][] {
		{-1, 0},
		{0, -1},
		{1, 0},
		{0, 1}
	};
	
    public static int numIslands(char[][] grid) { 
        if (grid == null) {
        	return 0;
        }
        
        int nums = 0;
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < visited.length; i++) {
        	Arrays.fill(visited[i], false);
        }
        
        for (int i = 0; i < grid.length; i++) {
        	for (int j = 0; j< grid[0].length; j++) {
        		if (!visited[i][j] && grid[i][j] == '1') {
        			// found an island
        			nums++;
        			// mark all surrounding island visited
        			markIslandsVisited(grid, visited, i , j);       			
        		}
        	}
        }

        return nums;
     }
   
    public static void markIslandsVisited(char[][] grid, boolean[][] visited, int row, int col) {

    	visited[row][col] = true;
    	
    	// checked all neightbors visited
    	for (int i = 0; i < delta.length; i++) {
    		int newRow = row + delta[i][0];
    		int newCol = col + delta[i][1];
    		if (newCol >= 0 && newCol < grid[0].length && 
    				newRow >= 0 && newRow < grid.length && 
    				!visited[newRow][newCol] && grid[newRow][newCol] == '1')
    			markIslandsVisited(grid, visited, newRow, newCol);   			
    	}
    	
    	return;
    }
    
	public static void main(String[] args) {
	      char matrix[][]=  new char[][] {
	    	    {'1', '1', '0', '0', '0'},
	            {'0', '1', '0', '0', '1'},
	            {'1', '0', '0', '1', '1'},
	            {'0', '0', '0', '0', '0'},
	            {'1', '0', '1', '0', '1'}
	           };
	      int numIsland = numIslands(matrix);
	      System.out.println(numIsland);   

	      char matrix2[][] =  new char[][] {
	    	    {'1', '1', '0', '1', '0'}	           
	      };
	      numIsland = numIslands(matrix2);
	      System.out.println(numIsland);   

	      char matrix3[][] =  new char[][] {
	    	    {'1'}, 
	    	    {'0'}, 
	    	    {'0'}, 
	    	    {'1'}, 
	    	    {'0'},
	    	    {'1'}
	      };
	      numIsland = numIslands(matrix3);
	      System.out.println(numIsland);   
	      
	      
	}
}
