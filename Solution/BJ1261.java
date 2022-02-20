package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1261 {
	
	static class Spot {
		
		int x;
		int y;
		int count; // 벽을 부순 횟수
		
		public Spot(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken()); // 가로
		int N = Integer.parseInt(st.nextToken()); // 세로
		
		int[][] map = new int [N][M];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		PriorityQueue<Spot> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.count, o2.count));
		boolean[][] visited = new boolean [N][M];
		pq.offer(new Spot(0, 0, 0));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Spot temp = pq.poll();
			int x = temp.x;
			int y = temp.y;
			int count = temp.count;
			
			if(x == N-1 && y == M-1) {
				System.out.println(count);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue; // 0-1 너비 우선 탐색에서는 방문처리까지 같이 체크 하며 우선순위큐 사용 (덱을 사용해도 된다)
				
				if(map[nx][ny] == 1) {
					pq.offer(new Spot(nx, ny, count+1));
					visited[nx][ny] = true;
				}
				
				else {
					pq.offer(new Spot(nx, ny, count));
					visited[nx][ny] = true;
				}
			}
		}
		
	}

}
