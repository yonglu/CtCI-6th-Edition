package Q1_01_Is_Unique;

public class MyAnswer {
	public static boolean isUniqueChars(String str) {
		if (str == null) {
			return false;
		}
		if (str.length() > 128) {
			return false;
		}
		boolean[] flags = new boolean[128];
		char[] strChars = str.toCharArray();
		for (int i=0; i<strChars.length; i++) {
			if (flags[strChars[i]]) {
				return false;
			}
			flags[strChars[i]] = true;
		}
		return true;
	}

	/*
	 Given a string, find the first non-repeating character in it and return it's index. 
	 If it doesn't exist, return -1
	 	s = "leetcode", return 0.
		s = "loveleetcode", return 2.
	*/
					
    public static int firstUniqChar(String s) {
    	if (s == null) {
    		return -1;
    	}
		int[] flags = new int[128];
		char[] char_set = s.toCharArray();
		for (int i=0; i<char_set.length; i++) {
			flags[char_set[i]] = flags[char_set[i]]+1;
		}
		for (int i=0; i<char_set.length; i++) {
			if (flags[char_set[i]] == 1) {
				return i;
			}
		}
		return -1;
        
    }

	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			System.out.println(word + ": " + isUniqueChars(word));
		}
		
		int i = firstUniqChar("leetcode");
		System.out.println(i);
		
		i = firstUniqChar("loveleetcode");
		System.out.println(i);

		i = firstUniqChar("loveleetcodevctd");
		System.out.println(i);
		
	}

}
