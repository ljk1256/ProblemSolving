package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11657 {
	
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int [N+1][N+1];
		for(int i=0; i<N+1; i++) {
			Arrays.fill(graph[i], INF);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(graph[A][B] > C) { // 어차피 최소 시간을 찾는 문제이므로 같은 노선에 여러값이 들어온다면 최소값으로 넣어준다
				graph[A][B] = C;
			}
		}
		
		long[] distance = new long [N+1];
		Arrays.fill(distance, INF);
		
		distance[1] = 0;
		// 모든 간선을 완탐
		for(int i=1; i<N+1; i++) { // 모든 정점에 대하여
			
			for(int j=1; j<N+1; j++) { // 모든 간선에 대하여
				if(distance[j] != INF) { // 현재까지 경로가 존재하지 않으면 탐색불가
					for(int k=1; k<N+1; k++) {
						if(distance[k] > distance[j] + graph[j][k]) {
							if(i == N) { // 음의 가중치 순환이 있다면, 모든 노드 탐색한 후 다시 탐색 시 갱신이 일어남
								System.out.println(-1);
								return;
							}
							distance[k] = distance[j] + graph[j][k];
						}
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<N+1; i++) {
			if(distance[i] == INF) {
				sb.append(-1).append("\n");
			}
			else sb.append(distance[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
