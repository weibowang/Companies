package Google;

/*
 * 第一题 我回来自己google 了下没找到原题 
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/.
感觉是这个，但是要找有多少种 palindromic-subsequence 例如 abac， 有 a, b, c, a, aa, aa, aba, aba, aca, aca, 
有重复的原因是index 不一样的character 也要算一种， 所以其实就是P（4,1）+P（2,2） + P（2，2）*P（2.1） 全排 。
我用DP 做的，但状态转移方程没写对，也就是条件转移方程没写出来。当然楼主给出的是暴利 bfs  全排2^N, 想尝试n^3 dp 失败。 
dp[s.lengfth()][s.lengfth()] 根据 length = 1,2,3,4 ……一直到N 判断。
 */

public class LongestPalindromicSubsequence {
	
	
	public static void main(String[] args) {
		String s = "BBABCBCAB";
		int val = pls(s);
		System.out.println(val);
	}
	
	public static int pls(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[][] dp = new int[s.length() + 1][s.length() + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}
		for (int j = 1; j < dp.length; j++) {
			for (int i = 1; i + j < dp.length; i++) {
				int x = i;
				int y = i + j;
				dp[x][y] = Math.max(dp[x + 1][y], dp[x][y - 1]);
				if (s.charAt(x - 1) == s.charAt(y - 1)) {
					if (x + 1 == y) {
						dp[x][y] = 2;
					} else {
						dp[x][y] = Math.max(dp[x][y], dp[x + 1][y - 1] + 2);
					}
				}
			}
		}
		return dp[1][dp.length - 1];
	}
}
