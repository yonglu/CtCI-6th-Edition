package EquilibriumIndexOfAnArray;

//Equilibrium index of an array is an index such that the sum of elements at lower indexes 
//is equal to the sum of elements at higher indexes. For example, in an arrya A:
//
//A[0] = -7, A[1] = 1, A[2] = 5, A[3] = 2, A[4] = -4, A[5] = 3, A[6]=0
//
//3 is an equilibrium index, because:
//A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
//
//6 is also an equilibrium index, because sum of zero elements is zero, i.e., 
//A[0] + A[1] + A[2] + A[3] + A[4] + A[5]=0
//
//7 is not an equilibrium index, because it is not a valid index of array A.
//
//Write a function int equilibrium(int[] arr, int n); that given a sequence arr[] of 
//size n, returns an equilibrium index (if any) or -1 if no equilibrium indexes exist.

public class EquilibriumIndexOfAnArray {
	
//	The idea is to get total sum of array first. Then Iterate through the array and 
//	keep updating the left sum which is initialized as zero. In the loop, we can get 
//	right sum by subtracting the elements one by one.
	
  public static void equilibrium(int arr[], int n)
  {
      int sum = 0;      // initialize sum of whole array
      int leftsum = 0; // initialize leftsum

      /* Find sum of the whole array */
      for (int i = 0; i < n; ++i)
          sum += arr[i];

      for (int i = 0; i < n; ++i)
      {
    	  int rightsum = sum - leftsum - arr[i];
    	  if (leftsum == rightsum) {
    		  System.out.println("equilibrium index is " + i);
    	  }
    	  
    	  leftsum += arr[i];
      }

      /* If no equilibrium index found, then return 0 */
      return;
  }	
  
  
	public static void main(String[] args) {
       int arr[] = {-7, 1, 5, 2, -4, 3, 0};
      int arr_size = arr.length;
      equilibrium(arr, arr_size);
	}
}
