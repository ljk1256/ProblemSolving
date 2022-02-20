package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10868 {
	
	static class Node {
		
		int start;
		int end;
		int merge;
		Node leftNode;
		Node rightNode;
		
		public Node(int start, int end, int merge, Node leftNode, Node rightNode) {
			super();
			this.start = start;
			this.end = end;
			this.merge = merge;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
	}
	
	static int[] arr;
	
	private static int makeMinSegment(int start, int end, Node childNode) {
		if(start == end) childNode.merge = arr[start];
		else {
			int mid = start + (end - start) / 2;
			childNode.leftNode = new Node(start, mid, 0, null, null);
			childNode.rightNode = new Node(mid+1, end, 0, null, null);
			childNode.merge = Math.min(makeMinSegment(start, mid, childNode.leftNode), makeMinSegment(mid+1, end, childNode.rightNode));
		}
		return childNode.merge;
	}
	
	private static int getMin(Node tempNode, int start, int end) {
		if(start > tempNode.end || end < tempNode.start || tempNode == null) return Integer.MAX_VALUE;
		if(start <= tempNode.start && tempNode.end <= end) return tempNode.merge;
		return Math.min(getMin(tempNode.leftNode, start, end), getMin(tempNode.rightNode, start, end));
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int [N+1];
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Node rootNode = new Node(1, N, 0, null, null);
		makeMinSegment(1, N, rootNode);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(getMin(rootNode, start, end)).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
