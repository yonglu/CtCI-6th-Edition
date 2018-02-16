package Q2_04_Partition;

import CtCILibrary.LinkedListNode;

//Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
//before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
//to be after the elements less than x (see below). The partition element x can appear anywhere in the
//"right partition"; it does not need to appear between the left and right partitions.
//EXAMPLE
//Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5)
//Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

public class MyAnswer {

	public static LinkedListNode partition(LinkedListNode node, int x) {
		if (node == null) { 
			return null;
		}
		LinkedListNode before = null;
		LinkedListNode beforeRunner = null;
		LinkedListNode after = null;
		LinkedListNode afterRunner = null;
		
		while (node != null) {
			LinkedListNode next = node.next;
			node.next = null;
			if (node.data < x) {
				if (before == null) {
					before = node;
					beforeRunner = before;
				} else {
					beforeRunner.next = node;
					beforeRunner = beforeRunner.next;
				}
			} else {
				if (after == null) {
					after = node;
					afterRunner = after;
				} else {
					afterRunner.next = node;
					afterRunner = afterRunner.next;
				}				
			}		
			node = next; 
		}
		
		if (before != null) {
			beforeRunner.next = after;
			return before;
		} else {
			return after;
		}
	}
	
	public static void main(String[] args) {
		/* Create linked list */
		int[] vals = {33,9,2,3,10,10389,838,874578,5};
		LinkedListNode head = new LinkedListNode(vals[0], null, null);
		LinkedListNode current = head;
		for (int i = 1; i < vals.length; i++) {
			current = new LinkedListNode(vals[i], null, current);
		}
		System.out.println(head.printForward());
		
		/* Partition */
		LinkedListNode h = partition(head, 9);
		
		/* Print Result */
		System.out.println(h.printForward());
	}

}
