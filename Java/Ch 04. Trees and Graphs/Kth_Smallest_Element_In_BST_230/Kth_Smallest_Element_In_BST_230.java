package Kth_Smallest_Element_In_BST_230;

import java.util.*;

/*
 * Leetcode # 230. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

*/

// Definition for a binary tree node.
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
};

 
public class Kth_Smallest_Element_In_BST_230 {

	public static int kthSmallest(TreeNode root, int k) {   		
		if (root == null) {
			return Integer.MIN_VALUE;
		}	
		
		if (k < 1) {
			return Integer.MIN_VALUE;
		}
		
		// Remember Primitive is pass by value, cannot just pass an "int count"
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		return inorder(root, k, list);
	}
	
	private static int inorder(TreeNode root, int k, List<Integer> list) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		
		int val = inorder(root.left, k, list);
		if (val != Integer.MIN_VALUE) {
			return val;
		}
		
		if (k == list.get(0)) {
			return root.val;
		}
		list.set(0, list.get(0)+1);
		
		val = inorder(root.right, k, list);
		if (val != Integer.MIN_VALUE) {
			return val;
		}
		
		return Integer.MIN_VALUE;
	}
	
	public static int kthLargest(TreeNode root, int k) {   		
		if (root == null) {
			return Integer.MIN_VALUE;
		}	
		
		if (k < 1) {
			return Integer.MIN_VALUE;
		}
		
		// Remember Primitive is pass by value, cannot just pass an "int count"
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		return inorderReverse(root, k, list);
	}
	
	private static int inorderReverse(TreeNode root, int k, List<Integer> list) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		
		int val = inorderReverse(root.right, k, list);
		if (val != Integer.MIN_VALUE) {
			return val;
		}
		
		if (k == list.get(0)) {
			return root.val;
		}
		list.set(0, list.get(0)+1);
		

		val = inorderReverse(root.left, k, list);
		if (val != Integer.MIN_VALUE) {
			return val;
		}
		
		return Integer.MIN_VALUE;
	}
	
	public static void main(String[] args) {

		TreeNode m = new TreeNode(3);
		m.left = new TreeNode(1);
        m.right = new TreeNode(4);
        m.left.right=new TreeNode(2);
		
        int val = kthSmallest(m, 1);
        System.out.println("1st smallest: (expected 1)" + val);        
        
        val = kthLargest(m, 1);
        System.out.println("1st largest: (expected 4)" + val);        

        val = kthLargest(m, 1);
        System.out.println("2nd largest: (expected 3)" + val);        

        TreeNode n = new TreeNode(5);
        n.left = new TreeNode(3);
        n.right = new TreeNode(6);
        n.left.left=new TreeNode(2);
        n.left.right=new TreeNode(4);
        n.left.left.left=new TreeNode(1);
        
        val = kthSmallest(n, 3);
        System.out.println("3rd smallest: (expected 3)" + val);        

        val = kthLargest(n, 3);
        System.out.println("3rd largest: (expected 4)" + val);        

        val = kthLargest(n, 2);
        System.out.println("2nd largest: (expected 5)" + val);        
        
	}
}
