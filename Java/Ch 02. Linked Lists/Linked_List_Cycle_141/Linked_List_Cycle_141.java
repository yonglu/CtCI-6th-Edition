package Linked_List_Cycle_141;

import java.util.*;


/* Leetcode 141. Linked List Cycle


Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents
the position (0-indexed) in the linked list where tail connects to. If pos is -1, then 
there is no cycle in the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: false
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

public class Linked_List_Cycle_141 {
	
	public static boolean hasCycle(ListNode head) {
		boolean ans = false;
	    if(head == null){
	        return ans;
	    } else if (head.next == null) {
	    	return ans;
	    }
	    
	    ListNode slow = head;
	    ListNode fast = head;
	    
	    while (fast != null && fast.next != null) {
	    	fast = fast.next.next;
	    	slow = slow.next;
	    	if(fast == slow) {
	    		return true;
	    	}
	    }
	    
	    return ans;
	}
	
	
    
	public static void main(String[] args) {
		ListNode tempNode1 = new ListNode(3);
		System.out.println(hasCycle(tempNode1)); // expected false
		ListNode tempNode2 = new ListNode(2);
		tempNode1.next = tempNode2;
		ListNode tempNode3 = new ListNode(0);
		tempNode2.next = tempNode3;
		
		ListNode tempNode4 = new ListNode(-4);
		tempNode3.next = tempNode4;
		tempNode4.next = tempNode2;
		
		System.out.println(hasCycle(tempNode1)); // expected true
		
		ListNode tempNode5 = new ListNode(1);
		ListNode tempNode6 = new ListNode(2);
		tempNode5.next = tempNode6;
		tempNode6.next = tempNode5;
		
		System.out.println(hasCycle(tempNode5)); // expected true
		
	}
}
