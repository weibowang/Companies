package Facebook;

import java.util.*;

/*
 * https://shepherdyuan.wordpress.com/2014/07/23/linkedin-k-closest-points/
 * https://discuss.leetcode.com/topic/101/k-closest-points-to-a-starting-point-in-cartesian-plane
 * 
 * 
 */
public class FindKClosest {
	public static class Point {
	    int x;
	    int y;
	    public Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
	public static class TypeOne {
	    Point point;
	    int distant;
	    public TypeOne(Point point, int distant) {
	        this.distant = distant;
	        this.point = point;
	    }
	}
	public static void main(String[] args) {
		Point center = new Point(2, 2);
		int k = 2;
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 3);
		Point p3 = new Point(4, 6);
		Point p4 = new Point(7, 9);
		Point[] p = new Point[4];
		p[0] = p4;
		p[1] = p3;
		p[2] = p2;
		p[3] = p1;
		List<Point> res = solution1(p, center, k);
		for (int i = 0; i < res.size(); i++) {
			Point point = res.get(i);
			System.out.println(point.x + " " + point.y); 
		}
	}
	
	//O(nlogn)
	public static List<Point> solution2(Point[] p, Point center, int k) {
		List<Point> res = new LinkedList<Point>();
		PriorityQueue<TypeOne> pq = new PriorityQueue<TypeOne>(k, new Comparator<TypeOne>() {
			public int compare(TypeOne left, TypeOne right) {
				return left.distant - right.distant;
			}
		});
		for (int i = 0; i < p.length; i++) {
			Point point = p[i];
			int distant = Math.abs(center.x - point.x) + Math.abs(center.y - point.y);
			TypeOne t = new TypeOne(point, distant);
			pq.add(t);
		}
		for (int i = 0; i < k; i++) {
			res.add(pq.poll().point);
		}
		return res;
	}
	
	
	//O(n)
	public static List<Point> solution1(Point[] p, Point center, int k) {
		List<Point> res = new LinkedList<Point>();
		partition(p, 0, p.length - 1, k, center);
		for (int i = 0; i < k; i++) {
			res.add(p[i]);
		}
		return res;
	}
	
	public static void partition(Point[] p, int start, int end, int k, Point center) {
		//System.out.println(start + " " + end);
		if (start >= end) {
			return;
		}
		int left = start;
		int right = end;
		Point point = p[start];
		int pivot = Math.abs(center.x - point.x) + Math.abs(center.y - point.y);
		while (left < right) {
			while (left < right && Math.abs(center.x - p[right].x) + Math.abs(center.y - p[right].y) >= pivot) {
				right--;
			}
			p[left] = p[right];
			while (left < right && Math.abs(center.x - p[left].x) + Math.abs(center.y - p[left].y) <= pivot) {
				left++;
			}
			p[right] = p[left];
		}
		p[left] = point;
		if (left + 1 == k) {
			return;
		}
		if (left + 1 < k) {
			partition(p, left + 1, end, k, center);
		}
		if (left + 1 > k) {
			partition(p, start, left - 1, k, center);
		}
	}
}
