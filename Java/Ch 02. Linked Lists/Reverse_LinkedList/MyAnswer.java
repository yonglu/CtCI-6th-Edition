package Reverse_LinkedList;

import CtCILibrary.LinkedListNode;

public class MyAnswer {
			
   // http://algorithms.tutorialhorizon.com/reverse-a-linked-list/   
   public static LinkedListNode reverseLinkedlist(LinkedListNode node) {
      if (node == null || node.next == null) {
         return node;
      }

      LinkedListNode current = node;
      LinkedListNode prev = null;
      LinkedListNode next = null;
      
      while (current != null) {
         next = current.next;
         current.next = prev;
         current.prev = next;  // if this is a double linked list
         prev = current;
         current = next;
      }
      
      node = prev;
      return node;
   }     

   
   public static LinkedListNode reverseAndClone(LinkedListNode node) {
      if (node == null || node.next == null) {
         return node;
      }

      LinkedListNode head = null;
      while (node != null) {
         LinkedListNode n = new LinkedListNode(node.data); // Clone
         n.next = head;
         head = n;
         node = node.next;
      }
      return head;
   }  
   

   public static LinkedListNode reverseLinkedlistReturnNew(LinkedListNode node) {
      if (node == null || node.next == null) {
         return node;
      }

      LinkedListNode reverse = null;
      
      LinkedListNode current = node;
      
      while (current != null) {
         LinkedListNode temp = new LinkedListNode();
         temp.data = current.data;
         if (reverse == null) {
            reverse = temp;
         } else {
            temp.next = reverse;
            reverse.prev = temp;    // if it is double linked list
         }
         
         reverse = temp;
         current = current.next;
      }
      
      node = reverse;
      return node;
   }     
   
   public static LinkedListNode reverseLinkedlistRecursive(LinkedListNode node) {
      if (node == null || node.next == null) {
         return node;
      }
      LinkedListNode rest = reverseLinkedlistRecursive(node.next);
      node.next.next = node;
      node.next = null;
      return rest;
   }   
   
   public static LinkedListNode reverseLinkedlist2(LinkedListNode node) {
		if (node == null) {
			return null;
		}
		
		LinkedListNode end = node;
		while (end.next != null) {
			end = end.next;
		}
		LinkedListNode endRunner = end;
		while (endRunner != null) {
			LinkedListNode temp = endRunner.next;
			endRunner.next = endRunner.prev;
			endRunner.prev = temp; 
			endRunner = endRunner.next;
		}
		return end;
	}		  
	
	public static void main(String[] args) {
      LinkedListNode lA1 = new LinkedListNode(3, null, null);
      LinkedListNode lA2 = new LinkedListNode(1, null, lA1);
      
      LinkedListNode lB1 = new LinkedListNode(5, null, null);
      LinkedListNode lB2 = new LinkedListNode(9, null, lB1);
      LinkedListNode lB3 = new LinkedListNode(1, null, lB2);   
      
      System.out.println("  " + lA1.printForward());     
      System.out.println("  " + lB1.printForward());        
      
      LinkedListNode reversedLA1New = reverseLinkedlistReturnNew(lA1);
      LinkedListNode reversedLB1New = reverseLinkedlistReturnNew(lB1);
      
      System.out.println("  " + reversedLA1New.printForward());   
      System.out.println("  " + reversedLB1New.printForward());   
      
      LinkedListNode reversedLA1 = reverseLinkedlist(lA1);
      LinkedListNode reversedLB1 = reverseLinkedlist(lB1);
      
      System.out.println("  " + reversedLA1.printForward());   
      System.out.println("  " + reversedLB1.printForward());   		
      
      LinkedListNode reversedLA1Recur = reverseLinkedlist(reversedLA1);
      LinkedListNode reversedLB1Recur = reverseLinkedlist(reversedLB1);
      
      System.out.println("  " + reversedLA1Recur.printForward());   
      System.out.println("  " + reversedLB1Recur.printForward());         
	}
}
