package Merge_K_Sorted_Lists_23.copy;

import java.util.*;

/* Leetcode 23. Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


Solution: https://leetcode.wang/leetCode-23-Merge-k-Sorted-Lists.html
*/

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { val = x; }
}

public class Merge_K_Sorted_Lists_23 {

    public static ListNode mergeKLists2By2(ListNode[] lists) {
        ListNode ans = new ListNode(0);
        
        if (lists == null) {
        	return ans;
        } else if (lists.length == 1) {
        	return lists[0];
        }
        
        
        
        return ans;
    }
    
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
	
		ListNode tempNode9 = new ListNode(3);
		ListNode tempNode10 = new ListNode(5);
		tempNode9.next = tempNode10;

		ListNode tempNode11 = new ListNode(7);
		ListNode tempNode12 = new ListNode(9);
		tempNode11.next = tempNode12;

		ListNode[] kLists = new ListNode[4];
		kLists[0] = tempNode1;
		kLists[1] = tempNode4;
		kLists[2] = tempNode9;
		kLists[3] = tempNode11;
		
		
		ListNode ans = mergeKLists2By2(kLists);
		while (ans != null) {
			System.out.print(ans.val + "->");
			ans = ans.next;
		}
		
	}
}
