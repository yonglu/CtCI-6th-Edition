package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

public class MyAnswerDWithoutParentLink {
	
	// See QuestionD implementation.  They check if p and q are under the root one time
	// at the beginning and then start the recursive check so no need to check again.
	
	// See solution 4 in Page 271 for optimize by bubbling up the result in cover() method.
	public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		boolean pIsOnLeft = false;
		boolean pIsOnRight = false;
		boolean qIsOnLeft = false;
		boolean qIsOnRight = false;
		pIsOnLeft = cover(root.left, p);
		qIsOnLeft = cover(root.left, q);
		if (!pIsOnLeft) {
			pIsOnRight = cover (root.right, p);
		}
		if (!qIsOnLeft) {
			qIsOnRight = cover (root.right, q);
		}
		if (pIsOnLeft == false && pIsOnRight == false) {
			return null;
		}
		if (qIsOnLeft == false && qIsOnRight == false) {
			return null;
		}
		if ((pIsOnLeft == true && qIsOnRight == true) || (pIsOnRight == true && qIsOnLeft == true)) {
			return root;
		}
		if ((pIsOnLeft == true && qIsOnLeft == true)) {
			commonAncestor(root.left, p, q);
		}
		if ((pIsOnRight == true && qIsOnRight == true)) {
			commonAncestor(root.right, p, q);
		}

		return null;
	}
	
	public static boolean cover(TreeNode root, TreeNode child) {
		if (root == null || child == null) {
			return false;
		}
		if (root.data == child.data) {
			return true;
		}
		return cover(root.left, child) || cover(root.right, child);
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(1);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = commonAncestor(root, n3, n7);
		System.out.println(ancestor.data);
	}

}
