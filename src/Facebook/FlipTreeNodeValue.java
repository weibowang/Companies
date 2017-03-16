package Facebook;

import java.util.LinkedList;
import java.util.Queue;

import Facebook.SalbringTree.TreeNode;

/*
 * 一个完全树。node有parent指针。
每个node的值为 0或 1
每个parent的值为两个子node的 “and” 结果
现在把一个leaf翻牌子（0变1或者1变0）. visit 1point3acres.com for more.
把树修正一遍
 */

public class FlipTreeNodeValue {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(0);
		TreeNode n3 = new TreeNode(0);
		TreeNode n4 = new TreeNode(0);
		TreeNode n5 = new TreeNode(0);
		TreeNode n6 = new TreeNode(1);
		TreeNode n7 = new TreeNode(1);
		TreeNode n8 = new TreeNode(1);
		TreeNode n9 = new TreeNode(1);
		TreeNode n10 = new TreeNode(1);
		TreeNode n11 = new TreeNode(1);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n4.left = n8;
		n4.right = n9;
		n5.left = n10;
		n5.right = n11;
		FlipTreeNodeValue(n1);
		print(n1);
	}
	public static TreeNode FlipTreeNodeValue(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode left = FlipTreeNodeValue(root.left);
		TreeNode right = FlipTreeNodeValue(root.right);
		if (left != null && right != null) {
			root.val = left.val & right.val;
			return root;
		}
		if (left != null) {
			root.val = left.val;
			return root;
		}
		if (right != null) {
			root.val = right.val;
			return root;
		}
		return root;
	}
	
	public static void print(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode n = q.poll();
				System.out.print(n.val + " ");
				if (n.left != null) {
					q.add(n.left);
				}
				if (n.right != null) {
					q.add(n.right);
				}
			}
			System.out.println();
		}
	}
}
