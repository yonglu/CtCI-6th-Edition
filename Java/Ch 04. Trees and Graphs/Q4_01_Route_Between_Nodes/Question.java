package Q4_01_Route_Between_Nodes;

import java.util.LinkedList;
import java.util.Queue;

//Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
//route between two nodes.

public class Question {
	public enum State {
		Unvisited, Visited, Visiting;
	} 

	public static void main(String a[])
	{
		Graph g = createNewGraph();
		Node[] n = g.getNodes();
		Node start = n[3];
		Node end = n[5];
      for (Node u : g.getNodes()) {
         u.state = State.Unvisited;
      }
		System.out.println(search(g, start, end));
      System.out.println(mysearchBFS(g, start, end));
      System.out.println(mysearchDFS(g, start, end));
	}
	
	public static Graph createNewGraph()
	{
		Graph g = new Graph();        
		Node[] temp = new Node[6];

		temp[0] = new Node("a", 3);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 1);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);

		temp[0].addAdjacent(temp[1]);
		temp[0].addAdjacent(temp[2]);
		temp[0].addAdjacent(temp[3]);
		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);
		for (int i = 0; i < 6; i++) {
			g.addNode(temp[i]);
		}
		return g;
	}

   public static boolean mysearchBFS(Graph g,Node start,Node end) {  
      if (start == null || end == null) {
         return false;
      }
      Queue<Node> queue = new LinkedList<Node>();
      start.state = Question.State.Visited;
      queue.add(start);
      while (!queue.isEmpty()) {
         Node temp = queue.poll();
         if (temp.getVertex().equals(end.getVertex())) {
            return true;
         }            
         for (Node n : temp.getAdjacent()) {
            if (n.state != State.Visited) {
               queue.add(n);
            }
         }
      }
      
      return false;
   }
   
   public static boolean mysearchDFS(Graph g,Node start,Node end) {  
      if (start == null || end == null) {
         return false;
      }      
      return mysearchDFS(start, end.getVertex());
   }
   
   public static boolean mysearchDFS(Node current, final String matchName) {  
      boolean isMatch = false;
      if (current == null) {
         return false;
      }
      current.state = State.Visited;
      if (current.getVertex().equals(matchName)) {
         return true;
      }  
      for (Node n : current.getAdjacent()) {
         if (n.state != State.Visited) {
            isMatch = mysearchDFS(n, matchName);
            if (isMatch == true) {
               return true;
            }
         }
      }
      
      return isMatch;
   }
	
    public static boolean search(Graph g,Node start,Node end) {  
        LinkedList<Node> q = new LinkedList<Node>();
        for (Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while(!q.isEmpty()) {
            u = q.removeFirst();
            if (u != null) {
	            for (Node v : u.getAdjacent()) {
	                if (v.state == State.Unvisited) {
	                    if (v == end) {
	                        return true;
	                    } else {
	                        v.state = State.Visiting;
	                        q.add(v);
	                    }
	                }
	            }
	            u.state = State.Visited;
            }
        }
        return false;
    }
}
