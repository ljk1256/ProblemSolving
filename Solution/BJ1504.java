package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1504 {
	
	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int [N+1][N+1];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i == j) {
					graph[i][j] = 0;
				}
				else {
					graph[i][j] = INF;
				}
			}
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a][b] = c;
			graph[b][a] = c;
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int[][] distance = new int [N+1][N+1];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				distance[i][j] = graph[i][j];
			}
		}
		
		for(int i=1; i<N+1; i++) { // 경유지
			for(int j=1; j<N+1; j++) { // 출발
				for(int k=1; k<N+1; k++) { // 도착
					if(distance[j][k] > distance[j][i] + distance[i][k]) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}
		
		int answer = 0;
		if(distance[1][v1] == INF || distance[v1][v2] == INF || distance[v2][N] == INF || distance[1][v2] == INF || distance[v1][N] == INF) {
			System.out.println(-1);
			return;
		}
		else {
			answer = distance[1][v1] + distance[v1][v2] + distance[v2][N];
			answer = Math.min(answer, distance[1][v2] + distance[v2][v1] + distance[v1][N]);
			System.out.println(answer);
		}
		
	}

}
