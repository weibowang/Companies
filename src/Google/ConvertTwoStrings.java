package Google;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441969&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3048%5D%5Bvalue%5D%3D2%26searchoption%5B3048%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 * 
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
				map.put(s.charAt(i), t.charAt(i));
			}
		}
		return true;
	}
	
}
