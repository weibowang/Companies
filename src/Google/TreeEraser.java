package Google;

/*
 * 
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=437316&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B2%5D%3D2%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 */

import java.util.*;

public class TreeEraser {

	public static void main(String[] args) {
		TreeNode a = new TreeNode("a");
		TreeNode b = new TreeNode("b");
		TreeNode c = new TreeNode("c");
		TreeNode d = new TreeNode("d");
		TreeNode e = new TreeNode("e");
		TreeNode f = new TreeNode("f");
		TreeNode g = new TreeNode("g");
		TreeNode h = new TreeNode("h");
		TreeNode i = new TreeNode("i");
		f.left = b;
		f.right = g;
		b.left = a;
		b.right = d;
		g.right = i;
		d.left = c;
		d.right = e;
		i.left = h;
		
		b.erased = true;
		i.erased = true;
		
		List<TreeNode> res2 = solution2(f);
		for (TreeNode n : res2) {
			printTree(n);
			System.out.println();
		}
	}
	
	static class TreeNode {
		TreeNode left;
		TreeNode right;
		String val;
		boolean erased;
		TreeNode(String val) {
			this.val = val;
		}
		
		boolean shouldBeErased() {
			return erased;
		}
	}
	
	public static List<TreeNode> solution1(TreeNode root) {
		List<TreeNode> res = helper1(root, true);
		return res;
	}
	
	public static List<TreeNode> helper1(TreeNode root, boolean parentIsErased) {
		List<TreeNode> res = new ArrayList<TreeNode>();
		if (root == null) {
			return res;
		}
		if (!root.shouldBeErased()) {
			if (parentIsErased) {
				res.add(root);
			}
		}
		res.addAll(helper1(root.left, root.shouldBeErased()));
		res.addAll(helper1(root.right, root.shouldBeErased()));
		if (root.left != null && root.left.shouldBeErased()) {
			root.left = null;
		}
		if (root.right != null && root.right.shouldBeErased()) {
			root.right = null;
		}
		return res;
	}
	
	
	
	
	public static List<TreeNode> solution2(TreeNode root) {
		List<TreeNode> res = new ArrayList<TreeNode>();
		helper2(res, root, true);
		return res;
	}
	
	public static void helper2(List<TreeNode> res, TreeNode root, boolean parentIsErased) {
		if (root == null) {
			return;
		}
		if (!root.shouldBeErased() && parentIsErased) {
			res.add(root);
		}
		helper2(res, root.left, root.shouldBeErased());
		helper2(res, root.right, root.shouldBeErased());
		if (root.left != null && root.left.shouldBeErased()) {
			root.left = null;
		}
		if (root.right != null && root.right.shouldBeErased()) {
			root.right = null;
		}
	}
	
	public static void printTree(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + ",");
		printTree(root.left);
		printTree(root.right);
	}
}
