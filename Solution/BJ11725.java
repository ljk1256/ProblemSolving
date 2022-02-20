package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ11725 {
	
	static ArrayList<Integer>[] graph;
	static int[] parent;
	static boolean[] visited;
	
	static void search(int root) { // 재귀호출 이용한 dfs
		for(int temp : graph[root]) { // 인덱스 사용 필요 없을땐 foreach
			if(!visited[temp]) {
				parent[temp] = root;
				visited[temp] = true;
				search(temp);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		parent = new int [N+1];
		visited = new boolean [N+1];
		graph = new ArrayList [N+1];
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b); // 누가 부모인지 모르기 때문에 양방향 연결
			graph[b].add(a);
		}
		
		search(1);
		
		for(int i=2; i<N+1; i++) {
			sb.append(parent[i]).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
