package Group_Anagrams_49;

import java.util.*;


/* Leetcode #49. Group Anagrams

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
] */

public class Group_Anagrams_49 {

	
	// Time O(NKlogK), where N is the length of strs, and K is the maximum length of a string in strs. 
	// Space O(NK)
	public static List<List<String>> groupAnagrams(String[] strs)  {
		List<List<String>> ans = new ArrayList<>();
		
		if (strs == null || strs.length == 0 ) {
			return ans;
//			throw new java.lang.IllegalArgumentException("bad parameters");
		}
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] keyChars = str.toCharArray();
			Arrays.sort(keyChars);
			String key = new String(keyChars);
			
			if (map.containsKey(key)) {
				map.get(key).add(str);
			} else {
				List<String> tempList = new ArrayList<String>();
				tempList.add(str);
				map.put(key, tempList);
			}
		}
		
		for (List<String> list : map.values()) {
			ans.add(list);
		}
		
		return ans;
	}

	public static void main(String[] args) {
		
		String[] strs = new String[] {
			"eat", 
			"tea", 
			"tan", 
			"ate", 
			"nat", 
			"bat"
		};

		List<List<String>> ans = groupAnagrams(strs);	
		System.out.println("Group Anagrams of [\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"] : ");
		for (List<String> list : ans) {
			for (String str : list) {
				System.out.print(str + " ");
			}
			System.out.println("");
		}
		
	}
}
