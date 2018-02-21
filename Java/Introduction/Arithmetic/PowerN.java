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
	
   /* function to multiply two numbers x and y*/
   public static int multiply2(int x, int y)
   {
      if (x == 0 || y == 0) {
         return 0;
      }
      
      boolean isNeg = false;
      
      if(x < 0 && y < 0) {
         x = -x;
         y = -y;
      } else if (x < 0 && y > 0) {
         x = -x;
         isNeg = true;
      } else if (x > 0 && y < 0) {
         y = -y;
         isNeg = true;
      }
         
      int ret = 0;
      for (int i = 0; i < y; i++) {
         ret += x;
      }
      
      if (isNeg) {
         ret = -ret;
      }
      
      return ret;
   }
	
	
	public static int pow(int x, int y) {
		if (y == 1) {
			return x;
		} else if ( y == 0) {
		   return 1;
		}
		
		
		if (y > 0) {
			return multiply(x, pow(x, y-1));
		}
		
		if (y < 0) {
			return 1/(multiply(x, pow(x, -(y-1))));
		}
		
		return 0;
	}
	
   public static float pow2(int x, int y) {
      float ret = 1;
      
      boolean isNeg = false;
      if (y < 0) {
         y = -y;
         isNeg = true;
      }
      
      for (int i = 1; i <= y; i++) {
         ret = multiply((int)ret, x);
      }
      
      if (isNeg) {
         ret = 1/ret;
      }
      
      return ret;
   }
	

	
	public static void main(String[] args) {
      System.out.println(multiply(5, 6));
      System.out.println(multiply(-5, 6));
      System.out.println(multiply(5, -6));
      System.out.println(multiply(-5, -6));
		System.out.println(multiply2(5, 6));
      System.out.println(multiply2(-5, 6));
      System.out.println(multiply2(5, -6));
      System.out.println(multiply2(-5, -6));
		int temp = pow(5, -3);
		System.out.println(pow(5, 3));
      System.out.println(pow(5, -3));
      System.out.println(pow2(5, 3));
      System.out.println(pow2(5, -3));
	}

}
