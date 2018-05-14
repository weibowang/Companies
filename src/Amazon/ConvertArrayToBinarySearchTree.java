package Amazon;

import java.util.*;

//http://www.1point3acres.com/bbs/thread-192414-1-1.html
//http://massiveprogramming.blogspot.com/2016/02/score-gathering-algobox-by-dietpepsi.html

public class ConvertArrayToBinarySearchTree {
	public static class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;
		int count;
		public TreeNode(int val, int count) {
			this.val = val;
			this.count = count;
		}
	}
	public static void main(String[] args) {
        int[] nums = {4, 2, 5, 5, 6, 1, 4};
        String res = convertArrayToBinarySearchTree(nums);
        System.out.println(res);
    }
	
	public static String convertArrayToBinarySearchTree(int[] nums) {
		String res = "";
		TreeNode root = new TreeNode(nums[0], 1);
		for (int i = 1; i < nums.length; i++) {
			buildTree(nums[i], root);
		}
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node == null) {
				res += ",";
			} else {
				res += node.val + ":" + node.count + ",";
				if (node.left != null) {
					q.add(node.left);
				} else {
					if (node.right != null) {
						q.add(null);
					}
				}
				if (node.right != null) {
					q.add(node.right);
				} else {
					if (node.left != null) {
						q.add(null);
					}
				}
			}
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}
	
	public static TreeNode buildTree(int num, TreeNode root) {
		if (root == null) {
			return new TreeNode(num, 1);
		}
		if (root.val == num) {
			root.count++;
			return root;
		}
		if (root.val > num) {
			root.left = buildTree(num, root.left);
		} else {
			root.right = buildTree(num, root.right);
		}
		return root;
	}
}
