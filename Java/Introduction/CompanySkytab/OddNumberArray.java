package CompanySkytab;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OddNumberArray {
	
//   QUESTION #1: Array with odd numbers
//
//	In the language of your choice, implement a function named
//	`get_odd_numbers`, which takes as input an array of integers and returns a
//	new array of only the odd numbers, where the order of the elements is
//	otherwise unchanged.
//
//	For instance, `get_odd_numbers([1,2,3,4,5,6,7])` should return `[1,3,5,7]`.

	public static int[] get_odd_numbers(int[] array) {
		if (array == null) {
			return null;
		}
		
		List<Integer> list = new ArrayList<Integer>();		
		for (int i : array) {
			if (i%2 != 0) {
				list.add(i);
			}
		}
		
		int[] ret = new int[list.size()];
		for (int j = 0; j < list.size(); j++) {
			ret[j] = list.get(j);
		}
		
		return ret;
	}		

	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7};
		int[] ret = get_odd_numbers(array);
		for (int r : ret) {
			System.out.print(r + " ");
		}

		System.out.println(" ");

		int[] array2 = {0, -1, -2, 2,4,3,7,6,8,9,1,2,4,2,3,5,81,2,3,4,5,6,7, 8,55};
		ret = get_odd_numbers(array2);
		for (int r : ret) {
			System.out.print(r + " ");
		}

		int[] array3 = {};
		ret = get_odd_numbers(array3);
		for (int r : ret) {
			System.out.print(r + " ");
		}
	
		System.out.println(" ");

		int[] array4 = {1, 2, 3};
		ret = get_odd_numbers(array4);
		for (int r : ret) {
			System.out.print(r + " ");
		}

//		ret = get_odd_numbers(null);
//		for (int r : ret) {
//			System.out.print(r + " ");
//		}
	}

}

