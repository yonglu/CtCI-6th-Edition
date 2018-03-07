package Introduction;

import java.util.NoSuchElementException;
import java.util.Queue;

// Implement queue from Array

public class MyCirculaQueue {
   private int QUEUE_SIZE = 3;
   private int[] data = new int[QUEUE_SIZE];
	
	private int first = 0;
	private int last = 0;

	public MyCirculaQueue() {
      super();
   }

   public void add(int item) throws Exception {
      if ((last+1) % QUEUE_SIZE == first) {
         throw new Exception("Queue is full");
      }
      data[last] = item;
      last = (last + 1) % QUEUE_SIZE;
	} 

	public int remove() throws NoSuchElementException {
	   if (first == last) {
	        throw new NoSuchElementException();	      
	   }
	   int item = data[first];
	   first = (first+1) % QUEUE_SIZE;;
	   return item;
	}
	
	public int peek() throws Exception {
	     if (first == last) {
           throw new Exception("Queue is empty");        
      }
      int item = data[first];

      return item;
	}
	
	public boolean isEmpty() {
	   return first == last? true : false;
	}
	
	public static void main(String[] args) {
		MyCirculaQueue myQ = new MyCirculaQueue();
		try {
			myQ.add(1);
			myQ.add(2);
//			myQ.add(3);
			int t = myQ.remove();
			System.out.println(t);
			myQ.add(4);
			t = myQ.peek();
			System.out.println(t);
			t = myQ.remove();
			System.out.println(t);
			t = myQ.remove();
			System.out.println(t);
			
			// should have exception
			t = myQ.remove();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
