package Copy_List_With_Random_Pointer_138;

import java.util.*;

/* Leetcode 138. Copy List with Random Pointer


A linked list is given such that each node contains an additional random pointer which could point to 
any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of 
[val, random_index] where:

    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.


Example 1:

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.
 
*/

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
};


public class Copy_List_With_Random_Pointer_138 {

    public static Node copyRandomList(Node head) {
    	if (head == null) {
    		return null;
    	}
    	
    	// map of original node to new cooy node
    	Map<Node, Node> map = new HashMap<Node, Node>();
    	
    	Node runner = head;
    	
    	Node copy = new Node(runner.val);
		map.put(runner, copy);
    	
    	while (runner != null) {
    		Node temp = new Node(runner.val);
    		map.put(runner, temp);
    		
    		copy.next = temp;

    		runner = runner.next;
    		copy = copy.next;
    	}
    	
    	runner = head;
    	while (runner != null) {
    		map.get(runner).random = map.get(runner.random);
    		runner = runner.next;
    	}
    	
    	return map.get(head);
    }
    
    
	public static void main(String[] args) {
		
		Node n1 = new Node(7);
		Node n2 = new Node(13);
		Node n3 = new Node(11);
		Node n4 = new Node(10);
		Node n5 = new Node(1);
		
		n1.next = n2;
		n1.random = null;
		
		n2.next = n3;
		n2.random = n1;
		
		n3.next = n4;
		n3.random = n5;
		
		n4.next = n5;
		n4.random = n3;
		
		n5.next = null;
		n5.random = n1;
		
		Node ans = copyRandomList(n1);
		
		n1 = new Node(1);
		n2 = new Node(2);
		
		n1.next = n2;
		n1.random = n2;
		n2.next = null;
		n2.random = n2;
		
		ans = copyRandomList(n1);
		
		n1 = new Node(3);
		n2 = new Node(3);
		n3 = new Node(3);
		
		n1.next = n2;
		n1.random = null;
		
		n2.next = n3;
		n2.random = n1;
		
		n3.next = null;
		n3.random = null;
		ans = copyRandomList(n1);

		n1 = null;
		ans = copyRandomList(n1);
				
	}
}
