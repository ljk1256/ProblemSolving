package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ5427 {
	
	static class Point {
		
		int x;
		int y;
		int time;
		
		public Point(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	
	static StringBuilder sb;
	static char[][] map;
	static boolean flag;
	static int w;
	static int h;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Point> fire_q;
	static Queue<Point> escape_q;
	static boolean[][] fire_visited;
	static boolean[][] escape_visited;
	
	private static void escape() { // 출구에 도착했을때 1초 지나야 탈출하기에 +1 해준다
		int size = escape_q.size();
		
		if(size == 0) { // 더 이상 갈 곳이 없다면
			flag = false;
			sb.append("IMPOSSIBLE").append("\n");
			return;
		}
		
		for(int i=0; i<size; i++) {
			Point temp = escape_q.poll();
			
			if(temp.x == h-1 || temp.x == 0 || temp.y == w-1 || temp.y == 0) { // 탈출 했다면
				flag = false;
				sb.append(temp.time+1).append("\n");
				return;
			}
			
			for(int t=0; t<4; t++) {
				int nx = temp.x + dx[t];
				int ny = temp.y + dy[t];
				
				if(nx < 0 || nx >= h || ny < 0 || ny >= w || escape_visited[nx][ny]) continue;
				
				if(map[nx][ny] == '.') {
					escape_q.offer(new Point(nx, ny, temp.time+1));
					escape_visited[nx][ny] = true;
				}
			}
		}
	}
	
	private static void fire() {
		int size = fire_q.size();
		
		for(int i=0; i<size; i++) {
			Point temp = fire_q.poll();
			
			for(int t=0; t<4; t++) {
				int nx = temp.x + dx[t];
				int ny = temp.y + dy[t];
				
				if(nx < 0 || nx >= h || ny < 0 || ny >= w || fire_visited[nx][ny]) continue;
				
				if(map[nx][ny] != '#') { // 벽만 아니라면 번진다
					fire_q.offer(new Point(nx, ny, 0));
					map[nx][ny] = '*'; // 불이 번진다
					fire_visited[nx][ny] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new char [h][w];
			fire_visited = new boolean [h][w];
			escape_visited = new boolean [h][w];
			fire_q = new LinkedList<>();
			escape_q = new LinkedList<>();
			
			for(int j=0; j<h; j++) {
				String s = br.readLine();
				for(int k=0; k<w; k++) {
					map[j][k] = s.charAt(k);
					
					if(map[j][k] == '@') { // 상근이 좌표저장
						map[j][k] = '.'; // 좌표만 저장 하고 상근이 위치도 불이 붙을수 있기에 빈공간으로 만들어준다
						escape_q.offer(new Point(j, k, 0));
						escape_visited[j][k] = true;
					}
					
					else if(map[j][k] == '*') { // 불 좌표저장
						fire_q.offer(new Point(j, k, 0));
						fire_visited[j][k] = true;
					}
				}
			}
			
			flag = true; // 반복문 멈출때까지 수행
			while(flag) {
				fire();
				escape();
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
