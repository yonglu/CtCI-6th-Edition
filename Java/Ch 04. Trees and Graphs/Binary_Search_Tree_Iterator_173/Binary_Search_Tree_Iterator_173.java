package Binary_Search_Tree_Iterator_173;

import java.util.*;

/*
 * Leetcode # 173. Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

   7
  / \
 3   15
    /  \
   9   20 

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false

 

Note:

    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

*/

// Definition for a binary tree node.
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
};

class BSTIterator {

	private Stack<TreeNode> stack = new Stack<TreeNode>();
	
    public BSTIterator(TreeNode root) {
    	// Push the all left most nodes to stack
    	while (root !=  null) {
    		stack.push(root);
    		root = root.left;
    	}
    }
    
    /** @return the next smallest number */
    public int next() {
    	if (!hasNext()) {
    		return Integer.MAX_VALUE;
    	}
    	
    	TreeNode n = stack.pop();
    	
    	// after pop, push the right child's left most nodes to stack
        TreeNode temp = n.right;
    	while(temp != null) {
    		stack.push(temp);
    		temp = temp.left;
    	}
    	
    	return n.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
    	return !stack.isEmpty();       
    }
}
 

public class Binary_Search_Tree_Iterator_173 {
	
	public static void main(String[] args) {

        TreeNode n = new TreeNode(7);
        n.left = new TreeNode(3);
        n.right = new TreeNode(15);
        n.right.left=new TreeNode(9);
        n.right.right=new TreeNode(20);
        
        BSTIterator iterator = new BSTIterator(n);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false

        
	}
}
