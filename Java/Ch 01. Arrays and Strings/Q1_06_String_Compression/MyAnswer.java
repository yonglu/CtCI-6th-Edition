package Q1_06_String_Compression;


//String Compression: Implement a method to perform basic string compression using the counts
//of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
//"compressed" string would not become smaller than the original string, your method should return
//the original string. You can assume the string has only uppercase and lowercase letters (a - z).

public class MyAnswer {	
	public static String compress(String str) {
		StringBuilder sb = new StringBuilder();
		char[] strChars = str.toCharArray();
		
		int numRepeat = 1;
		char current = strChars[0];
		int i = 1;
		while (i < strChars.length) {			
			if (strChars[i] == current) {
				numRepeat++;
			} else {
				sb.append(current).append(numRepeat);
				numRepeat = 1;
				current = strChars[i];
			}					
			i++;
		}
		
		// Mistake in not adding the last character.
		sb.append(current).append(numRepeat);
		
		if (sb.length() == str.length()) {
			return str;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String str = "aaaaabbbbaaaabbddc";
		System.out.println(str);
		System.out.println(compress(str));
		
		str = "aabcccccaaa";
		System.out.println(str);
		System.out.println(compress(str));
		
		str = "aabb";
		System.out.println(str);
		System.out.println(compress(str));
	}
}
