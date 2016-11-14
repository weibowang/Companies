package Google;

import java.util.*;

/*
 * 
 * http://www.1point3acres.com/bbs/thread-182425-1-1.html
 * 5. 印度小伙， 同时参看上的图
最大假期问题， 之前面经看到过这个， 但是没有具体的描述， 就放过了。 结果就命背的被考到的。。。。。。。。。。我来详细描述下
input: 
a. 有n个城市， 每个城市之间有飞行时间， 
b. 给个飞行时间， .
c. 给个vacation array, 代表每个城市每周的假期。
d. 从第一个城市开始 

意思就是每个周你可以呆在一个城市， 然后享受那个城市的假期。 
还有个限制， 就是城市与城市之间的飞行时间不能超过给定的飞行时间
output: . 
求x weeks 你能享受到的最大假期总和
你自己设计输入的数据结构

描述起来真他母亲的繁琐， 怪不得没看到详细的说明。 

我的大概想法： 
1. 去掉那些飞行时间超过给定飞行时间的边。 
2. 用adjancey list做的， . 
3. 然后bfs, 暴力求解。 
4. 没写完。。。。。。


如图所示， 最大的应该 
week1, A, sum=2; 
week2, B/C, sum=sum+1; 
week3, 回到A, sum+=3.
total sum =6 


DP 方程大概是 dp(i)(j) = Math.max(dp(i-1)(fromCity)+map(i)(j), dp(i)(j)


dp[i][j]    i = week  j = the max weeks to stay at j city at i week
 */

public class CityVacation {
	
	public static void main(String[] args) {
		int[][] edges = new int[4][4];
		int[][] matrix = new int[3][4];
		constructEdges(edges);
		constructMatrix(matrix);
		int result = CityVacation(matrix, edges, 10);
		System.out.println(result);
	}
	
	public static void constructEdges(int[][] edges) {
		edges[0][1] = 6;
		edges[0][2] = 2;
		edges[0][3] = 50;
		edges[1][2] = 20;
		edges[1][3] = 7;
		edges[2][3] = 10;
	}
	
	public static void constructMatrix(int[][] matrix) {
		matrix[0][0] = 2;
		matrix[0][1] = 1;
		matrix[0][2] = 1;
		matrix[0][3] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = 1;
		matrix[1][2] = 1;
		matrix[1][3] = 0;
		matrix[2][0] = 3;
		matrix[2][1] = 0;
		matrix[2][2] = 1;
		matrix[2][3] = 2;
	}
	
	public static int CityVacation(int[][] matrix, int[][] edges, int len) {
		int[] rows = new int[matrix[0].length];
		HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				if (edges[i][j] <= len && edges[i][j] != 0) {
					//System.out.println(i + " " + j + " " + len + " " + edges[i][j]);
					if (map.containsKey(i)) {
						map.get(i).add(j);
					} else {
						HashSet<Integer> set = new HashSet<Integer>();
						set.add(i);
						set.add(j);
						map.put(i, set);
					}
					if (map.containsKey(j)) {
						map.get(j).add(i);
					} else {
						HashSet<Integer> set = new HashSet<Integer>();
						set.add(j);
						set.add(i);
						map.put(j, set);
					}
				}
			}
		}
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				dp[i][j] = dp[i - 1][j];
				HashSet<Integer> set = map.get(j - 1);
				if (set == null || set.isEmpty()) {
					continue;
				}
				for (int n : set) {
					if (matrix[i - 1][j - 1] + dp[i - 1][n + 1] > dp[i][j]) {
						//System.out.println(i + " " + n + " " + j + " " + dp[i - 1][n + 1] + " " + matrix[i - 1][j - 1]);
						dp[i][j] = matrix[i - 1][j - 1] + dp[i - 1][n + 1];
						//rows[i - 1] = k; 
					}
				}
				//System.out.println(dp[i][j]);
			}
		}
		
		int max = 0;
		for (int i = 1; i < dp[0].length; i++) {
			max = Math.max(max, dp[dp.length - 1][i]);
		}
		return max;
	}
}
