package Google;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441969&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3048%5D%5Bvalue%5D%3D2%26searchoption%5B3048%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 *
 *http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=434945&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3087%255D%255Bvalue%255D%3D3%26searchoption%255B3087%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3048%255D%255Bvalue%255D%3D2%26searchoption%255B3048%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 *
 *这题有点恶心啊……感觉是要检查start和end里每个char的映射关系，build一个有向图，然后检查这个图里不能有环，但是可以有自环。比如：
aabc -> efab  false  因为a不能同时对应e和f. 
ab -> ba  false 因为有环.
ab -> aa  true  因为环是自环
 */

public class ConvertTwoStrings {
	public static void main(String[] args) {
		String s = "abc";
		String t = "bca";
		boolean res1 = solution1(s, t);
		System.out.println(res1);
		System.out.println(isCircle);
	}
	
	static boolean isCircle = false;
	public static boolean solution1(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				if (t.charAt(i) != map.get(s.charAt(i))) {
					return false;
				}
			} else {
				System.out.println(i);
				if (findCircle(map, t.charAt(i), s.charAt(i))) {
					isCircle = true;
				}
				map.put(s.charAt(i), t.charAt(i));
			}
		}
		return true;
	}
	
	public static boolean findCircle(HashMap<Character, Character> map, char start, char end) {
		while (map.containsKey(start) && map.get(start) != start && start != end) {
			start = map.get(start);
		}
		return start == end;
	}
}
