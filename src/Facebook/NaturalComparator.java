package Facebook;

import java.util.Comparator;

public class NaturalComparator implements Comparator<String>{
	 /**
     * return negative num if l < r.
     0  if l == r
     positive if l > r
     */
	public int compare(String left, String right) {
		int p1 = 0;
		int p2 = 0;
		while (p1 < left.length() && p2 < right.length() && !Character.isDigit(left.charAt(p1)) && !Character.isDigit(left.charAt(p2))) {
			if (left.charAt(p1) > right.charAt(p2)) {
				return 1;
			}
			if (left.charAt(p1) < right.charAt(p2)) {
				return -1;
			}
			p1++;
			p2++;
		}
		if (p1 == left.length() && p2 == right.length()) {
			return 0;
		}
		if (p1 == left.length()) {
			return -1;
		}
		if (p2 == right.length()) {
			return 1;
		}
		if (!Character.isDigit(left.charAt(p1))) {
			return 1;
		}
		if (!Character.isDigit(right.charAt(p2))) {
			return -1;
		}
		int l = Integer.parseInt(left.substring(p1));
		int r = Integer.parseInt(right.substring(p2));
		if (l > r) {
			return 1;
		}
		if (l < r) {
			return -1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String a = "abc";
		String b = "ab9";
		NaturalComparator n = new NaturalComparator();
		System.out.println(n.compare(a, b));
	}
	
	
}
