package Google;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=443906&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 */

public class BotLeftToBotRight {
	public static void main(String[] args) {
		int res1 = botLeftToBotRight(4, 5);
		System.out.println(res1);
	}
	
	public static int botLeftToBotRight(int m, int n) {
		int[][] dp = new int[m][n];
		for (int i = 0; i < Math.min(m, n); i++) {
			dp[m - 1 - i][i] = 1;
		}
		print(dp);
		System.out.println();
		for (int j = 0; j < n; j++) {
			for (int i = 1; m - i >= 0 && i + j < n; i++) {
				int x = m - i;
				int y = j + i;
				dp[x][y] = dp[x][y - 1];
				if (x - 1 >= 0 && y - 1 >= 0) {
					dp[x][y] += dp[x- 1][y - 1];
				}
				if (x + 1 < m && y - 1 >= 0) {
					dp[x][y] += dp[x + 1][y - 1];
				}
			}
		}
		
		print(dp);
		return dp[m - 1][n - 1];
	}
	
	public static void print(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println();
		}
	}
}
