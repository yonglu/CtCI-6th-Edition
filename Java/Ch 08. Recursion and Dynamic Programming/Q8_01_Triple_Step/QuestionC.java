package Q8_01_Triple_Step;

public class QuestionC {
	
	public static int countWays(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		}
		int a = 1;
		int b = 1;
		int c = 2;
		for (int i = 3; i < n; i++) {
			int d = a + b + c;
			a = b;
			b = c;
			c = d;
		}
		return a + b + c;
	}
	
	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		int n = 50;
		int ways = countWays(n);
		System.out.println(ways);
		System.out.println("Took: " + (System.currentTimeMillis() - before) + " milliseconds." );
	}

}
