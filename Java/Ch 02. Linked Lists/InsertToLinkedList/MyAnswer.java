package InsertToLinkedList;

import CtCILibrary.LinkedListNode;

public class MyAnswer {
			
   public static LinkedListNode insertNode(LinkedListNode head, int value) {
      if (head == null) {
         return head;
      }
      
      return head;
   }     
   
	
	public static void main(String[] args) {
      LinkedListNode lA1 = new LinkedListNode(2, null, null);
      LinkedListNode lA2 = new LinkedListNode(3, null, lA1);      
      LinkedListNode lA3 = new LinkedListNode(5, null, lA2);
      LinkedListNode lA4 = new LinkedListNode(6, null, lA3);
      LinkedListNode lA5 = new LinkedListNode(8, null, lA4);   
      
      System.out.println("  " + lA1.printForward());    
      
      LinkedListNode insertedLA1New = insertNode(lA1, 4);      
      System.out.println("  " + insertedLA1New.printForward());   

      insertedLA1New = insertNode(lA1, 9);      
      System.out.println("  " + insertedLA1New.printForward());   
      
      insertedLA1New = insertNode(lA1, 1);      
      System.out.println("  " + insertedLA1New.printForward());   

	}
}
