package univalued_binary_tree_965;


/*
 * Leetcode # 965. Univalued Binary Tree

A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

Example 1:

    1
   / \
  1   1
 / \   \
1   1   1


Input: [1,1,1,1,1,null,1]
Output: true

Example 2:


    2
   / \
  2   2
 / \   
5   2   

Input: [2,2,2,5,2]
Output: false


*/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
 
public class univalued_binary_tree_965 {

	public static boolean isUnivalTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		return isUnivalTree(root, root.val);
	}
	
	private static boolean isUnivalTree(TreeNode root, int expectedVal) {
		if (root == null) {
			return true;
		}
		
		if (expectedVal != root.val) {
			return false;
		}
		
		return (isUnivalTree(root.left, expectedVal) && isUnivalTree(root.right, expectedVal));
	}	

	
	public static void main(String[] args) {

		TreeNode n = new TreeNode(1);
        n.left = new TreeNode(1);
        n.right = new TreeNode(1);
        n.left.left=new TreeNode(1);
        n.left.right=new TreeNode(1);
        n.right.left = null;
        n.right.right = new TreeNode(1);        
		System.out.println("isUnivalTree: (expect true)  " + isUnivalTree(n));

		TreeNode m = new TreeNode(2);
        m.left = new TreeNode(2);
        m.right = new TreeNode(2);
        m.left.left=new TreeNode(5);
        m.left.right=new TreeNode(2);
        m.right.left = null;
        m.right.right = null;        
		System.out.println("isUnivalTree: (expect false)  " + isUnivalTree(m));
		
	}
}
