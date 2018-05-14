package Amazon;

import java.util.*;


public class LowestCommonBoss {
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
		TreeNode n = lowestCommonBoss(n1, n3, n10);
		System.out.println(n.val);
	}
	
	public static TreeNode lowestCommonBoss(TreeNode root, TreeNode a, TreeNode b) {
		if (root == a || root == b) {
			return root;
		}
		List<TreeNode> children = root.children;
		TreeNode node = null;
		for (TreeNode n : children) {
			TreeNode cur = lowestCommonBoss(n, a, b);
			if (cur == null) {
				continue;
			}
			if (node == null) {
				node = cur;
			} else {
				return root;
			}
		}
		return node;
	}
}
