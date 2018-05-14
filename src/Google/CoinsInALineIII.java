package Google;


/*
 * 
 * Coins in a Line III

There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Example
Given array A = [3,2,2], return true.

Given array A = [1,2,4], return true.

Given array A = [1,20,4], return false.

Challenge
Follow Up Question:

If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?

https://www.youtube.com/watch?v=WxpIHvsu1RI
 */
public class CoinsInALineIII {
	static class Pair {
		int first;
		int second;
	}
	
	public static void main(String[] args) {
		int[] coins = {3, 2, 2, 4, 5, 1};
		boolean check = CoinsInALineIII(coins);
		if (check) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

	}
	

	public static boolean CoinsInALineIII(int[] coins) {
		int[][] dp = new int[coins.length][coins.length];
		int[] sum = new int[coins.length + 1];
		for (int i = 1; i <= coins.length; i++) {
			sum[i] = sum[i - 1] + coins[i - 1];
		}
		for (int j = 0; j < coins.length; j++) {
			for (int i = 0; i + j < coins.length; i++) {
				int x = i;
				int y = i + j;
				if (x == y) {
					dp[x][y] = sum[y + 1] - sum[x];
				} else if (x + 1 == y) {
					dp[x][y] = Math.max(dp[x + 1][y], dp[x][y - 1]);
				} else {
					int all = sum[y + 1] - sum[x];
					dp[x][y] = Math.max(all - dp[x + 1][y], all - dp[x][y - 1]);
					int left = all - dp[x + 1][y];
					int right = all - dp[x][y - 1];
				}
			}
		}
		return dp[0][dp.length - 1] > (sum[sum.length - 1] - dp[0][dp.length - 1]);
	}

}
