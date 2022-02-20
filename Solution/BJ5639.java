package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5639 {
	
	static class Node {
		
		int data;
		Node left;
		Node right;
		
		public Node(int data) {
			super();
			this.data = data;
		}
		
	}
	
	static class Tree {
		
		Node root;
		
		public void Add_Node(int data) {
			if(root == null) { // 루트 노드가 없다면
				root = new Node(data);
			}
			else { // 있다면 탐색 후 저장
				Search_Node(root, data);
			}
		}
		
		public void Search_Node(Node root, int data) {
		
			if(root.data >= data) {
				if(root.left != null) {
					Search_Node(root.left, data);
				}
				else {
					root.left = new Node(data);
				}
			}
			
			else {
				if(root.right != null) {
					Search_Node(root.right, data);
				}
				else {
					root.right = new Node(data);
				}
			}
		}
		
		public void Postorder(Node root) {
			if(root.left != null) Postorder(root.left);
			if(root.right != null) Postorder(root.right);
			System.out.println(root.data);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		Tree tree = new Tree();
		
		while(true) {
			String s = br.readLine();
			if(s == null || s.equals("")) {
				break;
			}
			tree.Add_Node(Integer.parseInt(s));
		}
		tree.Postorder(tree.root);
	}
}
