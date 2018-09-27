package Google;

import java.util.*;

/*
 * 
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=443851&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * https://github.com/allaboutjst/airbnb
 * 
 */

public class CollatzConjecture {
	public static void main(String[] args) {
		int n = 101;
		int res = findMaxSteps(n);
		System.out.println(res);
	}
	
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static int findMaxSteps(int n) {
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int val = findStep(i);
			max = Math.max(max, val);
		}
		return max;
	}
	
	public static int findStep(int n) {
		if (n == 1) {
			return 1;
		}
		if (map.containsKey(n)) {
			return map.get(n);
		}
		if (n % 2 == 0) {
			int val = findStep(n / 2) + 1;
			map.put(n, val);
			return val;
		} else {
			int val = findStep(n * 3 + 1) + 1;
			map.put(n, val);
			return val;
		}
	}
}
