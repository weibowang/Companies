package Google;

import java.util.*;
/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=440401&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 * https://www.careercup.com/question?id=4681357536002048
 * 
 * https://blog.csdn.net/lightty/article/details/12616677
 */

public class CreateMaze {
	
	static class UndirectedGraphNode {
		int val;
		HashSet<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int val) {
			this.val = val;
			neighbors = new HashSet<UndirectedGraphNode>();
		}
	}
	public static void main(String[] args) {
		UndirectedGraphNode node = solution1();
		HashSet<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
		dfsPrint(node, set);
		
		int[][] maze = solution2();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + ",");
			}
			System.out.println();
		}
	}
	
	public static void dfsPrint(UndirectedGraphNode node, HashSet<UndirectedGraphNode> set) {
		if (set.contains(node)) {
			return;
		}
		set.add(node);
		System.out.println("# " + node.val);
		for (UndirectedGraphNode n : node.neighbors) {
			System.out.print("." + n.val);
		}
		System.out.println();
		for (UndirectedGraphNode n : node.neighbors) {
			dfsPrint(n, set);
		}
	}
	
	static int increase;
	static List<UndirectedGraphNode> list = new ArrayList<UndirectedGraphNode>();
	public static UndirectedGraphNode solution1() {
		UndirectedGraphNode node = new UndirectedGraphNode(increase);
		increase++;
		Random rand = new Random();
		int rHalf = rand.nextInt(1);
		if (rHalf < 1 && list.size() > 0) {
			int index = rand.nextInt(list.size());
			UndirectedGraphNode prev = list.get(index);
			node.neighbors.add(prev);
			prev.neighbors.add(node);
		}
		list.add(node);
		for (int i = 0; i < 4; i++) {
			int r = rand.nextInt(5);
			System.out.println(r);
			if (r <= 1) {
				UndirectedGraphNode neighbor = solution1();
				node.neighbors.add(neighbor);
				neighbor.neighbors.add(node);
			}
		}
		return node;
	}
	
	
	public static int[][] solution2() {
		int[][] maze = new int[10][10];
		plumb(maze, 3, 3);
		return maze;
	}
	
	public static void plumb(int[][] maze, int i, int j) {
		maze[i][j] = 1;
		int[] xOffset = {-1, 1, 0, 0};
		int[] yOffset = {0, 0, -1, 1};
		for (int k = 0; k < 4; k++) {
			int x = i + xOffset[k];
			int y = j + yOffset[k];
			Random rand = new Random();
			int r = rand.nextInt(3);
			if (r < 1 && x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 1) {
				plumb(maze, x, y);
			}
		}
	}
}
