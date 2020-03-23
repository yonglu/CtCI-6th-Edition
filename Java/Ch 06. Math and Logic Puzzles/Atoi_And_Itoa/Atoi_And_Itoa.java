package Atoi_And_Itoa;

import java.util.*;

public class Atoi_And_Itoa {

	/*
	 * 	- Use double or long to store result
	 * 	- Check value overflow
	 */
	public static int atoi(String str) {
	    if (str == null || str.length() < 1)
	        return 0;
	 
	        // trim white spaces
	        str = str.trim();
	 
	    char flag = '+';
	 
	    // check negative or positive
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        flag = '-';
	        i++;
	    } else if (str.charAt(0) == '+') {
	        i++;
	    }
	    // use double to store result
	    double result = 0;
	 
	    // calculate value
	    while (str.length() > i) {
	    	if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
	    		result = result * 10 + (str.charAt(i) - '0');
	    	}
	        i++;
	    }
	 
	    if (flag == '-')
	        result = -result;
	 
	    // handle max and min
	    if (result > Integer.MAX_VALUE)
	        return Integer.MAX_VALUE;
	 
	    if (result < Integer.MIN_VALUE)
	        return Integer.MIN_VALUE;
	 
	    return (int) result;
	}

	public static int atoi_mine(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}
		char[] chars = str.toCharArray();

		int beginIndex = 0;
		boolean neg = false;
		if (chars[0] == '-') {
			neg = true;
			beginIndex = 1;
		}

		boolean err = false;
		
		// use long to store result
		int ans = 0;
		for (int i = beginIndex; i < chars.length; i++) {
			char c = chars[i];
			if ('0' <= c && c <= '9') {
				int temp = c - '0';
				ans = ans * 10 + temp;
			}		}

		if (err) {
			return 0;
		}

		if (neg) {
			ans = -ans;
		}
		
		return ans;
	}
		
	public static String itoa(int n) {
		StringBuilder sb = new StringBuilder();
		
		boolean neg = false;
		if (n >> 31 == 1) {
			neg = true;
		}
		
		while (n > 10) {
			int reminder = n % 10;
			n = n / 10;
			sb.append((char)('0' + reminder));
		}
		
		char c = (char)('0' + n);
		sb.append(c);
		
		if (neg) {
			sb.append('-');
		}
		
		return sb.reverse().toString();
	}
	
	
	public static void main(String[] args) {
        System.out.println("atoi(45) is (expected 45): " + atoi("45"));
        
        System.out.println("atoi(-45) is (expected -45): " + atoi("-45"));     
        
        System.out.println("atoi(4a5) is (expected 45): " + atoi("4a5"));

        System.out.println("itoa(45) is (expected 45): " + itoa(45));
        
        System.out.println("itoa(-45) is (expected -45): " + itoa(-45));
	}
}
