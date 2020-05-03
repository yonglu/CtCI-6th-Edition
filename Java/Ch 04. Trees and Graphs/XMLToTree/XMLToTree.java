package XMLToTree;

import java.util.*;
import java.io.*;
import javax.xml.stream.*;

/*
Convert an XML string to an n-ary tree.

Read an XML element using the tokenizer.
Create a tree node of that element and push it on the stack.
Use stack to keep track of levels
	* If the element is an opening tag (startElement), add it as a child of the node at stack top, then push it to the stack.
	* If the element is a text, add it as a child of the node at stack top.  (don’t need to push it to stack.)
	* If the element is a closing tag (endElement) (indicating that we have processed all children of 
			the element on top of the stack), pop the top element from the stack.

*/

class TreeNode {
     String text;
     List<TreeNode> Children;
     TreeNode(String x) { text = x; Children = new ArrayList<TreeNode>();}
}
 
public class XMLToTree {

    public static TreeNode create_xml_tree(String xml) throws XMLStreamException{
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);
        Stack<TreeNode> stack = new Stack<TreeNode>();
    
        TreeNode last = null;
        while(reader.hasNext()) {
          if(reader.getEventType() == XMLStreamConstants.START_DOCUMENT || 
            reader.getEventType() == XMLStreamConstants.SPACE ||
            reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
            reader.next();
            continue;
          }
          else if (reader.getEventType() == XMLStreamConstants.END_ELEMENT){
            if(!stack.empty()) {
              last = stack.pop();
            }
            reader.next();
            continue;
          }
    
          if(reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
            TreeNode node = new TreeNode(reader.getLocalName());
    
            if(!stack.empty()) {
              stack.peek().Children.add(node);
            }
    
            stack.push(node);
          }
          else if(reader.getEventType() == XMLStreamConstants.CHARACTERS) {
            TreeNode node = new TreeNode(reader.getText());
    
            if(!stack.empty()) {
              stack.peek().Children.add(node);
            }
          }
        
          reader.next();
        }
        return last;
      }
      public static void print_tree(TreeNode root, int depth) {
        if (root == null) {
          return;
        }
    
        for (int i = 0; i < depth; ++i) System.out.print("\t");
        System.out.print(root.text + "\n");
        for (TreeNode child : root.Children) {
          print_tree(child, depth + 1);
        }
      }
	
	public static void main(String[] args) {
	      try {
	          String xml = "<xml><data>hello world     </data>    <a><b></b><b><c></c></b></a></xml>";
	          TreeNode result = create_xml_tree(xml);
	          print_tree(result,0);
	        }
	        catch(Exception ex) {
	          ex.printStackTrace();
	        }

	}
}
