package Vertical_Order_Traversal_Of_Binary_Tree_987;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

/*
 * Leetcode # 987. Vertical Order Traversal of a Binary Tree

Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively 
will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the 
vertical line touches some nodes, we report the values of the nodes in order 
from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is 
reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report 
will have a list of values of nodes.
 

Example 1:
		3
	9		20
		15		7

Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).

Example 2:

		1
	2		3
 4	  5	  6   7
 
Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according 
to the given scheme.  However, in the report "[1,5,6]", the node value of 5 
comes first since 5 is smaller than 6.
 

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
 * 
 * 
 * 
 */

/*
 // Definition for a binary tree node.
class TreeNode extends {
    int val;
    int col;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }
*/

class MyTreeNode {
	TreeNode node;
	int col;
	
	MyTreeNode(TreeNode mTreeNode, int mCol) {
		node = mTreeNode;
		col = mCol;
	}
}

public class Vertical_Order_Traversal_Of_Binary_Tree_987 {
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
    	List<List<Integer>> resultList = new ArrayList<>();
    	if (root == null) {
    		return resultList;
    	}
    	
    	// colMap is used to keep track of mapping of col number to list of nodes
    	Map<Integer, LinkedList<TreeNode>> colMap = new HashMap<Integer, LinkedList<TreeNode>>();

    	// treeLevelsLinkedLists is used to keep track of levels in BFS
        List<LinkedList<MyTreeNode>> treeLevelsLinkedLists = new ArrayList<>();
        
        LinkedList<MyTreeNode> current = new LinkedList<MyTreeNode>();
        current.add(new MyTreeNode(root, 0));
        
        LinkedList<TreeNode> colLinkedList = new LinkedList<TreeNode>();
        colLinkedList.add(root);
		colMap.put(0, colLinkedList);
		
        while (!current.isEmpty()) {
        	LinkedList<MyTreeNode> parent = current;
        	treeLevelsLinkedLists.add(new LinkedList<MyTreeNode>(current));
        	current = new LinkedList<MyTreeNode>();
        	while (!parent.isEmpty()) {
        		MyTreeNode myNode = parent.poll();
        		if (myNode.node.left != null) {
        			current.add(new MyTreeNode(myNode.node.left, myNode.col-1));
        			if (!colMap.containsKey(myNode.col-1)) {
        				colMap.put(myNode.col-1, new LinkedList<TreeNode>());
        			}
        			colMap.get(myNode.col-1).add(myNode.node.left);
        		}
        		if (myNode.node.right != null) {
        			current.add(new MyTreeNode(myNode.node.right, myNode.col+1));
        			if (!colMap.containsKey(myNode.col+1)) {
        				colMap.put(myNode.col+1, new LinkedList<TreeNode>());
        			}
        			colMap.get(myNode.col+1).add(myNode.node.right);
        		}
        	}
        }
        
        Integer[] keys = colMap.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);
        for (int i = 0; i<keys.length; i++) {
        	LinkedList<TreeNode> tempNodeList = colMap.get(keys[i]);
        	List<Integer> tempList = new ArrayList<Integer>();
        	while (!tempNodeList.isEmpty()) {
               	tempList.add(tempNodeList.pop().data);       		       		
        	}
         	resultList.add(tempList);
        }
    	return resultList;
    }
    


public static void main(String[] args) {
	int[] nodes_flattened = {1,2,3,4,5,6,7};
	TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
	List<List<Integer>> list = verticalTraversal(root);
	System.out.println(list);

	// [0,2,1,3,null,null,null,4,5,null,7,6,null,10,8,11,9]
	// output: [[4,10,11],[3,7,6],[2,5,8,9],[0],[1]]
	// expected: [[4,10,11],[3,6,7],[2,5,8,9],[0],[1]]
	
}
}

