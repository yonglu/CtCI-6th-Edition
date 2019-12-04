package Three_Sum_15;

import java.util.*;

/* Leetcode # 15. 3Sum

Given an array nums of n integers, are there elements a, b, c in nums 
such that a + b + c = 0? Find all unique triplets in the array which 
gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

public class Three_Sum_15 {


	// threeSumSortFirstTwoPointers is faster than threeSumSortFirstHashSet and threeSumNoSort
	// Algorithm:
	// 	1. sort the array ONlogN)  
	//         (this make it easy to avoid the duplicate set of triplets answer, 
	//          and can use two pointers technique when doing two sum in loop)
	//	2. remember to break if first item is positive and skip the repeat number
	//  3. use two pointers technique to find two sum.  Remember to start from right of
	// 			current index
	public static List<List<Integer>> threeSumSortFirstTwoPointers(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		
		if (nums == null || nums.length < 3) {
			return lists;
		}
		
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length - 2; i++) {
			// Since the nums is sorted, if the first one is positive, no way to make zero sum, we are done.
			if (nums[i] > 0) break;  
			
			// If the number is repeat, skip it.
			if(i > 0 && nums[i] == nums[i-1]) continue;
			
			int l = i + 1;			// left index pointer 
			int r = nums.length - 1;	// right index pointer
			
			while (l  < r) {
				if (nums[l] + nums[r] == -nums[i]) {
	            	List<Integer> tempList = Arrays.asList(nums[i], nums[l], nums[r]);
	            	if (!lists.contains(tempList)) {
	            		lists.add(tempList);
	            	}
	            	while (l < r && nums[l+1] == nums[l]) l++;
	            	while (l < r && nums[r-1] == nums[r]) r--;
					l++;
					r--;
				} else if (nums[l] + nums[r] < -nums[i]) {
					while (l < r && nums[l+1] == nums[l]) l++;
					l++;
				} else {
					while (l < r && nums[r-1] == nums[r]) r--;
					r--;					
				}
			}			
		}
		
		return lists;
	}
	
	/*
	 * threeSumSortFirst is faster than threeSumNoSort, I guess due to that no need to 
	 * sort and dedup the answers.
	 */
	
	public static List<List<Integer>> threeSumSortFirstHashSet(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		
		if (nums == null || nums.length < 3) {
			return lists;
		}
		
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] > 0) break;
//			if(i > 0 && nums[i] == nums[i-1]) continue; 
			// Set the startingPoint for twoSum so doesn't waste 
			// cycle to check from beginning
			twoSumHashSetInlineSaveToList(lists, nums, i+1, nums.length - 1, -nums[i]);
		}
		
		return lists;
	}
	
    private static void twoSumHashSetInlineSaveToList(List<List<Integer>> list, int[] nums,int low,int high,int target){
        if(low > high) return;
        HashSet<Integer> set = new HashSet<>();
        for(int i = low;i<= high;i++){
            if(set.contains(target - nums[i])) {
            	List<Integer> tempList = Arrays.asList(-target,nums[i],target-nums[i]);
            	if (!list.contains(tempList)) {
            		list.add(tempList);
            	}
            } else{
                set.add(nums[i]);
            }
        }
    }	

	// This works, but time exceeded.
	// Mistakes:
	//		1. not filter out duplicate triplets
	//		2. when calling twoSum, need to remove itself, 
	//			otherwise [2, 2, -4] is one of the answer
	//		3. better yet, set the startingPoint to be after the current index
	//			so no waste cycle.
	public static List<List<Integer>> threeSumNoSort(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		
		if (nums == null || nums.length < 3) {
			return lists;
		}
		
		Set<String> checkDupSet = new HashSet<String>();
		
		for (int i = 0; i < nums.length - 2; i++) {
			// Set the startingPoint for twoSum so doesn't waste 
			// cycle to check from beginning
			List<List<Integer>> twoSumLists = twoSumHashSet(nums, i+1, -nums[i]);
			for (List<Integer> list : twoSumLists) {
				List<Integer> tempList = new ArrayList<Integer>();
				tempList.add(nums[i]);
				tempList.add(list.get(0));
				tempList.add(list.get(1));
				
				// To filter out the duplicate triplets
				Collections.sort(tempList);
				if (!checkDupSet.contains(tempList.toString())) {
					checkDupSet.add(tempList.toString());
					lists.add(tempList);
				}
			}
		}
		
		return lists;
	}
	
	public static List<List<Integer>> twoSumHashSet(int[] nums, Integer startingPoint, int target)  {
		List<List<Integer>> lists = new ArrayList<>();
		
		Set<Integer> set = new HashSet<Integer>();
		for (int i = startingPoint; i < nums.length; i++) {
			int otherPart = target - nums[i];
			if (set.contains(otherPart)) {
				List<Integer> tempList = new ArrayList<Integer>();
				tempList.add(otherPart);
				tempList.add(nums[i]);
				lists.add(tempList);
			}
			set.add(nums[i]);
		}
		
		return lists;
	}


	public static void main(String[] args) {
		
		List<List<Integer>> results = twoSumHashSet(new int[] { 2, 5, 7, 11, 4, 15 }, 0, 9);
		
		System.out.println("Two Sum of [2, 5, 7, 11, 4, 15] with target 9 :");
		for (List<Integer> list : results) {
			System.out.println(list.toString());
		}
	
	    results = threeSumNoSort(new int[] {-1, 0, 1, 2, -1, -4 });		
		System.out.println("Three Sum of [-1, 0, 1, 2, -1, -4] with target 0 :");
		for (List<Integer> list : results) {
			System.out.println(list.toString());
		}

	    results = threeSumSortFirstHashSet(new int[] {-1, 0, 1, 2, -1, -4 });		
		System.out.println("Three Sum of [-1, 0, 1, 2, -1, -4] with target 0 :");
		for (List<Integer> list : results) {
			System.out.println(list.toString());
		}
		
	    results = threeSumSortFirstHashSet(new int[] {0, 0, 0, 0});		
		System.out.println("Three Sum of [0, 0, 0, 0] with target 0 :");
		for (List<Integer> list : results) {
			System.out.println(list.toString());
		}

	    results = threeSumSortFirstTwoPointers(new int[] {-1, 0, 1, 2, -1, -4 });		
		System.out.println("Three Sum of [-1, 0, 1, 2, -1, -4] with target 0 :");
		for (List<Integer> list : results) {
			System.out.println(list.toString());
		}
		
	    results = threeSumSortFirstTwoPointers(new int[] {0, 0, 0, 0});		
		System.out.println("Three Sum of [0, 0, 0, 0] with target 0 :");
		for (List<Integer> list : results) {
			System.out.println(list.toString());
		}
		
	}
}
