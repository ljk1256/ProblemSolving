package Solution;

import java.util.Arrays;

public class test1 {
	
	static final int INF = 10000000; 

	public static void main(String[] args) {
		int[][] graph = {
				{0, 0, 0, 0, 0},
				{0, 0, 1, 1, 0},
				{0, 1, 0, 0, 3},
				{0, 1, 0, 0, 5},
				{0, 0, 3, 5, 0}
		};
		
		int[] distance = new int [5];
		Arrays.fill(distance, INF);
		
		boolean[] visited = new boolean [5];
		distance[1] = 0;
		
		for(int i=1; i<5; i++) {
			int idx = 0;
			int min = Integer.MAX_VALUE;
			
			for(int j=1; j<5; j++) { // 다음 경유지 선택을 위한 탐색
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					idx = j;
				}
			}
			
			for(int j=1; j<5; j++) { // 선택된 경유지로 거리가 더 짧은지 탐색
				if(!visited[j] && graph[idx][j] != 0) {
					if(distance[j] > distance[idx] + graph[idx][j]) {
						distance[j] = distance[idx] + graph[idx][j];
					}
				}
			}
			
			visited[idx] = true; // 경유지 방문 처리
		}
		
		System.out.println(distance[4]);
	}

}
