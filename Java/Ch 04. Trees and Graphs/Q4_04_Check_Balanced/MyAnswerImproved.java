package Q4_04_Check_Balanced;
import CtCILibrary.TreeNode;

public class MyAnswerImproved {
		
//	This improved algorithm works by checking the height of each subtree as we recurse down from the root.
//	On each node, we recursively get the heights of the left and right subtrees through the checkHeight
//	method. If the subtree is balanced, then checkHeight will return the actual height of the subtree. If the
//	subtree is not balanced, then checkHeight will return an error code. We will immediately break and
//	return an error code from the current call.
			
	public static int checkHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = checkHeight(root.left);
		if ( leftHeight == -1) {
			return -1;
		}
		int rightHeight = checkHeight(root.right);
		if (rightHeight == -1) {
			return -1;
		}
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	public static boolean isBalanced(TreeNode root) {
		return checkHeight(root) != -1;
	}
	
	public static void main(String[] args) {
		// Create balanced tree
		int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);

		
		System.out.println("Is balanced? " + isBalanced(root));
		
		root.insertInOrder(4); // Add 4 to make it unbalanced

		System.out.println("Is balanced? " + isBalanced(root));
	}

}
