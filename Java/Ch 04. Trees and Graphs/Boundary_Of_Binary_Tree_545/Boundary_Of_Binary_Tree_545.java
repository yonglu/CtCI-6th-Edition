package Boundary_Of_Binary_Tree_545;

import java.util.*;

/*
 * Leetcode # 545 Boundary of Binary Tree

Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].

 

Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

*/

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
 
public class Boundary_Of_Binary_Tree_545 {

	public static List<Integer> boundaryOfBinaryTree(Node root) {   
		
		List<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}	
		
		list.add(root.val);
		printLeftNodes(root.left, list);
		printLeafNodes(root, list);
		printRightNodes(root.right, list);
		
		return list;
	}
	
	private static void printLeftNodes(Node node, List<Integer> list) {
		if (node == null) {
			return;
		}
		
		// if it is leaf node, just return
		if (node.left == null && node.right == null) {
			return;
		}
		
		list.add(node.val);
		
		if (node.left != null) {
			printLeftNodes(node.left, list);
		} else {
			printLeftNodes(node.right, list);
		}		
	}
	
	private static void printLeafNodes(Node node, List<Integer> list) {
		if (node == null) {
			return;
		}
		
		// if it is leaf node, add it
		if (node.left == null && node.right == null) {
			list.add(node.val);
		}
		
		printLeafNodes(node.left, list);
		printLeafNodes(node.right, list);
	}

	
	private static void printRightNodes(Node node, List<Integer> list) {
		if (node == null) {
			return;
		}
		
		// if it is leaf node, just return
		if (node.left == null && node.right == null) {
			return;
		}
		
		if (node.right != null) {
			printRightNodes(node.right, list);
		} else {
			printRightNodes(node.left, list);
		}	
		
		// add by the end for wanting reverse order in right nodes
		list.add(node.val);
	}
	
	
	public static void main(String[] args) {

		Node m = new Node(1);
        m.right = new Node(2);
        m.right.left=new Node(3);
        m.right.right=new Node(4);
		
        List<Integer> list = boundaryOfBinaryTree(m);
        for (Integer i : list) {
        	System.out.print(i + ", ");
        }
        
        System.out.println("");
        
		Node n = new Node(1);
        n.left = new Node(2);
        n.right = new Node(3);
        n.left.left=new Node(4);
        n.left.right=new Node(5);
        n.left.right.left=new Node(7);
        n.left.right.right=new Node(8);
        n.right.left = new Node(6);
        n.right.left.left = new Node(9);        
        n.right.left.right = new Node(10);        
        
        list = boundaryOfBinaryTree(n);

        for (Integer i : list) {
        	System.out.print(i + ", ");
        }
		
	}
}
