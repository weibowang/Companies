package Google;

import java.util.*;

public class GraphTraversal {
	static class Node {
		int val;
		List<Node> neighbors;
		Node(int val) {
			this.val = val;
			neighbors = new ArrayList<Node>();
		}
	}
	
	public static void main(String[] args) {
		Node root = buildNodes();
		List<Integer> result = new LinkedList<Integer>();
		PreorderTraversalRecursion(root, result);
		print(result);
		System.out.println();
		print(PreorderTraversalStack(root));
		
	}
	
	public static void PreorderTraversalRecursion(Node root, List<Integer> result) {
		if (root == null) {
			return;
		}
		result.add(root.val);
		for (int i = 0; i < root.neighbors.size(); i++) {
			Node n = root.neighbors.get(i);
			PreorderTraversalRecursion(n, result);
		}
	}
	
	public static List<Integer> PreorderTraversalStack(Node root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		HashSet<Node> set = new HashSet<Node>();
		pushToStack(root, stack, set, result);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.println("a " + node.val);
			for (int i = 0; i < node.neighbors.size(); i++) {
				Node n = node.neighbors.get(i);
				if (!set.contains(n)) {
					System.out.println("b " + n.val);
					pushToStack(n, stack, set, result);
				}
			}
		}
		return result;
	}
	
	private static void pushToStack(Node root, Stack<Node> stack, HashSet<Node> set, List<Integer> result) {
		stack.push(root);
		set.add(root);
		result.add(root.val);
		for (int i = 0; i < root.neighbors.size(); i++) {
			Node n = root.neighbors.get(i);
			if (set.contains(n)) {
				continue;
			}
			stack.push(n);
			set.add(n);
			result.add(n.val);
			root = n;
			i = 0;
		}
	}
	
	private static Node buildNodes() {
		Node root = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		Node node8 = new Node(8);
		Node node9 = new Node(9);
		Node node10 = new Node(10);
		Node node11 = new Node(11);
		Node node12 = new Node(12);
		root.neighbors.add(node2);
		root.neighbors.add(node3);
		root.neighbors.add(node4);
		node2.neighbors.add(node5);
		node3.neighbors.add(node6);
		node3.neighbors.add(node7);
		node3.neighbors.add(node8);
		node4.neighbors.add(node9);
		node4.neighbors.add(node10);
		node7.neighbors.add(node11);
		node8.neighbors.add(node12);
		return root;
	}
	
	private static void print(List<Integer> result) {
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}
}
