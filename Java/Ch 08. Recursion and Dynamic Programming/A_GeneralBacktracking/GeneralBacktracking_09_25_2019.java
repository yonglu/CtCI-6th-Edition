package A_GeneralBacktracking;

import java.util.*;

/*
 *    A general approach to backtracking questions in Java.  This is good for
 *    walking down the array/string and find the combination of something. 
 *    (Subsets, Subset Sum, Palindrome Partitioning, etc...)
 *    
 *    It can work for Permutation too, but the code pattern is different and
 *    doesn't work if there is duplicate items in the array or string.  Use different 
 *    technique for permutation. 
 *    
 *    https://medium.com/leetcode-patterns/leetcode-pattern-3-backtracking-5d9e5a03dc26
 *    
 *    https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
public class GeneralBacktracking_09_25_2019 {

   public static List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> lists = new ArrayList<>();
      Arrays.sort(nums);
      subsetsWithDup(lists, new ArrayList<Integer>(), nums, 0);
      return lists;
  }

   public static void subsetsWithDup(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int start)
   {
	   lists.add(new ArrayList<Integer>(tempList));
	   for (int i=start; i<nums.length; i++) {
		   // check duplicate
		   if (i>start && nums[i] == nums[i-1]) 
		   {
			   continue;
		   }
		   tempList.add(nums[i]);
		   subsetsWithDup(lists, tempList, nums, i+1);
		   tempList.remove(tempList.size()-1);
	   }
	   return;
   }

   
  public static List<List<Integer>> combinationSum(int[] nums, int target) {
     List<List<Integer>> lists = new ArrayList<>();
     Arrays.sort(nums);
     combinationSum(lists, new ArrayList<Integer>(), nums, target, 0);
     return lists;
 }
  
  
  public static void combinationSum(List<List<Integer>> lists, List<Integer> tempList, 
		  int[] nums, int target, int start)
  {
	  if (target < 0) return;
	  if (target == 0) {
		  lists.add(new ArrayList<Integer>(tempList));
	  }
	  for (int i=start; i<nums.length; i++) {
		   tempList.add(nums[i]);
		   combinationSum(lists, tempList, nums, target-nums[i], i+1);
		   tempList.remove(tempList.size()-1);
	   }
	   return;
  }

  
	/*
	 * // https://leetcode.com/problems/combination-sum/
	 * 
	 * Given a set of candidate numbers (candidates) (without duplicates) and a
	 * target number (target), find all unique combinations in candidates where the
	 * candidate numbers sums to target.
	 * 
	 * The same repeated number may be chosen from candidates unlimited number of
	 * times.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. The solution set
	 * must not contain duplicate combinations.
	 * 
	 * Example 1:
	 * 
	 * Input: candidates = [2,3,6,7], target = 7, A solution set is: [ [7], [2,2,3]
	 * ]
	 */
  

 // Given a string s, partition s such that every substring of the partition is a palindrome.
 // Return all possible palindrome partitioning of s.
 // For example, given s = "aab",
 // return:
 // [
 //  ["aa","b"],
 //  ["a","a","b"]
 // ]
  
  
  
  
 public static List<List<String>> palindromePartition(String s) {
    List<List<String>> list = new ArrayList<>();
    return list;
 }
 
 /*
 *    It can work for Permutation too, but the code pattern is different and
 *    doesn't work if there is duplicate items in the array or string.  Use different 
 *    technique for permutation. 
 */
  public static List<List<Integer>> permute(int[] nums) {
     List<List<Integer>> list = new ArrayList<>();
     // Arrays.sort(nums); // not necessary
     permute(list, new ArrayList<Integer>(), nums);
     return list;
  }

  private static void permute(List<List<Integer>> list, List<Integer> tempList, int [] nums){
     if(tempList.size() == nums.length){
        list.add(new ArrayList<Integer>(tempList));
     }
     
     for(int i = 0; i < nums.length; i++){ 
        if(tempList.contains(nums[i])) continue; // element already exists, skip
        tempList.add(nums[i]);
        permute(list, tempList, nums);
        tempList.remove(tempList.size() - 1);
     }
  }   
  
	public static void main(String[] args) {
		int[] set = new int[] { 2, 3, 5, 6, 7 };
//  	int[] set = new int[] { -10, -8, -2, -1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		List<List<Integer>> results = subsetsWithDup(set);
      System.out.println("Subset with Dup:");
		for (List<Integer> subset : results) {
			System.out.println(subset.toString());
		}
		
      List<List<Integer>> results2 = combinationSum(set, 8);
      System.out.println("Subset Sum:");
      for (List<Integer> subset : results2) {
         System.out.println(subset.toString());
      }
		
      String s = "AppleApp";
      List<List<String>> results4 = palindromePartition(s);
      System.out.println("Palindrome Partition:");
      for (List<String> subset : results4) {
         System.out.println(subset.toString());
      }
      
      List<List<Integer>> results3 = permute(new int[] { 2, 3, 4});
      System.out.println("Permute:");
      for (List<Integer> subset : results3) {
         System.out.println(subset.toString());
      }      
	}

}
