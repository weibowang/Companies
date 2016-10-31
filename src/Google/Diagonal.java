package Google;

public class Diagonal {
	public static void main(String[] args) {
		int[][] matrix = {{3, 2, 3, 4}, {2, 5, 2, 7}, {3, 4, 7, 9}};
		printDiagonal(matrix);
		System.out.println("**************");
		printAntiDiagonal(matrix);
		System.out.println("**************");
		if (isSymmetric(matrix)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		System.out.println("**************");
		if (isSymmetric2(matrix)) {
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
			} else {
				aX = m - 1;
				aY = i - m + 1;
			}
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
	
	
	public static boolean isSymmetric2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return true;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int x = 0;
		int y = 0;
		int aX = 0;
		int aY = 0;
		int bX = 0;
		int bY = 0;
		int len = 0;
		int count = 0;
		while (true) {
			while (len < m + n - 1 - count) {
				//System.out.println("len " + len + " m " + m + " n " + n);
				if (matrix[aX][aY] != matrix[bX][bY]) {
					//System.out.println("aX " + aX + " aY " + aY + " bX " + bX + " bY " + bY);
					return false;
				}
				if (len < m) {
					aX = len;
					aY = count;
				} else {
					aX = m - 1;
					aY = len - m + 1;
				}
				if (len < n) {
					bX = count;
					bY = len;
				} else {
					bX = len - n + 1;
					bY = n - 1;
				}
				len++;
			}
			//System.out.println("round");
			count++;
			m--;
			n--;
			if (count >= m || count >= n) {
				break;
			}
			aX = count;
			aY = count;
			bX = count;
			bY = count;
			len = count;
		}
		return true;
	}
}
