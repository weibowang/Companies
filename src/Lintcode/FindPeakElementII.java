package Lintcode;

import java.util.*;

public class FindPeakElementII {
	public static void main(String[] args) {
		int[] a1 = {1 ,2 ,3 ,6 ,5};
		int[] a2 = {16,41,23,22,6};
		int[] a3 = {15,17,24,21,7};
		int[] a4 = {14,18,19,20,10};
		int[] a5 = {13,14,11,10,9};
		int[][] matrix = new int[5][5];
		matrix[0] = a1;
		matrix[1] = a2;
		matrix[2] = a3;
		matrix[3] = a4;
		matrix[4] = a5;
		List<Integer> res = findPeakElementII(matrix);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}
	
	public static List<Integer> findPeakElementII(int[][] matrix) {
		List<Integer> res = new LinkedList<Integer>();
		int left = 1;
		int right = matrix[0].length - 1;
		int top = 1;
		int bot = matrix.length - 1;
		int k = 0;
		while (left < right && top < bot) {
			int index = 0;
			int max = Integer.MIN_VALUE;
			if (k % 2 == 0) {
				int mid = top + (bot - top) / 2;
				for (int i = 1; i < matrix[0].length - 1; i++) {
					if (matrix[mid][i] > matrix[mid - 1][i] && matrix[mid][i] > matrix[mid][i - 1] && matrix[mid][i] > matrix[mid + 1][i] && matrix[mid][i] > matrix[mid][i + 1]) {
						res.add(mid);
						res.add(i);
						return res;
					}
					if (matrix[mid][i] > max) {
						max = matrix[mid][i];
						index = i;
					}
				}
				if (matrix[mid][index] <= matrix[mid + 1][index]) {
					top = mid + 1;
				} else {
					bot = mid - 1;
				}
			} else {
				int mid = left + (right - left) / 2;
				for (int i = 1; i < matrix.length - 1; i++) {
					if (matrix[i][mid] > matrix[i][mid - 1] && matrix[i][mid] > matrix[i - 1][mid] && matrix[i][mid] > matrix[i][mid + 1] && matrix[i][mid] > matrix[i + 1][mid]) {
						res.add(i);
						res.add(mid);
						return res;
					}
					if (matrix[i][mid] > max) {
						max = matrix[i][mid];
						index = i;
					}
				}
				if (matrix[index][mid] <= matrix[index][mid + 1]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			k++;
		}
		return res;
	}
}
