package MatchStringPattern;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MatchStringPattern {

//	* - any character and length, can match nothing
//  . - one character
//
    static boolean matchStringPattern(String word, String pattern) {
    	boolean result = false;
    	if ((word == null) || (pattern == null)) {
    		return false;
    	}
    	if (pattern.equals("*")) {
    		return true;
    	}
    	int w = 0;
    	int p = 0;
    	while (w < word.length() && p < pattern.length()) {
    		if (word.charAt(w) == pattern.charAt(p)) {
    			w++;
    			p++;
    		} else if (pattern.charAt(p) == '.') {
    			p++;
    			w++;
    		} else if (pattern.charAt(p) == '*') {
    			StringBuilder sb = new StringBuilder();
    			int p2 = p + 1;
    			while (p2 < pattern.length() && pattern.charAt(p2) != '*' ) {
    				sb.append(pattern.charAt(p2));
    				p2++;
    			}
    			
    			int w2 = w;
    			boolean matchAheadStr = true;
    			for (int i = 0; i < sb.length(); i++) {
    				if (w2 >= word.length()) {
    					return false;
    				}
    				if (sb.charAt(i) != word.charAt(w2)) {
    					matchAheadStr = false;
    					break;
    				}
    				w2++;
    			}
    			
    			if (matchAheadStr) {
    				p = p2;
    				w = w2;
    			} else {
    				w++;
    			}
    			
    		} else {
    			return false;
    		}
    	}
    	
    	if (p != pattern.length()) {
    		return false;
    	}
    	
        return true;
    }
    
    public static void main(String[] args) throws IOException {

    	// Not completely working yet    	
    	System.out.println(matchStringPattern("abcd", "abgd"));
    	System.out.println(matchStringPattern("abcd", "abcde"));
    	System.out.println(matchStringPattern("abcd", "ab.d"));
    	System.out.println(matchStringPattern("abcd", "ab*"));
    	System.out.println(matchStringPattern("abcd", "abcd*")); // should be true
    	System.out.println(matchStringPattern("abcd", "ab*d"));
    	System.out.println(matchStringPattern("abcd", "ab*k"));

     }
}
