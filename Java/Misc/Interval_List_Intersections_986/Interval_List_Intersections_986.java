package Interval_List_Intersections_986;

import java.util.*;

/* Leetcode # 986. Interval List Intersections

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, or can be 
represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].) 

Example 1:

Input: 
	A = [[0,2],[5,10],[13,23],[24,25]], 
	B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 
*/

public class Interval_List_Intersections_986 {

	/*
	 *   1. Use Two Pointers
	 *   2. check if 2 intervals have intersection
	 *   3. if yes have intersection, then the intersection is bigger of starting and smaller of ending.
	 *   4. advance the pointer for the interval which's ending is smaller. (remember compare the ending)
	 *   
	 *   The solution is cleaner code
	 */
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
    	if (A == null || A.length == 0 || B == null || B.length == 0) {
    		return new int[0][];
    	}
/*    	
    	// sort the intervals in assending order based on the beginning
    	Arrays.sort(A, new Comparator<int[]>() {
    		@Override
    		public int compare(int[] i1, int[] i2) {
    			return i1[0] - i2[0];
    		}
    	});
    	
    	Arrays.sort(B, new Comparator<int[]>() {
    		@Override
    		public int compare(int[] i1, int[] i2) {
    			return i1[0] - i2[0];
    		}
    	});
*/
    	List<int[]> lists = new ArrayList<int[]>();
    	
    	int aIndex = 0;
    	int bIndex = 0;
    	
    	while (aIndex < A.length && bIndex < B.length) {
    		if (hasIntersection(A[aIndex][0], A[aIndex][1], B[bIndex][0], B[bIndex][1])) {
    			int start = A[aIndex][0];
        		if (A[aIndex][0] <= B[bIndex][0]) {
        			start = B[bIndex][0];
        		}
        		int end = B[bIndex][1];
        		if (A[aIndex][1] <= B[bIndex][1]) {
        			end = A[aIndex][1];
        		}
        		
        		if (start <= end) {
        			lists.add(new int[]{start, end} );
        		}
    		} 
    		
    		// mistake here to compare the starting points.  should compare the end points.
//    		if (A[aIndex][0] <= B[bIndex][0]) {
    		if (A[aIndex][1] <= B[bIndex][1]) {
    			++aIndex;
    		} else {
    			++bIndex;
    		}
    	}    	
    	
    	int[][] result = new int[lists.size()][];  
    	for (int i = 0; i < lists.size(); i++) {
    		result[i] = lists.get(i);
    	}
    	return result;   	
    }
    
    
    public static int[][] intervalIntersection_Solution(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
          // Let's check if A[i] intersects B[j].
          // lo - the startpoint of the intersection
          // hi - the endpoint of the intersection
          int lo = Math.max(A[i][0], B[j][0]);
          int hi = Math.min(A[i][1], B[j][1]);
          if (lo <= hi)
            ans.add(new int[]{lo, hi});

          // Remove the interval with the smallest endpoint
          if (A[i][1] < B[j][1])
            i++;
          else
            j++;
        }

        return ans.toArray(new int[ans.size()][]);
      }
    
    private static boolean hasIntersection(int aStart, int aEnd, int bStart, int bEnd) {
    	if ( ((bStart <= aStart) && (aStart <= bEnd)) || 
    		 ((aStart <= bStart) && (bStart <= aEnd)) )	{
    		return true;
    	} else {
    		return false;
    	}
    }
    
	public static void main(String[] args) {
		int [][] result;
		int[][] A = new int[][] {
			{0, 2},
			{5, 10},
			{13, 23},
			{24, 25}
		};

		int[][] B = new int[][] {
			{1, 5},
			{8, 12},
			{15, 24},
			{25, 26}
		};
		
		result = intervalIntersection(A, B);		
		System.out.println("Interval List Intersections is : " + 
				Arrays.deepToString(result) );		
		System.out.println("Expected: [[1, 2],[5, 5],[8, 10],[15, 23],[24, 24],[25, 25]]");
		
		A = new int[][] {
			{8, 15}
		};
		B = new int[][] {
			{2,6},
			{8,10},
			{12,20}
		};
		result = intervalIntersection(A, B);		
		System.out.println("Interval List Intersections is : " + 
				Arrays.deepToString(result) );		
		System.out.println("Expected: [[8,10],[12,15]]" + 
				"]");
	}
}
