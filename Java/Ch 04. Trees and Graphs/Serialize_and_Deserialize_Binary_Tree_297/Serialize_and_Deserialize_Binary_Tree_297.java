package Serialize_and_Deserialize_Binary_Tree_297;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import CtCILibrary.TreeNode;

/*
 * Leetcode #297. Serialize and Deserialize Binary Tree
 * 
Serialization is the process of converting a data structure or object into a sequence of 
bits so that it can be stored in a file or memory buffer, or transmitted across a network 
connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on 
how your serialization/deserialization algorithm should work. You just need to ensure that 
a binary tree can be serialized to a string and this string can be deserialized to the 
original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
*/

/*
 * Solution: https://www.programcreek.com/2014/05/leetcode-serialize-and-deserialize-binary-tree-java/
 */

public class Serialize_and_Deserialize_Binary_Tree_297 {
	
	/**
	 * Definition for a binary tree node.
	 */
	static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }  
	}

	
    /*
     *  Encodes a tree to a single string in DFS (pre order recursively).
     *  
     *  This is cleaner implementation than serializePreOrderRecursively
     *  
    1
   / \
  2   3
     / \
    4   5

as "[1,2,#,#,3,4,#,#,5,#,#]"
	*/
	public static String serializeDFS(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serializeDFS(root, sb);
		return sb.toString();
	}

	private static void serializeDFS(TreeNode x, StringBuilder sb) {
		if (x == null) {
			sb.append("#,");
		} else {
			sb.append(x.val + ",");
			serializeDFS(x.left, sb);
			serializeDFS(x.right, sb);
		}
	}

    /*
     *  Decodes your encoded DFS (pre order) data to tree.
     *  
     *  This is cleaner implementation than deserializePreOrderRecursively
     *  
"[1,2,#,#,3,4,#,#,5,#,#]" as

    1
   / \
  2   3
     / \
    4   5

     */
	public static TreeNode deserializeDFS(String s) {
		if (s == null || s.length() == 0)
			return null;
		StringTokenizer st = new StringTokenizer(s, ",");
		return deserializeDFS(st);
	}

	private static TreeNode deserializeDFS(StringTokenizer st) {
		if (!st.hasMoreTokens())
			return null;
		String val = st.nextToken();
		if (val.equals("#"))
			return null;
		TreeNode root = new TreeNode(Integer.parseInt(val));
		root.left = deserializeDFS(st);
		root.right = deserializeDFS(st);
		return root;
	}
	
	
    /*
     *  Encodes a tree to a single string in level order.
     *  
    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,#,#,4,5,#,#,#,#]"

     */	
    public static String serializeLevelOrder(TreeNode root) {
    	if (root == null) {
    		return "";
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		if (node == null) {
    			sb.append("#,");
    			continue;
     		} else {
     			sb.append(node.val + ",");
     		}
    		
    		queue.add(node.left);
    		queue.add(node.right);
    	}
    	
    	sb.delete(sb.length()-1, sb.length());
    	
		return sb.toString();        
    }

    /*
     *  Decodes your encoded level order data to tree.
     *  
"[1,2,3,#,#,4,5,#,#,#,#]" as

    1
   / \
  2   3
     / \
    4   5

     */
    public static TreeNode deserializeLevelOrder(String data) {
    	if (data == null || data.length() == 0) {
    		return null;
    	}
    	String[] arr = data.split(",");
        if (arr[0].equals("#")) {
            return null;
        }    
        
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();   	
    	TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
    	queue.add(root);
    	
    	int i = 1;
    	while (!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	
        	if (!arr[i].equals("#")) {
        		TreeNode leftNode = new TreeNode(Integer.parseInt(arr[i]));
        		node.left = leftNode;
        		queue.add(leftNode);
        	}
        	i++;
 
        	if (!arr[i].equals("#")) {
        		TreeNode rightNode = new TreeNode(Integer.parseInt(arr[i]));
        		node.right = rightNode;
        		queue.add(rightNode);
        	}
        	i++;       	
    	}
    	
		return root;        
    }
  
    /*
     *  Encodes a tree to a single string in pre order recursively.
     *  
    1
   / \
  2   3
     / \
    4   5

as "[1,2,#,#,3,4,#,#,5,#,#]"

     */	
    public static String serializePreOrderRecursive(TreeNode root) {
    	if (root == null) {
    		return "";
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	serializePreOrderRecursive(root, sb);
    	
    	sb.delete(sb.length()-1, sb.length());
		return sb.toString();        
    }
    
    public static void serializePreOrderRecursive(TreeNode root, StringBuilder sb) {
    	if (root == null) {
    		sb.append("#,");
    		return;
    	}
    	
    	sb.append(root.val).append(",");
    	serializePreOrderRecursive(root.left, sb);
    	serializePreOrderRecursive(root.right, sb);   	
    }
    
    public static String serializePreOrderInteratively(TreeNode root) {
    	if (root == null) {
    		return "";
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	
    	while (!stack.isEmpty()) {
    		TreeNode node = stack.pop();
    		
    		if (node == null) {
    			sb.append("#,");
    			continue;
    		}
    		
    		sb.append(node.val).append(",");
       		stack.push(node.right);
       		stack.push(node.left);
     	}
    	
    	sb.delete(sb.length()-1, sb.length());
		return sb.toString();        
    }
   
    /*
     *  Decodes your encoded pre order data to tree.
     *  
"[1,2,#,#,3,4,#,#,5,#,#]" as

    1
   / \
  2   3
     / \
    4   5

     */
    public static TreeNode deserializePreOrder(String data) {
    	if (data == null || data.length() == 0) {
    		return null;
    	}
    	String[] arr = data.split(",");
        if (arr[0].equals("#")) {
            return null;
        }    
        
    	Queue<String> queue = new LinkedList<String>(); 
    	for (String s : arr) {
    		queue.add(s);
    	}
    	
		return deserializePreOrderHelper(queue);        
    }    
    
    private static TreeNode deserializePreOrderHelper(Queue<String> queue) {
     	
    	if (queue.isEmpty()) {
    		return null;
    	}
    	
		String s = queue.poll();
		
		if (s.equals("#")) {
			return null;
		} else {
			TreeNode node = new TreeNode(Integer.parseInt(s));
			node.left = deserializePreOrderHelper(queue);
			node.right = deserializePreOrderHelper(queue);
			return node;
		}
     	
    }
    
    
	public static void main(String[] args) {
		TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.right = new TreeNode(3);
        n.left.left=null;
        n.left.right=null;
        n.right.left = new TreeNode(4);
        n.right.right = new TreeNode(5);
        n.right.left.left = null;
        n.right.left.right = null;
        n.right.right.left = null;
        n.right.right.right = null;
        
        System.out.println("DFS: " + serializeDFS(n));
        TreeNode root = deserializeDFS("1,2,3,#,#,4,5,#,#,#,#");
        System.out.println("DFS: " + serializeDFS(root));
		
        System.out.println("");

        System.out.println("Level order: " + serializeLevelOrder(n));
        root = deserializeLevelOrder("1,2,3,#,#,4,5,#,#,#,#");
        System.out.println("Level Order: " + serializeLevelOrder(root));
		
        System.out.println("");
        
        System.out.println("Pre Order Recursive: " + serializePreOrderRecursive(n));
        root = deserializePreOrder("1,2,#,#,3,4,#,#,5,#,#");
        System.out.println("Pre Order Interatively: " + serializePreOrderInteratively(root));

	}
}
