package Q2_06_Palindrome;

import CtCILibrary.LinkedListNode;

public class MyAnswer {
	public static boolean isPalindrome(LinkedListNode head) {
	   LinkedListNode reverse = reverseLinkedlistReturnNew(head);
	   return isEqual(head, reverse);
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
		
	public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
	   LinkedListNode oneRunner = one;
      LinkedListNode twoRunner = two;
	   while (oneRunner != null && twoRunner != null) {
	      if (oneRunner.data != twoRunner.data) {
	         return false;
	      }
	      oneRunner = oneRunner.next;
	      twoRunner = twoRunner.next;
	   }
	   if ( (one != null && two == null) || (one == null && two != null)){
	      // different length
	      return false;
	   }
	   return true;
	}
	
	public static void main(String[] args) {
		int length = 9;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}
		
		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		// nodes[length - 2].data = 9; // Uncomment to ruin palindrome
		
		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		System.out.println(isPalindrome(head));
	}

}
