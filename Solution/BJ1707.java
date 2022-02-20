package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1707 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) { // 테스트 케이스만큼 수행
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수
			
			int[] divide = new int [V+1]; // 정점의 번호와 인덱스 일치
			ArrayList<Integer>[] list = new ArrayList[V+1];
			for(int j=1; j<V+1; j++) {
				list[j] = new ArrayList<>();
			}
			
			for(int j=0; j<E; j++) { // 무향 그래프는 양쪽 전부 넣어주면 코드 짜기 편함 (효율성은 좋지않음)
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				list[u].add(v);
				list[v].add(u);
			}
			
			Queue<Integer> q = new LinkedList<>();
			boolean flag = true;
			bp:for(int j=1; j<V+1; j++) {
				if(divide[j] == 0) { // 아직 방문 하지 않는 곳이라면
					q.offer(j);
					divide[j] = 1;
				}
				
				while(!q.isEmpty()) {
					int temp = q.poll();
					
					for(int t=0; t<list[temp].size(); t++) {
						if(divide[list[temp].get(t)] == 0) { // 아직 방문 하지 않은 곳이라면
							divide[list[temp].get(t)] = divide[temp] == 1 ? -1 : 1;
							q.offer(list[temp].get(t));
						}
						
						else if(divide[list[temp].get(t)] == divide[temp]) {
							sb.append("NO").append("\n");
							flag = false;
							break bp;
						}
					}
				}
			}
			if(flag) {
				sb.append("YES").append("\n");
			}
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
