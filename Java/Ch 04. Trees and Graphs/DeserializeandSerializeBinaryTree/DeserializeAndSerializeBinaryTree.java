package DeserializeandSerializeBinaryTree;

import java.util.StringTokenizer;

import CtCILibrary.TreeNode;

public class DeserializeAndSerializeBinaryTree {

	public static String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		return sb.toString();
	}

	private static void serialize(TreeNode x, StringBuilder sb) {
		if (x == null) {
			sb.append("#,");
		} else {
			sb.append(x.data + ",");
			serialize(x.left, sb);
			serialize(x.right, sb);
		}
	}

	public static TreeNode deserialize(String s) {
		if (s == null || s.length() == 0)
			return null;
		StringTokenizer st = new StringTokenizer(s, ",");
		return deserialize(st);
	}

	private static TreeNode deserialize(StringTokenizer st) {
		if (!st.hasMoreTokens())
			return null;
		String val = st.nextToken();
		if (val.equals("#"))
			return null;
		TreeNode root = new TreeNode(Integer.parseInt(val));
		root.left = deserialize(st);
		root.right = deserialize(st);
		return root;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		// We needed this code for other files, so check out the code in the library
		TreeNode root = TreeNode.createMinimalBST(array);
		System.out.println("Root? " + root.data);
		System.out.println("Created BST? " + root.isBST());
		System.out.println("Height: " + root.height());
		root.print();

		String str = serialize(root);
		System.out.println("Serialize string: " + str);
		TreeNode newroot = deserialize(str);
		newroot.print();

	}

}
