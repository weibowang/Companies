package Google;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=351065&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 */

import java.util.*;

public class TwoStringBacktrack {

	public static void main(String[] args) {
		String s1 = "ab";
		String s2 = "cd";
		List<String> res = twoStringBacktrack(s1, s2);
		int count = 0;
		for (String s : res) {
			count++;
			System.out.println(s);
		}
		System.out.println(count);
	}
	
	public static List<String> twoStringBacktrack(String s1, String s2) {
		List<String> res = new ArrayList<String>();
		res.add("");
		for (int i = 0; i < s1.length(); i++) {
			List<String> newRes = new ArrayList<String>();
			for (char c = s1.charAt(i); c <= s2.charAt(i); c++) {
				List<String> list = new ArrayList<String>(res);
				for (String s : list) {
					s += c;
					newRes.add(s);
				}
			}
			res = newRes;
		}
		return res;
	}
}
