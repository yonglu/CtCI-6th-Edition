package Merge_Intervals_56;

import java.util.*;

/* Leetcode # 56. Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 
*/

public class Merge_Intervals_56 {

    public static int[][] mergeIntervals(int[][] intervals) {
    	if ( intervals == null || intervals.length == 0 ) {
    		return new int[0][];
    	}
    	
    	// sort the intervals in assending order based on the beginning
    	Arrays.sort(intervals, new Comparator<int[]>() {
    		@Override
    		public int compare(int[] i1, int[] i2) {
    			return i1[0] - i2[0];
    		}
    	});
    	
    	List<int[]> lists = new ArrayList<int[]>();
    	lists.add(intervals[0]);
    	
    	for (int i = 1; i< intervals.length; i++) {   		
    		// if intervals not overlap
    		// just add the second interval to our answer
    		if (lists.get(lists.size()-1)[1] < intervals[i][0]) {
    			lists.add(intervals[i]);
    		} else {	// merge the overlap intervals   		
    			lists.get(lists.size()-1)[1] = Math.max
    					(lists.get(lists.size()-1)[1], intervals[i][1]);
    		}
    	}
    	
    	int[][] result = new int[lists.size()][];  
    	for (int i = 0; i < lists.size(); i++) {
    		result[i] = lists.get(i);
    	}
    	return result;
    	
    }
    
    
	public static void main(String[] args) {
		int [][] result;
		int[][] intervals = new int[][] {
			{1, 3},
			{8, 10},
			{2, 6},
			{15, 18}
		};

		result = mergeIntervals(intervals);		
		System.out.println("Merge intervals of [[1,3],[2,6],[8,10],[15,18]] is : " + 
				Arrays.deepToString(result) );		
	}
}
