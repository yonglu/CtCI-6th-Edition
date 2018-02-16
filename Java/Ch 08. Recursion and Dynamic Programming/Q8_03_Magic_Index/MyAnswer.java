package Q8_03_Magic_Index;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

//Magic Index: A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] =
//i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
//array A.
//FOLLOW UP
//What if the values are not distinct?

public class MyAnswer {

	public static int magicSlow(int[] array) {
	   if (array == null || array.length == 0) {
	      return -1;
	   }
	   for (int i=0; i < array.length; i++) {
	      if (array[i] == i) {
	         return i;
	      }
	   }
	   return -1;
	}
	
	public static int magicFast(int[] array, int start, int end) {
	   if (start > end) {
	      return -1;
	   }
	   int middle = (start + end) / 2;
	   if (middle == array[middle]) {
	      return middle;
	   } else if (middle < array[middle]) {
	      return magicFast(array, start, middle - 1);
	   } else {
	      return magicFast(array, middle + 1, end);
	   }	   
	}
	
	public static int magicFast(int[] array) {
      if (array == null || array.length == 0) {
         return -1;
      }
      return magicFast(array, 0, array.length - 1);
	}
	
	/* Creates an array that is distinct and sorted */
	public static int[] getDistinctSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i-1]) {
				array[i]++;
			} else if (array[i] < array[i - 1]) {
				array[i] = array[i-1] + 1;
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getDistinctSortedArray(size);
			int v2 = magicFast(array);
			if (v2 == -1 && magicSlow(array) != -1) {
				int v1 = magicSlow(array);
				System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			} else if (v2 > -1 && array[v2] != v2) {
				System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			}
	      System.out.println("Array is: " + Arrays.toString(array));
         System.out.println("Magic index is: " + v2);
		}
	}

}
