package CompanySkytab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

import CtCILibrary.AssortedMethods;
import CtCILibrary.BTreePrinter;
import CtCILibrary.TreeNode;

public class BinaryTree {
	

//	QUESTION #2: Binary tree
//
//	Let's say we have a class called Node, which represents a node in a binary
//	tree. Instances of Node have two instance variables: `left` and `right`.
//	They represent the node's children, and each may refer either to another
//	instance of Node or be null.
//
//	In the language of your choice, implement a method on the Node class named
//	`count_descendants`, which will return the total number of nodes in the
//	subtree of the node it's called on.
//
//	For instance, if we have this binary tree,
//
//	             n1
//	            /  \
//	          n2    n3
//	         /     /  \
//	       n4    n5    n6
//	      /     /
//	    n7    n8
//
//	then calling `n3.count_descendants()` should return 3. Calling
//	`n7.count_descendants()` should return 0.

	public static int count_descendants(TreeNode node) {
	     if (node == null) {
	         return 0;
	      }

		return count_nodes(node) - 1;
	}

	public static int count_nodes(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		if (node.left == null && node.right == null) {
			return 1;
		}
		
		return 1 + count_nodes(node.left) + count_nodes(node.right);
	}
	
	
	public static void main(String[] args) {
		int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
		root.print();
		
		System.out.println(count_descendants(root));
		System.out.println(count_descendants(root.left ));
		System.out.println(count_descendants(root.right));
		System.out.println(count_descendants(root.left.left));
      System.out.println(count_descendants(root.left.right));
		System.out.println(count_descendants(root.left.left.left));
      System.out.println(count_descendants(null));

	}
	public static void printResult(ArrayList<LinkedList<TreeNode>> result){
		int depth = 0;
		for(LinkedList<TreeNode> entry : result) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");
			while(i.hasNext()){
				System.out.print(" " + ((TreeNode)i.next()).data);
			}
			System.out.println();
			depth++;
		}
	}
	

}

