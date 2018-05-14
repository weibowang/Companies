package Lintcode;

import java.util.*;
/*
 * https://segmentfault.com/a/1190000006970751
 */

public class BuildPostOffice {
	public static void main(String[] args) {
		int[] a1 = {0, 1, 0, 0};
		int[] a2 = {1, 0, 1, 1};
		int[] a3 = {0, 1, 0, 0};
		int[][] matrix = new int[3][3];
		matrix[0] = a1;
		matrix[1] = a2;
		matrix[2] = a3;
		int res = buildPostOffice(matrix);
		System.out.println(res);
		int res2 = shortestDistance(matrix);
		System.out.println(res2);
	}
	
	public static int buildPostOffice(int[][] matrix) {
		ArrayList<Integer> xList = new ArrayList<Integer>();
		ArrayList<Integer> yList = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					xList.add(i + 1);
					yList.add(j + 1);
				}
			}
		}
		Collections.sort(xList);
		Collections.sort(yList);
		int[] xSum = new int[xList.size() + 1];
		int[] ySum = new int[yList.size() + 1];
		for (int i = 1; i < xSum.length; i++) {
			xSum[i] = xSum[i - 1] + xList.get(i - 1);
			ySum[i] = ySum[i - 1] + yList.get(i - 1);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					int x = find(xList, i + 1);
					int y = find(yList, j + 1);
					
					int xVal = x * (i + 1) - xSum[x] + xSum[xSum.length - 1] - xSum[x] - (xSum.length - x - 1) * (i + 1);
					int yVal = y * (j + 1) - ySum[y] + ySum[ySum.length - 1] - ySum[y] - (ySum.length - y - 1) * (j + 1);
					int sum = xVal + yVal;
					min = Math.min(sum, min);
				}
			}
		}
		return min;
	}
	
	public static int find(ArrayList<Integer> list, int target) {
		if (list.size() == 0) {
			return 0;
		}
		if (list.size() == 1) {
			if (list.get(0) <= target) {
				return 1;
			}
			return 0;
		}
		int left = 0;
		int right = list.size() - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			int val = list.get(mid);
			if (val <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if (list.get(right) <= target) {
			return right + 1;
		}
		if (list.get(left) <= target) {
			return left + 1;
		}
		return 0;
	}
	
	
	
	// jiuzhang's solution
	public static int shortestDistance(int[][] grid) {
        // Write your code here
        int n = grid.length;
        if (n == 0)
            return -1;

        int m = grid[0].length;
        if (m == 0)
            return -1;

        List<Integer> sumx = new ArrayList<Integer>();
        List<Integer> sumy = new ArrayList<Integer>();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
        
        Collections.sort(x);
        Collections.sort(y);

        int total = x.size();

        sumx.add(0);
        sumy.add(0);
        for (int i = 1; i <= total; ++i) {
            sumx.add(sumx.get(i-1) + x.get(i-1));
            sumy.add(sumy.get(i-1) + y.get(i-1));
        }
        
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (grid[i][j] == 0) {
                    int cost_x = get_cost(x, sumx, i, total);
                    int cost_y = get_cost(y, sumy, j, total);
                    if (cost_x + cost_y < result)
                        result = cost_x + cost_y;
                }

        return result;
    }

    public static int get_cost(List<Integer> x, List<Integer> sum, int pos, int n) {
        if (n == 0)
            return 0;
        if (x.get(0) > pos)
            return sum.get(n) - pos * n;

        int l = 0, r = n - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (x.get(mid) <= pos)
                l = mid;
            else
                r = mid - 1;
        }
        
        int index = 0;
        if (x.get(r) <= pos)
            index = r;
        else
            index = l;
        return sum.get(n) - sum.get(index + 1) - pos * (n - index - 1) + 
               (index + 1) * pos - sum.get(index + 1);
    }
}
