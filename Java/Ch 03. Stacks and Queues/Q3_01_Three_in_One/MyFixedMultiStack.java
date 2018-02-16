package Q3_01_Three_in_One;

import java.util.EmptyStackException;

import CtCILibrary.AssortedMethods;

//Three in One: Describe how you could use a single array to implement three stacks.

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
	   if (stackNum >= numberOfStacks) {
	      throw new IllegalArgumentException("Stack " + stackNum + " not available.");
	   }
	   if (isFull(stackNum)) {
	      throw new FullStackException("Stack is full");
	   }
	   sizes[stackNum]++;
	   values[indexOfTop(stackNum)] = value;
	}

	/* Pop item from top stack. */
	public int pop(int stackNum) {
      int temp = peek(stackNum);
      sizes[stackNum]--;
      return temp;
	}

	/* Return top element. */
	public int peek(int stackNum) {
      if (stackNum >= numberOfStacks) {
         throw new IllegalArgumentException("Stack " + stackNum + " not available.");
      }
      if (isEmpty(stackNum)) {
         throw new EmptyStackException();
      }
      int temp = values[indexOfTop(stackNum)];
      return temp;
	}

	/* Return if stack is empty. */
	public boolean isEmpty(int stackNum) {
      if (stackNum >= numberOfStacks) {
         throw new IllegalArgumentException("Stack " + stackNum + " not available.");
      }
		return sizes[stackNum] == 0;
	}
	
	/* Return if stack is full. */
	public boolean isFull(int stackNum) {
      if (stackNum >= numberOfStacks) {
         throw new IllegalArgumentException("Stack " + stackNum + " not available.");
      }
		return sizes[stackNum] == stackCapacity;
	}
	
	/* Returns index of the top of the stack. */
	private int indexOfTop(int stackNum) {
      if (stackNum >= numberOfStacks) {
         throw new IllegalArgumentException("Stack " + stackNum + " not available.");
      }
      return stackNum * stackCapacity + sizes[stackNum] - 1; 
	}	
	
	public int[] getValues() {
		return values;
	}
	
	public int[] getStackValues(int stackNum) {
      if (stackNum >= numberOfStacks) {
         throw new IllegalArgumentException("Stack " + stackNum + " not available.");
      }
      int[] stackValues = new int[sizes[stackNum]];
      for (int i = 0; i < sizes[stackNum]; i++) {
         stackValues[i] = values[stackNum * stackCapacity + i];
      }
      return stackValues;
	}
	
	public String stackToString(int stackNum) {
      if (stackNum >= numberOfStacks) {
         throw new IllegalArgumentException("Stack " + stackNum + " not available.");
      }
		int[] items = getStackValues(stackNum);
		return stackNum + ": " + AssortedMethods.arrayToString(items);
	}	
}
