package Task_Scheduler_621;

import java.util.*;

/* Leetcode # 621. Task Scheduler

Given a char array representing tasks CPU need to do. It contains capital letters A to Z 
where different letters represent different tasks. Tasks could be done without original 
order. Each task could be done in one interval. For each interval, CPU could finish one 
task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

*/

public class Task_Scheduler_621 {

    public static int leastInterval(char[] tasks, int n)  {
    	return 0;
    }
    
	public static void main(String[] args) {
		int ans;
		ans = leastInterval(new char[] {'A','A','A','B','B','B'}, 2);		
		System.out.println("Least Interva of ['A','A','A','B','B','B'] and n=2 is : " + ans);
	}
}
