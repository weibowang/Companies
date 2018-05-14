package Amazon;

import java.util.*;

public class ShortestSubsequenceContainsAllTags {
	public static void main(String[] args) {
		String[] array = {"made", "a","b","c","in", "china","made","b","c","d"};
		String[] tag = {"made", "china", "in"};
		int[] res = shortestSubsequenceContainsAllTags(array, tag);
		System.out.println(Arrays.toString(res));
	}
	
	public static int[] shortestSubsequenceContainsAllTags(String[] array, String[] tag) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int count = tag.length;
		for (String s: tag) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}
		int left = 0;
		int[] res = new int[2];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			String word = array[i];
			if (map.containsKey(word)) {
				map.put(word, map.get(word) - 1);
				if (map.get(word) >= 0) {
					count--;
				}
			}
			while (!map.containsKey(array[left]) || map.get(array[left]) < 0) {
				if (map.containsKey(array[left])) {
					map.put(array[left], map.get(array[left]) + 1);
				}
				left++;
			}
			if (count == 0) {
				if (i - left < min) {
					min = i - left;
					res[0] = left;
					res[1] = i;
				}
			}
		}
		return res;
	}
}
