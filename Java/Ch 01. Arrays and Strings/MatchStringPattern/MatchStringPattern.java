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
    		   // If it is '*' need to look ahead to next '*' or end of pattern for the case of "abc*ef"
    			StringBuilder sb = new StringBuilder();
    			int p2 = p + 1;
    			while (p2 < pattern.length() && pattern.charAt(p2) != '*') {
    				sb.append(pattern.charAt(p2));
    				p2++;
    			}
    			
    			int w2 = w;
    			boolean matchAheadStr = true;
            if (sb.length() == 0) {
               matchAheadStr = false;
            } else {
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
    	
    	// If we have not matched through the length of work, then it is false.
    	if (w != word.length()) {
    	   return false;
    	}
    	
    	// It is OK if we have not matched through the length of pattern and the left over are only '*'
    	// e.g. abcd***
    	if (p != pattern.length()) {
    	   String restOfPattern = pattern.substring(p);
    	   if (restOfPattern != null) {
    		 for (char c: restOfPattern.toCharArray()) {
    		    if (c != '*') {
    		       return false;
    		    }
    		 }
    	   }
    	}
    	
      return true;
    }
    
    public static void main(String[] args) throws IOException {

    	// Not completely working yet    	
    	System.out.println(matchStringPattern("abcd", "abgd"));
    	System.out.println(matchStringPattern("abcd", "abcde"));
    	System.out.println(matchStringPattern("abcd", "ab.d"));
    	System.out.println(matchStringPattern("abcd", "ab*"));
      System.out.println(matchStringPattern("abcd", "ab***"));
    	System.out.println(matchStringPattern("abcd", "abcd*"));
    	System.out.println(matchStringPattern("abcd", "ab*d"));
    	System.out.println(matchStringPattern("abcd", "ab*k"));

     }
}
