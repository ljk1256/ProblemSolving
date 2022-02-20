package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1865 {
	
	static final int INF = 5000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
	cp: while(TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			int[][] world = new int [N+1][N+1];
			for(int i=1; i<N+1; i++) {
				Arrays.fill(world[i], INF);
			}
			
			for(int i=0; i<M; i++) { // 도로
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				if(world[S][E] > T) { // 양방향 그래프, 더 작은 값이 있다면 작은값으로 선정
					world[S][E] = T;
					world[E][S] = T;
				}
			}
			
			for(int i=0; i<W; i++) { // 웜홀
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				world[S][E] = -1 * T;
			}
			
			int[] dist = new int [N+1];
			Arrays.fill(dist, INF);
			
			dist[1] = 0;
 			for(int i=1; i<N+1; i++) { // 모든 정점에 대하여
				
				for(int j=1; j<N+1; j++) { // 왜 dist[t] != INF 가 없어야 하는가
					for(int t=1; t<N+1; t++) {
						if(dist[t] > dist[j] + world[j][t]) {
							if(i == N) {
								sb.append("YES").append("\n"); // 음의 순환이 있다면
								continue cp;
							}
								
							dist[t] = dist[j] + world[j][t];
						}
					}
				}
			}
 			
 			sb.append("NO").append("\n"); // 음의 순환이 없다면
		}
		
		System.out.println(sb.toString());
	}

}
