package Q8_02_Robot_in_a_Grid;

import java.util.ArrayList;

import CtCILibrary.AssortedMethods;

public class MyAnswer {
	
	public static ArrayList<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0) return null;
		ArrayList<Point> path = new ArrayList<Point>();
		if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
			return path;
		} else {
			return null;
		}

	}	
	
	public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
		if (row < 0 || col < 0 || maze[row][col]) {
			return false;
		}
		// make mistake by adding the point here instead of inside the check.
		// The path is inverted and include dead paths that we visited.
		// path.add(new Point(row, col));
		if ((row == 0 && col == 0) || getPath(maze, row - 1, col, path) || getPath(maze, row, col - 1, path)){
			path.add(new Point(row, col));
			return true;
		} 

		return false;
	}
	
	public static void main(String[] args) {
		int size = 5;
		boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 20);
		
		AssortedMethods.printMatrix(maze);
		
		ArrayList<Point> path = getPath(maze);
		if (path != null) {
			System.out.println(path.toString());
		} else {
			System.out.println("No path found.");
		}
	}

}
