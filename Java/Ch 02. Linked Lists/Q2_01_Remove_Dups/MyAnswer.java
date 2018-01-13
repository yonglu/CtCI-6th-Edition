package Q2_01_Remove_Dups;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import CtCILibrary.LinkedListNode;

public class MyAnswer {
   // Use HashSet to check duplicate
	public static LinkedListNode deleteDupsA(LinkedListNode head) {
	   Set<Integer> data = new HashSet<Integer>();
      LinkedListNode current = head;
	   while (current != null) {
	      if (data.contains(current.data)) {
	         if (current.next != null) {
	            current.next.prev = current.prev;
	         }
	         current.prev.next = current.next;
	      } else {
	         data.add(current.data);
	      }
	      current = current.next;
	   }
	   
	   return head;
	}
	
	// Look ahead to check duplicate, use less space, but more runtime.
   public static LinkedListNode deleteDupsB(LinkedListNode head) {
      LinkedListNode current = head;
      while (current != null) {
         LinkedListNode lookAhead = current.next;
         while (lookAhead != null) {
            if (current.data == lookAhead.data) {
               lookAhead.prev.next = lookAhead.next;
               if (lookAhead.next != null) {
                  lookAhead.next.prev = lookAhead.prev;
               }
            }
            lookAhead = lookAhead.next;
         }
         current = current.next;
      }
      
      return head;
   }
	
	public static void main(String[] args) {	
		LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
		LinkedListNode head = first;
		LinkedListNode second = first;
		Random random = new Random(); 
		for (int i = 1; i < 8; i++) {
			second = new LinkedListNode(random.nextInt(3), null, null);
			first.setNext(second);
			second.setPrevious(first);
			first = second;
		}
		System.out.println(head.printForward());
		LinkedListNode newHead = deleteDupsB(head);
		System.out.println(newHead.printForward());
	}
}
