package AMergeSortLinkedListAndArray;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

public class Question {
	
	public static int[] mergeArray(int[] a, int[] b) {
		if (a == null || b == null) {
			return null;
		}
		
		int[] c = new int[a.length + b.length];		
		int aRunner = 0; 
		int bRunner = 0;
		int cRunner = 0;
		
		while (aRunner < a.length && bRunner < b.length) {
			if (a[aRunner] < b[bRunner]) {
				c[cRunner] = a[aRunner];
				aRunner++;
			} else {
				c[cRunner] = b[bRunner];
				bRunner++;
			}
			cRunner++;
		}
		
		if (aRunner < a.length && bRunner >= b.length) {
			for (int i = aRunner; i < a.length; i++) {
				c[cRunner] = a[i];
				cRunner++;
			}
		} else if (aRunner >= a.length && bRunner < b.length) {
			for (int i = bRunner; i < b.length; i++) {
				c[cRunner] = b[i];
				cRunner++;
			}
		}

		return c;
	}
	
	
	public static LinkedListNode mergeLinkedList(LinkedListNode a, LinkedListNode b) {
		if (a == null || b == null) {
			return null;
		}
		
		LinkedListNode newhead = new LinkedListNode();
		if (a.data < b.data) {
			newhead.data = a.data;
			a = a.next;
		} else {
			newhead.data = b.data;
			b = b.next;
		}
		newhead.next = null;
		newhead.prev = null;

		LinkedListNode current = newhead;	
		
		while (a != null && b != null) {
			LinkedListNode temp = new LinkedListNode();
			if (a.data < b.data) {
				temp.data = a.data;
				a = a.next;
			} else {
				temp.data = b.data;
				b = b.next;
			}
			current.next = temp;
			current = current.next;
		}
		
		if (a == null && b != null) {
			current.next = b;
		} else if (a != null && b == null) {
			current.next = a;
		}
		
		return newhead;
	}
	
	
	/** Merges array
	 * @param a first array
	 * @param b second array
	 * @param lastA number of "real" elements in a
	 * @param lastB number of "real" elements in b
	 */
	public static void merge(int[] a, int[] b, int lastA, int lastB) {
		int runner = lastA + lastB - 1;
		
		lastA--;
		lastB--;
		while (lastA >= 0 && lastB >= 0) {
			if (a[lastA] < b[lastB]) {
				a[runner] = b[lastB];
				lastB--;
			} else {
				a[runner] = a[lastA];
				lastA--;
			}
			runner--;
		}
		
		if (lastB >= 0) {
			while (lastB >= 0 ) {
				a[runner] = b[lastB];
				lastB--;
				runner--;
			}
		}
	
	}	
	public static void main(String[] args) {
		
		int[] e = {2, 3, 5, 6, 8, 10, 13, 100};
		int[] f = {1, 4, 7, 9, 11, 12};
		
		int[] c = mergeArray(e, f);
		System.out.println(AssortedMethods.arrayToString(c));
		
		LinkedListNode lA1 = new LinkedListNode(1, null, null);
		LinkedListNode lA2 = new LinkedListNode(3, null, lA1);
		LinkedListNode lA3 = new LinkedListNode(4, null, lA2);
		LinkedListNode lA4 = new LinkedListNode(7, null, lA3);
		LinkedListNode lA5 = new LinkedListNode(8, null, lA4);
		
		LinkedListNode lB1 = new LinkedListNode(2, null, null);
		LinkedListNode lB2 = new LinkedListNode(5, null, lB1);
		LinkedListNode lB3 = new LinkedListNode(6, null, lB2);	
		
		LinkedListNode sortedList = mergeLinkedList(lA1, lB1);

		System.out.println(sortedList.printForward());		
		
		int[] a = {2, 3, 5, 6, 8, 10, 13, 100, 0, 0, 0, 0, 0, 0};
		int[] b = {1, 4, 7, 9, 11, 12};
		merge(a, b, 8, 6);
		System.out.println(AssortedMethods.arrayToString(a));
	}

}
