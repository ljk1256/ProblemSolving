package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2638 {
	
	static class Cheese {
		
		int x;
		int y;
		
		public Cheese(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer;
	static int N;
	static int M;
	
	private static void melt() {
		Queue<Cheese> q = new LinkedList<>();
		boolean[][] visited = new boolean [N][M];
		int[][] copy = new int [N][M];
		
		q.offer(new Cheese(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Cheese temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue;
				}
				
				if(map[nx][ny] == 0) {
					q.offer(new Cheese(nx, ny));
					visited[nx][ny] = true;
				}
				
				if(map[nx][ny] == 1) { // 치즈를 만났을때
					copy[nx][ny]++;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy[i][j] >= 2) { // 두 변 이상 공기와 접촉한 치즈는 녹인다.
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static boolean isEmpty() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		while(!isEmpty()) {
			answer++;
			melt();
		}
		
		System.out.println(answer);
	}

}
