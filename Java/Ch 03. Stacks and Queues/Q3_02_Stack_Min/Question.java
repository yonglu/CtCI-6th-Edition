package Q3_02_Stack_Min;

//Stack Min: How would you design a stack which, in addition to push and pop, has a function min
//which returns the minimum element? Push, pop and min should all operate in 0(1) time.
//		
//		1. Keep track of min in the Node
//		2. Have another internal stack to keep track of min
//		
public class Question {
	public static void main(String[] args) {
		StackWithMin stack = new StackWithMin();
		StackWithMin2 stack2 = new StackWithMin2();
		int[] array = {2, 1, 3, 1};
		for (int value : array) {
			stack.push(value);
			stack2.push(value);
			System.out.print(value + ", ");
		}
		System.out.println('\n');
		for (int i = 0; i < array.length; i++) {
			System.out.println("Popped " + stack.pop().value + ", " + stack2.pop());
			System.out.println("New min is " + stack.min() + ", " + stack2.min());
		}
	}

}
