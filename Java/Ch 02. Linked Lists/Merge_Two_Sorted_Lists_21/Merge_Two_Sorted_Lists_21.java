package Merge_Two_Sorted_Lists_21;

import java.util.*;

/* Leetcode 21. Merge Two Sorted Lists


Merge two sorted linked lists and return it as a new list. The new list should be made by splicing 
together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { val = x; }
}

public class Merge_Two_Sorted_Lists_21 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null) {
    		return l2;
    	} else if (l2 == null) {
    		return l1;
    	}
    	
    	ListNode curL1 = l1;
    	ListNode curL2 = l2;
    	
    	ListNode ans;
    	if (curL1.val < curL2.val) {
    		ans = curL1;
    		curL1 = curL1.next;
    	} else {
    		ans = curL2;   
    		curL2 = curL2.next;
    	}
    	
    	ListNode cur = ans;
    	
    	while (curL1 != null && curL2 != null) {
    		if (curL1.val < curL2.val) {
    			cur.next = curL1;
    			curL1 = curL1.next;  			    			
    		} else {
    			cur.next = curL2;
    			curL2 = curL2.next;  			    			
    		}
    		cur = cur.next;
    	}
    	
		if (curL1 == null) {
			cur.next = curL2;
		} else {
			cur.next = curL1;
		} 
    	
    	return ans;
    }
 
    
	public static void main(String[] args) {
		ListNode tempNode1 = new ListNode(1);
		ListNode tempNode2 = new ListNode(2);
		tempNode1.next = tempNode2;
		ListNode tempNode3 = new ListNode(4);
		tempNode2.next = tempNode3;
		
		ListNode tempNode4 = new ListNode(1);
		ListNode tempNode5 = new ListNode(3);
		tempNode4.next = tempNode5;
		ListNode tempNode6  = new ListNode(4);
		tempNode5.next = tempNode6;
		ListNode tempNode7  = new ListNode(6);
		tempNode6.next = tempNode7;
		ListNode tempNode8  = new ListNode(8);
		tempNode7.next = tempNode8;
		
		
		ListNode ans = mergeTwoLists(tempNode1, tempNode4);
		while (ans != null) {
			System.out.print(ans.val + "->");
			ans = ans.next;
		}
		
	}
}
