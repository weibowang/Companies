package Google;

import java.util.*;


/*
 * ��һ�Ŷ������������ظ���subtree�����磺
                  1
                /   \
              2      3
             /     /   \
           4      2     4
                 /
                4
���Ӧ�÷���[ ( 2 -> 4), (4) ] ��������
 */
public class SameSubtree {
	public static class TreeNode {
		TreeNode left;
		TreeNode right;
		String str;
		int val;
		public TreeNode(int val) {
			this.val = val;
			left = null;
			right = null;
			str = "";
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.right = new TreeNode(3);
		root.right.right = new TreeNode(4);
		root.right.left = new TreeNode(2);
		root.right.left.left = new TreeNode(4);
		List<TreeNode> result = sameSubtree(root);
		for (int i = 0; i < result.size(); i++) {
			TreeNode n = result.get(i);
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(n);
			while (!q.isEmpty()) {
				TreeNode node = q.poll();
				System.out.print(node.val + " ");
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
			}
			System.out.println();
		}
		
		System.out.println("*********");
		result = dfs(root);
		for (int i = 0; i < result.size(); i++) {
			TreeNode n = result.get(i);
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(n);
			while (!q.isEmpty()) {
				TreeNode node = q.poll();
				System.out.print(node.val + " ");
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
			}
			System.out.println();
		}
	}
	
	public static List<TreeNode> sameSubtree(TreeNode root) {
		//return dfs(root);
		return postOrderDp(root);
	}
	
	// post order + dp   O(n),   pre order and inorder should get the right answer as well
	public static List<TreeNode> postOrderDp(TreeNode root) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		HashMap<String, TreeNode> map = new HashMap<String, TreeNode>();
		helper2(root, result, map);
		return result;
	}
	
	private static void helper2(TreeNode root, List<TreeNode> result, HashMap<String, TreeNode> map) {
		if (root == null) {
			return;
		}
		helper2(root.left, result, map);
		helper2(root.right, result, map);
		root.str = Integer.toString(root.val);
		if (root.left == null) {
			root.str += "#";
		} else {
			root.str += root.left.str;
		}
		if (root.right == null) {
			root.str += "#";
		} else {
			root.str += root.right.str;
		}
		if (map.containsKey(root.str)) {
			result.add(map.get(root.str));
		} else {
			//System.out.println(root.str);
			map.put(root.str, root);
		}
	}
	
	
	
	//dfs
	public static List<TreeNode> dfs(TreeNode root) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		if (root == null) {
			return result;
		}
		HashMap<Integer, ArrayList<TreeNode>> map = new HashMap<Integer, ArrayList<TreeNode>>();
		helper(root, result, map);
		return result;
	}
	
	private static void helper(TreeNode root, List<TreeNode> result, HashMap<Integer, ArrayList<TreeNode>> map) {
		if (root == null) {
			return;
		}
		helper(root.left, result, map);
		helper(root.right, result, map);
		if (map.containsKey(root.val)) {
			ArrayList<TreeNode> list = map.get(root.val);
			for (int i = 0; i < list.size(); i++) {
				if (isSameTree(root, list.get(i))) {
					result.add(root);
				}
			}
		} else {
			ArrayList<TreeNode> list = new ArrayList<TreeNode>();
			list.add(root);
			map.put(root.val, list);
		}
		
	}
	
	private static boolean isSameTree(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left != null && right == null) {
			return false;
		}
		if (left == null && right != null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		return isSameTree(left.left, right.left) && isSameTree(left.right, right.right);
	}
	
}
