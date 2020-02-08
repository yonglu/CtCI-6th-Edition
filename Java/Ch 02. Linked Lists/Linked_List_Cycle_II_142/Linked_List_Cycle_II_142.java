package Linked_List_Cycle_II_142;

import java.util.*;


/* Leetcode 142. Linked List Cycle II


Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the
position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is 
no cycle in the linked list.

Note: Do not modify the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

*/

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { 
		 val = x; 
		 next = null;
	}
}

public class Linked_List_Cycle_II_142 {
	
	public static ListNode detectCycle(ListNode head) {
		ListNode ans = null;
	    if(head == null || head.next == null){
	        return ans;
	    } 
	    
	    ListNode slow = head;
	    ListNode fast = head;
	    
	    boolean hasCycle = false;
	    while (fast != null && fast.next != null) {
	    	fast = fast.next.next;
	    	slow = slow.next;
	    	if(fast == slow) {
	    		// detect a cycle now
	    		/*** extra to print the cycle length */
	    		System.out.println("cycle length: " +  calculateCycleLength(slow));
	    		/****/
	    		hasCycle = true;
	    		break;
	    	}
	    }
	    
	    if (hasCycle) {
	    	slow = head;
	    	while (slow != null && slow.next != null ) {
	    		if (slow == fast) {
	    			return slow;
	    		}
	    		slow = slow.next;
	    		fast = fast.next;
	    	}
	    }
	    
	    return ans;
	}
		
	private static int calculateCycleLength(ListNode node) {
		ListNode cur = node;
		int count = 0;
		do {
			count++;
			cur = cur.next;
		} while (cur != node);
		
		return count;
	}
    
	public static void main(String[] args) {
		ListNode tempNode1 = new ListNode(3);
		if (detectCycle(tempNode1) == null) {
			System.out.println("No Cycle"); // expected "No Cycle"			
		}
		ListNode tempNode2 = new ListNode(2);
		tempNode1.next = tempNode2;
		ListNode tempNode3 = new ListNode(0);
		tempNode2.next = tempNode3;
		
		ListNode tempNode4 = new ListNode(-4);
		tempNode3.next = tempNode4;
		tempNode4.next = tempNode2;
		
		System.out.println(detectCycle(tempNode1).val); // expected 2
		
		ListNode tempNode5 = new ListNode(1);
		ListNode tempNode6 = new ListNode(2);
		tempNode5.next = tempNode6;
		tempNode6.next = tempNode5;
		
		System.out.println(detectCycle(tempNode5).val); // expected 1
		
	}
}
