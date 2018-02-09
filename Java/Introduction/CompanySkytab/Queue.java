package CompanySkytab;

import java.util.NoSuchElementException;

public class Queue {
	
//   QUESTION #1: Array with odd numbers
//
//	In the language of your choice, implement a function named
//	`get_odd_numbers`, which takes as input an array of integers and returns a
//	new array of only the odd numbers, where the order of the elements is
//	otherwise unchanged.
//
//	For instance, `get_odd_numbers([1,2,3,4,5,6,7])` should return `[1,3,5,7]`.



//	QUESTION #2: Binary tree
//
//	Let's say we have a class called Node, which represents a node in a binary
//	tree. Instances of Node have two instance variables: `left` and `right`.
//	They represent the node's children, and each may refer either to another
//	instance of Node or be null.
//
//	In the language of your choice, implement a method on the Node class named
//	`count_descendants`, which will return the total number of nodes in the
//	subtree of the node it's called on.
//
//	For instance, if we have this binary tree,
//
//	             n1
//	            /  \
//	          n2    n3
//	         /     /  \
//	       n4    n5    n6
//	      /     /
//	    n7    n8
//
//	then calling `n3.count_descendants()` should return 3. Calling
//	`n7.count_descendants()` should return 0.


//	QUESTION #3: Relational databases & SQL
//
//	Assume there are two database tables, `dinglebop` and `fleeb`.  `dinglebop` has
//	a single primary key column `dinglebop_id`, and `fleeb` has a single primary
//	key column `fleeb_id`.
//	There's also an associative table `grumbo (dinglebop_id, fleeb_id)` which references `dinglebop` and `fleeb` with foreign keys.
//
//
//	1. `grumbo` establishes a many-to-many relationship between `dinglebop` and `fleeb`. 
//	What needs to happen to ensure that  `dinglebop` can be associated to same `fleeb` only once?
   
//   'grumbo' table's primary key is a composite of both 'dinglebop' table's primary key 'dinglebop_id' 
//   and 'fleeb' table's primary key 'fleeb_id'.  'grumbo' table will have 'dinglebop_id' and 'fleeb_id' 
//   as foreign key column. 
   
//
//
//	2. Write an SQL query displaying counts of each `fleeb` grouped by the
//	associated `dinglebop`. Include the `dinglebop` records that have no `fleeb`,
//	example output:
//
//	  +--------------+-------------+
//	  | dinglebop_id | fleeb_count |
//	  +--------------+-------------+
//	  |            1 |           3 |
//	  |            2 |           1 |
//	  |            3 |           0 | # 0 or NULL
//	  +--------------+-------------+
//
//
//
//	3. Write an SQL query to find all `fleeb` that are not associated with any `dinglebop`



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
//		  q.put(30);
		  
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
			
			public boolean isFull() {
				   return numOfData == QUEUE_SIZE? true : false;
			}

		}

}

