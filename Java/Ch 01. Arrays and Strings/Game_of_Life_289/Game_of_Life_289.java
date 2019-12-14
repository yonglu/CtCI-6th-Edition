package Game_of_Life_289;

import java.util.*;


/* 
 * 
 * Leetcode #289. Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton 
devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts 
with its eight neighbors (horizontal, vertical, diagonal) using the following four rules 
(taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state. 
The next state is created by applying the above rules simultaneously to every cell in the current state, 
where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

 */

public class Game_of_Life_289 {

	static int[][] neighbors = new int[][] {
		{-1, -1},
		{-1, 0},
		{-1, 1},
		{0, -1},
		{0, 1},
		{1, -1},
		{1, 0},
		{1, 1}
	};

	/* Followup:
	 * 
	 *   In this question, we represent the board using a 2D array. In principle, the board is infinite, 
	 *   which would cause problems when the active area encroaches the border of the array. How would you 
	 *   address these problems?
	 *   
	 *   1. Just the location of only the live cells 
	 *     OR
	 *   2. In order for us to update a particular cell, we only have to look at its 8 neighbors which 
	 *      essentially lie in the row above and below it. So, for updating the cells of a row, 
	 *      we just need the row above and the row below. Thus, we read one row at a time from the 
	 *      file and at max we will have 3 rows in memory. We will keep discarding rows that are 
	 *      processed and then we will keep reading new rows from the file, one at a time.
     */
	
	/*
	 *  The problem could also be solved in-place. O(M×N) space complexity could be too expensive when the 
	 *  board is very large. We only have two states live(1) or dead(0) for a cell. We can use some dummy 
	 *  cell value to signify previous state of the cell along with the new changed value.
	 *  
	 *  If the value of the cell was 1 originally but it has now become 0 after applying the rule, then 
	 *  we can change the value to -1. The negative sign signifies the cell is now dead(0) but the 
	 *  magnitude signifies the cell was a live(1) cell originally.
	 *  
	 *  Also, if the value of the cell was 0 originally but it has now become 1 after applying the rule, 
	 *  then we can change the value to 2. The positive sign signifies the cell is now live(1) 
	 *  but the magnitude of 2 signifies the cell was a dead(0) cell originally.
	 */
	public static void gameOfLifeInPlace(int[][] board)  {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		
		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board[0].length; j++) {
				int neighborLive = countLiveNeighborInPlace(i, j, board);
				if (neighborLive < 2 && board[i][j] == 1) {
					board[i][j] = -1;
				} else if (neighborLive == 3 && board[i][j] == 0) {
					board[i][j] = 2;					
				} else if (neighborLive > 3 && board[i][j] == 1) {
					board[i][j] = -1;
				}
			}
		}

		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == -1) {
					board[i][j] = 0;
				} else if (board[i][j] == 2) {
					board[i][j] = 1;
				}
            }
        }
        
		return;        
	}
	
	private static int countLiveNeighborInPlace(int row, int col, int[][] board) {
		int liveNeighbors = 0;
		
		for (int i = 0; i < neighbors.length; i++) {
			int newRow = row + neighbors[i][0];
			int newCol = col + neighbors[i][1];
			if (newRow >= 0 && newRow < board.length &&
				newCol >= 0 && newCol < board[0].length &&
				(board[newRow][newCol] == 1 || board[newRow][newCol] == -1)) {
				liveNeighbors++;
			}
		}
		
		return liveNeighbors;
	}

	
	public static void gameOfLife(int[][] board)  {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		
		int[][] newBoard = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board[0].length; j++) {
				int neighborLive = countLiveNeighbor(i, j, board);
				if (neighborLive < 2) {
					newBoard[i][j] = 0;
				} else if (neighborLive == 2) {
					newBoard[i][j] = board[i][j];
				} else if (neighborLive == 3) {
					newBoard[i][j] = 1;					
				} else if (neighborLive > 3) {
					newBoard[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board[0].length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
        
		return;        
	}
	
	private static int countLiveNeighbor(int row, int col, int[][] board) {
		int liveNeighbors = 0;
		
		for (int i = 0; i < neighbors.length; i++) {
			int newRow = row + neighbors[i][0];
			int newCol = col + neighbors[i][1];
			if (newRow >= 0 && newRow < board.length &&
				newCol >= 0 && newCol < board[0].length &&
				board[newRow][newCol] == 1) {
				liveNeighbors++;
			}
		}
		
		return liveNeighbors;
	}

	public static void main(String[] args) {

		int[][] board = new int[][]
		{
			{0, 1, 0},
			{0, 0, 1},
			{1, 1, 1},
			{0, 0, 0}
		};
		
		gameOfLifeInPlace(board);	
//		gameOfLife(board);	
		System.out.println("Game of Life: ");
		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + ", ");
			}
			System.out.println("");
		}

	}
}
