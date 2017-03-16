package Facebook;

/*
 * balance parentheses in a string
例子：
"(a)()" -> "(a)()"
"((bc)" -> "(bc)"
")))a((" -> "a"
"(a(b)" ->"(ab)" or "a(b)"
Note: balance的意思就是把原来string里unpaired的括号变成paired的形式。如果有多个可能的结果， 
比如上述最后一种情况，我们就只需要输出一个对的结果即可，所以这点简化了题目的难度。感受： 遍历string， 
用一个stack存储每个open parenthesis的index，也就是'('的index, 每当遇到closed parenthesis就执行一次pop操作。
注意两种unbalanced的情况：
1. 出现多余的')':
    对应情况就是stack为空，但遇到了一个')'。
2. 出现多余的'(':
    对应情况就是遍历结束，stack未空
    
  http://www.1point3acres.com/bbs/thread-125084-1-1.html  
 */
public class BalanceParentheses {
	public static void main(String[] args) {
		String s1 = "(a)()";
		String s2 = "((bc)";
		String s3 = ")))a((";
		String s4 = "(a(b)";
		String a1 = BalanceParentheses(s1);
		System.out.println(a1);
		String a2 = BalanceParentheses(s2);
		System.out.println(a2);
		String a3 = BalanceParentheses(s3);
		System.out.println(a3);
		String a4 = BalanceParentheses(s4);
		System.out.println(a4);
		
		String s5 = "()";
		String s6 = ")(";
		String s7 = "((((((";
		String s8 = "(()()(";
		String s9 = ")(())(";
		String s10 = ")(())(";
		String s11 = ")))(((()()(())";
		String a5 = BalanceParentheses(s5);
		System.out.println(a5);
		String a6 = BalanceParentheses(s6);
		System.out.println(a6);
		String a7 = BalanceParentheses(s7);
		System.out.println(a7);
		String a8 = BalanceParentheses(s8);
		System.out.println(a8);
		String a9 = BalanceParentheses(s9);
		System.out.println(a9);
		String a10 = BalanceParentheses(s10);
		System.out.println(a10);
		String a11 = BalanceParentheses(s11);
		System.out.println(a11);
	}
	public static String BalanceParentheses(String s) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				count++;
				sb.append(c);
			} else if (c == ')') {
				if (count > 0) {
					count--;
					sb.append(c);
				}
			} else {
				sb.append(c);
			}
		}
		s = sb.toString();
		//System.out.println(s);
		sb = new StringBuilder();
		count = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == ')') {
				count++;
				sb.append(c);
			} else if (c == '(') {
				if (count > 0) {
					count--;
					sb.append(c);
				}
			} else {
				sb.append(c);
			}
		}
		sb = sb.reverse();
		return sb.toString();
	}
}
