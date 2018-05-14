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
	
	public static class PointComparator implements Comparator<Point> {
		public Point p;
		public PointComparator(Point p) {
			this.p = p;
		}
		public int compare(Point left, Point right) {
			int l = Math.abs(left.x - p.x) + Math.abs(left.y - p.y);
			int r = Math.abs(right.x - p.x) + Math.abs(right.y - p.y);
			return l - r;
		}
	}
	public static void main(String[] args) {
		Point center = new Point(0, 0);
		int k = 3;
		Point p1 = new Point(1, 8);
		Point p2 = new Point(2, 6);
		Point p3 = new Point(8, 9);
		Point p4 = new Point(5, 3);
		Point p5 = new Point(2, 7);
		Point p6 = new Point(3, 5);
		Point[] p = new Point[6];
		p[0] = p1;
		p[1] = p2;
		p[2] = p3;
		p[3] = p4;
		p[4] = p5;
		p[5] = p6;
		List<Point> res = solution1(p, center, k);
		for (int i = 0; i < res.size(); i++) {
			Point point = res.get(i);
			System.out.println(point.x + " " + point.y); 
		}
		System.out.println("**********************");
		List<Point> res2 = solution2(p, center, k);
		for (int i = 0; i < res2.size(); i++) {
			Point point = res2.get(i);
			System.out.println(point.x + " " + point.y); 
		}
		System.out.println("**********************");
		List<Point> res3 = solution3(p, center, k);
		for (int i = 0; i < res3.size(); i++) {
			Point point = res3.get(i);
			System.out.println(point.x + " " + point.y); 
		}
	}
	
	//O(nlogn)
	public static List<Point> solution3(Point[] p, Point center, int k) {
		List<Point> res = new LinkedList<Point>();
		PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new PointComparator(center));
		for (int i = 0; i < p.length; i++) {
			Point point = p[i];
			if (i < k) {
				pq.add(point);
			} else {
				int diff = Math.abs(point.x - center.x) + Math.abs(point.y - center.y);
				int now = Math.abs(pq.peek().x - center.x) + Math.abs(pq.peek().y - center.y);
				if (diff < now) {
					pq.poll();
					pq.add(point);
				}
			}
		}
		for (int i = 0; i < k; i++) {
			res.add(pq.poll());
		}
		return res;
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
