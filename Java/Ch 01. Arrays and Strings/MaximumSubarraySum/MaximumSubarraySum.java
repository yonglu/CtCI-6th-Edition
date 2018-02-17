package MaximumSubarraySum;

import java.io.IOException;
import java.util.Arrays; 
 
//Find the contiguous subarray within an array (containing at least one number) which 
//has the largest sum.

//For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray 
//[4,-1,2,1] has the largest sum = 6.

public class MaximumSubarraySum {  
 
//	Simple idea of the Kadane's algorithm is to look for all positive contiguous segments 
//	of the array (max_ending_here is used for this). And keep track of maximum sum 
//	contiguous segment among all positive segments (max_so_far is used for this). 
//	Each time we get a positive sum compare it with max_so_far and update 
//	max_so_far if it is greater than max_so_far.   
	
	public static int maxSubArraySum(int a[], int size)
	{
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
 
        for (int i = 0; i < size; i++)
        {
			max_ending_here += a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
        
	}   
	
	public static void maxSubArraySumWithStartingAndEndingIndex(int a[], int size) {
		int max_so_far = Integer.MIN_VALUE, max_ending_here = 0, start = 0, end = 0, s = 0;

		for (int i = 0; i < size; i++) {
			max_ending_here += a[i];
			if (max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
				start = s;
				end = i;
			}
			if (max_ending_here < 0) {
				max_ending_here = 0;
				s = i + 1;
			}
		}
		System.out.println("Maximum contiguous sum is " + max_so_far);
		System.out.println("Starting index " + start);
		System.out.println("Ending index " + end);
	}
   
   public static void main(String[] args) {
       int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
       int n = a.length;
       System.out.println(maxSubArraySum(a, n));
       
       maxSubArraySumWithStartingAndEndingIndex(a, n);
   }
}   
   
