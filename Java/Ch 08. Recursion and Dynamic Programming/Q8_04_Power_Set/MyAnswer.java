package Q8_04_Power_Set;

import java.util.*;

// Power Set: Write a method to return all subsets of a set.

public class MyAnswer {

	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
	   if (set == null) {
	      return null;
	   }
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i< set.size(); i++) {
		   ArrayList<Integer> self = new ArrayList<Integer>();
		   self.add(set.get(i));
		   ArrayList<ArrayList<Integer>> newsubsets = new ArrayList<ArrayList<Integer>>();
		   newsubsets.add(self);		   
		   for (ArrayList<Integer> subset : allsubsets) {
		      // Mistake I have here is that I didn't create newsubset, so it is changing the original data
		      // Also, cannot add it back to allsubsets while inside the loop, so need to create the newsubsets.
		      ArrayList<Integer> newsubset = new ArrayList<Integer>();
		      newsubset.addAll(subset);
		      newsubset.add(set.get(i));
		      newsubsets.add(newsubset);
		   }
         allsubsets.addAll(newsubsets);
		}
		return allsubsets;
	}
	
//   public static ArrayList<ArrayList<Integer>> getSubsetsRecursive(ArrayList<Integer> set) {
//      if (set == null) {
//         return null;
//      }
//
//      ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
//      getSubsetsRecursive(set, allsubsets);
//
//      return allsubsets;
//   }
   
   public static ArrayList<ArrayList<Integer>> getSubsetsRecursive(ArrayList<Integer> set) {
      if (set == null) {
         return null;
      }
      
      if (set.size() == 1) {
         ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
         subsets.add(set);
         return subsets;
      }
      
      Integer currentNum = set.get(set.size() - 1);
      set.remove(set.size() - 1);      
      ArrayList<ArrayList<Integer>> subsets = getSubsetsRecursive(set);
      
      ArrayList<ArrayList<Integer>> newsubsets = new ArrayList<ArrayList<Integer>>();      
      ArrayList<Integer> self = new ArrayList<Integer>();
      self.add(currentNum);
      newsubsets.add(self);
      
      for (ArrayList<Integer> subset : subsets) {
         ArrayList<Integer> newsubset = new ArrayList<Integer>();
         newsubset.addAll(subset);
         newsubset.add(currentNum);     
         newsubsets.add(newsubset);
      }
      subsets.addAll(newsubsets);
   
      return subsets;
   }
   
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		ArrayList<ArrayList<Integer>> subsets = getSubsets(list);
      System.out.println(subsets.toString());   
		ArrayList<ArrayList<Integer>> subsets2 = getSubsetsRecursive(list);
      System.out.println(subsets2.toString());   
	}

}
