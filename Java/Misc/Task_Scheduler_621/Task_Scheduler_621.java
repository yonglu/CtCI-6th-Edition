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

	/*
	 * Complexity Analysis:
	 * 
	 * Time complexity : O(time). Number of iterations will be equal to 
	 * 								resultant time time.
	 * Space complexity : O(1). Constant size array mapmap is used.
	 * 
	 * Note, it is possible use formula to calculate it:
	 *   times = (map[25] - 1) * (n+1) + whatever leftover
	 * 
	 */
    public static int leastInterval(char[] tasks, int n)  {
    	
    	if (tasks == null || tasks.length == 0) {
    		return -1;
    	}
    	
    	// Create a sorted task list
    	int[] map = new int[26];
    	for (char c : tasks) {
    		map[c-'A']++;
    	}
    	Arrays.sort(map);
    	
    	int time = 0;
    	while (map[25] > 0) {
    		
    		// we need to skip n different tasks before perform the same task
    		int i = 0;
    		while (i <= n) {
    			// check if we are done.  Because the 25th slot has the most
    			// task, so if the 25th slot is empty, that means we are done.
    			if (map[25] == 0) {
    				break;
    			}
    			
    			// If the decending slots have tasks, then reduce it, 
    			// otherwise it is an idle task, keep the time and i increase.
    			if (i<26 && map[25-i] > 0) {
    				map[25-i]--;
    			}
    			time++;
    			i++;
    		}
    		
    		//  Sort the tasks array with updated counts of instances and 
    		//  again pick up the tasks in the descending order of their 
    		//  number of instances.
    		Arrays.sort(map);
    	}
    	
    	return time;
    }
    
	public static void main(String[] args) {
		int ans;
		ans = leastInterval(new char[] {'A','A','A','B','B','B'}, 2);		
		System.out.println("Least Interva of ['A','A','A','B','B','B'] and n=2 is : " + ans);
		
		ans = leastInterval(new char[] {'A','A','A','A','A','B', 'C','D'}, 2);		
		System.out.println("Least Interva of ['A','A','A','A','A','B', 'C','D'] and n=2 is : " + ans);		
	}
}
