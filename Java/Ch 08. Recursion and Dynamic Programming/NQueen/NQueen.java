package NQueen;

import java.util.*;
import javafx.util.Pair;

//N Queen Problem is the problem of placing N chess queens on an NxN chessboard 
//so that no two queens attack each other, for which solutions exist for all 
//natural numbers n except n=2 and n=3.
//
//A solution requires that no two queens share the same row, column, or diagonal.
//
// N Queen Problem can be solved using a recursive backtracking algorithm. 
//
// http://codepumpkin.com/n-queen-problem/

public class NQueen {

	public static List<Pair<Integer, Integer>> nQueen(int n) {
		List<Pair<Integer, Integer>> lists = new ArrayList<>();
		nQueen(lists, n, 0);
		return lists;
	}

	private static boolean nQueen(List<Pair<Integer, Integer>> lists, int n, int row) {
		if (n < 3) {
			return false;
		}
		if (n == row) {
			return true;
		}
		for (int col = 0; col < n; col++) {
			if (canPlace(lists, row, col)) {
				lists.add(new Pair<Integer, Integer>(row, col));
				if (nQueen(lists, n, row + 1)) {
					return true;
				}
				lists.remove(lists.size() - 1);
			}
		}
		return false;
	}

	private static boolean canPlace(List<Pair<Integer, Integer>> lists, int row, int col) {
		boolean isSafe = true;
		for (Pair<Integer, Integer> pair : lists) {
			// Check whether in the queen's col or in the queen's diagonal line
			// Diagonal line means |row2 - row1| = |col2 - col1|
			if (pair.getValue() == col || 
					Math.abs(pair.getKey() - row) == Math.abs(pair.getValue() - col)) {
				isSafe = false;
			}
		}
		return isSafe;
	}

	public static void main(String[] args) {
		List<Pair<Integer, Integer>> results = nQueen(4);
		System.out.println("NQueeens: 4");
		for (Pair<Integer, Integer> pair : results) {
			System.out.println(pair.getKey() + ", " + pair.getValue());
		}

		results = nQueen(8);
		System.out.println("NQueeens: 8");
		for (Pair<Integer, Integer> pair : results) {
			System.out.println(pair.getKey() + ", " + pair.getValue());
		}

		results = nQueen(5);
		System.out.println("NQueeens: 5");
		for (Pair<Integer, Integer> pair : results) {
			System.out.println(pair.getKey() + ", " + pair.getValue());
		}

		results = nQueen(6);
		System.out.println("NQueeens: 6");
		for (Pair<Integer, Integer> pair : results) {
			System.out.println(pair.getKey() + ", " + pair.getValue());
		}

		results = nQueen(7);
		System.out.println("NQueeens: 7");
		for (Pair<Integer, Integer> pair : results) {
			System.out.println(pair.getKey() + ", " + pair.getValue());
		}
		
		results = nQueen(3);
		System.out.println("NQueeens: 3");
		for (Pair<Integer, Integer> pair : results) {
			System.out.println(pair.getKey() + ", " + pair.getValue());
		}

	}
}
