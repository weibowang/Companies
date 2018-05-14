package Lintcode;
import java.util.*;
/*
 * https://segmentfault.com/a/1190000006970751
 */
public class BuildPostOfficeII {
	public static void main(String[] args) {
		int[] a1 = {0, 1, 0, 0, 0};
		int[] a2 = {1, 0, 0, -1, 1};
		int[] a3 = {0, 1, 0, 0, 0};
		int[][] matrix = new int[5][5];
		matrix[0] = a1;
		matrix[1] = a2;
		matrix[2] = a3;
		int res = buildPostOfficeII(matrix);
		System.out.println(res);
	}
	static class TypeOne {
		int x;
		int y;
		int val;
		TypeOne(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	public static int buildPostOfficeII(int[][] matrix) {
		List<TypeOne> list = new LinkedList<TypeOne>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					matrix[i][j] = Integer.MIN_VALUE;
					TypeOne t = new TypeOne(i, j, matrix[i][j]);
					list.add(t);
				}
			}
		}
		
		int[][] counts = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != -1 && matrix[i][j] != Integer.MIN_VALUE) {
					counts[i][j] = list.size();
				}
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			int[][] check = new int[matrix.length][matrix[0].length];
			check[list.get(i).x][list.get(i).y] = Integer.MIN_VALUE;
			Queue<TypeOne> q = new LinkedList<TypeOne>();
			q.add(list.get(i));
			int level = 1;
			while (!q.isEmpty()) {
				int size = q.size();
				for (int j = 0; j < size; j++) {
					TypeOne t = q.poll();
					int[] xOffset = {0, 0, -1, 1};
					int[] yOffset = {-1, 1, 0, 0};
					for (int k = 0; k < 4; k++) {
						int x = t.x + xOffset[k];
						int y = t.y + yOffset[k];
						if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] != -1
								&& check[x][y] != Integer.MIN_VALUE && matrix[x][y] != Integer.MIN_VALUE) {
							matrix[x][y] += level;
							check[x][y] = Integer.MIN_VALUE;
							counts[x][y]--;
							q.add(new TypeOne(x, y, check[x][y]));
						}
					}
				}
				level++;
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != -1 && matrix[i][j] != Integer.MIN_VALUE && counts[i][i] == 0) {
					min = Math.min(min, matrix[i][j]);
				}
			}
		}
		return min;
	}
}
