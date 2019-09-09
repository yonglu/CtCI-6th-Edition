package DFSandBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import DFSandBFS.Node.State;
import Q4_07_Build_Order.DFS.Project;

public class DFSandBFS {
	

	public static void searchDFS(Node root) {
	    if (root == null || root.state == State.COMPLETE) 
	        return;
	    System.out.println(root.name);
	    root.state = State.COMPLETE;
	    for (Node node : root.children) {
	        if (node.state == State.BLANK) {
	            searchDFS(node);
	        }
	    }
	 }
	
	// different from searchDFS: check cycle and return Stack<Node>
	public static boolean searchDFSWithStack(Node root, Stack<Node> stack) {
	    if (root == null || root.state == State.COMPLETE) {
	        return true;
	    }
	    
		if (root.state == State.PARTIAL) {
			return false; // Cycle
		}
	    
//	    System.out.println(root.name);
//	    root.state = State.COMPLETE;
		root.state = State.PARTIAL;
	    for (Node node : root.children) {
	        if (node.state == State.BLANK) {
				if (!searchDFSWithStack(node, stack)) {
					return false;
				}
	        }
	    }
	    root.state = State.COMPLETE;
	    // note, not easy to use queue because we have to add to queue after set state to partial 
	    // but we also want to set the state to COMPLETE after done too.
	    stack.add(root);
	    
		return true;
	 }
	
	
	public static void searchBFS(Node root) {
		if (root == null || root.state == State.COMPLETE)
			return;
		Queue queue = new LinkedList();
	    root.state = State.COMPLETE;
	    queue.add(root); // Add to the end of queue

	    while (!queue.isEmpty()) {
	        Node r = (Node)queue.poll(); // Remove from the front of the queue
	        System.out.println(r.name);
	        for (Node node : r.children) {
		        if (node.state == State.BLANK) {
		        	node.state = State.COMPLETE;
		            queue.add(node);
		        }
		    }	        
	    }
	 }
	
	// different from searchBFS: return Queue<Node>
	public static void searchBFSWithQueue(Node root, Queue<Node> returnQ) {
		if (root == null || root.state == State.COMPLETE)
			return;
		Queue queue = new LinkedList();
	    root.state = State.COMPLETE;
	    queue.add(root); // Add to the end of queue

	    while (!queue.isEmpty()) {
	        Node r = (Node)queue.poll(); // Remove from the front of the queue
//	        System.out.println(r.name);
	        returnQ.add(r);
	        for (Node node : r.children) {
		        if (node.state == State.BLANK) {
		        	node.state = State.COMPLETE;
		            queue.add(node);
		        }
		    }	        
	    }
	 }
	
		
	public static void main(String[] args) {
			
		Graph graphDFS = new Graph(createNodes());		
		System.out.println("Print DFS:");
		for (Node node: graphDFS.nodes) {
			searchDFS(node);
		}
		
		Graph graphBFS = new Graph(createNodes());
		System.out.println("Print BFS:");
		for (Node node: graphBFS.nodes) {
			searchBFS(node);
		}
		
		Stack<Node> stack = new Stack<Node>();
		Graph graphDFSStack = new Graph(createNodes());		
		System.out.println("Print DFS in Stack:");
		for (Node node: graphDFSStack.nodes) {
			boolean notCycle = searchDFSWithStack(node, stack);
			if (!notCycle) {
				System.out.println("Detect cycle");
				break;
			}
		}
		convertStackToStringList(stack);
		
		Queue<Node> returnQ = new LinkedList<Node>();
		Graph graphBFSStack = new Graph(createNodes());		
		System.out.println("Print BFS in Queue:");
		for (Node node: graphBFSStack.nodes) {
			searchBFSWithQueue(node, returnQ); 
		}
		convertQueueToStringList(returnQ);
		
	}
	
	public static String[] convertStackToStringList(Stack<Node> stack) {
		String[] nodeOrder = new String[stack.size()];
		for (int i = 0; i < nodeOrder.length; i++) {
			nodeOrder[i] = stack.pop().name;
		}
		for (String str : nodeOrder) {
			System.out.println(str);
		}
		return nodeOrder;
	}

	public static String[] convertQueueToStringList(Queue<Node> queue) {
		String[] nodeOrder = new String[queue.size()];
		for (int i = 0; i < nodeOrder.length; i++) {
			nodeOrder[i] = queue.poll().name;
		}
		for (String str : nodeOrder) {
			System.out.println(str);
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
