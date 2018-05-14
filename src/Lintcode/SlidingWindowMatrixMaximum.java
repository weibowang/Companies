package Lintcode;

import java.util.*;

/*
 * https://zhengyang2015.gitbooks.io/lintcode/sliding_window_matrix_maximum_558.html
 */

public class SlidingWindowMatrixMaximum {
	public static void main(String[] args) {
		int[] a1 = {1, 5, 3};
		int[] a2 = {3, 2, 1};
		int[] a3 = {4, 1, 9};
		int[][] matrix = new int[3][3];
		int k = 2;
		matrix[0] = a1;
		matrix[1] = a2;
		matrix[2] = a3;
		int max = slidingWindowMatrixMaximum(matrix, k);
		System.out.println(max);
	}
	
	public static int slidingWindowMatrixMaximum(int[][] matrix, int k) {
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
		for (int i = 1; i <= matrix.length; i++) {
			for (int j = 1; j <= matrix[0].length; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + matrix[i - 1][j - 1] - dp[i - 1][j - 1];
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = k; i <= matrix.length; i++) {
			for (int j = k; j <= matrix[0].length; j++) {
				int area = dp[i][j] - dp[i - k][j] - dp[i][j - k] + dp[i - k][j - k];
				max = Math.max(max, area);
			}
		}
		return max;
	}
}
