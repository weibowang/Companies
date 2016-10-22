package Google;

public class Diagonal {
	public static void main(String[] args) {
		int[][] matrix = {{0, 2, 3, 4}, {2, 5, 87, 9}, {3, 4, 9, 9}};
		printDiagonal(matrix);
		System.out.println("**************");
		printAntiDiagonal(matrix);
		System.out.println("**************");
		if (isSymmetric(matrix)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	
	public static void printDiagonal(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int x = 0;
		int y = 0;
		for (int i = 0; i < m + n - 1; i++) {
			if (i < m) {
				x = i;
				y = 0;
				
			} else {
				x = m - 1;
				y = i - m + 1;
			}
			while (x >= 0 && y < n) {
				System.out.print(matrix[x][y] + " ");
				x--;
				y++;
			}
			System.out.println();
		}
	}
	
	public static void printAntiDiagonal(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int x = 0;
		int y = 0;
		for (int i = 0; i < m + n - 1; i++) {
			if (i < n) {
				x = 0;
				y = i;
			} else {
				x = i - n + 1;
				y = n - 1;
			}
			while (x < m && y >= 0) {
				System.out.print(matrix[x][y] + " ");
				x++;
				y--;
			}
			System.out.println();
		}
	}
	
	public static boolean isSymmetric(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return true;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int aX = 0;
		int aY = 0;
		int bX = 0;
		int bY = 0;
		for (int i = 0; i < m + n - 1; i++) {
			if (i < m) {
				aX = i;
				aY = 0;
				if (i < n) {
					bX = 0;
					bY = i;
				} else {
					bX = i - n + 1;
					bY = n - 1;
				}
				if (!check(matrix, aX, aY, bX, bY)) {
					return false;
				}
			} else {
				aX = m - 1;
				aY = i - m + 1;
				if (i < n) {
					bX = 0;
					bY = i;
				} else {
					bX = i - n + 1;
					bY = n - 1;
				}
				if (!check(matrix, aX, aY, bX, bY)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean check(int[][] matrix, int aX, int aY, int bX, int bY) {
		while (aX > bX && aY < bY) {
			if (matrix[aX][aY] != matrix[bX][bY]) {
				return false;
			}
			aX--;
			aY++;
			bX++;
			bY--;
		}
		return true;
	}
}
