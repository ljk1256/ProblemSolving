package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14938 {
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 지역 갯수
		int m = Integer.parseInt(st.nextToken()); // 수색 범위
		int r = Integer.parseInt(st.nextToken()); // 길의 갯수
		
		int[] item = new int [n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] graph = new int [n+1][n+1]; // 인덱스와 지역번호 일치
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 지역번호 1
			int b = Integer.parseInt(st.nextToken()); // 지역번호 2
			int l = Integer.parseInt(st.nextToken()); // 거리
			
			graph[a][b] = l;
			graph[b][a] = l;
		}
		
		answer = 0;
		for(int i=1; i<n+1; i++) { // 모든 지역을 시작 정점으로 탐색
			int[] distance = new int [n+1];
			boolean[] visited = new boolean [n+1];
			
			Arrays.fill(distance, Integer.MAX_VALUE); // 탐색 가능한 범위보다 크게 설정
			distance[i] = 0;
			visited[i] = true;
			
			for(int j=1; j<n+1; j++) { // 시작 정점에서의 직접 연결된 거리 탐색
				if(!visited[j] && graph[i][j] != 0) {
					distance[j] = graph[i][j];
				}
			}
			
			for(int j=1; j<n+1; j++) {
				int tempmin = Integer.MAX_VALUE;
				int index = 0;
				
				for(int k=1; k<n+1; k++) { // 다음 경유지 선택
					if(!visited[k] && tempmin > distance[k]) { // 경유지로 선택된 적이 없으면서 최소 거리인 경유지 선택
						tempmin = distance[k];
						index = k;
					}
				}
				
				for(int k=1; k<n+1; k++) {
					if(!visited[k] && graph[index][k] != 0) { // 경유지로 선택된 적이 없고 길이 존재한다면
						if(distance[k] > distance[index] + graph[index][k]) {
							distance[k] = distance[index] + graph[index][k];
						}
					}
				}
				
				visited[index] = true; // 경유지로 방문 체크
			}
			
			int sum = 0; // 낙하한 지역에서 획득할 수 있는 아이템의 수
			for(int j=1; j<n+1; j++) {
				if(distance[j] <= m) { // 탐색 가능한 범위라면
					sum += item[j];
				}
			}
			
			answer = Math.max(answer, sum);
		}
		
		System.out.println(answer);
	}

}
