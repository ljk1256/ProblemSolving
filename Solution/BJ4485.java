package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4485 {
	
	static int N;
	static int answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visited;
	
	private static void bfs() {
		PriorityQueue<Zelda> q = new PriorityQueue<>(new Comparator<Zelda>() {

			@Override
			public int compare(Zelda o1, Zelda o2) {
				return o1.cost - o2.cost;
			}
		});
		
		q.offer(new Zelda(0, 0, 0));
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Zelda temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			
			if(x == N-1 && y == N-1) {
				answer += temp.cost;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
					continue;
				}
				
				q.offer(new Zelda(nx, ny, temp.cost+map[nx][ny]));
				visited[nx][ny] = true;
			}
		}
	}
	
	private static void dijkstra(int x, int y) {
		int INF = Integer.MAX_VALUE;
		int[][] dp = new int [N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dp[i][j] = INF;
			}
		}
		
		dp[x][y] = map[x][y];
		while(true) {
			int minCost = INF; // 계속 최대 값으로 초기화 해야 다음 값을 탐색 할 수 있음
			int r = 0, c = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && dp[i][j] < minCost) {
						minCost = dp[i][j];
						r = i;
						c = j;
					}
				}
			}
			
			visited[r][c] = true; // 경유지 선택 후 다른 정점에서 경유지로 고려하지 않음
			if(r == N-1 && c == N-1) {
				answer = minCost;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || dp[nr][nc] < minCost + map[nr][nc]) {
					continue;
				}
				else {
					dp[nr][nc] = minCost + map[nr][nc];
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = 0;
		while(true) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) {
				break;
			}
			
			t++;
			answer = 0;
			map = new int [N][N];
			visited = new boolean [N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/*answer += map[0][0];
			bfs();*/
			dijkstra(0, 0);
			sb.append("Problem ").append(t).append(": ").append(answer);
			System.out.println(sb.toString());
		}
	}

}

class Zelda {
	int x;
	int y;
	int cost;
	
	public Zelda(int x, int y, int cost) {
		super();
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	
}
