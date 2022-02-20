package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ13023 {
	
	static ArrayList<ArrayList<Integer>> list;
	
	private static void dfs(int depth, int idx, boolean[] visited) {
		
		if(depth == 4) { // 하나라도 존재 한다면 프로그램 종료
			System.out.println(1); 
			System.exit(0);
		}
		
		visited[idx] = true;
		for(int j=0; j<list.get(idx).size(); j++) {
			int temp = list.get(idx).get(j);
			if(!visited[temp]) {
				visited[temp] = true;
				dfs(depth+1, temp, visited);
				visited[temp] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i=0; i<N; i++) { // 리스트 초기화
			list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list.get(start).add(end); // 무향 그래프 입력
			list.get(end).add(start);
		}
		
		for(int i=0; i<N; i++) {
			boolean[] visited = new boolean [N];
			dfs(i, 0, visited);
		}
		System.out.println(0); // 전부 탐색해도 없다면 0 출력
	}

}
