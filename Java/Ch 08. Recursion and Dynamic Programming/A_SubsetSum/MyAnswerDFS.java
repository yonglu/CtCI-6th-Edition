package A_SubsetSum;

import java.util.*;

public class MyAnswerDFS {	
	
   public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
      ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
   
      if(candidates == null || candidates.length == 0) return result;
   
      ArrayList<Integer> current = new ArrayList<Integer>();
      Arrays.sort(candidates);
   
      combinationSum(candidates, target, 0, current, result);
   
      return result;
  }
   
  public static void combinationSum(int[] candidates, int target, int j, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> result){
     if(target == 0){
         ArrayList<Integer> temp = new ArrayList<Integer>(curr);
         result.add(temp);
         return;
     }
   
     for(int i=j; i<candidates.length; i++){
        if(target < candidates[i]) 
              return;
   
         curr.add(candidates[i]);
         combinationSum(candidates, target - candidates[i], i, curr, result);
         curr.remove(curr.size()-1); 
     }
  }	
  
  public List<List<Integer>> subsetsWithDup(int[] nums) {
     List<List<Integer>> list = new ArrayList<>();
     Arrays.sort(nums);
     backtrack(list, new ArrayList<>(), nums, 0);
     return list;
 }

 private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
     list.add(new ArrayList<>(tempList));
     for(int i = start; i < nums.length; i++){
         if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
         tempList.add(nums[i]);
         backtrack(list, tempList, nums, i + 1);
         tempList.remove(tempList.size() - 1);
     }
 } 
 
 // Returns true if there is a subset of set[] with sum
 // equal to given sum
static boolean isSubsetSum(int set[], int n, int sum)
{
// Base Cases
if (sum == 0)
  return true;
if (n == 0 && sum != 0)
  return false;

// If last element is greater than sum, then ignore it
if (set[n-1] > sum)
  return isSubsetSum(set, n-1, sum);

/* else, check if sum can be obtained by any of the following
   (a) including the last element
   (b) excluding the last element   */
return isSubsetSum(set, n-1, sum) || 
                            isSubsetSum(set, n-1, sum-set[n-1]);
}
 
	public static void main(String[] args) {
	   int[] set = new int[] {2, 3, 3, 6, 7};
	   ArrayList<ArrayList<Integer>> results = combinationSum(set, 8);
	   for(ArrayList<Integer> subset : results) {
	      System.out.println(subset.toString());
	   }
      boolean f = isSubsetSum(set, set.length, 8);
         System.out.println(f);
	}

}
