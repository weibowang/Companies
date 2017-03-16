package Facebook;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/thread-193898-1-1.html
 */

public class MedianOfKSortedArray {
	static class Pair implements Comparable<Pair> {
		int x;
		int y;
		int val;
		public Pair(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
		
		public int compareTo(Pair right) {
			return this.val - right.val;
		}
	}
	
	
	
	public static void main(String[] args) {
		int[] nums1 = {1, 3, 6, 7, 9};
		int[] nums2 = {2, 4, 8};
		int[] nums3 = {5};
		int[][] matrix = new int[3][];
		matrix[0] = nums1;
		matrix[1] = nums2;
		matrix[2] = nums3;
		System.out.println(MedianOfKSortedArray(matrix));
	}
	public static double MedianOfKSortedArray(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return -1;
		}
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			count += matrix[i].length;
		}
		int k = count / 2;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		for (int i = 0; i < matrix.length; i++) {
			Pair p = new Pair(i, 0, matrix[i][0]);
			pq.add(p);
		}
		int first = 0;
		int second = 0;
		for (int i = 0; i <= k; i++) {
			System.out.println(i + " " + k);
			Pair p = pq.poll();
			if (count % 2 == 1 && i == k) {
				//System.out.println("aa");
				return p.val;
			}
			if (count % 2 == 0 && i == k - 1) {
				first = p.val;
			}
			if (count % 2 == 0 && i == k) {
				second = p.val;
				return (first + second) / 2.0;
			}
			int x = p.x;
			int y = p.y;
			if (p.y < matrix[x].length - 1) {
				Pair cur = new Pair(x, y + 1, matrix[x][y + 1]);
				pq.add(cur);
			}
		}
		return -1;
	}
}
