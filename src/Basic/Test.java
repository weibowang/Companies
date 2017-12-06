package Basic;

import java.util.*;
import java.util.List;

public class Test {
	public static class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		    this.left = this.right = null;
		}
	}

	public static void main(String[] args) {
		TreeNode n = new TreeNode(1);
		n.left = new TreeNode(2);
		n.right = new TreeNode(3);
		System.out.println(maxPathSum(n));
	}
	
	
	static int max = Integer.MIN_VALUE;
    static public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    
    public static int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // if (root.left == null && root.right == null) {
        //     max = Math.max(max, root.val);
        //     return root.val;
        // }
        //System.out.println(root.val);
        int left = helper(root.left);
        int right = helper(root.right);
        int val = root.val;
        if (left > 0) {
            //System.out.println("left " + left);
            val += left;
        }
        if (right > 0) {
            //System.out.println("right " + right);
            val += right;
        }
        //System.out.println("* " + val);
        max = Math.max(val, max);
        if (left > right) {
            if (left > 0) {
                return root.val + left;
            } else {
                return root.val;
            }
        } else {
            if (right > 0) {
                return root.val + right;
            } else {
                return root.val;
            }
        }
    }
       
}
