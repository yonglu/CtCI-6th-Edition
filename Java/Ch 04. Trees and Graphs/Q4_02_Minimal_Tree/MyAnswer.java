package Q4_02_Minimal_Tree;

import CtCILibrary.TreeNode;

//Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
//to create a binary search tree with minimal height.

public class MyAnswer {	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		// We needed this code for other files, so check out the code in the library
//		TreeNode root = TreeNode.createMinimalBST(array);
      TreeNode root = myCreateMinimalBST(array);
		System.out.println("Root? " + root.data);
		System.out.println("Created BST? " + root.isBST());
		System.out.println("Height: " + root.height());
		root.print();
	}
	
   public static TreeNode myCreateMinimalBST(int[] array) {
      if (array.length == 0) {
         return null;
      }
      return myCreateMinimalBST(array, 0, array.length - 1);
   }
   
   public static TreeNode myCreateMinimalBST(int[] array, int start, int end) {
      if (array.length == 0) {
         return null;
      }
      if (start > end) {
         return null;
      }
      int middle = (start + end) / 2;
      TreeNode node = new TreeNode(array[middle]);
      node.left = myCreateMinimalBST(array, start, middle - 1);
      node.right = myCreateMinimalBST(array, middle + 1, end);
      
      return node;
   }
   
	
	public static TreeNode myCreateMinimalBST2(int[] array) {
	   if (array.length == 0) {
	      return null;
	   }
	   int middle = array.length / 2;
	   TreeNode node = new TreeNode(array[middle]);
	   int[] left = new int[middle];
	   int[] right = new int[array.length - middle - 1];
	   for (int i = 0; i < middle; i++) {
	      left[i] = array[i];
	   }
	   for (int j=0; j < array.length - middle - 1; j++) {
	      right[j] = array[middle + 1 + j];
	   }
	   node.left = myCreateMinimalBST2(left);
	   node.right = myCreateMinimalBST2(right);
	   
	   return node;
	}

}
