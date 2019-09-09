package DFSandBFS;

import java.util.ArrayList;

public class Node {
	public enum State {COMPLETE, PARTIAL, BLANK};
    public String name;
	public State state;
    public ArrayList<Node> children;
    
	public Node(String name, State state, ArrayList<Node> children) {
		super();
		this.name = name;
		this.state = state;
		this.children = children;
	}
    
}