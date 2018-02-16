package ArrangeArrayAlternatingPosAndNeg;

import java.io.IOException;
import java.util.Arrays; 
 
//Rearrange array in alternating positive & negative items with O(1) extra space | Set 1 3.7
//Given an array of positive and negative numbers, arrange them in an alternate fashion such that every positive number is followed by negative and vice-versa maintaining the order of appearance.
//Number of positive and negative numbers need not be equal. If there are more positive numbers they appear at the end of the array. If there are more negative numbers, they too appear in the end of the array.
//
//Example:
//
//Input:  arr[] = {1, 2, 3, -4, -1, 4}
//Output: arr[] = {-4, 1, -1, 2, 3, 4}
//
//Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
//output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0} 
//This question has been asked at many places (See this and this)
//
//The above problem can be easily solved if O(n) extra space is allowed. It becomes interesting due to the limitations that O(1) extra space and order of appearances.
//The idea is to process array from left to right. While processing, find the first out of place element in the remaining unprocessed array. An element is out of place if it is negative and at odd index, or it is positive and at even index. Once we find an out of place element, we find the first element after it with opposite sign. We right rotate the subarray between these two elements (including these two).

public class ArrangeArrayAlternatingPosAndNeg {  
 
   // Utility function to right rotate all elements 
   // between [outofplace, cur]
   public static void rightrotate(int arr[], int n, int outofplace, int cur) 
   {
       int tmp = arr[cur];
       for (int i = cur; i > outofplace; i--)
           arr[i] = arr[i - 1];
       arr[outofplace] = tmp;
   }
   
   public static void rearrange(int arr[], int n) 
   {
      int outofplace = -1;

      for (int index = 0; index < n; index++) 
      {
          if (outofplace >= 0) 
          {
              // find the item which must be moved into the out-of-place
              // entry if out-of-place entry is positive and current
              // entry is negative OR if out-of-place entry is negative
              // and current entry is negative then right rotate
              //
              // [...-3, -4, -5, 6...] -->   [...6, -3, -4, -5...]
              //      ^                          ^
              //      |                          |
              //     outofplace      -->      outofplace
              //
              if (((arr[index] >= 0) && (arr[outofplace] < 0))
                      || ((arr[index] < 0) && (arr[outofplace] >= 0))) 
              {
                  rightrotate(arr, n, outofplace, index);

                  // the new out-of-place entry is now 2 steps ahead
                  if (index - outofplace > 2) 
                      outofplace = outofplace + 2;
                  else
                      outofplace = -1;
              }
          }

          // if no entry has been flagged out-of-place
          if (outofplace == -1) 
          {
              // check if current entry is out-of-place
              if (((arr[index] >= 0) && ((index & 0x01)==0))
                      || ((arr[index] < 0) && (index & 0x01)==1))
                  outofplace = index;
          }
      }
  }

   public static void printArray(int arr[], int n) 
   {
       for (int i = 0; i < n; i++)
           System.out.print(arr[i] + " ");
       System.out.println("");
   }
   
   public static void main(String[] args) {
      //int arr[n] = {-5, 3, 4, 5, -6, -2, 8, 9, -1, -4};
      //int arr[] = {-5, -3, -4, -5, -6, 2 , 8, 9, 1 , 4};
      //int arr[] = {5, 3, 4, 2, 1, -2 , -8, -9, -1 , -4};
      //int arr[] = {-5, 3, -4, -7, -1, -2 , -8, -9, 1 , -4};
      int arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
      int n = arr.length;

      System.out.println("Given array is ");
      printArray(arr, n);

      rearrange(arr, n);

      System.out.println("RearrangeD array is ");
      printArray(arr, n);
   }
}   
   
