package Q2_02_Return_Kth_To_Last;

import CtCILibrary.*;

public class MyAnswer {

	public static LinkedListNode printKthToLast(LinkedListNode head, int k) {
      LinkedListNode runner = head;
	   LinkedListNode lookAhead = head;
	   for (int i = 0; i < k; i++) {
	      if (lookAhead == null) {
	         return null;
	      }
	      lookAhead = lookAhead.next;
	   }
	   
	   while (lookAhead != null) {
	      runner = runner.next;
	      lookAhead = lookAhead.next;
	   }
	   
	   return runner;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 4, 5, 6};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode tempNode = printKthToLast(head, i);
			if (tempNode == null) {
				System.out.println(i + "th to last is null");				
			} else {
				System.out.println(i + "th to last is: " + tempNode.data);
			}
		}
	}

}
