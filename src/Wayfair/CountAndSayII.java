package Wayfair;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=431902&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D63%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 * Count and Say的变形，输入一个字符串，输出简化后的字符串。char只出现一次的时候次数不用加（“111122344” -> "4122324"）。Follow up，次数可以合并，字母顺序不能变，“11112234411” -> "6122324";.
 */

public class CountAndSayII {
	public static void main(String[] args) {
		String s = "111122344";
		String res = countAndSayII(s);
		System.out.println(res);
		String s2 = "11112234411";
		String res2 = countAndSayII2(s2);
		System.out.println(res2);
	}
	
	public static String countAndSayII(String s) {
		int count = 0;
		char cur = s.charAt(0);
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == cur) {
				count++;
			}
			if (c != cur) {
				if (count > 1) {
					res += count;
				}
				res += cur;
				cur = c;
				count = 1;
			}
			if (i == s.length() - 1) {
				if (count > 1) {
					res += count;
				}
				res += cur;
			}
		}
		return res;
	}
	
	public static String countAndSayII2(String s) {
		int[] array = new int[256];
		for (int i = 0; i < s.length(); i++) {
			array[s.charAt(i)]++;
		}
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			if (array[s.charAt(i)] > 0) {
				if (array[s.charAt(i)] > 1) {
					res += array[s.charAt(i)];
				}
				res += s.charAt(i);
				array[s.charAt(i)] = 0;
			}
		}
		return res;
	}
}
