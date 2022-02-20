package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1738 {
	
	static final int INF = -100000000;
	static int[] tracks;
	
	private static StringBuilder Tracking(int start, int end) {
		StringBuilder sb = new StringBuilder();
		if(tracks[end] == 0 || tracks[end] == start) {
			sb.append(start).append(" ").append(end);
			return sb;
		}
		
		sb.append(start).append(" ");
		dfs(start, tracks[end], sb);
		sb.append(tracks[end]).append(" ").append(end);
		
		return sb;
	}
	
	private static void dfs(int start, int end, StringBuilder sb) {
		if(tracks[end] == 0 || tracks[end] == start) return;
		
		dfs(start, tracks[end], sb);
		sb.append(tracks[end]).append(" ");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int [n+1][n+1];
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i == j) {
					graph[i][j] = 0;
					continue;
				}
				
				graph[i][j] = INF;
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u][v] = w;
		}
		
		int[] distance = new int [n+1];
		Arrays.fill(distance, INF);
		distance[1] = 0;
		
		tracks = new int [n+1];
		for(int i=1; i<n+1; i++) {
			
			for(int j=1; j<n+1; j++) {
				if(distance[j] != INF) {
					for(int k=1; k<n+1; k++) {
						if(distance[k] < distance[j] + graph[j][k]) {
							distance[k] = distance[j] + graph[j][k];
							tracks[k]= j;
							
							if(i == n) {
								distance[k] = -INF;
							}
						}
						
					}
					
				}
			}
		}
		
		if(distance[n] == -INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(Tracking(1, n).toString());
	}

}
