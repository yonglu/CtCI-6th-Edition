package Word_Search_79;

/*
 * Leetcode # 79. Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

public class word_Search_79 {

	static int[][] delta = new int[][] {
		{0, -1},
		{0, 1},
		{-1, 0},
		{1, 0}
	};
	
    public static boolean wordSearchExist(char[][] board, String word) { 
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
        	return false;
        }
         
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if (board[i][j] == word.charAt(0)) {
        			boolean[][] visited = new boolean[board.length][board[0].length];
        			visited[i][j] = true;
        			if (wordSearchExist(board, word, visited, i, j, 1)) {
        				return true;
        			}
        		}
        	}
        }
        
        return false;
     }
    
    private static boolean wordSearchExist(char[][] board, String word,
    		boolean[][] visited, int row, int col, int index) { 
        if (board == null || board.length == 0 || word == null) {
        	return false;
        }
        
        if (index > word.length()) {
        	return false;
        } else if (index == word.length()) {
        	return true;
        }
        
        for (int i = 0; i < delta.length; i++) {
    		int newRow = row + delta[i][0];
    		int newCol = col + delta[i][1];
    		if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length &&
    				!visited[newRow][newCol] && board[newRow][newCol] == word.charAt(index)) {
    			if (index == word.length() - 1) {
    				return true;
    			}
    			visited[newRow][newCol] = true;
    			if (wordSearchExist(board, word, visited, newRow, newCol, index+1)) {
    				return true;
    			}  
    			visited[newRow][newCol] = false;
    		}
        }
        
        return false;
     }
    
    
	public static void main(String[] args) {
	      char board[][]=  new char[][] {
	    	    {'A', 'B', 'C', 'E'},
	            {'S', 'F', 'C', 'S'},
	            {'A', 'D', 'E', 'E'}
	           };
	      boolean isExist = wordSearchExist(board, "ABCCED");
	      System.out.println("ABCCED exist? :" + isExist);   

	      isExist = wordSearchExist(board, "SEE");
	      System.out.println("SEE exist? :" + isExist);   
	      
	      isExist = wordSearchExist(board, "ABCB");
	      System.out.println("ABCB exist? :" + isExist);   
	      
	      char board2[][]=  new char[][] {
	    	    {'A', 'B', 'C', 'E'},
	            {'S', 'F', 'E', 'S'},
	            {'A', 'D', 'E', 'E'}
	           };
	      isExist = wordSearchExist(board2, "ABCESEEEFS");
	      System.out.println("ABCESEEEFS exist? :" + isExist);   
	      
	}
}
