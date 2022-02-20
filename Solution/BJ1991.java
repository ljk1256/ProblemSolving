package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import sun.awt.geom.AreaOp.AddOp;

public class BJ1991 {
	
	static class Node {
		
		String data;
		Node left;
		Node right;
		
		public Node(String data) { // 자식 노드는 있을때만 생성해 따로 생성해 준다
			super();
			this.data = data;
		}
		
	}
	
	static class Tree {
		
		Node root;
		
		public void Add(String data, String left_data, String right_data) { // 다른 클래스에서 사용하기 때문에 private은 안된다
			
			if(root == null) {
				if(!data.equals(".")) root = new Node(data);
				if(!left_data.equals(".")) root.left = new Node(left_data);
				if(!right_data.equals(".")) root.right = new Node(right_data);
			}
			
			else SearchNode(root, data, left_data, right_data); // 이미 생성된 루트 노드가 있다면 탐색 후 생성
		}
		
		public void SearchNode(Node root, String data, String left_data, String right_data) {
			
			if(root == null) return; // 자식의 루트 노드에서 없을경우 탐색 종료
			
			else if(data.equals(root.data)) { // 해당 노드를 찾았다면
				if(!left_data.equals(".")) root.left = new Node(left_data); // 왼쪽 자식 노드가 있다면
				if(!right_data.equals(".")) root.right = new Node(right_data);
			}
			
			else { // 해당 노드에서 찾지 못했다면 자식 노드를 루트노드로 놓고 탐색 시작
				SearchNode(root.left, data, left_data, right_data);
				SearchNode(root.right, data, left_data, right_data);
			}
		}
		
		public void Preorder(Node root) { // 전위 순회 : 부모 > 왼쪽 > 오른쪽
			System.out.print(root.data); // 루트 노드 출력
			
			if(root.left != null) Preorder(root.left); 
			
			if(root.right != null) Preorder(root.right);
		}
		
		public void Inorder(Node root) { // 중위 순회 : 왼쪽 > 부모 > 오른쪽
			if(root.left != null) Inorder(root.left); // 제일 아래층 자식부터 탐색 하기에 재귀 호출
			
			System.out.print(root.data);
			
			if(root.right != null) Inorder(root.right);
		}
		
		public void Postorder(Node root) { // 후위 순회 : 왼쪽 > 오른쪽 > 부모
			
			if(root.left != null) Postorder(root.left);
			
			if(root.right != null) Postorder(root.right);
			
			System.out.print(root.data);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Tree tree = new Tree();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String parent = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();
			
			tree.Add(parent, left, right);
		}
		
		tree.Preorder(tree.root);
		System.out.println();
		tree.Inorder(tree.root);
		System.out.println();
		tree.Postorder(tree.root);
	}
}
