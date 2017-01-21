package Google;

import java.util.*;

/*
 * 
 * http://www.1point3acres.com/bbs/thread-182425-1-1.html
 * 5. ӡ��С� ͬʱ�ο��ϵ�ͼ
���������⣬ ֮ǰ�澭����������� ����û�о���������� �ͷŹ��ˡ� ����������ı������ġ�������������������������ϸ������
input: 
a. ��n�����У� ÿ������֮���з���ʱ�䣬 
b. ��������ʱ�䣬 .
c. ����vacation array, ����ÿ������ÿ�ܵļ��ڡ�
d. �ӵ�һ�����п�ʼ 

��˼����ÿ��������Դ���һ�����У� Ȼ�������Ǹ����еļ��ڡ� 
���и����ƣ� ���ǳ��������֮��ķ���ʱ�䲻�ܳ��������ķ���ʱ��
output: . 
��x weeks �������ܵ����������ܺ�
���Լ������������ݽṹ

������������ĸ�׵ķ����� �ֲ���û������ϸ��˵���� 

�ҵĴ���뷨�� 
1. ȥ����Щ����ʱ�䳬����������ʱ��ıߡ� 
2. ��adjancey list���ģ� . 
3. Ȼ��bfs, ������⡣ 
4. ûд�ꡣ����������


��ͼ��ʾ�� ����Ӧ�� 
week1, A, sum=2; 
week2, B/C, sum=sum+1; 
week3, �ص�A, sum+=3.
total sum =6 


DP ���̴���� dp(i)(j) = Math.max(dp(i-1)(fromCity)+map(i)(j), dp(i)(j)


dp[i][j]    i = week  j = the max weeks to stay at j city at i week
 */

public class CityVacation {
	
	public static void main(String[] args) {
		int[][] edges = new int[4][4];
		int[][] matrix = new int[3][4];
		int len = 10;
		constructEdges(edges);
		constructMatrix(matrix);
		int result = CityVacation(matrix, edges, len);
		System.out.println(result);
		int result2 = CityVacation2(matrix, edges, len);
		System.out.println(result2);
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
	
	// same solution as first one
	public static int CityVacation2(int[][] matrix, int[][] edges, int len) {
		int[][] dp = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix[0].length; i++) {
			dp[0][i] = matrix[0][i];
		}
		HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[0].length; j++) {
				if (edges[i][j] != 0 && edges[i][j] <= len) {
					if (map.containsKey(i)) {
						map.get(i).add(j);
					} else {
						HashSet<Integer> set = new HashSet<Integer>();
						set.add(j);
						set.add(i);
						map.put(i, set);
					}
					if (map.containsKey(j)) {
						map.get(j).add(i);
					} else {
						HashSet<Integer> set = new HashSet<Integer>();
						set.add(i);
						set.add(j);
						map.put(j, set);
					}
				}
			}
		}
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = matrix[0][i];
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (map.containsKey(j)) {
					HashSet<Integer> set = map.get(j);
					for (int x : set) {
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][x] + matrix[i][j]);
					}
				}
			}
		}
		int max = 0;
		for (int i = 0; i < dp[0].length; i++) {
			max = Math.max(dp[dp.length - 1][i], max);
		}
		return max;
	}

}
