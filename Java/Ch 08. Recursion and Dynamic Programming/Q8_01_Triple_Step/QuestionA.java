package Q8_01_Triple_Step;

public class QuestionA {
	
	public static int countWays(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
		}
	}
	
	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		int n = 35;
		int ways = countWays(n);
		System.out.println(ways);
		System.out.println("Took: " + (System.currentTimeMillis() - before) + " milliseconds." );
	}

}
