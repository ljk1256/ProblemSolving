package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2211 {
	
	static final int INF = 100000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] network = new int [N+1][N+1];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i == j) network[i][j] = 0;
				else network[i][j] = INF;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(network[A][B] > C) {
				network[A][B] = C;
				network[B][A] = C;
			}
		}
		
		int[] distance = new int [N+1];
		boolean[] visited = new boolean [N+1];
		Arrays.fill(distance, INF);
		distance[1] = 0;
		
		for(int i=1; i<N+1; i++) {
			int min = INF;
			int index = 0;
			
			for(int j=1; j<N+1; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					index = j;
				}
			}
			
			visited[index] = true;
			for(int j=1; j<N+1; j++) {
				if(!visited[j] && network[index][j] != INF) {
					if(distance[j] > distance[index] + network[index][j]) {
						distance[j] = distance[index] + network[index][j];
					}
				}
			}
			
		}
		
		int[] check = new int [N+1];
		int count = 0;
		visited = new boolean [N+1];
		Arrays.fill(check, INF);
		check[1] = 0;
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N+1; i++) {
			int min = INF;
			int index = 0;
			
			for(int j=1; j<N+1; j++) {
				if(!visited[j] && min > check[j]) {
					min = check[j];
					index = j;
				}
			}
			
			visited[index] = true;
			for(int j=1; j<N+1; j++) {
				if(!visited[j] && network[index][j] != INF) {
					if(check[j] > check[index] + network[index][j]) {
						check[j] = check[index] + network[index][j];
						
						if(check[j] == distance[j]) {
							sb.append(index).append(" ").append(j).append("\n");
							count++;
						}
					}
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(count);
		System.out.println(sb.toString());
	}

}
