package Q2_03_Delete_Middle_Node;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

//Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
//the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
//that node.
//EXAMPLE
//Input: the node c from the linked list a - >b- >c - >d - >e- >f
//Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f

public class MyAnswer {

	public static boolean deleteNode(LinkedListNode n) {
		if (n == null || n.next == null) {
			return false;
		}
		
		// it is single linked list
		n.data = n.next.data;
		n.next = n.next.next;
		
		return true;
	}
	
	public static void main(String[] args) {
		LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
		System.out.println(head.printForward());
		deleteNode(head.next.next.next.next); // delete node 4
		System.out.println(head.printForward());
	}

}
