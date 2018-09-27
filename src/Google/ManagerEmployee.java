package Google;

import java.util.*;


public class ManagerEmployee {
	static HashMap<Integer, Integer> fathers = new HashMap<Integer, Integer>();
	static HashSet<Integer> managers = new HashSet<Integer>();
	static HashSet<Integer> employees = new HashSet<Integer>();
	public static void main(String[] args) {
		addManager(1, 3);
		addPeer(1, 2);
		System.out.println("1->3 " + isManager(1, 3));
		System.out.println("2->3 " + isManager(2, 3));
		addPeer(4, 5);
		addPeer(5, 6);
		addManager(4, 7);
		System.out.println("4->7 " + isManager(4, 7));
		System.out.println("5->7 " + isManager(5, 7));
		System.out.println("6->7 " + isManager(6, 7));
		addPeer(6, 8);
		System.out.println("8->7 " + isManager(8, 7));
	}
	
	public static void addPeer(int a, int b) {
		int f1 = find(a);
		int f2 = find(b);
		if (f1 != f2) {
			if (isManager(a, f1)) {
				fathers.put(b, f1);
			} else if (isManager(b, f2)) {
				fathers.put(a, f2);
			} else {
				fathers.put(a, f2);
			}
		}
		employees.add(a);
		employees.add(b);
	}
	
	public static void addManager(int a, int m) {
		int f = find(a);
		fathers.put(f, m);
		employees.add(a);
		managers.add(m);
	}
	
	public static boolean isManager(int a, int m) {
		int f = find(a);
		return managers.contains(m) && f == m;
	}
	
	public static int find(int x) {
		if (!fathers.containsKey(x) || fathers.get(x) == x) {
			fathers.put(x, x);
			return x;
		}
		int val = find(fathers.get(x));
		fathers.put(x, val);
		return val;
	}
}
