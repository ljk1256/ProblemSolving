package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ5719 {
	
	static int N;
	static int[][] roads;
	static final int INF = 1000000000;
	// 인접리스트 역 추적의 경우 리스트에 갱신 될때마다 경유지 정보를 넣어준다 >> 대신 같은 값일때도 넣어줘야 한다 다만, 더 작은 값으로 경유지가 된다면 이전에 리스트는 clear하고 넣어준다
	private static int[] dijkstra(boolean[] visited, int[] distance, int startIdx) {
		distance[startIdx] = 0; // 출발점
		
		for(int i=0; i<N; i++) {
			int index = 0, min = INF;
			for(int j=0; j<N; j++) {
				if(!visited[j] && distance[j] < min) {
					index = j;
					min = distance[j];
				}
			}
			
			for(int j=0; j<N; j++) {
				if(!visited[j] && roads[index][j] != 0) {
					if(distance[j] > distance[index] + roads[index][j]) {
						distance[j] = distance[index] + roads[index][j];
					}
				}
			}
			
			visited[index] = true; // 경유지로 봤으니 방문처리
		}
		return distance;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break; // 둘 다 0이 들어오면 종료
			
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			roads = new int [N][N];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				
				roads[U][V] = P; // 인접행렬로 경로 저장
			}
			
			int[] distance = new int [N];
			Arrays.fill(distance, INF);
			int[] minDistance = dijkstra(new boolean [N], distance, S); // 첫번째 다익스트라 탐색
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(D);
			
			while(!q.isEmpty()) {
				int temp = q.poll(); // 경로 역추적 (이유는 출발점부터 돌리면 중간 지점까지는 경로로 채택됐지만 마지막까지 아니란 이유로 추적이 불가능할 수 있다.)
				
				for(int i=0; i<N; i++) {
					if(roads[i][temp] != 0 && minDistance[temp] == minDistance[i] + roads[i][temp]) {
						roads[i][temp] = 0;
						q.offer(i);
					}
				}
			}
			
			int[] distance2 = new int [N];
			Arrays.fill(distance2, INF);
			int[] nextDistance = dijkstra(new boolean [N], distance2, S); // 2번째 다익스트라 탐색
			
			if(nextDistance[D] != INF && nextDistance[D] > minDistance[D]) sb.append(nextDistance[D]).append("\n"); // 다익스트라를 돌렸을때 새로 찾은 다익스트라가 존재하고 그 값이 이전 다익스트라 보다 크다면
			else sb.append(-1).append("\n"); // 아닐경우
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
