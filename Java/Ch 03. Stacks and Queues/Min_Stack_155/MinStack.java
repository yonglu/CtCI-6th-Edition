package Min_Stack_155;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * 
 * Leetcode # 155. Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

 

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

*/


public class MinStack extends Stack<Integer>{
	Stack<Integer> s2;
	public MinStack() {
		s2 = new Stack<Integer>();
	}
	
	public void push(int v) {
		super.push(v);
		if (s2.isEmpty()) {
			s2.push(v);
			
		/*
		 *  NOTE: don't forget we want to push to s2 if it is equal to peek.
		 *  for case of push(0), push(1), push(0), then getMin(), pop(), getMin().
		 *  if we don't do equal, then the min value of 0 on top already pop in second getMIn().
		 */
		} else if (v <= s2.peek()) {
			s2.push(v);
		}
	}
	
	public Integer pop() {
		int ans = super.pop();
		if (!s2.isEmpty()) {
			if (ans == s2.peek()) {
				s2.pop();
			}
		}
		return ans;
	}
	
	public Integer top() {
		return super.peek();
	}
	
	public Integer getMin() {
		return s2.peek();
	}
		
	public static void main(String[] args) throws IOException {
		int result;
	
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		result = minStack.getMin();	// Returns -3.
		System.out.println(result);
		result = minStack.pop();	// -3 
		System.out.println(result);
		result = minStack.top();  	// Returns 0.
		System.out.println(result);
		result = minStack.getMin();	// Returns -2.
		System.out.println(result);
	}
}
