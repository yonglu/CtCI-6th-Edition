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

public class MyAnswerTrie {	
	
    private static Set<String> result = new HashSet<String>(); 
    
    public static Set<String> findWords(char[][] board, String[] words) {
 
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }
 
        int m=board.length;
        int n=board[0].length;
 
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                boolean[][] visited = new boolean[m][n];
                dfs(board, visited, "", i, j, trie);
            }
        }
 
        return result;
    }
 
    public static void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie){
        int m=board.length;
        int n=board[0].length;
 
        if(i<0 || j<0||i>=m||j>=n){
            return;
        }
 
        if(visited[i][j])
            return;
 
        str = str + board[i][j];
 
        if(!trie.startsWith(str))
            return;
 
        if(trie.search(str)){
            result.add(str);
        }
 
        visited[i][j]=true;
        dfs(board, visited, str, i-1, j, trie);
        dfs(board, visited, str, i+1, j, trie);
        dfs(board, visited, str, i, j-1, trie);
        dfs(board, visited, str, i, j+1, trie);
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
