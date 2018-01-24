package Q8_01_Triple_Step;

import java.util.ArrayList;
import java.util.Arrays;

public class MyAnswer {
	
	public static int countWays(int n) {
	   if (n < 0) {
	      return 0;
	   } else if (n == 0) {
         return 1;
      } else if (n == 1) {
	      return 1;
	   } 
	   
	   return countWays(n-1) + countWays(n-2) + countWays(n-3);
	}
	
   public static int countWaysWithCache(int n) {
      int[] cache = new int[n+1];
      Arrays.fill(cache, -1);
      return countWaysWithCache(n, cache);
   }

   public static int countWaysWithCache(int n, int[] cache) {
      if (n < 0) {
         return 0;
      } else if (n == 0) {
         return 1;
      } else if (n == 1) {
         return 1;
      } 
      
      if (cache[n] == -1) {
         cache[n] = countWays(n-1) + countWays(n-2) + countWays(n-3);
      }
      return cache[n];
   }
   
	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		int n = 35;
		int ways = countWays(n);
		System.out.println(ways);
		System.out.println("Took: " + (System.currentTimeMillis() - before) + " milliseconds." );
		
		before = System.currentTimeMillis();
      n = 35;
      ways = countWaysWithCache(n);
      System.out.println(ways);
      System.out.println("Took: " + (System.currentTimeMillis() - before) + " milliseconds." );
		
	}

}
