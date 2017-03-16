package Facebook;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/thread-170477-1-1.html
 */

public class SalbringTree {
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
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n3.right = n5;
		n4.left = n6;
		n5.right = n7;
		n7.right = n8;
		solution2(n1);
		print(n1);
	}
	
	public static void print(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (cur.left != null) {
				q.add(cur.left);
			}
			TreeNode node = cur;
			while (node != null) {
				System.out.print(node.val + " ");
				node = node.right;
			}
			System.out.println();
		}
	}
	

	
	public static void solution2(TreeNode root) {
		if (root == null) {
			 return;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		TreeNode prevHead = null;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				if (i == 0) {
					if (prevHead != null) {
						prevHead.left = cur;
					}
					prevHead = cur;
				}
				if (cur.left != null) {
					q.add(cur.left);
				}
				if (cur.right != null) {
					q.add(cur.right);
				}
				if (i == size - 1) {
					cur.right = null;
				} else {
					cur.right = q.peek();
				}
				cur.left = null;
			}
		}
	}
}
