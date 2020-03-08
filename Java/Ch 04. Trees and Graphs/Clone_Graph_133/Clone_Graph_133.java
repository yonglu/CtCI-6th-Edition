package Clone_Graph_133;

import java.util.*;

/*
 * Leetcode # 133. Clone Graph

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

*/

class Node {
	public enum State {COMPLETE, BLANK};
	public State state;
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
        state = State.BLANK;
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
        state = State.BLANK;
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
        state = State.BLANK;
    }
}
 
public class Clone_Graph_133 {

	public static Node cloneGraph(Node node)  {
		if (node == null) {
			return null;
		}
		
		Map<Node, Node> map = new HashMap<Node, Node>();
		
		return cloneGraph(node, map);
	}
	
	private static Node cloneGraph(Node node, Map<Node, Node> map) {
		if (node == null) {
			return null;
		}
		
		if (map.containsKey(node)) {
			return map.get(node);
		}
		
		Node copy = new Node(node.val);
		map.put(node, copy);
		
		for (Node n : node.neighbors) {
			if (map.containsKey(n)) {
				copy.neighbors.add(map.get(n));
			} else {
				copy.neighbors.add(cloneGraph(n, map));
			}
		}
		
		return copy;
	}
	
	public static Node cloneGraphDFSItertive(Node node)  {
		if (node == null) {
			return null;
		}
		
		Map<Node, Node> map = new HashMap<Node, Node>();
		Stack<Node> stack = new Stack<Node>();
		
		Node copy = new Node(node.val);		
		map.put(node, copy);
		stack.push(node);
		
		while (!stack.isEmpty()) {
			Node old = stack.pop();
			Node cur = map.get(old);
			for (Node n : old.neighbors) {
				if (n != null) {
					if (map.containsKey(n)) {
						cur.neighbors.add(map.get(n));
					} else {
						Node temp = new Node(n.val);
						cur.neighbors.add(temp);
						map.put(n, temp);
						stack.add(n);
					}
				}
			}
		}
		
		return copy;
	}
	
	
	// BFS to print queue
	private static void printNode(Node node) {
		if (node == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		node.state = Node.State.COMPLETE;
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.print("Node " + cur.val + " with neighbors: ");
			for (Node n : cur.neighbors) {
				if (n != null) {
					System.out.print("  " + n.val);
					if( n.state != Node.State.COMPLETE) {
						queue.add(n);
						n.state = Node.State.COMPLETE;
					}
				}
			}
			System.out.println("");
		}
		
	}
	
	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		node1.neighbors.add(node2);
		node1.neighbors.add(node4);
		node2.neighbors.add(node1);
		node2.neighbors.add(node3);
		node3.neighbors.add(node2);
		node3.neighbors.add(node4);
		node4.neighbors.add(node1);
		node4.neighbors.add(node3);
		
		printNode(node1);
		
		Node cur = cloneGraph(node1);
		
		System.out.println("");		
		printNode(cur);

		Node cur2 = cloneGraphDFSItertive(node1);
		
		System.out.println("");		
		printNode(cur2);
		
	}
}
