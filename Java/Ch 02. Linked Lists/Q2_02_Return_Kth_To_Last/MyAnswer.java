package Q2_02_Return_Kth_To_Last;

import CtCILibrary.*;

public class MyAnswer {

	public static int printKthToLast(LinkedListNode head, int k) throws Exception {
      LinkedListNode runner = head;
	   LinkedListNode lookAhead = head;
	   for (int i = 0; i < k; i++) {
	      if (lookAhead == null) {
	         throw new Exception("k is out of is longer than the linked list");
	      }
	      lookAhead = lookAhead.next;
	   }
	   while (lookAhead.next != null) {
	      runner = runner.next;
	      lookAhead = lookAhead.next;
	   }
	   
	   return runner.data;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 4, 5, 6};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
      try {
         System.out.println(0 + "th to last is: " + printKthToLast(head, 7));
      } catch (Exception e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

		for (int i = 0; i <= array.length + 1; i++) {
			try {
	         System.out.println(i + "th to last is: " + printKthToLast(head, i));
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
		}
	}

}
