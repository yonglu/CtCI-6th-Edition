package InsertToLinkedList;

import CtCILibrary.LinkedListNode;

public class MyAnswer {
			
   public static LinkedListNode insertNode(LinkedListNode head, int value) {
      if (head == null) {
    	  LinkedListNode temp = new LinkedListNode(value);
    	  return temp;
      }
      
      LinkedListNode runner = head;
      
      // insert first
      if (runner.data > value) {
    	  LinkedListNode temp = new LinkedListNode(value);
    	  temp.next = runner;
    	  return temp;    	  
      }     
      
      // insert middle
      while (runner.next != null) {
    	  if (runner.next.data > value) {
        	  LinkedListNode temp = new LinkedListNode(value);
    		  temp.next = runner.next;
    		  runner.next = temp;
    		  break;
    	  }
    	  runner = runner.next;
      }
      
      // insert to the last one
      if(runner.next == null) {
       	  LinkedListNode temp = new LinkedListNode(value);
       	  runner.next = temp;
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
