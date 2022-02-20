package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2146 {
	
	static int[][] island;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer;
	static int N;
	static boolean[][][] visited;
	static PriorityQueue<Island> bq = new PriorityQueue<>(new Comparator<Island>() { // 큐를 사용할때 우선순위를 써야 하는지 방문처리를 어떻게 해야하는지 잘 고려해봐야 한다.

		@Override
		public int compare(Island o1, Island o2) {
			return o1.bridge - o2.bridge;
		}
	});
	
	static class Island {
		
		int x;
		int y;
		int bridge;
		
		public Island(int x, int y, int bridge) {
			super();
			this.x = x;
			this.y = y;
			this.bridge = bridge;
		}
		
	}
	
	private static int rabel() { // 섬이 2부터 시작하는 숫자로 라벨링이 된다.
		
		Queue<Island> q = new LinkedList<>();
		boolean[][] visit = new boolean [N][N];
		int cnt = 2;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(island[i][j] == 1) {
					island[i][j] = cnt;
					q.offer(new Island(i, j, 0));
					visit[i][j] = true;
					
					while(!q.isEmpty()) {
						Island temp = q.poll();
						int x = temp.x;
						int y = temp.y;
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) {
								continue;
							}
							
							if(island[nx][ny] == 1) {
								q.offer(new Island(nx, ny, 0));
								visit[nx][ny] = true;
								island[nx][ny] = cnt;
							}
						}
					}
					cnt++;
				}
			}
		}
		return cnt - 1;
	}
	
	private static void bfs(int idx) {
		int index = idx;
		while(!bq.isEmpty()) {
			Island temp = bq.poll();
			int x = temp.x;
			int y = temp.y;
			int bridge = temp.bridge;
			
			if(island[x][y] != 0 && island[x][y] != index) {
				answer = Math.min(answer, bridge-1);
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny][index]) { // 주위에 모드 같은 섬일수 있기에 여기서 같은 섬 체크를 하면안된다.
					continue;
				}
				
				if(island[nx][ny] == index) {
					bq.offer(new Island(nx, ny, 0)); // 같은 섬에 둘러 쌓인경우 다리를 놓는것이 아니니까 갯수변동 없음
					visited[nx][ny][index] = true;
				}else {
					bq.offer(new Island(nx, ny, bridge+1));
					visited[nx][ny][index] = true;
				}
				
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		island = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				island[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = rabel(); // 섬의 갯수, 라벨링
		visited = new boolean [N][N][count+2]; // 섬 라벨이 2부터 시작하고, 배열의 인덱스와 섬의 라벨을 일치시키기 위해 +2
		answer = Integer.MAX_VALUE;
		int idx = 2; // 섬 라벨 인덱스
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(island[i][j] == idx && !visited[i][j][idx]) {
					bq.offer(new Island(i, j, 0));
					visited[i][j][idx] = true;
					bfs(idx);
					idx++;
				}
			}
		}
		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
	}

}