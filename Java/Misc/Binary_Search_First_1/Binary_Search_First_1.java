package Binary_Search_First_1;

import java.util.*;

/* 
 * Finx index of first 1 in string
 * 
Example 1:

Input: 00000000000111
Output: 11

Example 2:

Input: 000
Output: -1


*/

public class Binary_Search_First_1 {

    public static int binarySearch(String s) {
    	int ans = -1;
    	if (s == null || s.isEmpty()) {
    		return ans;
    	}
    	
    	int left = 0;
    	int right = s.length() - 1;
    	
    	while (left <= right) {
    		int mid = (left + right)/2;
    		if (s.charAt(mid) == '1') {
    			if (mid == 0 || ((mid-1)>=0 && s.charAt(mid-1) == '0')) {
    				return mid;
    			} else {
    				right = mid - 1;
    			}
    		} else {
    			left = mid + 1;
    		}
    	}
    	
    	return ans;
    }
    
    
	public static void main(String[] args) {
		int result;

		result = binarySearch("00000000000111");		
		System.out.println(result);	// expectd 11

		result = binarySearch("000");		
		System.out.println(result);	 // expected -1	

		result = binarySearch("1111");		
		System.out.println(result);	 // expected 0	

		result = binarySearch("000001");		
		System.out.println(result);	 // expected 5	
	}
}
