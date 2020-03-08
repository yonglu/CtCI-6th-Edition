package Invert_Binary_Tree_226;


/*
 * Leetcode # 226. Invert Binary Tree

Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

*/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
 
public class Invert_Binary_Tree_226 {

	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		
		return root;
	}
	

	private static void printTree(TreeNode node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.val + ", ");
		printTree(node.left, sb);
		printTree(node.right, sb);
		return;
	}
	
	public static void main(String[] args) {

		TreeNode n = new TreeNode(4);
        n.left = new TreeNode(2);
        n.right = new TreeNode(7);
        n.left.left=new TreeNode(1);
        n.left.right=new TreeNode(3);
        n.right.left = new TreeNode(6);
        n.right.right = new TreeNode(9);        

        StringBuilder sb = new StringBuilder();
        printTree(n, sb);
        System.out.println(sb.toString());
        
        TreeNode inv = invertTree(n);
        
        sb = new StringBuilder();
        printTree(inv, sb);
        System.out.println(sb.toString());

		
	}
}
