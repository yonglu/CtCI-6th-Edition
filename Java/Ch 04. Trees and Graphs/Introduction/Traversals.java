package Introduction;

import java.util.Stack;

import CtCILibrary.TreeNode;

public class Traversals {
	
	private static int nn = 1;
	
	public static void visit(TreeNode node) {
		if (node != null) {
			System.out.print(node.data + ", ");
		}
	}
	
	// Print the kth smallest in tree also.
	public static void visit2(TreeNode node, int k, int n) {
		if (node != null) {
			System.out.print(node.data + ", ");
//			System.out.println("nn = " + nn);
			if (nn==5) {
				System.out.println(" ");
				System.out.println("the " + k + "th smallest is " + node.data);
				System.out.println(" ");
			}
			nn++;
		}
	}
	
	public static void inOrderTraversal(TreeNode node, int k, int n) {
		if (node != null) {
			inOrderTraversal(node.left, k, n);
			visit2(node, k, n);
			inOrderTraversal(node.right, k, n);
		}
	}
	
	public static void preOrderTraversal(TreeNode node) {
		if (node != null) {
			visit(node);
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}
	
	public static void postOrderTraversal(TreeNode node) {
		if (node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			visit(node);
		}
	}	
	
//	Inorder Tree Traversal without Recursion
//	2.8
//	Using Stack is the obvious way to traverse tree without recursion. Below is an algorithm for traversing binary tree using stack. See this for step wise step execution of the algorithm.
//
//	1) Create an empty stack S.
//	2) Initialize current node as root
//	3) Push the current node to S and set current = current->left until current is NULL
//	4) If current is NULL and stack is not empty then 
//	     a) Pop the top item from stack.
//	     b) Print the popped item, set current = popped_item->right 
//	     c) Go to step 3.
//	5) If current is NULL and stack is empty then we are done.
//	Let us consider the below tree for example
//
//	            1
//	          /   \
//	        2      3
//	      /  \
//	    4     5
//
//	Step 1 Creates an empty stack: S = NULL
//
//	Step 2 sets current as address of root: current -> 1
//
//	Step 3 Pushes the current node and set current = current->left until current is NULL
//	     current -> 1
//	     push 1: Stack S -> 1
//	     current -> 2
//	     push 2: Stack S -> 2, 1
//	     current -> 4
//	     push 4: Stack S -> 4, 2, 1
//	     current = NULL
//
//	Step 4 pops from S
//	     a) Pop 4: Stack S -> 2, 1
//	     b) print "4"
//	     c) current = NULL /*right of 4 */ and go to step 3
//	Since current is NULL step 3 doesn't do anything. 
//
//	Step 4 pops again.
//	     a) Pop 2: Stack S -> 1
//	     b) print "2"
//	     c) current -> 5/*right of 2 */ and go to step 3
//
//	Step 3 pushes 5 to stack and makes current NULL
//	     Stack S -> 5, 1
//	     current = NULL
//
//	Step 4 pops from S
//	     a) Pop 5: Stack S -> 1
//	     b) print "5"
//	     c) current = NULL /*right of 5 */ and go to step 3
//	Since current is NULL step 3 doesn't do anything
//
//	Step 4 pops again.
//	     a) Pop 1: Stack S -> NULL
//	     b) print "1"
//	     c) current -> 3 /*right of 5 */  
//
//	Step 3 pushes 3 to stack and makes current NULL
//	     Stack S -> 3
//	     current = NULL
//
//	Step 4 pops from S
//	     a) Pop 3: Stack S -> NULL
//	     b) print "3"
//	     c) current = NULL /*right of 3 */  
//
//	Traversal is done now as stack S is empty and current is NULL. 	
	
    public static void inOrderUsingStack(TreeNode root) {
        if (root == null) {
            return;
        }
        
        //keep the nodes in the path that are waiting to be visited
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
         
        //first node to be visited will be the left one
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
         
        // traverse the tree
        while (stack.size() > 0) {
           
            // visit the top node
            node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) {
                node = node.right;
                 
                // the next node to be visited is the leftmost
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }	
    }
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		// We needed this code for other files, so check out the code in the library
		TreeNode root = TreeNode.createMinimalBST(array);
		root.print();
		
		System.out.println(" ");
		System.out.println("In Order: ");
		inOrderTraversal(root, 5, 0);
		
		System.out.println(" ");
		System.out.println("Pre Order: ");
		preOrderTraversal(root);
		
		System.out.println(" ");
		System.out.println("Post Order: ");
		postOrderTraversal(root);
		
		System.out.println(" ");
		System.out.println("In Order Using Stack: ");
		inOrderUsingStack(root);
	}

}
