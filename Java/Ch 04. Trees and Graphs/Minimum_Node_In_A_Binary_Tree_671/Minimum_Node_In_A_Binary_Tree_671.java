package Minimum_Node_In_A_Binary_Tree_671;

import java.util.*;

/*
 * Leetcode # 671. Second Minimum Node In a Binary Tree

Given a non-empty special binary tree consisting of nodes with the non-negative value, 
where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, 
then this node's value is the smaller value among its two sub-nodes. More formally, 
the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of 
all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

 

Example 2:

Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.


Example 3:

Input: 
    1
   / \
  3   1
     / \
    1   1
       / \
      1  2

Output: 2
*/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
 
public class Minimum_Node_In_A_Binary_Tree_671 {

	public static int findSecondMinimumValue(TreeNode root) { 
		int ans = -1;
		
		if ((root == null) || (root.left == null && root.right == null)) {
			return -1;
		}
		
		if (root.left.val < root.right.val) {
			if (root.left.val != root.val) {
				return root.left.val;
			} 
		} else if (root.left.val > root.right.val) {
			if (root.right.val != root.val) {
				return root.right.val;
			} 
		}
		
		// Handle the special case when the second minimum is not at the second level.
		return helper(root, root.val);
	}
	
	
	private static int helper(TreeNode root, int smallestValue) {
		if (root == null) {
			return -1;
		}
		
		if (root.val > smallestValue) {
			return root.val;
		}
		
		int temp1 = helper(root.left, smallestValue);
		int temp2 = helper(root.right, smallestValue);
		
		if (temp1 == -1 && temp2 == -1) {
			return -1;
		} else if (temp1 == -1) {
			return temp2;
		} else if (temp2 == -1) {
			return temp1;
		} else {
			return Math.min(temp1, temp2);
		}		
		
	}
	
	
	public static void main(String[] args) {
		int ans;
		
		TreeNode n = new TreeNode(2);
        ans = findSecondMinimumValue(n);
        System.out.println("findSecondMinimumValue is (expected -1): " + ans);
        n.left = new TreeNode(2);
        n.right = new TreeNode(5);
        n.right.left = new TreeNode(5);
        n.right.right = new TreeNode(7);        
        
        ans = findSecondMinimumValue(n);
        System.out.println("findSecondMinimumValue is (expected 5): " + ans);
        
        
		TreeNode m = new TreeNode(2);
        m.left = new TreeNode(2);
        m.right = new TreeNode(2);
        
        ans = findSecondMinimumValue(m);
        System.out.println("findSecondMinimumValue is (expected -1): " + ans);
        
		TreeNode o = new TreeNode(1);
        o.left = new TreeNode(3);
        o.right = new TreeNode(1);
        o.right.left = new TreeNode(1);
        o.right.right = new TreeNode(1);
        o.right.right.left = new TreeNode(1);
        o.right.right.right = new TreeNode(2);
        
        
        ans = findSecondMinimumValue(o);
        System.out.println("findSecondMinimumValue is (expected 2): " + ans);
        
        
		
	}
}
