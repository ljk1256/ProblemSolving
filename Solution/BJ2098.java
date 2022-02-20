package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2098 {
	
	static int N;
	static int MAX;
	static final int INF = 20000000;
	static int[][] costs;
	static int[][] dp;
	
	private static int TCP(int tempCity, int visited) {
		if(visited == MAX) { // 맥스 와 비교하는 코드가 먼저 위치해야 인덱스 오류가 나질않는다
			if(costs[tempCity][0] != 0) return costs[tempCity][0];
			else return INF;
		}
		
		if(dp[tempCity][visited] != INF) return dp[tempCity][visited];
		
		for(int i=0; i<N; i++) {
			int nextCity = 1 << i;
			
			if((visited & nextCity) == 0 && costs[tempCity][i] != 0) {
				nextCity = visited | nextCity;
				dp[tempCity][visited] = Math.min(dp[tempCity][visited], TCP(i, nextCity) + costs[tempCity][i]);
			}
		}
		return dp[tempCity][visited];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		MAX = (1 << N) - 1;
		
		costs = new int [N][N];
		dp = new int [N][MAX];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		System.out.println(TCP(0, 1));
	}

}
