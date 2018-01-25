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

public class MyAnswer {	
	
	public static List<String> findWords(char[][] board, String[] words) {
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
      
      List<String> wordsFound = findWords(board, words);
      for (String word : wordsFound) {
    	  System.out.println(word);
      }
	}

}
