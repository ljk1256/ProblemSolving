package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14442 {
	
	static int N;
	static int M;
	static int K;
	static int answer;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][][] visited;
	
	private static void bfs(Wall start) {
		Queue<Wall> q = new LinkedList<Wall>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			Wall temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int cnt = temp.cnt;
			int dis = temp.dis;
			visited[x][y][cnt] = true;
			
			if(x == N-1 && y == M-1) {
				answer = dis;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][cnt]) { // 범위를 벗어 나거나 방문을 했을시 
					continue;
				}
				
				if(cnt < K) { // 벽 부순 횟수가 최대 보다 적을때
					if(map[nx][ny] == 1 && !visited[nx][ny][cnt+1]) {
						q.offer(new Wall(nx, ny, cnt+1, dis+1)); // 벽 부신 횟수 하나 카운트
						visited[nx][ny][cnt+1] = true;
					}
					else {
						if(map[nx][ny] != 1) { // 벽을 부시지 않고 갈때
							q.offer(new Wall(nx, ny, cnt, dis+1));
							visited[nx][ny][cnt] = true;
						}
					}
				}
				else { // 최대 횟수로 부셨을때
					if(map[nx][ny] != 1) {
						q.offer(new Wall(nx, ny, cnt, dis+1));
						visited[nx][ny][cnt] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		visited = new boolean [N][M][K+1];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		answer = -1;
		bfs(new Wall(0, 0, 0, 1)); // 시작위치도 거리에 포함
		System.out.println(answer);
	}

}

class Wall {
	
	int x;
	int y;
	int cnt;
	int dis;
	
	public Wall(int x, int y, int cnt, int dis) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dis = dis;
	}
	
}
