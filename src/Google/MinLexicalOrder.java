package Google;

import java.util.*;


/*
 * ��һ��string, �ҳ�lexical order ��С�ģ� size==k�ģ� subsequence, (note, not substring)
String findMin(String s, k){} . 围观我们@1point 3 acres
e.g.. 围观我们@1point 3 acres
input
s=pineapple, k==3, 

output: ale
ale is the lexical order smallest subsequnce of length 3. 
���Ǳ������ģ� 
1. find the first occur position of distinct char. 
2. then start from that position. . from: 1point3acres.com/bbs 
3. dfs to find lenght==3, subsequence(dfs, combination way); 
4. find the one with smallest lexical order. 

similar to lc316
 */

public class MinLexicalOrder {
	
	public static void main(String[] args) {
		String s = "pineapple";
		int k = 3;
		String result = solution1(s, k);
		String result2 = solution2(s, k);
		String result3 = findMin(s, k);
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
	}
	
	private static String solution2(String s, int k) {
		if (s == null || s.length() == 0 || k == 0) {
			return "";
		}
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty()) {
				stack.push(s.charAt(i));
				continue;
			}
			while (!stack.isEmpty() && stack.peek() > s.charAt(i) && stack.size() + s.length() - i - 1 >= k) {
				stack.pop();
			}
			stack.push(s.charAt(i));
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();
		return sb.toString();
	}
	
	
	//recursive
	static String gs;
	private static String solution1(String s, int k) {
		gs = s;
		return recurse(s, k, "", 0);
	}
	
	private static String recurse(String s, int k, String str, int pos) {
		if (str.length() == k) {
			return str;
		}
		if (pos >= s.length()) {
			return gs;
		}
		String result = gs;
		for (int i = pos; i < s.length(); i++) {
			String newStr = str + s.charAt(i);
			String recursion = recurse(s, k, newStr, i + 1);
			if (result.compareTo(recursion) > 0) {
				result = recursion;
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String findMin(String s, int k) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (stack.isEmpty()) {
				stack.push(c);
				continue;
			}
			int count = s.length() - i;
			while (!stack.isEmpty() && stack.peek() > c && stack.size() + count > k) {
				stack.pop();
			}
			stack.push(c);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


