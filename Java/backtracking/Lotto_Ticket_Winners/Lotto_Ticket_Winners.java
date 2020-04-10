package Lotto_Ticket_Winners;

import java.util.*;

/*
 * I handed out lotto tickets.  I will have winners if I have a subset of zero.  
 * 
 * All the tickets in subset of zero are winners.
 * 
 * Tickets:    -10 .. +10

Handout:    2 3 7 -1 -4 -> winners: 2, 3, -1, -4
Handout:    2 3 5 -5 -> winders 5, -5 or 2, 3, -5 

 * 
 */

public class Lotto_Ticket_Winners {

    public static List<List<Integer>> getWinningTickets(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	
        if ( nums == null || nums.length == 0 ) {
        	return result;
        }
        
        List<Integer> temp = new ArrayList<Integer>();
        int curCount = 0;
        
        getWinningTickets(result, temp, nums, curCount, 0);
        
        return result;
    }
    
    private static void getWinningTickets(List<List<Integer>> result, List<Integer> temp, int[] nums, int curCount, int start) {
    	if (curCount == 0 && start != 0) {
    		result.add(new ArrayList<Integer>(temp));
    	}
    	if (start == nums.length) {
    		return;
    	}
    	for (int i = start; i < nums.length; i++) {
    		temp.add(nums[i]);
    		curCount += nums[i];
    		getWinningTickets(result, temp, nums, curCount, i + 1);
    		temp.remove(temp.size() - 1);
    		curCount -= nums[i];
    	}
    	
    	return;
    }
    
    
    public static void main(String[] args) {
    	List<List<Integer>> ans;
		ans = getWinningTickets(new int[] {2,3,7,-1,-4});
		System.out.println("getWinningTickets [2,3,7,-1,-4] : ");
		for (List<Integer> list : ans) {
			for (int i: list) {
				System.out.print(i + ", ");
			}
			System.out.println(" ");
		}

		ans = getWinningTickets(new int[] {2,3,5,-5});
		System.out.println("getWinningTickets [2,3,5,-5] : ");
		for (List<Integer> list : ans) {
			for (int i: list) {
				System.out.print(i + ", ");
			}
			System.out.println(" ");
		}
    }
}
