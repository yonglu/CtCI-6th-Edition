package K_Closest_Points_To_Origin_973;

import java.util.*;

/* Leetcode # 973. K Closest Points to Origin

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)


Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 
*/

public class K_Closest_Points_To_Origin_973 {

    public static int[][] kClosest(int[][] points, int K) {
    	if ( points == null || points.length == 0 || points[0].length != 2 || K <= 0) {
    		return new int[0][];
    	}
    	
    	PriorityQueue<int[]> myQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
    		@Override
    		public int compare(int[] i1, int[] i2) {
    			return (i1[0] * i1[0] + i1[1] * i1[1]) - (i2[0] * i2[0] + i2[1] * i2[1]);
    		}
    		
    	});
    	
    	for (int[] point : points) {
    		myQueue.add(point);
    	}
    	
    	// check if we are asking more than we have.
    	if ( K > points.length ) {
    		K = points.length;
    	}
    	
    	int[][] ans = new int[K][2];
    	
    	for (int i = 0; i < K; i++) {
    		ans[i] = myQueue.poll();
    	}
    	
    	return ans;
    	
    }
    
    
	public static void main(String[] args) {
		int [][] result;
		int[][] intervals = new int[][] {
			{1, 3},
			{8, 10},
			{2, 6},
			{15, 18}
		};

		result = kClosest(intervals, 2);		
		System.out.println("2 kClosest of [[1,3],[2,6],[8,10],[15,18]] is : " + 
				Arrays.deepToString(result) );		
	}
}
