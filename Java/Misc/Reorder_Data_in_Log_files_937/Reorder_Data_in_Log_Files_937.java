package Reorder_Data_in_Log_files_937;

import java.util.*;

/* Leetcode # 937. Reorder Data in Log Files
Easy

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.

We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at 
least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered 
lexicographically ignoring identifier, with the identifier used in case of ties.  
The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 
*/

public class Reorder_Data_in_Log_Files_937 {

    public static String[] reorderLogFiles(String[] logs) {
    	if ( logs == null || logs.length == 0) {
    		return new String[0];
    	}
    	String[] ans;
    	
    	List<String> letterLogs = new ArrayList<String>();
    	List<String> digitLogs = new ArrayList<String>();
    	
    	for (String log : logs) {
    		String[] parts = log.split(" ");
    		char c = parts[1].charAt(0);
    		if ('0' <= c && '9' >= c) {
    			digitLogs.add(log);
    		} else {
    			letterLogs.add(log);
    		}
    	}
    	
    	Collections.sort(letterLogs, new Comparator<String>() { 
    		@Override
    		public int compare(String s1, String s2) {
    			String s1ID = s1.substring(0, s1.indexOf(" "));
    			String s2ID = s2.substring(0, s2.indexOf(" "));;
    			String s1Content = s1.substring(s1.indexOf(" ") + 1);
    			String s2Content = s2.substring(s2.indexOf(" ") + 1);
    			int compareResult = s1Content.compareTo(s2Content);
    			if (compareResult != 0) {
    				return compareResult;
    			}
    			return s1ID.compareTo(s2ID);
    		}
    		
    	});
    	
    	ans = new String[letterLogs.size() + digitLogs.size()];
    	int index = 0;
    	for (String s : letterLogs) {
    		ans[index++] = s;
    	}
    	for (String s : digitLogs) {
    		ans[index++] = s;
    	}
    	return ans;   	
    }
    
    
	public static void main(String[] args) {
		String [] result;
		String [] logs = new String[] {
				"dig1 8 1 5 1",
				"let1 art can",
				"dig2 3 6",
				"let2 own kit dig",
				"let3 art zero"		
		};

		result = reorderLogFiles(logs);		
		System.out.println("reorderLogFiles of [\"dig1 8 1 5 1\",\"let1 art can\",\"dig2 3 6\",\"let2 own kit dig\",\"let3 art zero\"] is : " + 
				Arrays.deepToString(result) );	
		// expect: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
		
	}
}
