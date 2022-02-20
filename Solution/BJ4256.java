package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4256 {
	
	static int[] preorders;
	static int[] inorders;
	static StringBuilder sb;
	
	private static void makePostorder(int preLeft, int preRight, int inLeft, int inRight) {
		
		if(inLeft > inRight || preLeft > preRight) return;
		
		if(inLeft == inRight) {
			sb.append(inorders[inLeft]).append(" ");
			return;
		}
		
		int root = preorders[preLeft];
		int index = inLeft;
		for(; index <= inRight; index++) {
			if(inorders[index] == root) break;
		}
		
		int leftNode = index - inLeft; // 왼쪽 노드가 몇개 있는지
		
		// 후위 순회 : 왼쪽노드 > 오른쪽노드 > 루트노드
		makePostorder(preLeft+1, preLeft+leftNode, inLeft, index-1); // 왼쪽
		makePostorder(preLeft+leftNode+1, preRight, index+1, inRight); // 오른쪽
		
		sb.append(root).append(" "); // 루트
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		sb = new StringBuilder();
		
		for(int i=0; i<T; i++) {
			int n = Integer.parseInt(br.readLine());
			
			preorders = new int [n];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				preorders[j] = Integer.parseInt(st.nextToken());
			}
			
			inorders = new int [n];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				inorders[j] = Integer.parseInt(st.nextToken());
			}
			
			makePostorder(0, n-1, 0, n-1);
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
