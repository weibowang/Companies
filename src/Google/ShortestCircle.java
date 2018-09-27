package Google;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441435&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3087%255D%255Bvalue%255D%3D3%26searchoption%255B3087%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 * 
 */

public class ShortestCircle {
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		List<Integer> l3 = new ArrayList<Integer>();
		List<Integer> l4 = new ArrayList<Integer>();
		List<Integer> l5 = new ArrayList<Integer>();
		List<Integer> l6 = new ArrayList<Integer>();
		List<Integer> l7 = new ArrayList<Integer>();
		List<Integer> l8 = new ArrayList<Integer>();
		l1.add(2);
		l2.add(3);
		l2.add(4);
		l3.add(1);
		l4.add(5);
		l4.add(6);
		l4.add(7);
		l5.add(1);
		l6.add(2);
		l7.add(8);
		l8.add(1);
		HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		graph.put(1, l1);
		graph.put(2, l2);
		graph.put(3, l3);
		graph.put(4, l4);
		graph.put(5, l5);
		graph.put(6, l6);
		graph.put(7, l7);
		graph.put(8, l8);
		List<Integer> res1 = solution1(1, graph);
		for (Integer i : res1) {
			System.out.print(i + ", ");
		}
		System.out.println();
		List<Integer> res2 = solution2(1, graph);
		for (Integer i : res2) {
			System.out.print(i + ", ");
		}
	}
	
	public static List<Integer> solution1(int start, HashMap<Integer, List<Integer>> graph) {
		Queue<List<Integer>> q = new LinkedList<List<Integer>>();
		List<Integer> first = new ArrayList<Integer>();
		first.add(start);
		q.add(first);
		while (!q.isEmpty()) {
			List<Integer> cur = q.poll();
			int last = cur.get(cur.size() - 1);
			List<Integer> neighbors = graph.get(last);
			for (int n : neighbors) {
				if (n == start) {
					return cur;
				}
				List<Integer> newCur = new ArrayList<Integer>(cur);
				newCur.add(n);
				q.add(newCur);
			}
		}
		return null;
	}
	
	
	public static List<Integer> solution2(int start, HashMap<Integer, List<Integer>> graph) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		boolean find = false;
		while (!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> neighbors = graph.get(cur);
			for (int n : neighbors) {
				if (n == start) {
					find = true;
					map.put(start, cur);
					break;
				} else {
					q.add(n);
					if (!map.containsKey(n)) {
						map.put(n, cur);
					}
				}
			}
			if (find) {
				break;
			}
		}
		return findPath(map, start);
	}
	
	public static List<Integer> findPath(HashMap<Integer, Integer> map, int start) {
		List<Integer> res = new ArrayList<Integer>();
		int key = start;
		while (map.get(key) != start) {
			key = map.get(key);
			res.add(key);
		}
		return res;
	}
}
