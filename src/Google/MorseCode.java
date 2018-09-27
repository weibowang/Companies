package Google;

import java.util.*;

/*
 * 
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=351065&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 * 
 */

public class MorseCode {
	public static void main(String[] args) {
		HashMap<Character, String> map = new HashMap<Character, String>();
		map.put('a', "._");
		map.put('i', "..");
		map.put('r', "._.");
		map.put('u', "..");
		map.put('b', "..._");
		map.put('d', ".");
		List<String> words = new ArrayList<String>();
		words.add("air");
		words.add("rue");
		words.add("abd");
		
		int res1 = morseCode(map, words);
		System.out.println(res1);
	}
	
	public static int morseCode(HashMap<Character, String> map, List<String> words) {
		int count = 0;
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for (String word : words) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				sb.append(map.get(word.charAt(i)));
			}
			String key = new String(sb);
			if (counts.containsKey(key)) {
				counts.put(key, counts.get(key) + 1);
			} else {
				counts.put(key, 1);
			}
			if (counts.get(key) > 1) {
				count++;
			}
		}
		return count;
	}
}
