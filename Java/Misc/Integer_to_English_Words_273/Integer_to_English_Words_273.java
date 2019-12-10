package Integer_to_English_Words_273;

import java.util.*;

/* Leetcode # 273. Integer to English Words

Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"

Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 
*/

public class Integer_to_English_Words_273 {

    public static String numberToWords(int num) {
    	if (num < 0) {
    		return "";
    	} 
    	
    	StringBuilder sb = new StringBuilder();
    	String numStr = String.valueOf(num);
    	
    	if (1 <= numStr.length() && numStr.length() <= 3 ) {
    		sb.append(thousandsToString(num));
    	} else if (4 <= numStr.length() && numStr.length() <= 6 ) {
    		sb.append(thousandsToString(num/1000)).append(" thousands ").append(thousandsToString(num%1000));
    	} else if (7 <= numStr.length() && numStr.length() <= 9 ) {
    		
    	} else if (10 <= numStr.length() && numStr.length() <= 12) {
    		
    	}
    	
    	return sb.toString();
    }
    
    private static String digitsToString(int num) {
    	switch (num) {
	    	case 0:
	    		return "zero";
	    	case 1:
	    		return "one";
	    	case 2:
	    		return "two";
	    	case 3:
	    		return "three";
	    	case 4:
	    		return "four";
	    	case 5:
	    		return "five";
	    	case 6:
	    		return "six";
	    	case 7:
	    		return "seven";
	    	case 8:
	    		return "eight";
	    	case 9:
	    		return "nine";
	    	case 10:
	    		return "ten";
	    	case 11:
	    		return "eleven";
	    	case 12:
	    		return "twelve";
	    	case 13:
	    		return "thirdteen";
	    	case 14:
	    		return "fourteen";
	    	case 15:
	    		return "fifteen";
	    	case 16:
	    		return "sixteen";
	    	case 17:
	    		return "seventeen";
	    	case 18:
	    		return "eighteen";
	    	case 19:
	    		return "nineteen";
	    	default:
	    		return "";
    	}
    }
    
    private static String tensToString(int num) {
    	switch (num) {
	    	case 2:
	    		return "twenty";	
	    	case 3:
	    		return "thirty";	
	    	case 4:
	    		return "forty";	
	    	case 5:
	    		return "fifty";	
	    	case 6:
	    		return "sixty";	
	    	case 7:
	    		return "seventy";	
	    	case 8:
	    		return "eighty";	
	    	case 9:
	    		return "ninety";	
	    	case 10:
	    		return "one hundred";	
	    	default:
	    		return "";
    	}
    }
   
    
	public static void main(String[] args) {
		String result;

		result = numberToWords(123);		
		System.out.println("123 is : " + result);	
		
	}
}
