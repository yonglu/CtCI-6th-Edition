package ArrayHopper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
 
/* Given an array of integers where each element represents the max number of steps that can be made 
 * forward from that element. Write a function to return the minimum number of jumps to reach the 
 * end of the array (starting from the first element). If an element is 0, then cannot move 
 * through that element.
 * 
 */

/*
 *  See Leetcode #55 Jump Games
 */
public class ArrayHopper {  
	private static int minJumps(int[] arr) { 
		if (arr == null || arr[0] == 0) {
			return Integer.MAX_VALUE;
		}
		
		List<Integer> allNumJumps = new ArrayList<Integer>();
		minJumps(arr, 0, 0, allNumJumps);
		
		int ret = Integer.MAX_VALUE;
		for (int i : allNumJumps) {
			if (i < ret) {
				ret = i;
			}
		}
		return ret;
	}

	private static void minJumps(int[] arr, int index, int numJump, List<Integer> allNumJumps) { 
		if (index >= arr.length - 1) {
			allNumJumps.add(numJump);
			return;
		}
		if (arr[index] == 0) {
			return;
		}
		
		int n = arr[index];
		numJump = numJump+1;
		
		// performance improvement.
		// No need to go through all the step, but just pick the largest jump from the next n.
		int largestSteps = Integer.MIN_VALUE;
		int nextIndex = index+1;
		for (int i = 1; i <= n; i++) {
			if (i+index>=arr.length-1) {
				allNumJumps.add(numJump);
				return;
			}
			int t = i + arr[i+index];
			if (t>largestSteps) {
				largestSteps = t;
				nextIndex = i;
			}
		}
		minJumps(arr, index+nextIndex, numJump, allNumJumps);

	}
	
	private static void minJumpsBruteForce(int[] arr, int index, int numJump, List<Integer> allNumJumps) { 
		if (index >= arr.length - 1) {
			allNumJumps.add(numJump);
			return;
		}
		if (arr[index] == 0) {
			return;
		}
		
		int n = arr[index];
		numJump = numJump+1;
		// In here, we recursively go through each of the next jump
		// See the above example of picking the largest steps so no need to do many calls.
		for (int i = 1; i <= n; i++) {
			minJumps(arr, index+i, numJump, allNumJumps);
		}
	}

   public static void main(String[] args) {
	    int arr[] = {1, 3, 6, 1, 0, 9};
        
	    System.out.println("Minimum number of jumps to reach end is : "+ minJumps(arr));
	    
	    int arr2[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
	    System.out.println("Minimum number of jumps to reach end is : "+
                minJumps(arr2));
	    
	    int arr3[] = {1, 3, 6, 3, 2, 3, 6, 8, 9, 5}; 
	    System.out.println("Minimum number of jumps to reach end is : "+
                minJumps(arr3));

   }
}
