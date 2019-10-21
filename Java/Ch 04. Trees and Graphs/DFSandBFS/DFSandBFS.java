package DFSandBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import DFSandBFS.Node.State;
import Q4_07_Build_Order.DFS.Project;

public class DFSandBFS {
	

	public static void searchDFS(Node root) {
	    if (root == null) 
	        return;
	    root.state = State.COMPLETE;
	    System.out.print(root.name + " ");
	    for (Node node : root.children) {
	        if (node != null && node.state == State.BLANK) {
	            searchDFS(node);
	        }
	    }
	 }
	
    // Iterative DFS using internal stack and return queue
   public static void searchDFSWithStackAndReturnQueue(Node root, Queue<Node> returnQ)
   {
       Stack<Node> internalStack=new Stack<Node>();
       internalStack.add(root);
       while (!internalStack.isEmpty())
       {
           Node element=internalStack.pop();
    	   element.state = State.COMPLETE;
    	   // Print out and add to return queue
    	   System.out.print(element.name + " ");
    	   returnQ.add(element);
	   	   for (Node node : element.children) {
		        if (node != null && node.state == State.BLANK) {
		            internalStack.add(node);
		        }
		    }
       }
   }
   	   
	// different from searchDFS: check cycle and return Stack<Node>
	public static boolean searchDFSWithReturnStack(Node root, Stack<Node> returnStack) {
	    if (root == null) {
	        return true;
	    }
	    
		if (root.state == State.PARTIAL) {
			return false; // Cycle
		}
	    
//	    System.out.println(root.name);
//	    root.state = State.COMPLETE;
		if (root.state == State.BLANK) {
			root.state = State.PARTIAL;
		    for (Node node : root.children) {
		        if (node != null && node.state != State.COMPLETE) {
					if (!searchDFSWithReturnStack(node, returnStack)) {
						return false;
					}
		        }
		    }
		    root.state = State.COMPLETE;
		    // note, not easy to use queue because we have to add to queue after set state to partial 
		    // but we also want to set the state to COMPLETE after done too.
		    returnStack.add(root);
		}
		return true;
	 }
	
	// searchBFS and return Queue<Node>
	public static void searchBFSWithReturnQueue(Node root, Queue<Node> returnQ) {
		if (root == null)
			return;
		Queue<Node> queue = new LinkedList<Node>();
	    queue.add(root); // Add to the end of queue

	    while (!queue.isEmpty()) {
	        Node r = (Node)queue.poll(); // Remove from the front of the queue
        	r.state = State.COMPLETE;
	        System.out.print(r.name + " ");
	        returnQ.add(r);
	        for (Node node : r.children) {
		        if (node != null && node.state == State.BLANK) {
		            queue.add(node);
		        }
		    }	        
	    }
	 }	
		
	public static void main(String[] args) {
			
		Graph graphDFS = new Graph(createNodes());		
		System.out.println("Print DFS recursive:");
		System.out.print("    ");
		for (Node node: graphDFS.nodes) {
			searchDFS(node);
		}
	    System.out.println();
	    
		Queue<Node> returnQ = new LinkedList<Node>();
		Graph graphDFSWithStack = new Graph(createNodes());		
		System.out.println("Print DFS Iterative using stack:");
		System.out.print("    ");
		for (Node node: graphDFSWithStack.nodes) {
			searchDFSWithStackAndReturnQueue(node, returnQ);
		}
	    System.out.println();
	    
		System.out.println("Print DFS Iterative in Return Queue:");
		System.out.print("    ");
		convertQueueToStringList(returnQ);
	    System.out.println();
	    

		Stack<Node> stack = new Stack<Node>();
		Graph graphDFSStack = new Graph(createNodes());		
		System.out.println("Print DFS in Return Stack:");
		System.out.print("    ");
		for (Node node: graphDFSStack.nodes) {
			boolean notCycle = searchDFSWithReturnStack(node, stack);
			if (!notCycle) {
				System.out.println("Detect cycle");
				break;
			}
		}
		convertStackToStringList(stack);
	    System.out.println();

	    Queue<Node> returnQ2 = new LinkedList<Node>();
		Graph graphBFSStack = new Graph(createNodes());		
		System.out.println("Print BFS:");
		System.out.print("    ");
		for (Node node: graphBFSStack.nodes) {
			searchBFSWithReturnQueue(node, returnQ2); 
		}
	    System.out.println();
		System.out.println("Print BFS in Return Queue:");
		System.out.print("    ");
		convertQueueToStringList(returnQ2);
	    System.out.println();
		
	}
	
	public static String[] convertStackToStringList(Stack<Node> stack) {
		String[] nodeOrder = new String[stack.size()];
		for (int i = 0; i < nodeOrder.length; i++) {
			nodeOrder[i] = stack.pop().name;
		}
		for (String str : nodeOrder) {
			System.out.print(str + " ");
		}
		return nodeOrder;
	}

	public static String[] convertQueueToStringList(Queue<Node> queue) {
		String[] nodeOrder = new String[queue.size()];
		for (int i = 0; i < nodeOrder.length; i++) {
			nodeOrder[i] = queue.poll().name;
		}
		for (String str : nodeOrder) {
			System.out.print(str + " ");
		}
		return nodeOrder;
	}
	
	public static ArrayList<Node> createNodes() {
		//             root
		//     a        b         c                      
		//     d                  e
		//   f  g                 h
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		Node g = new Node("g", State.BLANK, new ArrayList<Node>());
		Node f = new Node("f", State.BLANK, new ArrayList<Node>());
		ArrayList<Node> dChild = new ArrayList<Node>();
		dChild.add(f);		
		dChild.add(g);
		Node d = new Node("d", State.BLANK, dChild);
		ArrayList<Node> aChild = new ArrayList<Node>();
		aChild.add(d);
		Node a = new Node("a", State.BLANK, aChild);
		
		Node b = new Node("b", State.BLANK, new ArrayList<Node>());
		
		Node h = new Node("h", State.BLANK, new ArrayList<Node>());
		ArrayList<Node> eChild = new ArrayList<Node>();
		eChild.add(h);
		Node e = new Node("e", State.BLANK, eChild);
		
		ArrayList<Node> cChild = new ArrayList<Node>();
		cChild.add(e);
		Node c = new Node("c", State.BLANK, cChild);
		
		ArrayList<Node> rootChild = new ArrayList<Node>();
		rootChild.add(a);
		rootChild.add(b);
		rootChild.add(c);
		Node root = new Node("root", State.BLANK, rootChild);
		
		nodes.add(root);

		return nodes;
	}	
		
}
