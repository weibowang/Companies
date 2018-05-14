package Amazon;

import java.util.*;

public class MostCommonWord {
	
	public static void main(String[] args) {
		String paragraph = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
		String[] s = {"and", "he", "the", "to", "is", "Jack", "Jill"};
		List<String> banned = Arrays.asList(s);
		List<String> res = FindMostFreqWords(paragraph, banned);
		for (String ss : res) {
			System.out.println(ss);
		}
	}
	
	public static List<String> FindMostFreqWords(String paragraph, List<String> banned) {
		List<String> res = new LinkedList<String>();
		paragraph = paragraph.trim().toLowerCase();
		paragraph = paragraph.replaceAll("[^a-z]+", " ");
		String[] split = paragraph.split("\\s+");
		HashSet<String> banSet = new HashSet<String>();
		if (banned != null) {
			for (String s : banned) {
				s = s.toLowerCase();
				banSet.add(s);
			}
		}

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int max = 0;
		for (String word : split) {
			if (word.length() > 0 && !banSet.contains(word)) {
				if (map.containsKey(word)) {
					map.put(word, map.get(word) + 1);
				} else {
					map.put(word, 1);
				}
				max = Math.max(max, map.get(word));
			}
		}
		for (String word : map.keySet()) {
			if (map.get(word) == max) {
				res.add(word);
			}
		}
		return res;
	}
}
