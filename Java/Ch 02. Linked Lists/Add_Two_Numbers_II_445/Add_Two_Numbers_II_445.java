package Add_Two_Numbers_II_445;

import java.util.*;

/* Leetcode 445. Add Two Numbers II

You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the 
number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists 
is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7 
*/

class ListNode {
	ListNode next;
	int val;
	public ListNode(int mVal) {
		val = mVal;
		next = null;
	}
}

public class Add_Two_Numbers_II_445 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if (l1 == null) {
    		return l2;
    	} else if (l2 == null) {
    		return l1;
    	}
    	
    	l1 = reverseLinkList(l1);
    	l2 = reverseLinkList(l2);
    	
    	ListNode ans = null;
    	ListNode cur = null;
    	ListNode l1Cur = l1;
    	ListNode l2Cur = l2;
    	int carry = 0;    	
    	
    	while (l1Cur != null || l2Cur != null) {
    		int temp = carry;
    		if (l1Cur != null) {
    			temp = temp + l1Cur.val;
    			l1Cur = l1Cur.next;
    		}
    		if (l2Cur != null) {
    			temp = temp + l2Cur.val;
    			l2Cur = l2Cur.next;
    		}
    		carry = temp / 10;
    		temp = temp % 10;
    		ListNode tempNode = new ListNode(temp);
    		if (ans == null) {
    			ans = tempNode;
    			cur = tempNode;
    		} else {
    			cur.next = tempNode;
    			cur = cur.next;
    		}
    	}
    	
    	// Mistake in forgetting the carry at the end.
    	if (carry > 0) {
    		cur.next = new ListNode(carry);
    	}
    	    	
    	return reverseLinkList(ans);
    }
 
    private static ListNode reverseLinkList(ListNode list) {
    	if (list == null) {
    		return list;
    	}
    	ListNode prev = null;
    	ListNode current = list;
    	ListNode next = null;
    	
    	while (current != null) {
    		
    		// mistake in not saving the .next first.
    		next = current.next;
    		current.next = prev;
    		prev = current;
    		current = next;
    	}
    	
    	return prev;
    }
    
    
	public static void main(String[] args) {
		ListNode tempNode1 = new ListNode(3);
		ListNode tempNode2 = new ListNode(4);
		tempNode2.next = tempNode1;
		ListNode tempNode3 = new ListNode(4);
		tempNode3.next = tempNode2;
		ListNode l1 = new ListNode(9);
		l1.next = tempNode3;
		
		ListNode tempNode4 = new ListNode(4);
		ListNode tempNode5 = new ListNode(6);
		tempNode5.next = tempNode4;
		ListNode l2 = new ListNode(5);
		l2.next = tempNode5;
		
		ListNode ans = addTwoNumbers(l1, l2);
		while (ans != null) {
			System.out.println(ans.val);
			ans = ans.next;
		}
		
	}
}
