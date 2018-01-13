package Q1_07_Rotate_Matrix;

import CtCILibrary.*;

public class MyAnswer {

	public static boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return false; // Not a square
		int n = matrix.length;
		
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for(int i = first; i < last; i++) {
				int offset = i - first;
				int top = matrix[first][i]; // save top

				// left -> top
				matrix[first][i] = matrix[last-offset][first]; 			

				// bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset]; 

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last]; 

				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
		return true;
	}
	
	public static int[][] rotateMatrixRight(int[][] matrix)
	{
       if (matrix.length == 0 || matrix[0].length == 0) return null; // Not a matrix
      
	    /* M and N are already swapped */
	    int m = matrix.length;
	    int n = matrix[0].length;
	    int[][] ret = new int[n][m];
	    for (int i = 0; i < m; ++i) {
	        for (int j = 0; j < n; ++j) {
	            ret[j][m - i - 1] = matrix[i][j];  // column become row
	        }
	    }
	    return ret;
	}


	public static int[][] rotateMatrixLeft(int[][] matrix)
	{
       if (matrix.length == 0 || matrix[0].length == 0) return null; // Not a matrix

       /* M and N are already swapped */
	    int m = matrix.length;
	    int n = matrix[0].length;   
	    int[][] ret = new int[n][m];
	    for (int i = 0; i < m; ++i) {
	        for (int j = 0; j < n; ++j) {
	            ret[n - j - 1][i] = matrix[i][j];  // row become column
	        }
	    }
	    return ret;
	}	

   public static int[][] rotateMatrix180(int[][] matrix)
   {
       if (matrix.length == 0 || matrix[0].length == 0) return null; // Not a matrix

       /* M and N are already swapped */
       int m = matrix.length;
       int n = matrix[0].length;   
       int[][] ret = new int[n][m];
       for (int i = 0; i < m; ++i) {
           for (int j = 0; j < n; ++j) {
               ret[m - i - 1][n - j - 1] = matrix[i][j]; 
           }
       }
       return ret;
   }  
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		AssortedMethods.printMatrix(matrix);
//		rotate(matrix);
//		System.out.println();
//		AssortedMethods.printMatrix(matrix);
		
      System.out.println("Rotate Right");
      AssortedMethods.printMatrix(rotateMatrixRight(matrix));
      
      System.out.println("Rotatae Left");
      AssortedMethods.printMatrix(rotateMatrixLeft(matrix));
      
      System.out.println("Rotatae 180");
      AssortedMethods.printMatrix(rotateMatrix180(matrix));
	}

}
