package Top_K_Frequent_Words_692;

import java.util.*;

/* Leetcode # 692. Top K Frequent Words

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, 
then the word with the lower alphabetical order comes first.

Example 1:

Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:

Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
 
*/

public class Top_K_Frequent_Words_692 {

    public static List<String> topKFrequent(String[] words, int k) {
    	List<String> result = new ArrayList<String>();
    	if (words == null || words.length == 0 || k == 0) {
    		return result;
    	}
    	
    	Map<String, Integer> frequentMap = new HashMap<String, Integer>();
    	for (String s : words) {
    		frequentMap.put(s, frequentMap.getOrDefault(s, 0) + 1);
    	}
    	
    	result = new ArrayList<String>(frequentMap.keySet());
    	Collections.sort(result, new Comparator<String>() {
    		// Override
    		public int compare(String s1, String s2) {
    			if (frequentMap.get(s1) != frequentMap.get(s2)) {
    				return frequentMap.get(s2) - frequentMap.get(s1);
    			} else {
    				// The result is a negative integer if this String object lexicographically precedes the argument string.
    				return s1.compareTo(s2);
    			}
    		}
    	}    			
    	);
    	
        // handle case if k is greater than number of unique elements
        if (k  > result.size()) {
            k = result.size();
        }
        
        return result.subList(0, k);
    }
    
    
	public static void main(String[] args) {
		List<String> result;
		
		String[] words = new String[] {
				"i", "love", "leetcode", "i", "love", "coding"
		};
		result = topKFrequent(words, 2);		
		System.out.println("Top 2 frequent of [\"i\", \"love\", \"leetcode\", \"i\", \"love\", \"coding\"] is : ");	
		for (String s : result) {
			System.out.println("    " + s);
		}
		System.out.println("Expected Output: [\"i\", \"love\"]");
		
		words = new String[] {
				"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"
		};
		result = topKFrequent(words, 4);		
		System.out.println("Top 4 frequent of [\"the\", \"day\", \"is\", \"sunny\", \"the\", \"the\", \"the\", \"sunny\", \"is\", \"is\"] is : ");	
		for (String s : result) {
			System.out.println("    " + s);
		}
		System.out.println("Expected Output: [\"the\", \"is\", \"sunny\", \"day\"]");
	
		words = new String[] {
				"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "is"
		};
		result = topKFrequent(words, 4);		
		System.out.println("Top 4 frequent of [\"the\", \"day\", \"is\", \"sunny\", \"the\", \"the\", \"the\", \"sunny\", \"is\", \"is\", \"is\"] is : ");	
		for (String s : result) {
			System.out.println("    " + s);
		}
		System.out.println("Expected Output: [\"is\", \"the\", \"sunny\", \"day\"]");
		
	}
}
