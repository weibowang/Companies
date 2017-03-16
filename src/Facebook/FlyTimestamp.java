package Facebook;

import java.util.*;

public class FlyTimestamp {
	static class Interval {
		int start;
		int end;
		Interval() {
		}
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static class TypeOne {
		int event;
		int value;
		TypeOne(int event, int value) {
			this.event = event;
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		Interval[] intervals = new Interval[4];
		intervals[0] = new Interval(1, 3);
		intervals[1] = new Interval(2, 7);
		intervals[2] = new Interval(4, 8);
		intervals[3] = new Interval(5, 9);
		Interval cur = FlyTimestamp(intervals);
		System.out.println(cur.start);
		System.out.println(cur.end);
	}
	
	
	public static Interval FlyTimestamp(Interval[] intervals) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < intervals.length; i++) {
			Interval cur = intervals[i];
			int first = cur.start;
			int second = cur.end;
			if (map.containsKey(first)) {
				map.put(first, map.get(first) + 1);
			} else {
				map.put(first, 1);
			}
			if (map.containsKey(second)) {
				map.put(second, map.get(second) - 1);
			} else {
				map.put(second, -1);
			}
		}
		int max = 0;
		int track = 0;
		int start = 0;
		for (int key : map.keySet()) {
			int val = map.get(key);
			track += val;
			if (track > max) {
				max = track;
				start = key;
			}
		}
		Interval res = new Interval();
		res.start = start;
		start = map.higherKey(start);
		while (map.get(map.higherKey(start)) >= 0) {
			start = map.higherKey(start);
		}
		res.end = start - 1;
		return res;
	}

}
