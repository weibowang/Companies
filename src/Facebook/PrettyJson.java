package Facebook;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/thread-172492-1-1.html
 */
public class PrettyJson {
	public static void main(String[] args) {
		String s = "[1,2,3,{foo:[4.0,5,6]}]";
		PrettyJson(s);
	}
	public static void PrettyJson(String s) {
		if (s == null || s.length() == 0) {
			return;
		}
		Stack<Character> stack = new Stack<Character>();
		String space = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '[' || c == '{') {
				if (!stack.isEmpty() && stack.peek() == ':') {
					System.out.print(" " + c);
					stack.pop();
				} else {
					System.out.print(space + c);
					space += "  ";
				}
				if (!stack.isEmpty() && stack.peek() == ',') {
					stack.pop();
				}
				System.out.println();
				stack.push(c);
			} else if (c == ']' || c == '}') {
				System.out.println();
				System.out.print(space + c);
				if (space.length() - 2 >= 0) {
					space = space.substring(0, space.length() - 2);
				}
			} else if (c == ',') {
				System.out.print(c);
				System.out.println();
				stack.push(',');
			} else if (!stack.isEmpty() && (stack.peek() == ',' || stack.peek() == '{' || stack.peek() == '[')){
				//System.out.print("**" + stack.peek());
				System.out.print(space + c);
				stack.pop();
			} else if (c == ':'){
				System.out.print(c);
				stack.push(':');
			} else {
				System.out.print(c);
			}
		}
	}
}
