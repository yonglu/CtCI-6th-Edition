package Q3_01_Three_in_One;

import java.util.EmptyStackException;

import CtCILibrary.AssortedMethods;

public class MyFixedMultiStack {
	private int numberOfStacks = 3;
	private int stackCapacity;
	private int[] values;
	private int[] sizes;
	
	public MyFixedMultiStack(int stackSize) {
		stackCapacity = stackSize;
		values = new int[numberOfStacks * stackCapacity];
		sizes = new int[numberOfStacks];
	}

	/* Push value onto stack. */
	public void push(int stackNum, int value) throws FullStackException {
	}

	/* Pop item from top stack. */
	public int pop(int stackNum) {
	}

	/* Return top element. */
	public int peek(int stackNum) {
	}

	/* Return if stack is empty. */
	public boolean isEmpty(int stackNum) {
		return sizes[stackNum] == 0;
	}
	
	/* Return if stack is full. */
	public boolean isFull(int stackNum) {
		return sizes[stackNum] == stackCapacity;
	}
	
	/* Returns index of the top of the stack. */
	private int indexOfTop(int stackNum) {
	}	
	
	public int[] getValues() {
		return values;
	}
	
	public int[] getStackValues(int stackNum) {
	}
	
	public String stackToString(int stackNum) {
		int[] items = getStackValues(stackNum);
		return stackNum + ": " + AssortedMethods.arrayToString(items);
	}	
}
