package Google;

import java.util.HashMap;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441969&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3048%5D%5Bvalue%5D%3D2%26searchoption%5B3048%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 */

public class CompareTwoTrees {
	static class TreeNode {
		String str;
		TreeNode left;
		TreeNode right;
		TreeNode(String str) {
			this.str = str;
		}
	}
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode("+");
		TreeNode n2 = new TreeNode("+");
		TreeNode n3 = new TreeNode("8x");
		TreeNode n4 = new TreeNode("2");
		TreeNode n5 = new TreeNode("-1");
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		TreeNode n6 = new TreeNode("+");
		TreeNode n7 = new TreeNode("1");
		TreeNode n8 = new TreeNode("*");
		TreeNode n9 = new TreeNode("2x");
		TreeNode n10 = new TreeNode("4");
		n6.left = n7;
		n6.right = n8;
		n8.left = n9;
		n8.right = n10;
		boolean res = compareTwoTrees(n1, n6);
		System.out.println(res);
	}
	
	public static boolean compareTwoTrees(TreeNode node1, TreeNode node2) {
		HashMap<String, Integer> map1 = calculate(node1);
		HashMap<String, Integer> map2 = calculate(node2);
		if (map1.size() != map2.size()) {
			return false;
		}
		for (String key : map1.keySet()) {
			if (!map2.containsKey(key) || map2.get(key) != map1.get(key)) {
				return false;
			}
		}
		return true;
	}
	
	public static HashMap<String, Integer> calculate(TreeNode root) {
		if (root == null) {
			return null;
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (root.str.equals("+")) {
			HashMap<String, Integer> left = calculate(root.left);
			HashMap<String, Integer> right = calculate(root.right);
			for (String key : left.keySet()) {
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + left.get(key));
				} else {
					map.put(key, left.get(key));
				}
			}
			for (String key : right.keySet()) {
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + right.get(key));
				} else {
					map.put(key, right.get(key));
				}
			}
		} else if (root.str.equals("-")) {
			HashMap<String, Integer> left = calculate(root.left);
			HashMap<String, Integer> right = calculate(root.right);
			for (String key : left.keySet()) {
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + left.get(key));
				} else {
					map.put(key, left.get(key));
				}
			}
			for (String key : right.keySet()) {
				if (map.containsKey(key)) {
					map.put(key, map.get(key) - right.get(key));
				} else {
					map.put(key, -right.get(key));
				}
			}
		} else if (root.str.equals("*")) {
			HashMap<String, Integer> left = calculate(root.left);
			HashMap<String, Integer> right = calculate(root.right);
			if (left.size() == 1 && left.containsKey("1")) {
				for (String key : right.keySet()) {
					map.put(key, right.get(key) * left.get("1"));
				}
			} else {
				for (String key : left.keySet()) {
					map.put(key, left.get(key) * right.get("1"));
				}
			}
		} else if (root.str.equals("/")) {
			HashMap<String, Integer> left = calculate(root.left);
			HashMap<String, Integer> right = calculate(root.right);
			if (left.size() == 1 && left.containsKey("1")) {
				for (String key : right.keySet()) {
					map.put(key, left.get("1") / right.get(key));
				}
			} else {
				for (String key : left.keySet()) {
					map.put(key, left.get(key) / right.get("1"));
				}
			}
		} else {
			String s = root.str;
			String var = "";
			String num = "";
			int f = 1;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '-') {
					f = -1;
				} else if (c >= '0' && c <= '9') {
					num += c;
				} else {
					var += c;
				}
				
			}
			if (num.equals("")) {
				num = "1";
			}
			if (var.equals("")) {
				map.put("1", f * Integer.parseInt(num));
			} else {
				map.put(var, f * Integer.parseInt(num));
			}
		}
		return map;
	}
}
