package LRU_Cache_146;

import java.util.*;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/* Leetcode 146. LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists 
in the cache, otherwise return -1.

put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently 
used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/

class Node { 
	// explain that for data ecapsolation, these should be private and 
	// will provide access methods.
    int key; 
    int value; 
    Node pre; 
    Node next; 
  
    public Node(int key, int value) 
    { 
        this.key = key; 
        this.value = value; 
    } 
} 
  
class LRUCache { 
    private HashMap<Integer, Node> map; 
    private int capicity, count; 
    private Node head, tail; 
  
    public LRUCache(int capacity) 
    { 
        this.capicity = capacity; 
        map = new HashMap<>(); 
        head = new Node(0, 0); 
        tail = new Node(0, 0); 
        head.next = tail; 
        tail.pre = head; 
        head.pre = null; 
        tail.next = null; 
        count = 0; 
    } 
  
    public void deleteNode(Node node) 
    { 
        node.pre.next = node.next; 
        node.next.pre = node.pre; 
    } 
  
    public void addToHead(Node node) 
    { 
        node.next = head.next; 
        node.next.pre = node; 
        node.pre = head; 
        head.next = node; 
    } 
  
    // This method works in O(1) 
    public int get(int key) 
    { 
        if (map.get(key) != null) { 
            Node node = map.get(key); 
            int result = node.value; 
            deleteNode(node); 
            addToHead(node); 
            System.out.println("Got the value : " + 
                  result + " for the key: " + key); 
            return result; 
        } 
        System.out.println("Did not get any value" + 
                            " for the key: " + key); 
        return -1; 
    } 
  
    // This method works in O(1) 
    public void put(int key, int value) 
    { 
        System.out.println("Going to set the (key, "+  
             "value) : (" + key + ", " + value + ")"); 
        if (map.get(key) != null) { 
            Node node = map.get(key); 
            node.value = value; 
            deleteNode(node); 
            addToHead(node); 
        } 
        else { 
            Node node = new Node(key, value); 
            map.put(key, node); 
            if (count < capicity) { 
                count++; 
                addToHead(node); 
            } 
            else { 
                map.remove(tail.pre.key); 
                deleteNode(tail.pre); 
                addToHead(node); 
            } 
        } 
    } 
} 

public class LRU_Cache_146 {
    
	public static void main(String[] args) {
		LRUCache cache = new LRUCache( 2);

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}
}
