package Rotate_List_61;

import java.util.*;

/* Leetcode 61. Rotate List

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

 
*/

class ListNode {
	int val;
	ListNode next; 
	ListNode(int x) { val = x; }
}


public class Rotate_List_61 {

	/*
	 * 		- Try implement with negative k
			- Find the length of the linked list.
			- If n is negative or n is larger than the length of the linked list, adjust this for the 
				number of rotations needed at the tail of the linked list. The adjusted number is 
				always an integer N where 0 <= N < n.
			- If the adjusted number of rotations is 0, then just return the head pointer. 
				This means that no rotations were needed.
			- Find the nth node from the last node of the linked list. As we already have the length 
				of the linked list, it is simpler. It is basically getting to the node 'x' at length 'n - N'. 
				Next pointer of node previous to this node i.e. 'x' should be updated to point to NULL.
			- Start from 'x' and move to the last node of the linked list. Update next pointer of the last 
				node to point to the head node.
			- Make 'x' as the new head node. 'x' is now the head of the linked list after performing n rotations.

	 */
    public static ListNode rotateRight(ListNode head, int k) {
    	if (head == null) {
    		return null;
    	}
    	
    	if (k == 0) {
    		return head;
    	}
    	
    	ListNode cur = head;
    	
    	// need to keep the tail so it will point to head after rotate.
    	ListNode tail = null;
    	
    	// get the length first
    	int len = 0;
    	while (cur != null) {
    		len += 1;
    		tail = cur;
    		cur = cur.next;
    	}
    	
    	// Note k is non negative in the Leetcode problem, just handle and do extra in case if k is negative.
    	boolean neg = false;
    	if (k < 0) {
    		neg = true;
    	}
    	
    	k = Math.abs(k);
    	
    	// Mod the len to make sure k is in the range of 0 < k < n
    	k = k % len;
    	
    	if (k == 0) {
    		return head;
    	}
    	
    	int fromBegin = len - k;
    	if (neg) {
    		fromBegin = k;;
    	}
    	
    	ListNode prev = null;
    	cur = head;
    	int count = 0;
    	while (cur != null) {
    		// break at the rotation point
    		if (fromBegin == count) {
    			break;
    		}
    		count += 1;
    		prev = cur;
    		cur = cur.next;
    	}
    	
    	ListNode temp = head;
    	prev.next = null;
    	tail.next = temp;
    	
    	return cur;
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
		
		ListNode ans = rotateRight(n1, 2);
    	System.out.println("Expected: 4->5->1->2->3");
		printList(ans); 

		System.out.println("");

		n1 = new ListNode(1);
		n2 = new ListNode(2);
		n3 = new ListNode(3);
		n4 = new ListNode(4);
		n5 = new ListNode(5);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = null;

		ans = rotateRight(n1, -2);
    	System.out.println("Expected: 3->4->5->1->2");
		printList(ans); 
		
		n1 = new ListNode(0);
		n2 = new ListNode(1);
		n3 = new ListNode(2);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = null;

		System.out.println("");

		ans = rotateRight(n1, 4);
    	System.out.println("Expected: 2->0->1");
		printList(ans); 
						
	}
}
