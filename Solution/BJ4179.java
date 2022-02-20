package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4179 {
	
	static class Point {
		
		int x;
		int y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[][] map = new char [r][c];
		Queue<Point> j_q = new ArrayDeque<>();
		Queue<Point> fire_q = new ArrayDeque<>();
		boolean[][] j_visited = new boolean [r][c];
		
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'J') { // 지훈이 좌표 저장
					map[i][j] = '.'; // 빈공간으로 변경
					j_q.offer(new Point(i, j));
					j_visited[i][j] = true;
				}
				
				else if(map[i][j] == 'F') {
					fire_q.offer(new Point(i, j));
				}
			}
		}
		
		int time = 0, size = 0;
		while(true) {
			
			size = fire_q.size();
			
			for(int i=0; i<size; i++) { // 불 움직임
				Point temp = fire_q.poll();
				
				for(int j=0; j<4; j++) {
					int nx = temp.x + dx[j];
					int ny = temp.y + dy[j];
					
					if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 'F') continue; // 범위를 벗어나거나 이미 불이 있다면
					
					if(map[nx][ny] == '.') {
						map[nx][ny] = 'F';
						fire_q.offer(new Point(nx, ny));
					}
				}
			}
			
			size = j_q.size();
			
			if(size == 0) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
			for(int i=0; i<size; i++) { // 지훈이 움직임
				Point temp = j_q.poll();
				
				if(temp.x == 0 || temp.x == r-1 || temp.y == 0 || temp.y == c-1) {
					System.out.println(time+1);
					return;
				}
				
				for(int j=0; j<4; j++) {
					int nx = temp.x + dx[j];
					int ny = temp.y + dy[j];
					
					if(nx < 0 || nx >= r || ny < 0 || ny >= c || j_visited[nx][ny]) continue;
					
					if(map[nx][ny] == '.') {
						j_q.offer(new Point(nx, ny));
						j_visited[nx][ny] = true;
					}
				}
			}
			
			time++; // 한 사이클 다 돌았으면 시간 추가
		}
	}

}
