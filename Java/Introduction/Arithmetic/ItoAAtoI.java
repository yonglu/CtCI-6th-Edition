package Arithmetic;

import java.util.ArrayList;
import java.util.List;


public class ItoAAtoI {


	public static final int MAX_DIGITS = 10;

	public static int strToInt(String str) {
		int i = 0, num = 0;
		boolean isNeg = false;
		int len = str.length();
		if (str.charAt(0) == '-') {
			isNeg = true;
			i = 1;
		}
		while (i < len) {
			num *= 10;
			num += (str.charAt(i++) - '0');
		}
		if (isNeg)
			num = -num;
		return num;
	}
	
	public static String intToStr(int num) {
		int i = 0;
		boolean isNeg = false;
		/* Buffer big enough for largest int and - sign */
		char[] temp = new char[MAX_DIGITS + 1];
		/* Check to see if the number is negative */
		if (num < 0) {
			num = -num;
			isNeg = true;
		}
		/* Fill buffer with digit characters in reverse order */
		while (num != 0) {
			temp[i++] = (char) ((num % 10) + '0');
			num /= 10;
		}
		StringBuilder b = new StringBuilder();
		if (isNeg)
			b.append('-');
		while (i > 0) {
			b.append(temp[--i]);
		}
		return b.toString();
	}
	
	// 732 % 10 -> 2
	// 73 % 10 -> 3
	// 7 % 10 -> 7
	public static int reverseInt(int x) {
	    int rev = 0;
	    while(x != 0){
	        rev = rev*10 + x%10;
	        x = x/10;
	    }	 
	    return rev;
	}
	
	
    public static int swapBits(int n, int i, int j) { 
    	
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;
        
        // only swap if they are different
        if (a != b) {
        	int bitMask = (1 << i) | (1 << j);
        	// XOR ^ basically flip the bit
            return n = n ^ bitMask;
        }
        return n;
    }
    
    public static int reverseBits(int n) {
    	for (int i = 0; i < 16; i++) {
    		n = swapBits(n, i, 32-i- 1);
    	}
    	return n;
    }
	
    public static int strToInt2(String str) {
       int i = 0; 
       int num = 0;
       boolean neg = false;
       if (str.charAt(i) == '-') {
          neg = true;
          i++;
       }
       for (int j=i; j<str.length();j++) {
         int digit = str.charAt(j) - '0';
         num = num * 10 + digit;
       }
       if (neg) {
          num = -1 * num;
       }
       return num;
    }

    public static String intToStr2(int num) {
       StringBuilder sb = new StringBuilder();
       List<Character> digits = new ArrayList<Character>();
       // if negative number
       // TODO just check if (num < 0)
       if ((num >> 31) != 0) {
          sb.append("-");
          num = -num;
       }       
       
       while (num > 0) {
          int digit = num % 10;
          digits.add((char)(digit + '0'));
          num = num / 10;
       }
       
       for(int i=digits.size()-1; i>=0; i--) {
          sb.append(digits.get(i));
       }
       
       return sb.toString();
    }
	
	public static void main(String[] args) {
		System.out.println(intToStr(12345));
      System.out.println(intToStr(-12345));
      System.out.println(intToStr2(12345));
      System.out.println(intToStr2(-12345));
		System.out.println(strToInt("654321"));
      System.out.println(strToInt("-654321"));
      System.out.println(strToInt2("654321"));
      System.out.println(strToInt2("-654321"));
		System.out.println(reverseInt(12345));
		System.out.println(reverseBits(12344));
	}

}
