package Introduction;

import java.util.NoSuchElementException;

public class MyQueue2 {
   private int QUEUE_SIZE = 102;
   private int[] data = new int[QUEUE_SIZE];
	
	private int first = 0;
	private int last = -1;
	private int numOfData = 0;

	public MyQueue2() {
      super();
   }

   public void add(int item) throws Exception {
      if (numOfData >= QUEUE_SIZE) {
         throw new Exception("Queue is full");
      }
      last = last + 1;
      if (last >= QUEUE_SIZE) {
         last = 0;
      }
      data[last] = item;
      numOfData++;
	} 

	public int remove() throws NoSuchElementException {
	   if (numOfData == 0) {
	        throw new NoSuchElementException();	      
	   }
	   int item = data[first];
	   first = first+1;
      if (first >= QUEUE_SIZE) {
         first = 0;
      }
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
}
