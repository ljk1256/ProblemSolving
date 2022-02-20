package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1956 {
	
	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int [V+1][V+1];
		for(int i=1; i<V+1; i++) { // 보통 자기 자신으로 가는것을 0으로 초기화 했지만 이 문제에서는 그렇게 하면안된다. 문제마다 초기화 기준을 잘 설정해야한다
			Arrays.fill(graph[i], INF);
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a][b] = c;
		}
		
		for(int i=1; i<V+1; i++) {
			for(int j=1; j<V+1; j++) {
				for(int k=1; k<V+1; k++) { // 출발지와 도착지가 자기 자신일때 경유지가 다른곳에서 돌아와서 값이 변경된다면 사이클이 존재
					if(graph[j][k] > graph[j][i] + graph[i][k]) {
						graph[j][k] = graph[j][i] + graph[i][k];
					}
				}
			}
		}
		
		int answer = INF;
		for(int i=1; i<V+1; i++) {
			for(int j=1; j<V+1; j++) {
				if(graph[i][j] != INF && graph[j][i] != INF) { // 경로가 있는것 중에서 사이클이 있는지 확인(두 마을 왕복도 사이클이라 정의)
					answer = Math.min(answer, graph[i][j] + graph[j][i]);
				}
			}
		}
		
		System.out.println(answer == INF ? -1 : answer);
	}

}
