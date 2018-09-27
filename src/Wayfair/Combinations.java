package Wayfair;

import java.util.*;

public class Combinations {
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("aaa");
		list1.add("bbb");
		list1.add("ccc");
		List<String> list2 = new ArrayList<String>();
		list2.add("11");
		list2.add("22");
		List<List<String>> input = new ArrayList<List<String>>();
		input.add(list1);
		input.add(list2);
		List<List<String>> res = solution1(input);
		for (List<String> list : res) {
			for (String s : list) {
				System.out.print(s + ",");
			}
			System.out.println();
		}
		
		List<List<String>> res2 = solution2(input);
		for (List<String> list : res2) {
			for (String s : list) {
				System.out.print(s + ",");
			}
			System.out.println();
		}
	}
	
	public static List<List<String>> solution1(List<List<String>> input) {
		List<List<String>> res = new ArrayList<List<String>>();
		List<String> first = new ArrayList<String>();
		res.add(first);
		for (List<String> list : input) {
			List<List<String>> newRes = new ArrayList<List<String>>();
			for (String s : list) {
				for (List<String> l : res) {
					List<String> newList = new ArrayList<String>(l);
					newList.add(s);
					newRes.add(newList);
				}
			}
			res = newRes;
		}
		return res;
	}
	
	public static List<List<String>> solution2(List<List<String>> input) {
		List<List<String>> res = new ArrayList<List<String>>();
		helper(res, new ArrayList<String>(), input, 0);
		return res;
	}
	
	public static void helper(List<List<String>> res, List<String> list, List<List<String>> input, int pos) {
		if (pos == input.size()) {
			List<String> newList = new ArrayList<String>(list);
			res.add(newList);
			return;
		}
		for (int j = 0; j < input.get(pos).size(); j++) {
			list.add(input.get(pos).get(j));
			helper(res, list, input, pos + 1);
			list.remove(list.size() - 1);
		}
	}
}
