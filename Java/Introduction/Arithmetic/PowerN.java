package Arithmetic;


// A to the power of N using only simple arithmetic operators (*, +, /, -). 
// Try to minimize number of operations.
public class PowerN {

	/* function to multiply two numbers x and y*/
	public static int multiply(int x, int y)
	{
	   /* 0  multiplied with anything gives 0 */
	   if(y == 0) {
	     return 0;
	   }
	 
	   /* Add x one by one */
	   if(y > 0 ) {
	     return (x + multiply(x, y-1));
	   }
	  
	  /* the case where y is negative */
	   if(y < 0 ) {
	     return -multiply(x, -y);
	   }
	   
	   return 0;
	}
	
	public static int pow(int x, int y) {
		if (y == 0) {
			return x;
		}
		
		if (y > 0) {
			return multiply(x, pow(x, y-1));
		}
		
		if (y < 0) {
			return 1/(multiply(x, pow(x, -(y-1))));
		}
		
		return 0;
	}

	
	public static void main(String[] args) {
		System.out.println(multiply(5, 6));
		int temp = pow(5, -3);
		System.out.println(pow(5, 3));
	}

}
