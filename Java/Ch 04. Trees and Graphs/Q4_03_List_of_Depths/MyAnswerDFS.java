package Q4_03_List_of_Depths;

import CtCILibrary.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyAnswerDFS {

	
	public static ArrayList<LinkedList<TreeNode>> myCreateLevelLinkedList(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		myCreateLevelLinkedList(root, result, 0);
		return result;
	}	
	
	public static void myCreateLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> result, int level) {
		if (root == null) {
			return;
		}
		
		LinkedList<TreeNode> levelLinkedList;
		if (result.size() < level + 1) {
			levelLinkedList = new LinkedList<TreeNode>();
			result.add(level, levelLinkedList);
		} else {
			levelLinkedList = result.get(level);
		}
		
		levelLinkedList.add(root);
		myCreateLevelLinkedList(root.left, result, level+1);
		myCreateLevelLinkedList(root.right, result, level+1);
		
		return;
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
		int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
		ArrayList<LinkedList<TreeNode>> list = myCreateLevelLinkedList(root);
		printResult(list);
	}

}
