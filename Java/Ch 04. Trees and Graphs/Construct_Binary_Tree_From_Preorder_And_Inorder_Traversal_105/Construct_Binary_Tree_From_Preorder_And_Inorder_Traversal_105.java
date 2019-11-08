package Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal_105;


/*
 * Leetcode # 105. Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note: You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]

Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

*/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
 
public class Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal_105 {

	/*
	 * Inorder:     left subtree | root | right subtree
	 * Preorder:   root | left subtree | right subtree
	 * Postorder: left subtree | right subtree | root 
	 * 
	 * 		1. So the root is first element in preorder. (For the same token, root is last element in postorder)
	 * 		2. From root->val, search inorder sequence for rootIndex.
	 * 		3. From rootIndex, everything left of rootIndex is left tree and everything right of rootIndex 
	 * 			is right tree.
	 * 		4. Build the left tree and right tree from left tree and right tree size.
	 */
	
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		if ( preorder == null || inorder == null || preorder.length != inorder.length) {
			return null;
		}
		
		int size = preorder.length;
		return buildTree(preorder, inorder, 0, size - 1, 0, size -1);
	}

	private static TreeNode buildTree(int[] preorder, int[] inorder,
			int preorderStart, int preorderEnd,
			int inorderStart, int inorderEnd) {
		
		if ( preorderStart > preorderEnd || inorderStart > inorderEnd ) {
			return null;
		}
		
		// root is first element in preorder.
		TreeNode root = new TreeNode(preorder[preorderStart]);
		
		// From root->val, search inorder sequence for rootIndex.
		int rootIndex = -1;
		for (int i = inorderStart; i <= inorderEnd; i++) {
			if ( inorder[i] == preorder[preorderStart]) {
				rootIndex = i;
			}
		}
		if ( rootIndex == -1 ) {
			return null;
		}
		
		// From rootIndex, everything left of rootIndex is left tree and everything right
		// of rootIndex is right tree.
		int leftTreeSize = rootIndex - inorderStart;
		int rightTreeSize = inorderEnd - rootIndex;
		
		// Build the left tree and right tree from left tree and right tree size.
		root.left = buildTree(preorder, inorder, 
								preorderStart + 1, preorderStart + leftTreeSize, 
								inorderStart, rootIndex - 1);
		root.right = buildTree(preorder, inorder, 
								preorderEnd - rightTreeSize + 1, preorderEnd, 
								rootIndex + 1, inorderEnd);
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root;

		int[] preorder = { 3,9,20,15,7 };
		int[] inorder = { 9,3,15,20,7 };
		
		root = buildTree(preorder, inorder);
		System.out.println(root);

	}
}
