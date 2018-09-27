package Google;

import java.util.*;

/*
 * 
 * http://www.1point3acres.com/bbs/thread-321199-1-1.html
 * 
 */

public class MaximizeDistanceToClosestPersonII {
	static class Pair implements Comparable<Pair> {
		int start;
		int end;
		int len;
		Pair(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}
		
		public int compareTo(Pair right) {
			int l1 = this.end - this.start;
			if (this.end != len && this.start != -1) {
				l1 = l1 / 2;
			}
			int l2 = right.end - right.start;
			if (right.end != len && right.start != -1) {
				l2 = l2 / 2;
			}
			return l2 - l1;
		}
	}
	static int[] array = {0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0};
	static PriorityQueue<Pair> pq;
	public static void main(String[] args) {
		init();
		System.out.println("len: " + array.length);
		System.out.println(insertSeat());
		System.out.println(insertSeat());
		System.out.println(insertSeat());
		System.out.println(insertSeat());
		System.out.println(insertSeat());
	}
	
	public static void init() {
		pq = new PriorityQueue<Pair>();
		int start = -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				Pair p = new Pair(start, i, array.length);
				start = i;
				pq.add(p);
			}
		}
		Pair last = new Pair(start, array.length, array.length);
		pq.add(last);
	}
	
	public static int insertSeat() {
		Pair p = pq.poll();
		int index = 0;
		if (p.start == -1) {
			index = 0;
		} else if (p.end == array.length) {
			index = array.length - 1;
		} else {
			index = p.start + (p.end - p.start) / 2;
		}
		Pair first = new Pair(p.start, index, array.length);
		Pair second = new Pair(index, p.end, array.length);
		pq.add(first);
		pq.add(second);
		return index;
	}
	
}
