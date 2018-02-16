package Q4_03_List_of_Depths;

import CtCILibrary.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
//at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).

public class MyAnswerBFS {

	// See QuestionBFS.java for cleaner implementation
	
	// Mistakes:
	// 1. Use LinkedList.poll() which remove the item from the result list.
	// 2. Did not check whether the next level nodeis null or the LinkedList is empty
	public static ArrayList<LinkedList<TreeNode>> myCreateLevelLinkedList(TreeNode root) {
		if (root == null) {
			return null;
		}
		int depth = treeDepth(root);
		System.out.println("Tree depth: " + treeDepth(root));
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>(depth);
		LinkedList<TreeNode> linkedList = new LinkedList<TreeNode>();
		linkedList.add(root);
		result.add(linkedList);
		for (int level = 0; level < depth; level++) {
			LinkedList<TreeNode> levelList = result.get(level);
			LinkedList<TreeNode> nextLevelLinkedList = new LinkedList<TreeNode>();
			if (levelList != null) {
				for (int i = 0; i < levelList.size(); i++) {
					TreeNode levelNode = levelList.get(i);
					if (levelNode != null) {
						if (levelNode.left != null) {
							nextLevelLinkedList.add(levelNode.left);
						}
						if (levelNode.right != null) {
							nextLevelLinkedList.add(levelNode.right);
						}
					}
				}
			}
			if (!nextLevelLinkedList.isEmpty()) {
				result.add(nextLevelLinkedList);
			}
		}
		
		return result;
	}
	
	public static int treeDepth(final TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(treeDepth(root.left), treeDepth(root.right));
	}
	
	public static void printResult(ArrayList<LinkedList<TreeNode>> result){
		int depth = 0;
		for(LinkedList<TreeNode> entry : result) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");
			while(i.hasNext()){
				System.out.print(" " + ((TreeNode)i.next()).data);
			}
			System.out.println();
			depth++;
		}
	}
	

	public static void main(String[] args) {
		int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
		ArrayList<LinkedList<TreeNode>> list = myCreateLevelLinkedList(root);
		printResult(list);
	}

}
