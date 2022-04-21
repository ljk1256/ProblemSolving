package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10971 {
	
	static final int INF = 100000000;
	static int N;
	static int MAX;
	static int[][] graph;
	static int[][] dp;
	
	private static int TCP(int tempCity, int visited) {
		if(visited == MAX) { // 모든 곳을 방문 하였다면(먼저 처리 해주지 않으면 인덱스 초과 발생)
			if(graph[tempCity][0] != 0) return graph[tempCity][0];
			else return INF;
		}
		
		if(dp[tempCity][visited] != INF) return dp[tempCity][visited];
		
		for(int i=0; i<N; i++) {
			int nextCityIdx = 1 << i;
			int nextVisited = visited | nextCityIdx;
			
			if((nextCityIdx & visited) == 0 && graph[tempCity][i] != 0) {
				dp[tempCity][visited] = Math.min(dp[tempCity][visited], TCP(i, nextVisited) + graph[tempCity][i]);
			}
		}
		
		return dp[tempCity][visited];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		MAX = (1 << N) - 1; // 각 자리 비트수는 방문 처리를 의미
		
		graph = new int [N][N];
		dp = new int [N][MAX];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		System.out.println(TCP(0, 1));
		
	}

}
