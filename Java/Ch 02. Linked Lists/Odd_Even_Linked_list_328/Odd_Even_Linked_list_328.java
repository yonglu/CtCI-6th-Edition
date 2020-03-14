package Odd_Even_Linked_list_328;

import java.util.*;

/* Leetcode 328. Odd Even Linked List

Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
 
*/

class ListNode {
	int val;
	ListNode next; 
	ListNode(int x) { val = x; }
}


public class Odd_Even_Linked_list_328 {

    /* 
    	- create two lists consisting of nodes at even and odd indices.
    	- (not need for this problem, only if want the even node in reverse order) 
        		While extracting even nodes, we will push them to the head of list_even 
        		since we need them in reverse order
    	- merging odd and even list nodes alternately.
     */
	
    public static ListNode oddEvenList(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    	
    	ListNode evenHead = head.next;
    	
    	ListNode cur = null;    	
    	if (evenHead != null && evenHead.next != null) {
    		cur = evenHead.next;
    	}
    	
    	ListNode oddRunner = head;
    	ListNode evenRunner = evenHead;
    	
    	boolean oddFlag = true;
    	while (cur != null) {
    		if (oddFlag) {
    			oddRunner.next = cur;
    			oddRunner = oddRunner.next;
    			oddFlag = false;
    		} else {
    			evenRunner.next = cur;
    			evenRunner = evenRunner.next;
    			oddFlag = true;
    		}
    		
    		cur = cur.next;
    	}
    	
    	// make sure set the evenRunner.next to null
        if (evenRunner != null) {
        	evenRunner.next = null;
        }
    	
    	// the oddRunner should be at the end of odd list already
    	oddRunner.next = evenHead;
    	
    	return head;
    }

    private static void printList(ListNode head) {
    	while (head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	System.out.println("");
    }
    
	public static void main(String[] args) {
		
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = null;
		
		ListNode ans = oddEvenList(n1);
    	System.out.println("Expected: 1->3->5->2->4");
		printList(ans); 

		System.out.println("");

		n1 = new ListNode(2);
		n2 = new ListNode(1);
		n3 = new ListNode(3);
		n4 = new ListNode(5);
		n5 = new ListNode(6);
		ListNode n6 = new ListNode(4);
		ListNode n7 = new ListNode(7);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = null;

		ans = oddEvenList(n1);
    	System.out.println("Expected: 2->3->6->7->1->5->4");
		printList(ans); 
		
	}
}
