package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2263 {
	
	static int[] inorders;
	static int[] postorders;
	static StringBuilder sb;
	
	private static void makePreorder(int inLeft, int inRight, int postLeft, int postRight) {
		if(inLeft > inRight || postLeft > postRight) return;
		
		if(inLeft == inRight) {
			sb.append(inorders[inLeft]).append(" ");
			return;
		}
		
		int root = postorders[postRight], index = inLeft;
		sb.append(root).append(" "); // 루트
		
		for(; index<=inRight; index++) {
			if(inorders[index] == root) break;
		}
		
		int leftNodeCnt = index - inLeft; // 중위 순회에서 왼쪽 노드를 계산 한 뒤, 남은 노드 갯수-1이  오른쪽 노드 갯수
		 
		makePreorder(inLeft, index-1, postLeft, postLeft+leftNodeCnt-1); // 왼쪽자식
		makePreorder(index+1, inRight, postLeft+leftNodeCnt, postRight-1); // 오른쪽자식
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		inorders = new int [n];
		postorders = new int [n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			inorders[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			postorders[i] = Integer.parseInt(st.nextToken());
		}
		
		sb = new StringBuilder();
		makePreorder(0, n-1, 0, n-1);
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
