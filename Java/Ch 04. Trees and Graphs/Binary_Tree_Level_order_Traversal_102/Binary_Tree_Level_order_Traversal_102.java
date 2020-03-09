package Binary_Tree_Level_order_Traversal_102;

import java.util.*;

/*
 * Leetcode # 102. Binary Tree Level Order Traversal

Given a binary tree, return the level order traversal of its nodes' values. 
(ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
*/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
 
public class Binary_Tree_Level_order_Traversal_102 {

	public static List<List<Integer>> levelOrder(TreeNode root)  {
		List<List<TreeNode>> nodeList = new ArrayList<>();
		List<List<Integer>> list = new ArrayList<>();

		if (root == null) {
			return list;
		}		
		
		List<TreeNode> rootNodeList = new ArrayList<TreeNode>();
		rootNodeList.add(root);
		nodeList.add(rootNodeList);
		
		List<Integer> rootList = new ArrayList<Integer>();
		rootList.add(root.val);
		list.add(rootList);
		
		int level = 0;
		while (!nodeList.get(level).isEmpty()) {
			List<TreeNode> parentNodeList = nodeList.get(level);
			List<TreeNode> curNodeList = new ArrayList<TreeNode>();
			List<Integer> curList = new ArrayList<Integer>();
			for (TreeNode n : parentNodeList) {
				if (n.left != null) {
					curNodeList.add(n.left);
					curList.add(n.left.val);
				}
				if (n.right != null) {
					curNodeList.add(n.right);
					curList.add(n.right.val);
				}				
			}
			
			nodeList.add(curNodeList);
			if (!curNodeList.isEmpty()) {
				list.add(curList);
			}
			++level;
		}		
		
		return list;
	}
	
	
	public static void main(String[] args) {

		TreeNode n = new TreeNode(3);
        n.left = new TreeNode(9);
        n.right = new TreeNode(20);
//        n.left.left=new TreeNode(1);
//        n.left.right=new TreeNode(3);
        n.right.left = new TreeNode(15);
        n.right.right = new TreeNode(7);        
        
        List<List<Integer>> list = levelOrder(n);

        for (List<Integer> l : list) {
        	for (Integer i : l) {
        		System.out.print(i + ", ");
        	}
        	System.out.println();
        }

		
	}
}
