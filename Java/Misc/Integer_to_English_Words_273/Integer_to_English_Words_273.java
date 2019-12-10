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
    	StringBuilder sb = new StringBuilder();
    	if (num < 0) {
    		sb.append("Negative ");
    		num = -num;
    	} 
    	    	  	
    	String numStr = String.valueOf(num);
    	
    	if (1 <= numStr.length() && numStr.length() <= 3 ) {
    		sb.append(threeDigitsToString(num));
    	} else if (4 <= numStr.length() && numStr.length() <= 6 ) {
    		sb.append(sixDigitsToString(num));
    	} else if (7 <= numStr.length() && numStr.length() <= 9 ) {
    		sb.append(nineDigitsToString(num));
    	} else if (10 <= numStr.length() && numStr.length() <= 12) {
    		sb.append(twleveDigitsToString(num));
    	}
    	
    	return sb.toString();
    }
    
    
    private static String twleveDigitsToString(int num) {
    	StringBuilder sb = new StringBuilder();
		int part1Num = num/1000000000;
		int part1Remind = num%1000000000;
		if (part1Num > 0) {
			sb.append(threeDigitsToString(part1Num)).append(" Billion");
		}
		if (part1Remind > 0) {
			if ((part1Remind/1000) > 0) {
				sb.append(" ");
			}
			sb.append(nineDigitsToString(part1Remind));   
		}
		return sb.toString();
    }

    private static String nineDigitsToString(int num) {
    	StringBuilder sb = new StringBuilder();
		int part1Num = num/1000000;
		int part1Remind = num%1000000;
		if (part1Num > 0) {
			sb.append(threeDigitsToString(part1Num)).append(" Million");
		}
		if (part1Remind > 0) {
			if ((part1Remind/1000) > 0) {
				sb.append(" ");
			}
			sb.append(sixDigitsToString(part1Remind));   
		}
		return sb.toString();
    }
    
    private static String sixDigitsToString(int num) {
    	StringBuilder sb = new StringBuilder();
		int part1Num = num/1000;
		int part1Remind = num%1000;
		if (part1Num > 0) {
			sb.append(threeDigitsToString(part1Num)).append(" Thousand");
		}
		if (part1Remind > 0) {
			sb.append(" ").append(threeDigitsToString(part1Remind));   
		}
		return sb.toString();
    }
    
    private static String threeDigitsToString(int num) {
    	StringBuilder sb = new StringBuilder();
    	if (0 <= num && 99 >= num) {
    		sb.append(twoDigitsToString(num));
    	} else if (100 <= num && 999 >= num) {
    		int part1Num = num/100;
    		int part1Remind = num%100;
    		if (part1Num > 0) {
    			sb.append(digitsToString(part1Num)).append(" Hundred");
    		}
    		if (part1Remind > 0) {
    			sb.append(" ").append(twoDigitsToString(part1Remind));   
    		}
    	}    	
    	return sb.toString();
    }    
    
    private static String twoDigitsToString(int num) {
    	StringBuilder sb = new StringBuilder();
    	if (0 <= num && 9 >= num) {
    		sb.append(digitsToString(num));
    	} else if (10 <= num && 19 >= num) {
        	switch (num) {
		    	case 10:
		    		return "Ten";
		    	case 11:
		    		return "Eleven";
		    	case 12:
		    		return "Twelve";
		    	case 13:
		    		return "Thirteen";
		    	case 14:
		    		return "Fourteen";
		    	case 15:
		    		return "Fifteen";
		    	case 16:
		    		return "Sixteen";
		    	case 17:
		    		return "Seventeen";
		    	case 18:
		    		return "Eighteen";
		    	case 19:
		    		return "Nineteen";
		    	default:
		    		return "";
        	}
    	} else if (20 <= num && 99 >= num) {
    		int part1Num = num/10;
    		int part1Remind = num%10;
    		sb.append(tensToString(part1Num));
    		if (part1Remind > 0) {
    			sb.append(" ").append(digitsToString(part1Remind));  	
    		}
    	}

    	return sb.toString();
    }
    
    private static String digitsToString(int num) {
    	switch (num) {
	    	case 0:
	    		return "Zero";
	    	case 1:
	    		return "One";
	    	case 2:
	    		return "Two";
	    	case 3:
	    		return "Three";
	    	case 4:
	    		return "Four";
	    	case 5:
	    		return "Five";
	    	case 6:
	    		return "Six";
	    	case 7:
	    		return "Seven";
	    	case 8:
	    		return "Eight";
	    	case 9:
	    		return "Nine";
	    	default:
	    		return "";
    	}
    }

    private static String tensToString(int num) {
    	switch (num) {
	    	case 2:
	    		return "Twenty";	
	    	case 3:
	    		return "Thirty";	
	    	case 4:
	    		return "Forty";	
	    	case 5:
	    		return "Fifty";	
	    	case 6:
	    		return "Sixty";	
	    	case 7:
	    		return "Seventy";	
	    	case 8:
	    		return "Eighty";	
	    	case 9:
	    		return "Ninety";	
	    	default:
	    		return "";
    	}
    }
   
    
	public static void main(String[] args) {
		String result;

		result = numberToWords(123);		
		System.out.println("123 is : " + result);	

		result = numberToWords(12345);		
		System.out.println("12345 is : " + result);	

		result = numberToWords(1234567);		
		System.out.println("1234567 is : " + result);	

		result = numberToWords(1234567891);		
		System.out.println("1234567891 is : " + result);	
		
		result = numberToWords(6030);		
		System.out.println("6000 is : " + result);	

		result = numberToWords(60000001);		
		System.out.println("60000001 is : " + result);	
	}
}
