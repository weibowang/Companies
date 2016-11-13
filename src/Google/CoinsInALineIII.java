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
		int[] coins = {3, 9, 1, 2};
		boolean check = CoinsInALineIII(coins);
		if (check) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	
	public static boolean CoinsInALineIII(int[] coins) {
		if (coins == null || coins.length == 0) {
			return false;
		}
		
		Pair[][] dp = new Pair[coins.length][coins.length];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				dp[i][j] = new Pair();
			}
		}
		for (int i = 0; i < coins.length; i++) {
			dp[i][i].first = coins[i];
		}
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = i + 1; j < dp[i].length; j++) {
				//dp[i][j].first = Math.max(coins[i] + dp[i + 1][j].second, coins[j] + dp[i][j - 1].second);
				if (coins[i] + dp[i + 1][j].second > coins[j] + dp[i][j - 1].second) {
					dp[i][j].first = coins[i] + dp[i + 1][j].second;
					dp[i][j].second = dp[i + 1][j].first;
				} else {
					dp[i][j].first = coins[j] + dp[i][j - 1].second;
					dp[i][j].second = dp[i][j - 1].first;
				}
			}
		}
		System.out.println(dp[0][coins.length - 1].first + " " + dp[0][coins.length - 1].second);
		return dp[0][coins.length - 1].first > dp[0][coins.length - 1].second;
	}
}
