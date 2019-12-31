package Find_Median_from_Data_Stream_295;

import java.util.*;

/* Leetcode # 295. Find Median from Data Stream

Median is the middle value in an ordered integer list. If the size of the list is even, there is no 
middle value. So the median is the mean of the two middle value.

For example,

[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.
 
*/


/*
 * 

Two priority queues:

A max-heap lo to store the smaller half of the numbers
A min-heap hi to store the larger half of the numbers

The max-heap lo is allowed to store, at worst, one more element more than the min-heap hi.

Number always add into lo heap, need to balance the heaps: 
	if the lo heap size is 1 greater than the hi heap size, then move the top of hi heap to lo heap, 
			and move the top of lo heap to hi heap; 
	if the lo heap size is 2 greater than the hi heap size, just move the top of lo heap to hi heap.

 
Adding number 41
MaxHeap lo: [41]           // MaxHeap stores the largest value at the top (index 0)
MinHeap hi: []             // MinHeap stores the smallest value at the top (index 0)
Median is 41
=======================
Adding number 35
MaxHeap lo: [35]
MinHeap hi: [41]
Median is 38
=======================
Adding number 62
MaxHeap lo: [41, 35]
MinHeap hi: [62]
Median is 41
=======================
Adding number 4
MaxHeap lo: [35, 4]
MinHeap hi: [41, 62]
Median is 38
=======================
Adding number 97
MaxHeap lo: [41, 35, 4]
MinHeap hi: [62, 97]
Median is 41
=======================
Adding number 108
MaxHeap lo: [41, 35, 4]
MinHeap hi: [62, 97, 108]
Median is 51.5

 */

class MedianFinder {
    /** initialize your data structure here. */
	PriorityQueue<Integer> lo;
	PriorityQueue<Integer> hi;
    public MedianFinder() {
    	// lo is max priority queue to hold the first smaller half of data
        lo = new PriorityQueue<Integer>(Collections.reverseOrder());
        // hi is min priority queu to hold the second larger half of data
        hi = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
    	lo.add(num);
    	
    	// balance the lo and hi heaps 
        if ((lo.size() - hi.size()) == 1 && hi.size() != 0) {
        	lo.add(hi.remove());
        	hi.add(lo.remove());
        } else if ((lo.size() - hi.size()) == 2) {
        	hi.add(lo.remove());        	
        }
    }
    
    public double findMedian() {
        if (lo.size() == 0 && hi.size() == 0) {
        	return 0.0;
        }
        
        if (lo.size() > hi.size()) {
        	return lo.peek();
        } else {
        	int temp = lo.peek() + hi.peek();
        	return (double) temp/2;
        }
    }
}    



public class Find_Median_from_Data_Stream_295 {

	public static void main(String[] args) {
	
		MedianFinder obj = new MedianFinder();
		obj.addNum(41);
		double ans1 = obj.findMedian();		
		System.out.println("expected: 41 : " + ans1);

		obj.addNum(35);
		ans1 = obj.findMedian();		
		System.out.println("expected: 38 : " + ans1);
		obj.addNum(62);
		ans1 = obj.findMedian();		
		System.out.println("expected: 41 : " + ans1);
		obj.addNum(4);
		ans1 = obj.findMedian();		
		System.out.println("expected: 38 : " + ans1);
		obj.addNum(97);
		ans1 = obj.findMedian();		
		System.out.println("expected: 41 : " + ans1);
		obj.addNum(108);
		ans1 = obj.findMedian();		
		System.out.println("expected: 51.5 : " + ans1);
	}
}
