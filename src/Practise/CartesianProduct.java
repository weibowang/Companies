package Practise;
import java.util.*;
public class CartesianProduct {
	public static void main(String[] args) {
		Integer[] i1 = new Integer[] {1,2,3};
		Integer[] i2 = new Integer[] {2,3,4,5};
		Integer[] i3 = new Integer[] {6,7,8};
		List<Integer> a1 = new ArrayList<>(Arrays.asList(i1)); // good
		List<Integer> a2 = new ArrayList<>(Arrays.asList(i2));
		List<Integer> a3 = new ArrayList<>(Arrays.asList(i3));
		List<List<Integer>> sets = new LinkedList<List<Integer>>();
		sets.add(a1);
		sets.add(a2);
		sets.add(a3);
		List<List<Integer>> res = cartesianProduct2(sets);
		System.out.println(res.size());
		for (List<Integer> list : res) {
			System.out.println();
			for (int i : list) {
				System.out.print(i + " ");
			}
		}
	}
	
	public static List<List<Integer>> cartesianProduct(List<List<Integer>> sets) {
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		for (List<Integer> list : sets) {
			List<List<Integer>> newRes = new LinkedList<List<Integer>>();
			for (int val : list) {
				if (res.isEmpty()) {
					List<Integer> newList = new LinkedList<Integer>();
					newList.add(val);
					newRes.add(newList);
				} else {
					for (List<Integer> old : res) {
						List<Integer> newList = new LinkedList<Integer>(old);
						newList.add(val);
						newRes.add(newList);
					}
				}
			}
			res = newRes;
		}
		return res;
	}
	
	public static List<List<Integer>> cartesianProduct2(List<List<Integer>> sets) {
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		helper(res, sets);
		return res;
	}
	
	public static void helper(List<List<Integer>> res, List<List<Integer>> sets) {
		if (sets.size() == 0) {
			return;
		}
		List<Integer> set = sets.remove(0);
		List<List<Integer>> newRes = new LinkedList<List<Integer>>();
		if (res.isEmpty()) {
			for (int e : set) {
				List<Integer> newList = new LinkedList<Integer>();
				newList.add(e);
				newRes.add(newList);
			}
		} else {
			for (List<Integer> list : res) {
				for (int e : set) {
					List<Integer> newList = new LinkedList<Integer>(list);
					newList.add(e);
					newRes.add(newList);
				}
			}
		}
		res.clear();
		res.addAll(newRes);
		helper(res, sets);
	}
}
