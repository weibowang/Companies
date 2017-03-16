package Facebook;

import java.util.*;
/*
 * http://www.1point3acres.com/bbs/thread-167887-1-1.html
 */

public class LowestCommonAncestor {
	static class TreeNode {
		int val;
		ArrayList<TreeNode> children;
		public TreeNode(int val) {
			this.val = val;
			children = new ArrayList<TreeNode>();
		}
	}
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		TreeNode n10 = new TreeNode(10);
		n1.children.add(n2);
		n1.children.add(n3);
		n1.children.add(n4);
		n2.children.add(n5);
		n2.children.add(n6);
		n4.children.add(n7);
		n5.children.add(n8);
		n6.children.add(n9);
		n6.children.add(n10);
		TreeNode n = LowestCommonAncestor(n1);
		System.out.println(n.val);
	}
	
	static HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
	public static TreeNode LowestCommonAncestor(TreeNode root) {
		if (root == null || root.children.isEmpty()) {
			return root;
		}
		TreeMap<Integer, ArrayList<TreeNode>> tMap = new TreeMap<Integer, ArrayList<TreeNode>>(new Comparator<Integer>() {
			public int compare(Integer left, Integer right) {
				return right - left;
			}
		});
		ArrayList<TreeNode> nodes = root.children;
		for (int i = 0; i < nodes.size(); i++) {
			TreeNode n = nodes.get(i);
			int height = maxHeight(n);
			if (tMap.containsKey(height)) {
				ArrayList<TreeNode> list = tMap.get(height);
				list.add(n);
			} else {
				ArrayList<TreeNode> list = new ArrayList<TreeNode>();
				list.add(n);
				tMap.put(height, list);
			}
		}
		for (int key : tMap.keySet()) {
			ArrayList<TreeNode> list = tMap.get(key);
			if (list.size() > 1) {
				return root;
			} else {
				TreeNode n = tMap.get(key).get(0);
				return LowestCommonAncestor(n);
			}
		}
		return null;
	}
	
	public static int maxHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (map.containsKey(root)) {
			return map.get(root);
		}
		int max = 0;
		for (int i = 0; i < root.children.size(); i++) {
			max = Math.max(max, maxHeight(root.children.get(i)));
		}
		max++;
		map.put(root, max);
		return max;
	}
}
