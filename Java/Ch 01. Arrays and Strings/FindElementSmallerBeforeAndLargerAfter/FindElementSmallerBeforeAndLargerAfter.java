package FindElementSmallerBeforeAndLargerAfter;

import java.io.IOException;
import java.util.Arrays; 
 
/* Given an array, find an element before which all elements are smaller than it, and after which 
 * all are greater than it. Return index of the element if there is such an element, otherwise return -1.
 */
public class FindElementSmallerBeforeAndLargerAfter {  
 
   public static int findElement(int[] array) {
      int[] leftMax = new int[array.length];
      leftMax[0] = Integer.MIN_VALUE;
      for (int i = 1; i < array.length; i++) {
         leftMax[i] = Math.max(leftMax[i-1], array[i-1]);
      }
      
      int index = -1;
      int rightMin = Integer.MAX_VALUE;
      for (int j = array.length - 1; j >= 0; j--) {
         if (array[j] > leftMax[j] && array[j] < rightMin) {
            index = j;
            break;
         }
         rightMin = Math.min(rightMin,  array[j]);
      }
      
      return index;
   }


   public static void main(String[] args) {
      int[] array = {5, 1, 4, 3, 6, 8, 10, 7, 9};

      System.out.println("Element is: " + findElement(array));
   }
}   
   
