package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576 {
	
	static int M;
	static int N;
	static int day;
	static boolean flag;
	static int[][] warehouse;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static boolean[][] visited;
	static Queue<Tomato> q = new LinkedList<Tomato>();
	
	private static boolean isFull() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(warehouse[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void bfs() {
		
		int time = q.size();
		if(time != 0) {
			day++;
		}
		else {
			if(isFull()) {
				flag = false;
				day = day - 1;
				return;
			}
			else {
				day = -1;
				flag = false;
				return;
			}
		}
		
		for(int i=0; i<time; i++) {
			Tomato temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			visited[x][y] = true;
			
			for(int j=0; j<4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
					if(warehouse[nx][ny] == 0) {
						warehouse[nx][ny] = 1;
						q.offer(new Tomato(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		warehouse = new int [N][M];
		visited = new boolean [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				warehouse[i][j] = Integer.parseInt(st.nextToken());
				
				if(warehouse[i][j] == 1) {
					q.offer(new Tomato(i, j));
				}
			}
		}
		
		day = 0;
		flag = true;
		while(flag) {
			bfs();
		}
		System.out.println(day);
	}

}

class Tomato {
	int x;
	int y;
	
	public Tomato(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
