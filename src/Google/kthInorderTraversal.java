package Google;

public class kthInorderTraversal {

	static class TreeNode {
		int val;
		int leftCount;
		int rightCount;
		TreeNode left;
		TreeNode right;
		TreeNode(int _val) {
			val = _val;
		}
	}
	public static void main(String[] args) {
		TreeNode n0 = new TreeNode(0);
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
		TreeNode n11 = new TreeNode(11);
		TreeNode n12 = new TreeNode(12);
		TreeNode n13 = new TreeNode(13);
		TreeNode n14 = new TreeNode(14);
		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		n2.right = n6;
		n3.left = n7;
		n3.right = n8;
		n4.left = n9;
		n4.right = n10;
		n5.left = n11;
		n5.right = n12;
		n6.left = n13;
		n6.right = n14;
		
		int k = 16;
		int res1 = solution1(n0, k);
		int res2 = solution2(n0, k);
		int res3 = solution3(n0, k);
		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
	}
	
	public static int solution1(TreeNode root, int k) {
		buildCount(root);
		return findKth(root, k);
	}
	
	public static int buildCount(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l = buildCount(root.left);
		int r = buildCount(root.right);
		root.leftCount = l;
		root.rightCount = r;
		return l + r + 1;
	}
	
	public static int findKth(TreeNode root, int k) {
		if (root == null) {
			return -1;
		}
		if (k - 1 == root.leftCount) {
			return root.val;
		} else if (k - 1 < root.leftCount) {
			return findKth(root.left, k);
		} else {
			return findKth(root.right, k - root.leftCount - 1);
		}
	}
	
	
	//https://www.geeksforgeeks.org/find-n-th-node-inorder-traversal/
	static int count = 0;
	public static int solution2(TreeNode root, int k) {
		if (root == null) {
			return -1;
		}
		if (count <= k) {
			int l = solution2(root.left, k);
			if (l != -1) {
				return l;
			}
			count++;
			if (count == k) {
				return root.val;
			}
			int r = solution2(root.right, k);
			return r;
		}
		return -1;
	}
	
	public static int solution3(TreeNode root, int k) {
		buildCount(root);
		int[] array = new int[root.leftCount + root.rightCount + 1];
		if (k > array.length) {
			return -1;
		}
		inorder(root, array);
		return array[k - 1];
	}
	static int index = 0;
	public static void inorder(TreeNode root, int[] array) {
		if (root == null) {
			return;
		}
		inorder(root.left, array);
		array[index++] = root.val;
		inorder(root.right, array);
	}
}
