package print_all_string_combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrintAllStringCombinations {
	
	// f(int n)
	// generate and return all possible strings of length n
	// containing exactly two a's

	// e.g.  f(4)
	// returns aabb, aabc, ... gaua...  zzaa

	private static Set<Character> allowChars = new HashSet<Character>();
	private static ArrayList<String> my_strings = new ArrayList<String>();
	 
	public static void f(int n) {
	        
	    for(int i = 0; i < n-1; i++) {
	        for(int j = i+1; j < n; j++) {
	        	char[] temp_str = new char[n];
	        	temp_str[i] = 'a';
	        	temp_str[j] = 'a';
	    		boolean[] filledLoc = new boolean[n];
	    		filledLoc[i] = true;
	        	filledLoc[j] = true;
	            constructString(n, filledLoc, temp_str);                    
	        }
	    }

	} 

	public static void constructString(int n, boolean[] remainLoc, char[] temp_str) {
	    int remain = -1;
	    for (int k = 0; k < n; k++) {
	    	if (remainLoc[k] == false) {
	    		remain = k;
	    		break;
	    	}
	    }
	    
	    if (remain == -1) {
	        my_strings.add(new String(temp_str));
	        return;
	    }
	    
	    for (Character c: allowChars) {
	    	temp_str[remain] = c;
		    remainLoc[remain] = true;
	    	constructString(n, remainLoc, temp_str); 
	    	//TODO forgot to do the backtracking in first run.
		    remainLoc[remain] = false;
	    }
	}
	
	public static void main(String[] args) {
		allowChars.add('b');
		allowChars.add('c');
		
		f(4);
		for (String s : my_strings) {
			System.out.println(s);
		}
	}

}
