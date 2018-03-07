package Introduction;

import java.util.NoSuchElementException;
import java.util.Queue;

// Implement queue from Array

public class MyCirculaQueueNoSize {
   private int QUEUE_SIZE = 3;
   private int[] data = new int[QUEUE_SIZE];
	
	private int first = 0;
	private int last = 0;
	private int numOfData = 0;

	public MyCirculaQueueNoSize() {
      super();
   }

   public void add(int item) throws Exception {
      if (numOfData >= QUEUE_SIZE) {
         throw new Exception("Queue is full");
      }
      data[last] = item;
      last = (last + 1) % QUEUE_SIZE;
      numOfData++;
	} 

	public int remove() throws NoSuchElementException {
	   if (numOfData == 0) {
	        throw new NoSuchElementException();	      
	   }
	   int item = data[first];
	   first = (first+1) % QUEUE_SIZE;;
	   numOfData--;
	   return item;
	}
	
	public int peek() throws Exception {
	     if (numOfData == 0) {
           throw new Exception("Queue is empty");        
      }
      int item = data[first];

      return item;
	}
	
	public boolean isEmpty() {
	   return numOfData == 0? true : false;
	}
	
	public static void main(String[] args) {
		MyCirculaQueueNoSize myQ = new MyCirculaQueueNoSize();
		try {
			myQ.add(1);
			myQ.add(2);
			myQ.add(3);
			int t = myQ.remove();
			System.out.println(t);
			myQ.add(4);
			t = myQ.peek();
			System.out.println(t);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
