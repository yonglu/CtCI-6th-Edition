package CompanySkytab;

import java.util.NoSuchElementException;

public class Queue {
	

//	QUESTION #4: Threading.
//
//	Implement a thread-safe Queue class with the methods put(x), get().
//
//	Make get() method block if there's nothing in the queue.
//
//	Use example (Python code)
//
//	  q = Queue()
//	  q.put(10)
//	  q.put(20)
//	  
//	  q.get() # returns 10
//	  q.get() # returns 20
//	  q.get() # blocks until another thread put()s an element
	  
	private BaseQueue baseQueue;

	public Queue() {
		super();
		baseQueue = new BaseQueue();
	}

	public synchronized void put(int x) {
		while (baseQueue.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Intereupted!");
			}
		}
		try {
			baseQueue.add(x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyAll();
	}

	public synchronized int get() {
		while (baseQueue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Intereupted!");
			}
		}
		int ret = baseQueue.remove();
		notifyAll();
		return ret;
	}

	public static void main(String[] args) {
		Queue q = new Queue();
		q.put(10);
		q.put(20);
		// q.put(30);

		System.out.println(q.get()); // returns 10
		System.out.println(q.get()); // returns 20
		System.out.println(q.get()); // blocks until another thread put()s an element
		System.out.println("Done");
	}

	public class BaseQueue {
		private int QUEUE_SIZE = 2;
		private int[] data = new int[QUEUE_SIZE];

		private int first = 0;
		private int last = -1;
		private int numOfData = 0;

		public BaseQueue() {
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
			first = first + 1;
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
			return numOfData == 0 ? true : false;
		}

		public boolean isFull() {
			return numOfData == QUEUE_SIZE ? true : false;
		}

	}

}
