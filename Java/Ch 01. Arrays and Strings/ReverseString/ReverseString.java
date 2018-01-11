package ReverseString;

import java.io.IOException;
import java.util.Arrays; 
 
public class ReverseString {
	public static String reverseString(final String s) {
		if (s == null) {
			return null;
		}
		
		if (s.length() <= 1) {
			return s;
		}
		
		char[] chars = s.toCharArray();
		
		int i = 0;
		int j = chars.length - 1;
		
		while (i < j) {
			char temp = chars[i];
			chars[i] = chars[j];
			chars[j] = temp;
			i++;
			j--;
		}

		return new String(chars);
	}

//	Given a string and an integer k, you need to reverse the first k characters for every 2k characters 
//	counting from the start of the string. If there are less than k characters left, reverse all of them. 
//	If there are less than 2k but greater than or equal to k characters, then reverse the first k 
//	characters and left the other as original. 
//  Input: s = "abcdefg", k = 2
//  Output: "bacdfeg"
	
	public static String reverseString2K(final String s, final int k) {
		if (s == null) {
			return null;
		}
		
		if (s.length() <= 1 || k == 0 || k == 1) {
			return s;
		}
		
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		
		while (true) {
			int remaingingChars = s.length() - i;
			if (remaingingChars < 1) {
				break;
			}
			else if ((remaingingChars < 2*k) && (remaingingChars >= k)) {
				// reverse first k and left the other as original
				sb.append(reverseString(s.substring(i, i+k)));
				sb.append(s.substring(i+k));
				break;
			} else if (remaingingChars < k) {
				// reverse all of them
				sb.append(reverseString(s.substring(i)));
				break;
			} else {
				// reverse the first k characters for every 2k characters
				sb.append(reverseString(s.substring(i, i+k)));
				sb.append(s.substring(i+k, i+2*k));
				i = i+2*k;
			}
		}

		return sb.toString();
	}

	// Given a string, you need to reverse the order of characters in each word 
	// within a sentence while still preserving whitespace and initial word order.    
	// Input: "Let's take LeetCode contest"
	// Output: "s'teL ekat edoCteeL tsetnoc"
	public static String reverseWords(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String[] words = s.split(" ");
		for (String word : words) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(reverseString(word));
		}
        return sb.toString();
    }

	public static void main(String[] args) throws IOException {
		String str = reverseString("abcdefgh");
		System.out.println(str);

		str = reverseString2K("abcdefg", 2);
		System.out.println(str);

		str = reverseString2K("abcdefg", 3);
		System.out.println(str);
		
		str = reverseString2K("abcdefg", 4);
		System.out.println(str);
		
		str = reverseString2K("abcdefghijklmnopqrs", 3);
		System.out.println(str);
		
		str = reverseString2K("abcdefghijklmnopqrstxy", 3);
		System.out.println(str);
		
		str = reverseWords("Let's take LeetCode contest");
		System.out.println(str);
	}
}