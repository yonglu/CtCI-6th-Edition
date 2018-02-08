package BoogleBoardWordSearch;

import java.util.*;

/*
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example, given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Return ["eat","oath"].
*/

/*
 *   Solution with DFS and Trie.  https://www.programcreek.com/2014/06/leetcode-word-search-ii-java/
 *   If find a predetermined word, Trie doesn't have advantage, but if need to find all possible word on board, 
 *   then Trie is more efficient because if can check prefix and backtrack earlier.
 */

public class MyAnswerDFS {	
	
	private static int[][] deltas = {
			{-1, -1}, {-1,0}, {-1, 1}, {0,-1},{0,1}, {1, -1}, {1,0}, {1, 1}
	};
	
	public static List<String> findWords2(char[][] board, String[] words) {
		ArrayList<String> result = new ArrayList<String>();
	 
		int m = board.length;
		int n = board[0].length;
	 
		for (String word : words) {
			boolean flag = false;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					char[][] newBoard = new char[m][n];
					for (int x = 0; x < m; x++)
						for (int y = 0; y < n; y++)
							newBoard[x][y] = board[x][y];
	 
					if (dfs(newBoard, word, i, j, 0)) {
						flag = true;
					}
				}
			}
			if (flag) {
				result.add(word);
			}
		}
	 
		return result;
	}
	 
	public static boolean dfs(char[][] board, String word, int i, int j, int k) {
		int m = board.length;
		int n = board[0].length;
	 
		if (i < 0 || j < 0 || i >= m || j >= n || k > word.length() - 1) {
			return false;
		}
	 
		if (board[i][j] == word.charAt(k)) {
			char temp = board[i][j];
			board[i][j] = '#';
	 
			if (k == word.length() - 1) {
				return true;
			} else if (dfs(board, word, i - 1, j, k + 1)
					|| dfs(board, word, i + 1, j, k + 1)
					|| dfs(board, word, i, j - 1, k + 1)
					|| dfs(board, word, i, j + 1, k + 1)) {
				board[i][j] = temp;
				return true;
			}
	 
		} else {
			return false;
		}
	 
		return false;
	}	
	
	public static class Point {
		public int row;
		public int col;
		Point(int r, int c) {
			row = r;
			col = c;
		}
	}
	
	public static boolean findWords(char[][] board, String word, int row, int col, 
			int index, Set<Point> visited) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
				index >= word.length() ) {
			return false;
		}
		char c = word.charAt(index);
		
/*		
 * 	See the improve block below		
 * 
 * 		// if it is last character, that's the end case
		if (index == word.length() - 1) {
			if (c == board[row][col]) {
				return true;
			} else {
				return false;
			}
		} else {
			visited.add(new Point(row, col));
			if (c == board[row][col]) {
				for (int[] delta : deltas) {
					int newRow = row + delta[0];
					int newCol = col + delta[1];
					if (!visited.contains(new Point(newRow, newCol))) {
						// if find our word, no need to search other paths
						if (findWords(board, word, newRow, newCol, index+1, visited)) {
							return true;
						}
					}
				}
			} else {
				return false;
			}			
		} 
*/		
		visited.add(new Point(row, col));
		if (c == board[row][col]) {
			if (index == word.length() - 1) {
				return true;
			} else {
			   for (int i = -1; i <= 1; i++) {
			      for (int j= -1; j<=1; j++) {
			         if (i==0 && j==0) continue;
	               int newRow = row + i;
	               int newCol = col + j;
	               if (!visited.contains(new Point(newRow, newCol))) {
	                  // if find our word, no need to search other paths
	                  if (findWords(board, word, newRow, newCol, index+1, visited)) {
	                     return true;
	                  }
	               }
			         
			      }
			   }
/*				for (int[] delta : deltas) {
					int newRow = row + delta[0];
					int newCol = col + delta[1];
					if (!visited.contains(new Point(newRow, newCol))) {
						// if find our word, no need to search other paths
						if (findWords(board, word, newRow, newCol, index+1, visited)) {
							return true;
						}
					}
				}	
*/			
/*          if (findWords(board, word, row-1, col-1, index+1, visited) ||
            findWords(board, word, row-1, col, index+1, visited) ||
            findWords(board, word, row-1, col+1, index+1, visited) ||
            findWords(board, word, row, col-1, index+1, visited) ||
            findWords(board, word, row, col+1, index+1, visited) ||
            findWords(board, word, row+1, col-1, index+1, visited) ||
            findWords(board, word, row+1, col, index+1, visited) ||
            findWords(board, word, row+1, col+1, index+1, visited)) {
         return true;
      } 
*/			   
			}
		} else {
			return false;
		}
		
		return false;
	}
	
	public static Set<String> findWords(char[][] board, String[] words) {
		if (board == null || words == null || 
				(board.length == 0 && board[0].length == 0) || words.length == 0) {
			return null;
		}
		// Use set instead of map so no duplicate words
		Set<String> wordsFound = new HashSet<String>();
		for (String word : words) {
			char c = word.charAt(0);
			for (int i=0; i< board.length; i++) {
				for (int j=0; j<board[0].length; j++) {
					if (c == board[i][j]) {
						Set<Point> visited = new HashSet<Point>();
						if (findWords(board, word, i, j, 0, visited)) {
							wordsFound.add(word);
						}						
					}
				}
			}
		}
		
		return wordsFound;
	}	
	
	public static void main(String[] args) {
      char board[][] = new char[][] {  
    	  	{'o','a','a','n'},
    	  	{'e','t','a','e'},
    	  	{'i','h','k','r'},
    	  	{'i','f','l','v'}
      };
      
      String[] words = new String[] {
    		  "oath","pea","eat","rain"
      };
      
      Set<String> wordsFound = findWords(board, words);
      for (String word : wordsFound) {
    	  System.out.println(word);
      }
	}

}
