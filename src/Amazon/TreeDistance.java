package Amazon;

import java.util.*;


public class TreeDistance {
    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 3, 7, 5, 6, 8};
        int dis = TreeDistance(nums, 5, 8);
        System.out.println(dis);
    }
    
    public static int TreeDistance(int[] nums, int a, int b) {
        Node root = new Node(Integer.MAX_VALUE);
        for (int i = 0; i < nums.length; i++) {
            Node node = new Node(nums[i]);
            addNode(root, node);
        }
        root = root.left;
        
        //print root
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                System.out.print(node.val + " ");
                Node left = node.left;
                Node right = node.right;
                if (left != null) {
                    q.add(left);
                }
                if (right != null) {
                    q.add(right);
                }
            }
            System.out.println("*********");
        }
        
        Node common = lowestCommonAncestor(root, a, b);
        System.out.println(common.val);
        int aDis = distance(common, a);
        int bDis = distance(common, b);
        
        return aDis + bDis;
    }
    
    public static Node addNode(Node root, Node node) {
        if (root == null) {
            return node;
        }
        
        if (root.val > node.val) {
            if (root.left == null) {
                root.left = node;
            } else {
                addNode(root.left, node);
            }
        } else {
            if (root.right == null) {
                root.right = node;
            } else {
                addNode(root.right, node);
            }
        }
        return root;
    }
    
    
    public static Node lowestCommonAncestor(Node root, int a, int b) {
        if (root == null || root.val == a || root.val == b) {
            return root;
        }
        
        Node left = lowestCommonAncestor(root.left, a, b);
        Node right = lowestCommonAncestor(root.right, a, b);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
    }

    public static int distance(Node root, int val) {

        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                int num = node.val;
                if (num == val) {
                    return level;
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return 0;
    }
    
}
