package Google;

import java.util.*;

public class ColorRoute {
	
	public static void main(String[] args) {
		Node n1 = new Node(Color.Green, 1);
		Node n2 = new Node(Color.Green, 2);
		Node n3 = new Node(Color.Green, 3);
		Node n4 = new Node(Color.Green, 4);
		Node n5 = new Node(Color.Black, 5);
		n1.toOthers.add(n2);
		n1.toOthers.add(n3);
		n2.toOthers.add(n5);
		n2.toOthers.add(n4);
		n3.toOthers.add(n4);
		n4.toOthers.add(n2);
		List<Node> allNodes = new ArrayList<Node>();
		allNodes.add(n1);
		allNodes.add(n2);
		allNodes.add(n3);
		allNodes.add(n4);
		allNodes.add(n5);
		List<List<Node>> result = ColorRoute(allNodes);
		for (int i = 0; i < result.size(); i++) {
			System.out.println("**************\n");
			for (int j = 0; j < result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j).val + " ");
			}
		}
	}
	
	public enum Color {
		Black, Green, Red;
	}
	public static class Node {
		Color color;
		int val;
		HashSet<Node> toOthers;
		public Node(Color color, int val) {
			this.color = color;
			this.val = val;
			toOthers = new HashSet<Node>();
		}
	}
	
	public static List<List<Node>> ColorRoute(List<Node> allNodes) {
		
		List<List<Node>> result = new ArrayList<List<Node>>();
		
		//find start nodes
		HashSet<Node> startNodes = new HashSet<Node>();
		for (int i = 0; i < allNodes.size(); i++) {
			if (allNodes.get(i).color == Color.Green) {
				startNodes.add(allNodes.get(i));
			}
		}
		for (int i = 0; i < allNodes.size(); i++) {
			for (Node node : allNodes.get(i).toOthers) {
				if (startNodes.contains(node)) {
					startNodes.remove(node);
				}
			}
		}
		
		for (Node startNode : startNodes) {
			List<Node> list = new ArrayList<Node>();
			list.add(startNode);
			HashSet<Node> used = new HashSet<Node>();
			used.add(startNode);
			buildRoute(result, list, used);
		}
		return result;
	}
	
	private static void buildRoute(List<List<Node>> result, List<Node> list, HashSet<Node> used) {
		Node current = list.get(list.size() - 1);
		if (current.color == Color.Black) {
			if (current.toOthers == null || current.toOthers.size() == 0) {
				result.add(list);
			} else {
				return;
			}
		}
		for (Node node : current.toOthers) {
			if (!used.contains(node) && node.color != Color.Red) {
				List<Node> currentList = new ArrayList<Node>(list);
				currentList.add(node);
				HashSet<Node> currentUsed = new HashSet<Node>(used);
				currentUsed.add(node);
				buildRoute(result, currentList, currentUsed);

			}
		}
	}
}
