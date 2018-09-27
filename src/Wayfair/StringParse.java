package Wayfair;

import java.util.*;


public class StringParse {
	public static void main(String[] args) {
		String s = "-15 + 2d4 - 3d2";
		int s1 = solution1(s);
		int s2 = solution2(s);
		System.out.println("*" + s2);
	}
	
	
	public static int solution1(String s) {
		String[] strArray = s.split("\\+|\\-|\\/|\\*");
		ArrayList<Integer> ops = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				ops.add(-1);
			} else if (s.charAt(i) == '+') {
				ops.add(1);
			}
		}
		if (strArray.length > ops.size()) {
			ops.add(0, 1);
		}
		Random rand = new Random();
		int res = 0;
		for (int i = 0; i < strArray.length; i++) {
			String str = strArray[i].trim();
			if (str.length() == 0) {
				continue;
			}
			String[] array = str.split("d");
			if (array.length == 1) {
				res += Integer.parseInt(array[0]) * ops.get(i);
			} else if (array.length == 2) {
				int a1 = Integer.parseInt(array[0]);
				int a2 = Integer.parseInt(array[1]);
				for (int j = 0; j < a1; j++) {
					int val = rand.nextInt(a2);
					res += val * ops.get(i);
				}
			}
		}
		return res;
	}
	
	public static int solution2(String s) {
		s = s.replace(" ", "");
		int sign = 1;
		boolean d = false;
		String first = "";
		String second = "";
		int res = 0;
		Random rand = new Random();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '-') {
				if (second.equals("")) {
					if (!first.equals("")) {
						res += Integer.parseInt(first) * sign;
					}
				} else {
					int num1 = Integer.parseInt(first);
					int num2 = Integer.parseInt(second);
					for (int j = 0; j < num1; j++) {
						int val = rand.nextInt(num2) * sign;
						System.out.println(val);
						res += val;
					}
				}
				sign = -1;
				d = false;
				first = "";
				second = "";
			} else if (c == '+') {
				if (second.equals("")) {
					if (!first.equals("")) {
						res += Integer.parseInt(first) * sign;
					}
				} else {
					int num1 = Integer.parseInt(first);
					int num2 = Integer.parseInt(second);
					for (int j = 0; j < num1; j++) {
						int val = rand.nextInt(num2) * sign;
						System.out.println(val);
						res += val;
					}
				}
				sign = 1;
				d = false;
				first = "";
				second = "";
			} else if (c == 'd') {
				d = true;
			} else if (!d) {
				first += c;
			} else if (d) {
				second += c;
			}
		}
		if (second.equals("")) {
			if (!first.equals("")) {
				res += Integer.parseInt(first) * sign;
			}
		} else {
			int num1 = Integer.parseInt(first);
			int num2 = Integer.parseInt(second);
			for (int j = 0; j < num1; j++) {
				int val = rand.nextInt(num2) * sign;
				System.out.println(val);
				res += val;
			}
		}
		return res;
	}
}
