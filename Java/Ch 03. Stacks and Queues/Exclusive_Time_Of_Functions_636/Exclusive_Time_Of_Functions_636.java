package Exclusive_Time_Of_Functions_636;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack; 
 
/*
 * 
 * Leetcode # 636. Exclusive Time of Functions

On a single threaded CPU, we execute some functions.  
Each function has a unique id between 0 and N-1.

We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  
For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  
"1:end:2" means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.  
Note that this does not include any recursive calls to child functions.

The CPU is single threaded which means that only one function is being executed at a given time unit.

Return the exclusive time of each function, sorted by their function id.


Example 1:

Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the 
end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, 
thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of 
total time executing.


Note:

    1 <= n <= 100
    Two functions won't start or end at the same time.
    Functions will always log when they exit.

 */

/*
 * Note: function 0 call function 1, function 1 call function 2, etc...
 */

public class Exclusive_Time_Of_Functions_636 {
    public static int[] exclusiveTime(int n, List<String> logs) {
        if (n == 0 || logs.size() == 0) {
        	return new int[] {};
        }
         
        int[] answer = new int[n];
        
        // Use stack to keep track of what function are we at.
        // Use a stack to mimic a function call stack. Stack top will always yield 
        // the currently executing function.
        Stack<Integer> stack = new Stack<Integer>();
        
        String[] entry;
        int prev = 0;
        
        for (String log : logs) {
        	entry = log.split(":");
        	if (entry[1].equals("start")) {
        		if (!stack.isEmpty()) {
        			// entry[2] is the start of next interval, doesn't belong to current interval.
        			answer[stack.peek()] += Integer.parseInt(entry[2]) - prev;
        		}
    			prev = Integer.parseInt(entry[2]);
        		stack.push(Integer.parseInt(entry[0]));
        	} else {
        		// entry[2] is end of current interval, belong to current interval. 
        		// That's why we have +1 here
        		answer[Integer.parseInt(entry[0])] += Integer.parseInt(entry[2]) - prev + 1;
        		// prev means the start of next interval, so we need to +1
        		prev = Integer.parseInt(entry[2]) + 1;
        		stack.pop();
        	}
        }
        
        return answer;
    }

    
	public static void main(String[] args) throws IOException {	
		
		List<String> list = new ArrayList<String>();
		list.add("0:start:0");
		list.add("1:start:2");
		list.add("1:end:5");
		list.add("0:end:6");
		
		int[] result = exclusiveTime(2, list);
				
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}
}