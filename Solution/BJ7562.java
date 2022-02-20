package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7562 {
	
	static class Knight {
		
		int x;
		int y;
		int time;
		
		public Knight(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	
	static Knight destination;
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int I = Integer.parseInt(br.readLine());
			boolean[][] visited = new boolean [I][I];
			Queue<Knight> q = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine()); // 출발지점 나이트
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			destination = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0); // 도착지점 나이트
			
			q.offer(new Knight(start_x, start_y, 0)); // 출발지점 나이트 좌표 큐에 넣고 bfs 탐색 시작
			visited[start_x][start_y] = true;
			
			int answer = 0;
			while(!q.isEmpty()) {
				Knight temp = q.poll();
				
				if(temp.x == destination.x && temp.y == destination.y) { // 도착한다면
					answer = temp.time;
					break;
				}
				
				for(int k=0; k<8; k++) {
					int nx = temp.x + dx[k];
					int ny = temp.y + dy[k];
					
					if(nx < 0 || nx >= I || ny < 0 || ny >= I || visited[nx][ny]) continue; // 범위를 벗어나거나 방문을 했다면
					
					q.offer(new Knight(nx, ny, temp.time+1));
					visited[nx][ny] = true; // 큐에 넣고 방문처리
				}
			}
			System.out.println(answer);
		}
	}

}
