package Google;

import java.util.*;

public class TimeLimitCache {
	
	class Pair {
		String key;
		int time;
		Pair(String key, int time) {
			this.key = key;
			this.time = time;
		}
	}
	
	public static void main(String[] args) {
		TimeLimitCache cache = new TimeLimitCache();
		cache.put("a", 1, 1, 5);
		cache.get("b", 2);
		cache.get("a", 3);
		cache.clean(5);
		cache.get("a", 6);
		cache.get("a", 7);
		cache.clean(8);
		cache.put("c", 3, 8, 5);
		cache.clean(9);
		cache.put("d", 4, 10, 5);
		cache.get("c", 11);
		cache.get("d", 11);
		cache.get("c", 12);
		cache.get("d", 12);
		cache.get("c", 13);
		cache.get("d", 13);
		cache.clean(14);
		cache.get("c", 15);
		cache.get("d", 15);
		cache.get("c", 16);
		cache.get("d", 16);
	}
	
	HashMap<String, Integer> map;
	HashMap<String, Integer> times;
	PriorityQueue<Pair> pq;
	TimeLimitCache() {
		map = new HashMap<String, Integer>();
		times = new HashMap<String, Integer>();
		pq = new PriorityQueue<Pair>(11, new Comparator<Pair>() {
			public int compare(Pair left, Pair right) {
				return left.time - right.time;
			}
		});
	}
	
	public void put(String key, int value, int currentTime, int addTime) {
		map.put(key, value);
		times.put(key, currentTime + addTime);
		Pair pair = new Pair(key, currentTime + addTime);
		pq.add(pair);
		System.out.println("put: " + key + " " + value + " " + currentTime + " " + addTime);
	}
	
	public Integer get(String key, int currentTime) {
		if (!map.containsKey(key) || times.get(key) < currentTime) {
			System.out.println("get " + key + " " + currentTime + " null");
			return null;
		}
		System.out.println("get " + key + " " + currentTime + " " + map.get(key));
		return map.get(key);
	}
	
	public void clean(int currentTime) {
		while (!pq.isEmpty() && pq.peek().time < currentTime) {
			Pair p = pq.poll();
			map.remove(p.key);
			times.remove(p.key);
		}
		System.out.println("clean...");
		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
	}
}
