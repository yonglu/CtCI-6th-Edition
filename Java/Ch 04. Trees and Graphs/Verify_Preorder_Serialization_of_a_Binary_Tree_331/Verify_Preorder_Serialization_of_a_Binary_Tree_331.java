package Verify_Preorder_Serialization_of_a_Binary_Tree_331;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Leetcode # 331. Verify Preorder Serialization of a Binary Tree

One way to serialize a binary tree is to use pre-order traversal. When we encounter a 
non-null node, we record the node's value. If it is a null node, we record using a 
sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #

For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", 
where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal 
serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' 
representing null pointer.

You may assume that the input format is always valid, for example it could never contain 
two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true

Example 2:

Input: "1,#"
Output: false

Example 3:

Input: "9,#,#,1"
Output: false

*/


public class Verify_Preorder_Serialization_of_a_Binary_Tree_331 {

	/*
	 *   The key of the problem is if a preorder traversal of a binary tree is valid, 
	 *   a leaf node must have the sequence like "number, #, #". Therefore, we can start 
	 *   from leaf nodes of tree, remove the leaf nodes by replacing the number, #, # 
	 *   by a single # until the tree becomes empty. 	 
	 *   
	 *   At the end, we should only have one "#" left.
	 */
	
	public static boolean isValidSerialization(String preorder)  {
		if ( preorder == null || preorder.length() == 0 ) {
			return false;
		}
		
		List<String> list = new ArrayList<String>();
//		list.addAll(Arrays.asList(preorder.split(",")));
		String[] nodes = preorder.split(",");
		for (String node : nodes) {
			list.add(node);
		}
		
		//TODO This doesn't work
		while (list.size() >= 3 && 
			   list.get(list.size()-1).equals("#") &&
			   list.get(list.size()-2).equals("#") &&
			   !list.get(list.size()-3).equals("#")	 ) {
			
			list.remove(list.size()-1);
			list.remove(list.size()-1);
			list.remove(list.size()-1);
			
			list.add("#");
		}
				
		return (list.size() == 1 && list.get(0) == "#") ? true : false;
	}
	
	public static boolean isValidSerializationStack(String preorder)  {
        if (preorder == null || preorder.length() == 0) {
            return true;
        }
         
        String[] nodes = preorder.split(",");
        Stack<String> stack = new Stack<>();
         
        for (String node : nodes) {
            if (node.equals("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                     
                    stack.pop();
                }
            }
             
            stack.push(node);
        }
         
        return stack.size() == 1 && stack.peek().equals("#");
	}
	
	public static void main(String[] args) {
		boolean ans = false;
		
		ans = isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
		System.out.println(ans);

		ans = isValidSerialization("1,#");
		System.out.println(ans);
		
		ans = isValidSerialization("9,#,#,1");
		System.out.println(ans);

		ans = isValidSerializationStack("9,3,4,#,#,1,#,#,2,#,6,#,#");
		System.out.println(ans);

		ans = isValidSerializationStack("1,#");
		System.out.println(ans);
		
		ans = isValidSerializationStack("9,#,#,1");
		System.out.println(ans);
		
	}
}
