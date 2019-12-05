package Merge_K_Sorted_Lists_23;

import java.util.*;

import com.sun.glass.ui.Size;

import sun.security.util.Length;

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
*/

/*
Solution: https://leetcode.wang/leetCode-23-Merge-k-Sorted-Lists.html

Approach 1: Brute Force O(NlogN)
Intuition & Algorithm

Traverse all the linked lists and collect the values of the nodes into an array.
Sort and iterate over this array to get the proper value of nodes.
Create a new sorted linked list and extend it with the new nodes.

Approach 2: Compare one by one O(kN)
Algorithm

Compare every k nodes (head of every linked list) and get the node with the 
smallest value.
Extend the final sorted linked list with the selected nodes.

Approach 3: Optimize Approach 2 by Priority Queue O(NlogK)
Algorithm

Almost the same as the one above but optimize the comparison process by priority queue. 

Approach 4: Merge lists one by one O(kN)
Algorithm

Convert merge k lists problem to merge 2 lists (k-1) times.

Approach 5: Merge with Divide And Conquer  O(NlogK)
Intuition & Algorithm

This approach walks alongside the one above but is improved a lot. We don't need to 
traverse most nodes many times repeatedly

Pair up k lists and merge each pair.

After the first pairing, k lists are merged into k/2 lists with average 2N/k length, 
then k/4, k/8 and so on.

Repeat this procedure until we get the final sorted linked list.

Thus, we'll traverse almost N nodes per pairing and merging, and repeat this procedure 
about log2^K times.
*/

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { val = x; }
}

public class Merge_K_Sorted_Lists_23 {

	// This is algorithm number 5 in above
	public static ListNode mergeKLists2By2(ListNode[] lists) {
	    if(lists.length==0){
	        return null;
	    }
	    int interval = 1;
	    
	    // very smart way to merge
	    while(interval<lists.length){
	        for (int i = 0; i + interval< lists.length; i=i+interval*2) {
	            lists[i]=mergeTwoLists(lists[i],lists[i+interval]);            
	        }
	        interval*=2;
	    }

	    return lists[0];
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

		// test for even number of ListNode
//		ListNode[] kLists = new ListNode[4];
		// test for odd number of ListNodes
		ListNode[] kLists = new ListNode[3];

		kLists[0] = tempNode1;
		kLists[1] = tempNode4;
		kLists[2] = tempNode9;
//		kLists[3] = tempNode11;		
		
		ListNode ans = mergeKLists2By2(kLists);
		while (ans != null) {
			System.out.print(ans.val + "->");
			ans = ans.next;
		}
		
	}
	
    public static ListNode mergeKLists2By2HasBug(ListNode[] lists) {
        
        if (lists == null || lists.length == 0) {
        	return null;
        } else if (lists.length == 1) {
        	return lists[0];
        }
        
        int size = lists.length;
        int reminder = 0;
        if (lists.length % 2 != 0) {
        	size = lists.length - 1;
        	reminder = 1;
        }
        while (true) {
            int index = 0;
        	for (int i = 0; i < size; i=i+2) {
        		lists[index] = mergeTwoLists(lists[i], lists[i+1]);
         		index++;
        	}
      
     		size = size / 2;

    		if (size <= 1) {
	        	if (reminder == 1) {
	        		lists[0] = mergeTwoLists(lists[0], lists[lists.length-1]);
	        	}
	        	break;
    		}
        	
        }
               
        return lists[0];
    }

}
