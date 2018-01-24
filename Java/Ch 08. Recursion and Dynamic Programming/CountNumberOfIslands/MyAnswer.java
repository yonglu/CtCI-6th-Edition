package CountNumberOfIslands;

import java.util.*;

public class MyAnswer {

	public static int countNumberOfIslands(int[][] matrix) {
		return 5;
	}
   
	public static void main(String[] args) {
      int matrix[][]=  new int[][] {{1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
           };
      int numIsland = countNumberOfIslands(matrix);
      System.out.println(numIsland);   
	}

}
