package KthPermutationSequence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
 
//Find the kth Permutation Sequence
//The set [1,2,3,…,n] contains a total of n! unique permutations.
//By listing and labeling all of the permutations in order,
//We get the following sequence (ie, for n = 3):
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//Given n and k, return the kth permutation sequence.
//Note: Given n will be between 1 and 9 inclusive.

//The idea is to firstly find the number at the first position. At position n, the total number of 
//permutations of remaining digits is (n-1)! Therefore k/(n-1)! can tell us which number should be 
//placed at the first place. Then we iteratively find the next number after the first one. At this time, 
//we again calculate the position of the number similarly, by reducing n to n-1. However, we should 
//pick the position from the remaining digits. Thus we use an ArrayList to do so. As elements can be 
//added to or removed from an arraylist conveniently, we can easily get the number at position pos.

// http://smartlhc.blogspot.com/2012/08/find-kth-permutation-sequence.html

public class KthPermutationSequence {  
   
   public static String getPermutation(int n, int k) {
      StringBuffer sb = new StringBuffer();
      ArrayList<Integer> ll = new ArrayList<Integer>();
      for (int i = 0; i < n; i++)
      {
         ll.add(i + 1);
      }
      doPermute(sb, n, k - 1, ll);
      return sb.toString();
   }

   public static String getPermutationFromString(String str, int k) {
      int n = str.length();
      StringBuffer sb = new StringBuffer();
      ArrayList<Character> ll = new ArrayList<Character>();
      for (int i = 0; i < n; i++)
      {
         ll.add(str.charAt(i));
      }
      doPermuteFromString(sb, n, k - 1, ll);
      return sb.toString();
   }
   
   public static int factorial(int n)
   {
      if (n <= 1)
         return 1;
      int fact = n;
      while (--n >= 1)
      {
         fact = fact * n;
      }
      return fact;
   }

   public static void doPermute(StringBuffer sb, int n, int k, ArrayList<Integer> ll)
   {
      int numGroup;
      int pos;
      int val;
      while (n >= 1)
      {
         numGroup = factorial(n - 1);
         pos = k / numGroup;
         val = ll.get(pos);
         ll.remove(pos);
         sb.append(val);
         k = k % numGroup;
         n--;
      }
   }
   
   public static void doPermuteFromString(StringBuffer sb, int n, int k, ArrayList<Character> ll)
   {
      int numGroup;
      int pos;
      char val;
      while (n >= 1)
      {
         numGroup = factorial(n - 1);
         pos = k / numGroup;
         val = ll.get(pos);
         ll.remove(pos);
         sb.append(val);
         k = k % numGroup;
         n--;
      }
   }
   

   public static void main(String[] args) {
	    System.out.println(getPermutation(4, 7));
       System.out.println(getPermutationFromString("abcd", 7));
   }
}
