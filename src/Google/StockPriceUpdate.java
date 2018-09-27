package Google;

import java.util.*;
/*
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=436592&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3087%255D%255Bvalue%255D%3D3%26searchoption%255B3087%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3048%255D%255Bvalue%255D%3D2%26searchoption%255B3048%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1

http://www.1point3acres.com/bbs/thread-229745-1-1.html
 * 
 */


public class StockPriceUpdate {
	public static void main(String[] args) {
		String s = "a";
		updatePrice(s, 11, 850);
		print(s);
		updatePrice(s, 12, 852);
		print(s);
		updatePrice(s, 13, 848);
		print(s);
		updatePrice(s, 12, 849);
		print(s);
		updatePrice(s, 14, 855);
		print(s);
		updatePrice(s, 13, 853);
		print(s);
		updatePrice(s, 15, 854);
		print(s);
		updatePrice(s, 15, 853);
		print(s);
		remove(s, 13);
		print(s);
	}
	
	static HashMap<String, TreeMap<Integer, Integer>> prices = new HashMap<String, TreeMap<Integer, Integer>>();
	static HashMap<String, TreeMap<Integer, Integer>> counts = new HashMap<String, TreeMap<Integer, Integer>>();
	
	public static void updatePrice(String s, int time, int price) {
		if (prices.containsKey(s)) {
			TreeMap<Integer, Integer> tm = prices.get(s);
			if (tm.containsKey(time)) {
				remove(s, time);
			}
			tm.put(time, price);
		} else {
			TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
			tm.put(time, price);
			prices.put(s, tm);
		}
		if (counts.containsKey(s)) {
			TreeMap<Integer, Integer> tm = counts.get(s);
			if (tm.containsKey(price)) {
				tm.put(price, tm.get(price) + 1);
			} else {
				tm.put(price, 1);
			}
		} else {
			TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
			tm.put(price, 1);
			counts.put(s, tm);
		}
	}
	
	public static boolean remove(String s, int time) {
		if (!prices.containsKey(s)) {
			return false;
		}
		TreeMap<Integer, Integer> tm1 = prices.get(s);
		if (tm1.containsKey(time)) {
			int price = tm1.get(time);
			if (counts.containsKey(s)) {
				TreeMap<Integer, Integer> tm2 = counts.get(s);
				if (tm2.containsKey(price)) {
					tm2.put(price, tm2.get(price) - 1);
					if (tm2.get(price) == 0) {
						tm2.remove(price);
					}
				}
			}
			tm1.remove(time);
			return true;
		}
		return false;
	}
	
	public static int getLatestPrice(String s) {
		if (!prices.containsKey(s)) {
			return -1;
		}
		TreeMap<Integer, Integer> tm = prices.get(s);
		return tm.lastEntry().getValue();
	}
	
	public static int getMinPrice(String s) {
		if (!counts.containsKey(s)) {
			return -1;
		}
		TreeMap<Integer, Integer> tm = counts.get(s);
		return tm.firstEntry().getKey();
	}
	
	public static int getMaxPrice(String s) {
		if (!counts.containsKey(s)) {
			return -1;
		}
		TreeMap<Integer, Integer> tm = counts.get(s);
		return tm.lastEntry().getKey();
	}
	
	public static void print(String s) {
		System.out.println(s + ": last:" + getLatestPrice(s) + " min:" + getMinPrice(s) + " max:" + getMaxPrice(s));	
	}
}
