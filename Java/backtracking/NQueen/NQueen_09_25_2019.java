package NQueen;

import java.util.*;

//N Queen Problem is the problem of placing N chess queens on an NxN chessboard 
//so that no two queens attack each other, for which solutions exist for all 
//natural numbers n except n=2 and n=3.
//
//A solution requires that no two queens share the same row, column, or diagonal.
//
// N Queen Problem can be solved using a recursive backtracking algorithm. 
//
// http://codepumpkin.com/n-queen-problem/

public class NQueen_09_25_2019 {

	public static List<List<String>> solveNQueens(int n) {
		List<List<String>> lists = new ArrayList<>();
		List<List<Pair>> pairLists = nQueen(n);
		lists = constructResult(pairLists, n);
		
		return lists;
	}

	
	public static List<List<Pair>> nQueen(int n) {
		List<List<Pair>> lists = new ArrayList<>();
		
		// if only one solution is needed, then use the boolean return to short curcuit it. 
		nQueenAllSolutions(lists, new ArrayList<Pair>(), n, 0);
//		nQueenOneSolutionWithShortCircuit(lists, new ArrayList<Pair>(), n, 0);
		
		return lists;
	}

	private static void nQueenAllSolutions(List<List<Pair>> lists, List<Pair> tempList, int n, int row) {
        if (n==1) {       
            List<Pair> myTemp = new ArrayList<Pair>();
            myTemp.add(new Pair(0, 0));
            lists.add(myTemp);
            return;
        }
		if (n < 3) {
			return;
		}
		if (n == row) {
			lists.add(new ArrayList<Pair>(tempList));
			return;
		}
		for (int col = 0; col < n; col++) {
			if (canPlace(tempList, row, col)) {
				tempList.add(new Pair(row, col));
				nQueenAllSolutions(lists, tempList, n, row + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
		return;
	}
	
	// if only one solution is needed, then use the boolean return to short circuit it. 
	private static boolean nQueenOneSolutionWithShortCircuit(List<List<Pair>> lists, List<Pair> tempList, int n, int row) {
        if (n==1) {       
            List<Pair> myTemp = new ArrayList<Pair>();
            myTemp.add(new Pair(0, 0));
            lists.add(myTemp);
            return true;
        }
		if (n < 3) {
			return false;
		}
		if (n == row) {
			lists.add(new ArrayList<Pair>(tempList));
			return true;
		}
		for (int col = 0; col < n; col++) {
			if (canPlace(tempList, row, col)) {
				tempList.add(new Pair(row, col));
				if (nQueenOneSolutionWithShortCircuit(lists, tempList, n, row + 1)) {
					// short circuit here
					return true;
				}
				tempList.remove(tempList.size() - 1);
			}
		}
		return false;
	}
	
	
	private static boolean canPlace(List<Pair> lists, int row, int col) {
		boolean isSafe = true;
		for (Pair pair : lists) {
			// Check whether in the queen's col or in the queen's diagonal line
			// Diagonal line means |row2 - row1| = |col2 - col1|
			if (pair.getValue() == col || 
					Math.abs(pair.getKey() - row) == Math.abs(pair.getValue() - col)) {
				isSafe = false;
			}
		}
		return isSafe;
	}
	
	private static List<List<String>> constructResult(List<List<Pair>> pairLists, int n) {
		List<List<String>> lists = new ArrayList<>();
		for (int i = 0; i < pairLists.size(); i++ ) {
			List<String> tempList = new ArrayList<String>();
			for (int j = 0; j < pairLists.get(i).size(); j++) {
				Pair pair = pairLists.get(i).get(j);
				StringBuilder sb = new StringBuilder();
				for (int k = 0; k < n; k++) {
					if (pair.getValue() == k) {
						sb.append("Q");
					} else {
						sb.append(".");
					}
				}
				tempList.add(sb.toString());
			}
			lists.add(tempList);
		}
		return lists;
	}

	public static void main(String[] args) {
		List<List<Pair>> results = nQueen(4);
		System.out.println("NQueeens: 4");
		for (List<Pair> list : results) {
			System.out.println();
			for (Pair pair : list) {
				System.out.println(pair.getKey() + ", " + pair.getValue());
			}
		}

		results = nQueen(8);
		System.out.println("NQueeens: 8");
		for (List<Pair> list : results) {
			System.out.println();
			for (Pair pair : list) {
				System.out.println(pair.getKey() + ", " + pair.getValue());
			}
		}

		results = nQueen(5);
		System.out.println("NQueeens: 5");
		for (List<Pair> list : results) {
			System.out.println();
			for (Pair pair : list) {
				System.out.println(pair.getKey() + ", " + pair.getValue());
			}
		}

		results = nQueen(6);
		System.out.println("NQueeens: 6");		
		for (List<Pair> list : results) {
			System.out.println();
			for (Pair pair : list) {
				System.out.println(pair.getKey() + ", " + pair.getValue());
			}
		}

		results = nQueen(7);
		System.out.println("NQueeens: 7");
		for (List<Pair> list : results) {
			System.out.println();
			for (Pair pair : list) {
				System.out.println(pair.getKey() + ", " + pair.getValue());
			}
		}
		
		results = nQueen(3);
		System.out.println("NQueeens: 3");
		for (List<Pair> list : results) {
			System.out.println();
			for (Pair pair : list) {
				System.out.println(pair.getKey() + ", " + pair.getValue());
			}
		}

		List<List<String>> results2 = solveNQueens(4);
		System.out.println("solveNQueens: 4");
		for (List<String> list : results2) {
			System.out.println();
			for (String str : list) {
				System.out.println(str);
			}
		}
		
	}
}
