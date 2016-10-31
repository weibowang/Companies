package Google;

import java.util.*;

/*
 * 第一题, 一个二维数组代表了一个岛. 周围都是海, 岛的左侧和上侧通向Pacific, 右侧和下侧通向Atlantic. 每个数字都代表了那个位置的海拔高度.
 *  现在下雨了, 雨只有从海拔高的地儿能流向海拔低或者一样的地儿.
 *  返回岛上的分水岭的点, 就是在某个/某些点上, 雨水既能流进Pacific, 又能流向Atlantic.
 *  
 *  
 *  我的方法也是DP+DFS,只扫一次. 我用了两个HashSet分别用于存放能够流入Pacific或Atlantic的点的坐标. Pacific和Atlantic两个情况在DFS里同时做.
在DFS的时候, 先分别recursion上下左右四个方向, 如果hashset里面记录过就直接返回结果(当然如果用boolean[] visited去记录当前点有没有被扫过肯定更好,
 这题元素太多, 当时时间实在不够了...). 然后判断当前点是否能流入Pacific或Atlantic, 如果能, 分别记录在Hashset里.
最后只要看有哪些点在两个HashSet里面都出现就好了.
所以每个点只用扫一次, 空间复杂度就是符合分别能够流入Pacific或Atlantic的坐标个数..

 *  
 */
public class PacificAtlantic {

	public static void main(String[] args) {
		int[][] matrix = {{2, 0, 4, 0, 9}, {4, 2, 3, 2, 0}, {0, 5, 0, 4, 2}, {2, 4, 5, 2, 3}, {2, 3, 0, 3, 0}};
		List<int[]> list = PacificAtlantic(matrix);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(Arrays.toString(list.get(i)));
		}
	}
	static int[] xOffset = {0, 0, -1, 1};
	static int[] yOffset = {-1, 1, 0, 0};
	public static List<int[]> PacificAtlantic(int[][] matrix) {
		List<int[]> result = new ArrayList<int[]>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		
		int[][] pacific = new int[matrix.length][matrix[0].length];
		int[][] atlantic = new int[matrix.length][matrix[0].length];
		//initialize 
		for (int i = 0; i < pacific.length; i++) {
			if (pacific[i][0] <= 0) {
				pacific[i][0] = 1;
				oceanDFS(matrix, pacific, i, 0);
			}
		}
		for (int i = 0; i < pacific[0].length; i++) {
			if (pacific[0][i] <= 0) {
				pacific[0][i] = 1;
				oceanDFS(matrix, pacific, 0, i);
			}
		}
		for (int i = atlantic.length - 1; i >= 0; i--) {
			if (atlantic[i][0] <= 0) {
				atlantic[i][0] = 1;
				oceanDFS(matrix, atlantic, i, 0);
			}
		}
		for (int i = atlantic[0].length - 1; i >= 0; i--) {
			if (atlantic[0][i] <= 0) {
				atlantic[0][i] = 1;
				oceanDFS(matrix, atlantic, 0, i);
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (pacific[i][j] == 1 && atlantic[i][j] == 1) {
					int[] array = new int[2];
					array[0] = i;
					array[1] = j;
					result.add(array);
				}
			}
		}
		return result;
	}
	
	private static void oceanDFS(int[][] matrix, int[][] ocean, int x, int y) {
		for (int i = 0; i < 4; i++) {
			int a = x + xOffset[i];
			int b = y + yOffset[i];
			if (a >= 0 && a < ocean.length && b >= 0 && b < ocean[0].length && ocean[a][b] == 0 && matrix[a][b] >= matrix[x][y]) {
				ocean[a][b] = 1;
				oceanDFS(matrix, ocean, a, b);
			}
		}
	}
}
