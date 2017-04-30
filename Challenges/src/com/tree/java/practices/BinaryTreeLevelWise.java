package com.tree.java.practices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelWise {

	/**
	 * This class represents the individual nodes of the binary tree Each node
	 * has a left, right pointer of type Node and Value to hold the value
	 * 
	 * @author Aneesh
	 *
	 */
	class Node {
		Node left;

		Node right;

		int value;

		public Node(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node value=" + value + "";
		}

	}

	/**
	 * Driver function to test the code
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new BinaryTreeLevelWise().run();
	}

	/**
	 * This function inserts an element into the binary tree
	 * 
	 * @param node
	 * @param value
	 */
	public void insert(Node node, int value) {
		if (value < node.value) {
			if (node.left != null) {
				insert(node.left, value);
			} else {
				System.out.println("  Inserted " + value + " to left of " + node.value);
				node.left = new Node(value);
			}
		} else if (value > node.value) {
			if (node.right != null) {
				insert(node.right, value);
			} else {
				System.out.println("  Inserted " + value + " to right of " + node.value);
				node.right = new Node(value);
			}
		}
	}

	/**
	 * Builds the tree and executes some functions
	 */
	public void run() {
		// build the simple tree from chapter 11.
		Node root = new Node(5);
		System.out.println("Binary Tree Example");
		System.out.println("Building tree with root value " + root.value);
		insert(root, 1);
		insert(root, 8);
		insert(root, -2);
		insert(root, 6);
		insert(root, 3);
		insert(root, 9);
		insert(root, -3);
		insert(root, -1);
		/*
		 * System.out.println("Traversing tree in order"); printInOrder(root);
		 * System.out.println("Traversing tree front-to-back from location 7");
		 * printFrontToBack(root, 7);
		 */

		System.out.println("*************\nPrinting the tree levelWise");

		printLevelWise(root);
	}

	/**
	 * This functions uses a list of nodes and prints them level wise assuming a
	 * complete binary tree. Every level will have have 2^L elements where L is
	 * the level, root is level L=0
	 * 
	 * @param Root
	 *            of the tree of type {@link Node}
	 */
	public void printLevelWise(Node root) {

		List<List<Node>> levels = traverseLevels(root);
		for (List<Node> level : levels) {
			for (Node node : level) {
				System.out.print(node.value + " ");
			}
			System.out.println();
		}
	}

	/**
	 * This function traverses the tree and puts all the nodes level wise into a
	 * list
	 * 
	 * @param root
	 * @param listOfNodes
	 * @param nodes
	 */
	private List<List<Node>> traverseLevels(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}
		Queue<Node> nodes = new LinkedList<>();
		List<List<Node>> levels = new LinkedList<>();
		nodes.add(root);
		while (!nodes.isEmpty()) {
			if (root != null) {
				List<Node> level = new ArrayList<>(nodes.size());
				levels.add(level);

				for (Node node : new ArrayList<>(nodes)) {
					level.add(node);
					if (node.left != null) {
						nodes.add(node.left);
					}
					if (node.right != null) {
						nodes.add(node.right);
					}
					nodes.poll();
				}

			}
		}
		return levels;
	}
}
