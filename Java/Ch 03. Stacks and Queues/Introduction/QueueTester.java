package Introduction;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import CtCILibrary.AssortedMethods;

public class QueueTester {

	public static void main(String[] args) {
		int[] array = AssortedMethods.randomArray(100, -100, 100);
		MyQueue<Integer> queue1 = new MyQueue<Integer>();		
		Queue<Integer> queue2 = new LinkedList<Integer>();
      MyQueue2 queue3 = new MyQueue2();
		
		for (int a : array) {
			if (a < 0) {
				int top1, top2, top3;
				try {
					top1 = queue1.remove();
				} catch (NoSuchElementException ex) {
					top1 = Integer.MIN_VALUE;
				}
				try {
					top2 = queue2.remove();
				} catch (NoSuchElementException ex) {
					top2 = Integer.MIN_VALUE;
				}
            try {
               top3 = queue3.remove();
            } catch (NoSuchElementException ex) {
               top3 = Integer.MIN_VALUE;
            }
				if (top1 != top2) {
					System.out.println("ERROR: mismatching tails");
				}
				else if (top2 != top3) {
	               System.out.println("ERROR: mismatching tails");
				} else {
					System.out.println("SUCCESS: matching tails: " + top1);
				}
			} else {
				queue1.add(a);
				queue2.add(a);
            try {
               queue3.add(a);
            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
			}
		}
		
		while (!queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty()) {
			int top1, top2, top3;
			try {
				top1 = queue1.remove();
			} catch (NoSuchElementException ex) {
				top1 = Integer.MIN_VALUE;
			}
			try {
				top2 = queue2.remove();
			} catch (NoSuchElementException ex) {
				top2 = Integer.MIN_VALUE;
			}
         try {
            top3 = queue3.remove();
         } catch (NoSuchElementException ex) {
            top3 = Integer.MIN_VALUE;
         }
			if (top1 != top2) {
				System.out.println("ERROR: mismatching tails");
			} else if (top2 != top3) {
            System.out.println("ERROR: mismatching tails");
         } else {
				System.out.println("SUCCESS: matching tails: " + top1);
			}
		}
		
		
      for (int a : array) {
         try {
            queue3.add(a);
            queue3.remove();
            queue3.add(a);
            queue3.remove();
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      if (!queue3.isEmpty()) {
         System.out.println("Error: queue should be empty");
      }
      
	}

}
