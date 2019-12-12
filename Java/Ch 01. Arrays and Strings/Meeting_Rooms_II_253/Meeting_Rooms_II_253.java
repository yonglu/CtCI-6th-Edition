package Meeting_Rooms_II_253;

import java.util.*;


/* 
 * 
 * Leetcode #253 LeetCode Java: Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],…] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

 Solution: https://cheonhyangzhang.wordpress.com/2017/01/09/253-leetcode-java-meeting-rooms-medium/
 */

public class Meeting_Rooms_II_253 {
	
	 // Definition for an interval.
	 static class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	  }

	public static int minMeetingRooms(Interval[] intervals) {

		if (intervals == null || intervals.length == 0) {
			return 0;
		}

		// first sort the meetings by beginning time
		Arrays.sort(intervals, new Comparator<Interval>() { 
			@Override
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		
		List<Interval> rooms = new ArrayList<Interval>();
		rooms.add(intervals[0]);		
		
		// Check which room can fit the meeting.  Since we sort the meeting starting time, so it 
		// doesn't matter which room is selected.
		for (int i = 1; i < intervals.length; i++) {
			boolean isFound = false;
			for (Interval room : rooms) {
				if (room.end <= intervals[i].start) {
					room.end = intervals[i].end;
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				rooms.add(intervals[i]);
			}
		}
		
		return rooms.size();
	}

	// Because we only care about the end time of a room, so actually we can use a min-heap to keep track 
	// of end time so that we don't need to iterate over the list of rooms. Because for a heap. 
	// add takes O(logN) in worse case and peek takes O(1) which would be much faster.
	public static int minMeetingRoomsHeap(Interval[] intervals) {

		if (intervals == null || intervals.length == 0) {
			return 0;
		}

		// first sort the meetings by beginning time
		Arrays.sort(intervals, new Comparator<Interval>() { 
			@Override
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(intervals[0].end);
		
		// Check which room can fit the meeting.  Poll the one one that has meeting end earliest.
		for (int i = 1; i < intervals.length; i++) {
			if ( pq.peek() <= intervals[i].start ) {
				pq.poll();
			}
			pq.add(intervals[i].end);
		}
		
		return pq.size();
	}
	
	
	public static void main(String[] args) {

		Interval[] i1 = new Interval[3]; 
		i1[0] = new Interval(0, 30);
		i1[1] = new Interval(5, 10);
		i1[2] = new Interval(15, 20);
		
		int result = minMeetingRooms(i1);	
		System.out.println("canAttendMeeting of [[0, 30],[5, 10],[15, 20]] : " + result);

		Interval[] i2 = new Interval[3]; 
		i2[0] = new Interval(0, 30);
		i2[1] = new Interval(5, 10);
		i2[2] = new Interval(15, 20);

		result = minMeetingRoomsHeap(i2);	
		System.out.println("canAttendMeetingHeap of [[0, 30],[5, 10],[15, 20]] : " + result);
		
	}
}
