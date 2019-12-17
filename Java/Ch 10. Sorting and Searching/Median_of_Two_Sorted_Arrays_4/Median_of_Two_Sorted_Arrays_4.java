package Median_of_Two_Sorted_Arrays_4;

import java.util.*;

/* Leetcode # 4. Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]
 
The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 
*/

/*
 *  Solution at: https://fizzbuzzed.com/top-interview-questions-2/
 */

public class Median_of_Two_Sorted_Arrays_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2)  {
    	if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
    		return -1;
    	}
    	
    	/*
    	 * It is easy to come up with a O(n) solution. Then it is high likely that the interviewer 
    	 * will ask you to give the algorithm with time complexity O(logn). 
    	 * 
    	 * For any problem with sorted array, to come up with a O(logN) solution, we should consider 
    	 * the binary search related algorithm. 
    	 * 
    	 * To find the median value of an array, if the array is even, we have to find the two 
    	 * values in the middle. We can solve the problem by adopting the method of getting 
    	 * the Kth number in two sorted arrays.  
    	 */
        int totalNums = nums1.length + nums2.length;

        if (totalNums % 2 == 0) {
            int first = findKth(nums1, 0, nums2, 0, totalNums / 2 - 1);
            int second = findKth(nums1, 0, nums2, 0, totalNums / 2);
            return (double)(first + second) / 2;
        }
        else {
            return findKth(nums1, 0, nums2, 0, totalNums / 2);      
        }
    }
    
    private static int findKth(int[] nums1, int nums1Start, int[] nums2, int nums2Start, int k) {   	

	/*
	 *  If any of the two arrays is empty, then the kth element is the non-empty array's kth element.
     *  If k == 0, the kth element is the first element of A or B.
     *  For all other cases, we compare the (k / 2)th number in A and the (k / 2)th number in B. 
     *    If keyA < keyB, we get rid of first k /2 elements. 
     *    We keep searching in the remainder for the (k /2)th element.
	 */
    	
        if (nums1Start == nums1.length)
            return nums2[nums2Start + k];
        else if (nums2Start == nums2.length)
            return nums1[nums1Start + k];
        else if  (k == 0)
            return Math.min(nums1[nums1Start], nums2[nums2Start]);

        int mid1 = Math.min(nums1.length - nums1Start, (k + 1) / 2);
        int mid2 = Math.min(nums2.length - nums2Start, (k + 1) / 2);
        int a = nums1[nums1Start + mid1 - 1];
        int b = nums2[nums2Start + mid2 - 1];

        if (a < b)
            return findKth(nums1, nums1Start + mid1, nums2, nums2Start, k - mid1);
        else 
        	return findKth(nums1, nums1Start, nums2, nums2Start + mid2, k - mid2);
    }    
    
	public static void main(String[] args) {
		double ans;
		ans = findMedianSortedArrays(new int[] {1, 3}, new int[] {2});		
		System.out.println("Median of [1, 3] and [2] is : " + ans);

		ans = findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4});		
		System.out.println("Median of [1, 2] and [3, 4] is : " + ans);
		
		ans = findMedianSortedArraysBruteForce(new int[] {1, 3}, new int[] {2});		
		System.out.println("Brute Force Median of [1, 3] and [2] is : " + ans);

		ans = findMedianSortedArraysBruteForce(new int[] {1, 2}, new int[] {3, 4});		
		System.out.println("Brute Force Median of [1, 2] and [3, 4] is : " + ans);
		
		ans = findMedianSortedArraysMerged(new int[] {1, 3}, new int[] {2});		
		System.out.println("Brute Force Median of [1, 3] and [2] is : " + ans);

		ans = findMedianSortedArraysMerged(new int[] {1, 2}, new int[] {3, 4});		
		System.out.println("Brute Force Median of [1, 2] and [3, 4] is : " + ans);
	}
	
	
	// This is O(nlogN)
    public static double findMedianSortedArraysBruteForce(int[] nums1, int[] nums2)  {
    	if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
    		return -1;
    	}
    	List<Integer> mylist = new ArrayList<Integer>();
    	for (int i : nums1)
    	{
    	    mylist.add(i);
    	}
    	for (int i : nums2)
    	{
    	    mylist.add(i);
    	}
    	Collections.sort(mylist);
    	
    	int totalNums = nums1.length + nums2.length;
    	if (totalNums % 2 == 0) {
    		int first = mylist.get(totalNums / 2 - 1);
    		int second = mylist.get(totalNums /2);
    		return (double) (first + second) / 2;
    	} else {
    		return mylist.get(totalNums / 2);
    	}    	
    }	
    
	// This is O(N)
    public static double findMedianSortedArraysMerged(int[] nums1, int[] nums2)  {
    	if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
    		return -1;
    	}
    	
    	int totalNums = nums1.length + nums2.length;

    	int index1 = 0; // index for nums1
    	int index2 = 0; // index for nums2
    	int[] merged = new int[totalNums/2 + 1];
    	int mergedIndex = 0;
    	for (int i = 0; i<= totalNums/2; i++) {
    		if (index1 >= nums1.length) {
    			merged[mergedIndex] = nums2[index2];
    			index2++;    			    			
    		} else if (index2 >= nums2.length) {
    			merged[mergedIndex] = nums1[index1];
    			index1++;    			    			
    		} else if (nums1[index1] < nums2[index2]) {
    			merged[mergedIndex] = nums1[index1];
    			index1++;
    		} else {
    			merged[mergedIndex] = nums2[index2];
    			index2++;    			
    		}
    		mergedIndex++;
    	}
    	
    	if (totalNums % 2 == 0) {
    		int first = merged[totalNums / 2 - 1];
    		int second = merged[totalNums /2];
    		return (double) (first + second) / 2;
    	} else {
    		return merged[totalNums / 2];
    	}    	
    }	   
}
