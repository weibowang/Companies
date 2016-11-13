package Google;

/*
 * 1. there are m piles of coins, each pile is consisted of coins with different values. you are allowed to take n coins home.
 *  However, you can only take coins from the top of each pile. What's the maximum value you can obtain..
 */
public class PilesOfCoins {
	public static void main(String[] args) {
		int[][] coins = {{1, 7}, {4, 3, 2},{6, 1}};
		int n = 2;
		System.out.println(PilesOfCoins(coins, n));
		System.out.println(PilesOfCoinsRecursion(coins, n));
	}
	
	// dp
	public static int PilesOfCoins(int[][] coins, int n) {
		if (n <= 0 || coins == null || coins.length == 0) {
			return 0;
		}
		int[][] dp = new int[coins.length + 1][n + 1];
		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= n; j++) {
				int max = dp[i - 1][j];
				int sum = 0;
				for (int k = 0; k < coins[i - 1].length; k++) {
					if (k >= j) {
						break;
					}
					sum += coins[i - 1][k];
					max = Math.max(max, dp[i - 1][j - k - 1] + sum);
				}
				dp[i][j] = max;
			}
		}
		return dp[coins.length][n];
	}
	
	
	// recursion
	public static int PilesOfCoinsRecursion(int[][] coins, int n) {
		if (n <= 0 || coins == null || coins.length == 0) {
			return 0;
		}
		int[] record = new int[coins.length];
		return helper(coins, record, n);
	}
	
	private static int helper(int[][] coins, int[] record, int n) {
		if (n <= 0) {
			return 0;
		}
		int max = 0;
		for (int i = 0; i < coins.length; i++) {
			if (record[i] >= coins[i].length) {
				continue;
			} else {
				int val = coins[i][record[i]];
				record[i]++;
				max = Math.max(max, val + helper(coins, record, n - 1));
				record[i]--;
			}
		}
		return max;
	}
}
