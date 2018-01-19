package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

public class MyAnswerBWithParentLink {
	public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
		if (p == q) {
			return p;
		}
		int depthP = depth(p);
		int depthQ = depth(q);
		if (depthP < depthQ) {
			q = goUpBy(q, depthQ - depthP);
		} else if (depthP > depthQ) {
			p = goUpBy(p, depthP - depthQ);
		}		
		while (p != null && q != null) {
			if (p.data == q.data) {
				return p;
			}
			p = p.parent;
			q = q.parent;
		}
		return null;
	}
	
	public static TreeNode goUpBy(TreeNode node, int delta) {
		if (node == null) {
			return node;
		}
		for (int i = 0; i < delta; i++) {
			if (node != null) {
				node = node.parent;
			}
		}
		return node;
	}
	
	public static int depth(TreeNode node) {
		int depth = 0;
		while (node != null) {
			node = node.parent;
			depth++;
		}
		return depth;
	}	
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(3);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = commonAncestor(n3, n7);
		System.out.println(ancestor.data);
	}

}
