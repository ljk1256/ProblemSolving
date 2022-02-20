package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1018 {
	
	static int minCnt;
	static int N;
	static int M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Point {
		
		int x;
		int y;
		char c;
		
		public Point(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
		
	}
	
	private static void bfs(Point point) {
		Queue<Point> q = new LinkedList<>();
		
		char[][] copymap1 = new char [8][8];
		for(int i=0; i<8; i++) {
			System.arraycopy(map[point.x+i], point.y, copymap1[i], 0, 8);
		}
		
		char[][] copymap2 = new char [8][8];
		for(int i=0; i<8; i++) {
			System.arraycopy(map[point.x+i], point.y, copymap2[i], 0, 8);
		}
		
		boolean[][] visited = new boolean [8][8];
		visited[0][0] = true;
		
		q.offer(new Point(0, 0, copymap1[0][0]));
		int tempMin = 0;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			char c = temp.c;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || visited[nx][ny]) continue;
				
				if(copymap1[nx][ny] == c) {
					tempMin++;
					if(c == 'B') copymap1[nx][ny] = 'W';
					else copymap1[nx][ny] = 'B';
				}
				
				q.offer(new Point(nx, ny, copymap1[nx][ny]));
				visited[nx][ny] = true;
			}
		}
		
		minCnt = Math.min(minCnt, tempMin);
		
		if(copymap2[0][0] == 'B') copymap2[0][0] = 'W';
		else copymap2[0][0] = 'B';
		
		visited = new boolean [8][8];
		visited[0][0] = true;
		
		q.offer(new Point(0, 0, copymap2[0][0]));
		tempMin = 1; // 하나 바꾸고 시작했으니
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			char c = temp.c;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || visited[nx][ny]) continue;
				
				if(copymap2[nx][ny] == c) {
					tempMin++;
					if(c == 'B') copymap2[nx][ny] = 'W';
					else copymap2[nx][ny] = 'B';
				}
				
				q.offer(new Point(nx, ny, copymap2[nx][ny]));
				visited[nx][ny] = true;
			}
		}
		
		minCnt = Math.min(minCnt, tempMin);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char [N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		minCnt = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			if(i+7 >= N) continue;
			for(int j=0; j<M; j++) {
				if(j+7 >= M) continue;
				bfs(new Point(i, j, map[i][j]));
			}
		}
		
		System.out.println(minCnt);
	}

}
