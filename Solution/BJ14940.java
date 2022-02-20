package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14940 {
	
	static class Point {
		
		int x;
		int y;
		int cnt;
		
		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
	
	static int n;
	static int m;
	static int[][] map;
	static int[][] answer;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Point start;
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		visited = new boolean [n][m];
		q.offer(start);
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int cnt = temp.cnt;
			
			answer[x][y] = cnt;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || map[nx][ny] == 0) continue;
				
				q.offer(new Point(nx, ny, cnt+1));
				visited[nx][ny] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int [n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					start = new Point(i, j, 0);
					map[i][j] = 0;
				}
			}
		}
		
		answer = new int [n][m];
		bfs();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					answer[i][j] = -1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
