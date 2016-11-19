package Google;

import java.util.*;
import java.util.TreeMap;

public class InfoSystem {
	
	static class MyCompare implements Comparator<Long> {
		public int compare(Long left, Long right) {
			return (int) (right - left);
		}
	}
	static class MyCompare2 implements Comparator<Integer> {
		public int compare(Integer left, Integer right) {
			return (int) (right - left);
		}
	}
	
	static class MySystem {
		TreeMap minMap;
		TreeMap maxMap;
		TreeMap<Long, Integer> timeMap;
		public MySystem() {
			
			minMap = new TreeMap();
			maxMap = new TreeMap(new MyCompare2());
			timeMap = new TreeMap(new MyCompare());
		}
		int getMax() {
			return (int) maxMap.firstKey();
		}
		
		int getMin() {
			return (int) minMap.firstKey();
		}
		
		int getRecent() {
			return timeMap.get(timeMap.firstKey());
		}
		  
		void add(long time, int price) {
			timeMap.put(time, price);
			minMap.put(price, null);
			maxMap.put(price, null);
		}
		
		void update(long time, int price) {
			int val = timeMap.get(time);
			minMap.remove(val);
			maxMap.remove(val);
			timeMap.put(time, price);
			minMap.put(price, null);
			maxMap.put(price, null);
		}
		
		void remove(long time) {
			int val = timeMap.get(time);
			minMap.remove(val);
			maxMap.remove(val);
			timeMap.remove(time);
		}
	}

	
	public static void main(String[] args) {
		MySystem s = new MySystem();
		s.add(1, 4);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
		s.add(4, 7);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
		s.add(2, 5);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
		s.remove(4);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
		s.add(2, 5);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
		s.update(2, 9);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
		s.add(3, 7);
		System.out.println("max: " + s.getMax() + "  min: " + s.getMin() + "  recent: " + s.getRecent());
	}
	
	
	
}
