package Next_Greater_Element_496;

import java.util.*;

/*
 * Leetcode # 496. Next Greater Element I

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are 
subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding 
places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right 
in nums2. If it does not exist, output -1 for this number.

Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

Note:

    All elements in nums1 and nums2 are unique.
    The length of both nums1 and nums2 would not exceed 1000
*/

public class Next_Greater_Element_496 {


	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ret = new int[nums1.length];
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();  // map from x to next greater element of x
        
        // We first use stack to process num2 to create a map of next
        // greater element.
        // We use a stack to keep a decreasing sub-sequence of num2[], 
        // whenever we see a number x greater than stack.peek() we pop all 
        // elements less than x and for all the popped ones, 
        // their next greater element is x
        // For example [9, 8, 7, 3, 2, 1, 6]
        // The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is 
        // greater than 1 so we pop 1 2 3 whose next greater element should be 6
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        } 
        
        
        for(int i = 0; i < nums1.length; i++) {
        	if (map.containsKey(nums1[i])) {
        		ret[i] = map.get(nums1[i]);
        	} else {
        		ret[i] = -1;
        	}
        }
        return ret;
   }

	public static void main(String[] args) {
		
		int[] result = nextGreaterElement(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 });		
		System.out.println("Next Greater Element : " + Arrays.toString(result));

		result = nextGreaterElement(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 });		
		System.out.println("Next Greater Element : " + Arrays.toString(result));

	}
}
