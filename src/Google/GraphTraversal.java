package Google;

import java.util.*;

/*
 * 题目叙述很简单，就是遍历多叉树，但要先遍历孩子，再遍历当前节点，感觉就是多叉树的POST-ORDER，
 * 我记得当时楼主用的RECURSION,但是面试官说不行，说这个会OVERFLOW，然后非要他用ITERATION，
 * 但传统的ITERATION也需要用STACK啊，我想那个面试官是不是想说不要用STACK的ITERATION，
 * 然后我上网研究了一下MORIS后续遍历，但不知道怎么改成多叉树的形式，不知道地里有没有什么大牛能说说看法
 */
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
		
		System.out.println();
		List<Integer> result2 = new LinkedList<Integer>();
		PostorderTraversalRecursion(root, result2);
		print(result2);
		System.out.println();
		print(PostorderTraversalStack(root));
		
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
	
	public static void PostorderTraversalRecursion(Node root, List<Integer> result) {
		if (root == null) {
			return;
		}
		for (int i = 0; i < root.neighbors.size(); i++) {
			Node n = root.neighbors.get(i);
			PostorderTraversalRecursion(n, result);
		}
		result.add(root.val);
	}
	
	public static List<Integer> PostorderTraversalStack(Node root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		HashSet<Node> set = new HashSet<Node>();
		pushStack(root, stack, set);
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			boolean check = true;
			for (int i = 0; i < node.neighbors.size(); i++) {
				Node n = node.neighbors.get(i);
				if (!set.contains(n)) {
					pushStack(n, stack, set);
					check = false;
					break;
				}
			}
			if (check) {
				result.add(stack.pop().val);
			}
		}
		return result;
	}
	
	private static void pushStack(Node root, Stack<Node> stack, HashSet<Node> set) {
		if (root == null) {
			return;
		}
		stack.push(root);
		set.add(root);
		while (root.neighbors.size() > 0) {
			for (int i = 0; i < root.neighbors.size(); i++) {
				Node n = root.neighbors.get(i);
				if (!set.contains(n)) {
					set.add(n);
					stack.push(n);
					root = n;
					break;
				}
			}
		}
	}
	
	public static List<Integer> PreorderTraversalStack(Node root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		HashSet<Node> set = new HashSet<Node>();
		pushToStack(root, stack, set, result);
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			boolean check = true;
			//System.out.println("a " + node.val);
			for (int i = 0; i < node.neighbors.size(); i++) {
				Node n = node.neighbors.get(i);
				if (!set.contains(n)) {
					//System.out.println("b " + n.val);
					pushToStack(n, stack, set, result);
					check = false;
					break;
				}
			}
			if (check) {
				stack.pop();
			}
		}
		return result;
	}
	
	private static void pushToStack(Node root, Stack<Node> stack, HashSet<Node> set, List<Integer> result) {
		stack.push(root);
		set.add(root);
		result.add(root.val);
		while (root.neighbors.size() > 0) {
			for (int i = 0; i < root.neighbors.size(); i++) {
				Node n = root.neighbors.get(i);
				//System.out.println("c " + n.val);
				if (set.contains(n)) {
					continue;
				}
				stack.push(n);
				set.add(n);
				result.add(n.val);
				root = n;
				break;
			}
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
