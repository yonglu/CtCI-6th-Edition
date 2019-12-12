package Meeting_Rooms_252;

import java.util.*;


/* 
 * 
 * Leetcode #LeetCode] 252. Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

 */

public class Meeting_Rooms_252 {

	
	 // Definition for an interval.
	 static class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	  }

	public static boolean canAttendMeetings(Interval[] intervals) {

		if (intervals == null || intervals.length == 0) {
			return false;
		}

		// first sort the meetings by beginning time
		Arrays.sort(intervals, new Comparator<Interval>() { 
			@Override
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		
		// Check if meetings are overlap.  If overlap, then means a person could not attend both
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i-1].end > intervals[i].start) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {

		Interval[] i1 = new Interval[3]; 
		i1[0] = new Interval(0, 30);
		i1[1] = new Interval(5, 10);
		i1[2] = new Interval(15, 20);
		
		boolean result = canAttendMeetings(i1);	
		System.out.println("canAttendMeeting of [[0, 30],[5, 10],[15, 20]] : " + result);

	}
}
