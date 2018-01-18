package Q2_05_Sum_Lists;


import java.util.ArrayList;

import CtCILibrary.LinkedListNode;

public class MyAnswer {
	
	private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
		if (l1 == null && l2 == null ) {
             return null;
		}
		
		int carry = 0;
		LinkedListNode sum = null;
		LinkedListNode sumRunner = null;
		LinkedListNode l1Runner = l1;
		LinkedListNode l2Runner = l2;
		while (l1Runner != null || l2Runner != null) {
			LinkedListNode currentNode = new LinkedListNode();
			int temp = carry;
			if (l1Runner != null) {
				temp += l1Runner.data;
			}
			if (l2Runner != null) {
				temp +=l2Runner.data;
			}
			if (temp >= 10) {
				carry = 1;
				temp = temp - 10;
			} else {
				carry = 0;
			}
			currentNode.data = temp;
			currentNode.next = null;
			
			if (sum == null) {
				sum = currentNode;
				sumRunner = sum;
			} else {
				sumRunner.next = currentNode;
				sumRunner = sumRunner.next;
			}
			
			if (l1Runner != null) {
				l1Runner = l1Runner.next;
			}
			if (l2Runner != null) {
				l2Runner = l2Runner.next;
			}
		}
		
		if (carry > 0) {
			LinkedListNode carryoverNode = new LinkedListNode();
			carryoverNode.data = carry;
			carryoverNode.next = null;
			sumRunner.next = carryoverNode;
		}
		return sum;
	}
	
	public static int linkedListToInt(LinkedListNode node) {
		if (node == null) {
			return -1;
		}
		int i = 0;
		int value = 0;
		while (node != null) {
			value += node.data * Math.pow(10, i);
			node = node.next;
			i++;
		}
		return value;
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
	
	public static void main(String[] args) {
		LinkedListNode lA1 = new LinkedListNode(9, null, null);
		LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
		LinkedListNode lA3 = new LinkedListNode(9, null, lA2);
		
		LinkedListNode lB1 = new LinkedListNode(1, null, null);
		LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
		LinkedListNode lB3 = new LinkedListNode(0, null, lB2);	
		
		LinkedListNode list3 = addLists(lA1, lB1);
		
		System.out.println("  " + lA1.printForward());		
		System.out.println("+ " + lB1.printForward());			
		System.out.println("= " + list3.printForward());	
		
		int l1 = linkedListToInt(lA1);
		int l2 = linkedListToInt(lB1);
		int l3 = linkedListToInt(list3);
		
		System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
		System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));		
		
		// For Part B followup with number order inverted
		System.out.println("");
		
		lA1 = new LinkedListNode(3, null, null);
		lA2 = new LinkedListNode(1, null, lA1);
		
		lB1 = new LinkedListNode(5, null, null);
		lB2 = new LinkedListNode(9, null, lB1);
		lB3 = new LinkedListNode(1, null, lB2);			
		
		System.out.println("  " + lA1.printForward());		
		System.out.println("+ " + lB1.printForward());		
		
		LinkedListNode lA1Reverse = reverseLinkedlistReturnNew(lA1);
      LinkedListNode lB1Reverse = reverseLinkedlistReturnNew(lB1);
		list3 = addLists(lA1Reverse, lB1Reverse);
		
		System.out.println("= " + reverseLinkedlistReturnNew(list3).printForward());	
		
		l1 = linkedListToInt(lA1Reverse);
		l2 = linkedListToInt(lB1Reverse);
		l3 = linkedListToInt(list3);
		
		System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
		System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));		
		
	}
}
