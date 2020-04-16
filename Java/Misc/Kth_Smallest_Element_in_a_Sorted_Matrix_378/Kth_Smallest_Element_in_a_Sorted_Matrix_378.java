package Kth_Smallest_Element_in_a_Sorted_Matrix_378;

import java.util.*;

/* Leetcode # 378. Kth Smallest Element in a Sorted Matrix

Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
 
*/

/*
 * Solutions: 
 * 
 * https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
 */

public class Kth_Smallest_Element_in_a_Sorted_Matrix_378 {

	/*
     *  Simple PriorityQueue based solution 
     *
	 *  Maintain a PriorityQueue with size k to track only the k smallest elements in the matrix. 
	 *  At the end, the kth smallest element in the matrix will be the largest one in the PriorityQueue.
	 *  
	 *  Time complexity: O(n^2)
	 *  Space complexity: O(n)
	 *  faster than 22.5%
	 */
    public static int kthSmallestPriorityQueueBased(int[][] matrix, int k) {
    	if (matrix == null || matrix.length == 0) {
    		return 0;
    	}
    	
        if (k<=0) {
            return matrix[0][0];
        }
        
        // Create PriorityQueue with descending order
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); 
    	
    	for (int i = 0; i < matrix.length; i++) {
    		for (int j = 0; j < matrix[0].length; j++) {
    			pq.offer(matrix[i][j]);
    			
    			// Poll the largest n - k items out
    			if (pq.size() > k) {
    				pq.poll();
    			}
    		}
    	}
    	
    	return pq.peek();        
    }
    
    
	/*
     *  PriorityQueue solution with optimization
     *  
     *   If the elements in the matrix are in random order, we have no better ways for finding 
     *   and removing the smallest elements in the matrix other than adding all elements to a 
     *   PriorityQueue. This will yield a time (and space) complexity no better, if not worse, 
     *   than that of the naive solution. However, with the rows (or columns) of the matrix sorted, 
     *   we don't have to add all elements to the PriorityQueue at once. Instead, we can create a 
     *   pool of candidate elements as long as we can ensure that it contains the smallest element 
     *   of the matrix (even after removing).
     *
     *   Assume the rows are sorted in ascending order, which implies initially the smallest element 
     *   of the matrix must be within the first column, therefore the pool can be initialized with 
     *   elements from the first column. Now as we are extracting and removing smallest elements 
     *   from the pool, we need to supplement more candiate elements. The key observation here is 
     *   that for each extracted element, it suffices to add to the pool only the element immediately 
     *   following it in the same row, without violating the property that the pool always contains the 
     *   smallest element of the matrix (even after removing).
   	 *  
	 *  Time complexity: O(max(n, k))
	 *  Space complexity: O(n)
	 *  faster than 26.8%
	 */
    public static int kthSmallestPriorityQueueOptimization(int[][] matrix, int k) {
    	if (matrix == null || matrix.length == 0) {
    		return 0;
    	}
    	
        if (k<=0) {
            return matrix[0][0];
        }
        
    	PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
    		// Override
    		public int compare(int[] a, int[]b) {
    			return matrix[a[0]][a[1]] - matrix[b[0]][b[1]];
    		}
    	}); 
    	
    	// add first column into queue
    	for ( int j=0; j<matrix.length; j++) {
    		pq.offer(new int[] {j, 0});
    	}
    	
    	while (k-1 > 0) {
    		int[] temp = pq.poll();
    		
    		// Note if we are at end of the row, don't add new one, just let it poll the next row item. 
    		if (temp[1] < matrix[0].length - 1) {
    			pq.offer(new int[] {temp[0], temp[1] + 1});
    		}
    		k--;
    	}
    	
    	return matrix[pq.peek()[0]][pq.peek()[1]];
    	
    }
    
    
    /*
     * Two pointers approach.  
     * 		- One for right moving pointer and one for down moving pointer.
     * 		- Need map to keep track of which one has visited
     * 		- Compare the value at right moving pointer and down moving pointer to determine which way to go
     * 		- when the move count is k, that's our answer
     * 		- the code is messier than Priority Queue solution, but faster.
     */
    public static int kthSmallestTwoPointers(int[][] matrix, int k) {
    	if (matrix == null || matrix.length == 0) {
    		return 0;
    	}
    	
        if (k<=0) {
            return matrix[0][0];
        }
        
        // ...
        return 0;
    }
    
    /*
     * Binary Search approach.
     * 
     * 		- Start with search space of [MIN, MAX], where MIN and MAX are the minimum and maximum elements in the matrix, respectively.
 	 *		- Find the middle value (candidate) of min + (max-min)/2
 	 *		- comparing the count of elements in the matrix less than or equal to the candidate solution, 
 	 *		  denoted as cnt, with the rank k: if cnt < k, we throw away the left half of the search space; 
 	 *		  otherwise we discard the right half.
 	 *
     */
    public static int kthSmallestBinarySearch(int[][] matrix, int k) {
    	if (matrix == null || matrix.length == 0) {
    		return 0;
    	}
    	
        if (k<=0) {
            return matrix[0][0];
        }
        
        // ...
        return 0;
    }

    
	public static void main(String[] args) {
		int result;
		int[][] matrix = new int[][] {
			{1, 5, 9},
			{10, 11, 13},
			{12, 13, 15}
		};

		result =  kthSmallestPriorityQueueBased(matrix, 4);		
		System.out.println("4th smallest is: (expected 10); " + result);	
		
		result =  kthSmallestPriorityQueueBased(matrix, 8);		
		System.out.println("8th smallest is: (expected 13); " + result);	

		result =  kthSmallestPriorityQueueOptimization(matrix, 4);		
		System.out.println("4th smallest is: (expected 10); " + result);	
		
		result =  kthSmallestPriorityQueueOptimization(matrix, 8);		
		System.out.println("8th smallest is: (expected 13); " + result);	
		
		int[][] matrix2 = new int[][] {
			{1, 3, 5},
			{6, 7, 12},
			{11, 14, 14}
		};
		result =  kthSmallestPriorityQueueOptimization(matrix2, 6);		
		System.out.println("6th smallest is: (expected 11); " + result);	

		result =  kthSmallestPriorityQueueOptimization(matrix2, 8);		
		System.out.println("6th smallest is: (expected 14); " + result);	
		
	}
}
