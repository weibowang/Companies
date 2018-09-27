package Google;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=443906&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 * 
这样子行吗
i表示当前位置，j表示颜色，0是白色，1是黑色 
初始化dp[0][0] = 0，dp[1][0] = 1, dp[2][0] = 1, dp[0][1] = 0, dp[1][1] = 0, dp[2][1] = 1
dp[i][0] = dp[i - 1][1] + dp[i - 2][1] + dp[i - 3][1].留学论坛-一亩-三分地
dp[i][1] = dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0]. 1point 3acres 论坛
最后返回dp[n][0]
 * 
 */

public class BarCode {
	public static void main(String[] args) {
		int n = 15;
		int res1 = solution1(n);
		System.out.println(res1);
		int res2 = solution2(n);
		System.out.println(res2);
	}
	
	public static int solution1(int n) {
		int[][] dp = new int[n + 1][2];
		dp[0][0] = 0;
		dp[1][0] = 1;
		dp[2][0] = 1;
		dp[0][1] = 0;
		dp[1][1] = 0;
		dp[2][1] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i][0] = dp[i - 1][1] + dp[i - 2][1] + dp[i - 3][1];
			dp[i][1] = dp[i - 1][0] + dp[i - 2][1] + dp[i - 3][1];
		}
		return dp[n][0];
	}
	
	public static int solution2(int n) {
		int[] array = new int[n];
		List<int[]> res = new ArrayList<int[]>();
		helper(res, array, 1);
		return res.size();
	}
	
	public static void helper(List<int[]> res, int[] array, int i) {
		if (i == array.length - 1) {
			int[] tmp = new int[array.length];
			System.arraycopy(array, 0, tmp, 0, array.length);
			res.add(tmp);
			return;
		}
		if (i - 1 >= 0 && array[i - 1] == 0 || (i - 2 >= 0 && array[i - 2] == 0) || (i - 3 >= 0 && array[i - 3] == 0)) {
			array[i] = 1;
			helper(res, array, i + 1);
		}
		if (i - 1 >= 1 && array[i - 1] == 1 || (i - 2 >= 0 && array[i - 2] == 1) || (i - 3 >= 0 && array[i - 3] == 1)) {
			array[i] = 0;
			helper(res, array, i + 1);
		}
	}
}
