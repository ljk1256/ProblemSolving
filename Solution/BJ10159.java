package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10159 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] graph = new boolean [N+1][N+1];
		int[][] measure = new int [N+1][N+1]; // 더 무거운쪽은 1, 가벼운쪽은 2
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int heavy = Integer.parseInt(st.nextToken());
			int light = Integer.parseInt(st.nextToken());
			
			graph[heavy][light] = true;
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i == j) graph[i][j] = true;
			}
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				for(int k=1; k<N+1; k++) {
					if(graph[i][k] && graph[j][i]) {
						graph[j][k] = true;
						measure[j][k] = 1;
						measure[k][j] = 2;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N+1; i++) {
			int cnt = 0;
			for(int j=1; j<N+1; j++) {
				if(i != j && measure[i][j] == 0) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
