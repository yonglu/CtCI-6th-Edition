package MaxConsecutiveRepeatingCharacters;

import java.io.IOException;
import java.util.Arrays; 
 
/* Given a string, the task is to find maximum consecutive repeating character in string.
 * 
 */
public class MaxConsecutiveRepeatingCharacters {  
   public static char myMaxRepeating(String str) {
	   char result;
	   if (str == null) {
		   throw new IllegalArgumentException("String cannot be null");
	   }
	   result = str.charAt(0);
	   int count = Integer.MIN_VALUE;
	   int runningCount = 1;
	   char previousChar = result;
	   for ( int i = 1; i < str.length(); i++) {
		   if (previousChar == str.charAt(i)) {
			   runningCount++;
		   } else {
			   runningCount = 1;
			   previousChar = str.charAt(i);
		   }
		   if (runningCount > count) {
			   count = runningCount;
			   result = str.charAt(i);
		   }
	   }
	   return result;
   }

   public static char maxRepeating2(String str) {
	   if (str == null) {
		   throw new IllegalArgumentException("String cannot be null");
	   }
	   int count = Integer.MIN_VALUE;
	   int current_count = 1;
	   char result = str.charAt(0);
	   for (int i=0; i < str.length() - 1; i++) {
		   if (str.charAt(i) == str.charAt(i+1)) {
			   current_count++;
			   if (current_count > count) {
				   count = current_count;
				   result = str.charAt(i);
			   }
		   } else {
			   current_count = 1;
		   }
	   }
	   
	   return result;
   }


   public static void main(String[] args) {
       String str = "aaaabbaaccde";
       System.out.println(myMaxRepeating(str));
       str = "abcdefgg";
       System.out.println(myMaxRepeating(str));
   }
}
