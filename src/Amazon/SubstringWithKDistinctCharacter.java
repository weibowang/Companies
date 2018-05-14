package Amazon;

import java.util.*;
//http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=405897&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B5%5D%3D5%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D5%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311

public class SubstringWithKDistinctCharacter {
	public static void main(String[] args) {
        String s = "abcdefdkfjnsd";
        int k = 4;
        List<String> res = substringWithKDistinctCharacter(s, k);
        for (String ss : res) {
        	System.out.println(ss);
        }
    }
	
	public static List<String> substringWithKDistinctCharacter(String s, int k) {
		List<String> res = new LinkedList<String>();
		HashSet<Character> set = new HashSet<Character>();
		HashSet<String> resSet = new HashSet<String>();
		int left = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			while (set.contains(c) || set.size() >= k) {
				set.remove(s.charAt(left));
				left++;
			}
			set.add(c);
			if (set.size() == k) {
				resSet.add(s.substring(left, i + 1));
			}
		}
		for (String ss : resSet) {
			res.add(ss);
		}
		Collections.sort(res);
		return res;
	}
}
