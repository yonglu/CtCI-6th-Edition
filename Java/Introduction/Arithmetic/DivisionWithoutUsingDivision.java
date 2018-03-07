package Arithmetic;

// https://stackoverflow.com/questions/5386377/division-without-using
//
// The trick is to do long division in binary and use left shift to move 
// the divisor to the right location.
// For example: 194 / 5
//
//          1  1  0  0  0  0  1  0
//-----------------------------
//101)1  1  1  1  0  0  1  1  0  0
//    1  0  1                             1
//    -----------------------------
//       1  0  1
//       1  0  1                           1
//    -----------------------------
//       0  0  0                          0
//    --------------------------
//          0  0  0                        0
//    -------------------------
//             0  0  1                      0
//    -------------------------
//                0  1  1                    0
//    -------------------------
//                   1  1  0   
//                   1  0  1                   1
//    ------------------------
//                      0  1  0                 0
//
//  The result is 11000010 (194) with reminder 010 (2)
//
public class DivisionWithoutUsingDivision {

	public static int divisionWithoutUsingDivision(int dividend, int divisor) {
	    //handle special cases
	    if(divisor==0) return Integer.MAX_VALUE;
	    if(divisor==-1 && dividend == Integer.MIN_VALUE)
	        return Integer.MAX_VALUE;
	 
	    //get positive values
	    long pDividend = Math.abs((long)dividend);
	    long pDivisor = Math.abs((long)divisor);
	 
	    int result = 0;
	    while(pDividend>=pDivisor){
	        //calculate number of left shifts
	        int numShift = 0;    
	        while(pDividend>=(pDivisor<<numShift)){
	            numShift++;
	        }
	 
	        //dividend minus the largest shifted divisor
	        result += 1<<(numShift-1);
	        pDividend -= (pDivisor<<(numShift-1));
	    }
	 
	    if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
	        return result;
	    }else{
	        return -result;
	    }
	}
	
	public static void main(String[] args) {
      System.out.println(divisionWithoutUsingDivision(972, 5));
	}

}
