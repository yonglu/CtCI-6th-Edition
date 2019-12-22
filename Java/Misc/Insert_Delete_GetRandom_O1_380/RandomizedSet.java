package Insert_Delete_GetRandom_O1_380;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import sun.nio.cs.ext.ISCII91;

/*
 * Leetcode #380 Insert Delete GetRandom O(1)

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must 
have the same probability of being returned.

Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

 */

/*
 * Solution:
 * 
 * 		https://www.programcreek.com/2014/08/leetcode-insert-delete-getrandom-o1-java/
 * 		http://blog.gainlo.co/index.php/2016/08/14/uber-interview-question-map-implementation/?utm_source=comment&utm_medium=81616&utm_campaign=comment
 */

public class RandomizedSet {

	Map<Integer, Integer> valueMap;
	Map<Integer, Integer> indexMap;
	
	public RandomizedSet() {
		valueMap = new HashMap<Integer,Integer>();
		indexMap = new HashMap<Integer, Integer>();
	}

	public boolean insert(int val) {
		if (valueMap.containsKey(val)) {
			return false;
		}
		
		valueMap.put(val, indexMap.size());
		indexMap.put(indexMap.size(), val);		
		
		return true;
	}
    
	public boolean remove(int val) {
		if (!valueMap.containsKey(val)) {
			return false;
		}
		
		int idx = valueMap.get(val);

		// swap the indexMap last to the delete index location
		int lastIndexValue = indexMap.get(indexMap.size()-1);
		indexMap.put(idx, lastIndexValue);
		indexMap.remove(indexMap.size()-1);		
		valueMap.put(lastIndexValue, idx);
		
		valueMap.remove(val);
		
		return true;
	}
	
	public int getRandom() {
	       
		if (indexMap.size() == 0) {
			return -1;
		}
		
		Random r = new Random();
	    int idx = r.nextInt(indexMap.size());   
	    
	   	return indexMap.get(idx);
	}
	
    public static void main(String[] args) throws IOException {
    	boolean flag = false;
    	RandomizedSet randomSet = new RandomizedSet();
    	System.out.println("Expect true: " + randomSet.insert(1));
    	System.out.println("Expect false: " + randomSet.remove(2));
    	System.out.println("Expect true: " + randomSet.insert(2));
    	System.out.println("Expect 1 or 2: " + randomSet.getRandom());
    	System.out.println("Expect true: " + randomSet.remove(1));
    	System.out.println("Expect false: " + randomSet.insert(2));
    	System.out.println("Expect 2: " + randomSet.getRandom());
     }
}
