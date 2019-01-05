package FirstUniqueCharacterInString;

import java.io.IOException;
import java.util.Arrays; 
 
//Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
//
//Examples:
//
//s = "leetcode"
//return 0.
//
//s = "loveleetcode",
//return 2.
//
//Note: You may assume the string contain only lowercase letters.


public class FirstUniqueCharacterInString {  
 
    public static int[] alpha = new int[26];

	public static int firstUniqChar(String s) {
		alpha = new int[26];
		for(int i = 0; i < s.length(); i++) {
			assignAlpha(s.charAt(i));
		}
		for(int i = 0; i < s.length(); i++) {
			if(alpha[getAlpha(s.charAt(i))] == 1)
				return i;
		}
		return -1;
	}
	
	public static void assignAlpha(char c) {
		int n = getAlpha(c);
		if(n != -1)
            alpha[n]++;
	}

	public static int getAlpha(char c) {
		if(c >= 'a' && c <= 'z')
            return c - 'a';
		return -1;
	}    
    
    // Driver Method
    public static void main(String[] args)
    {
    	System.out.println(firstUniqChar("leetcode"));
    	System.out.println(firstUniqChar("loveleetcode"));

    	System.out.println(firstUniqChar("dacca"));

    	System.out.println(firstUniqChar("accad"));

    }
}   
   
