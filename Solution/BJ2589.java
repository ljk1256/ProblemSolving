package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2589 {
	
	static char[][] map;
	static boolean[][] visited;
	static int[][] check;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static int max = 0;
	static int cnt;
	static int R;
	static int C;
	
	private static void bfs(int[] point) {
		Queue<int[]> q = new LinkedList<int[]>();
		check = new int [R][C];
		cnt = 0;
		
		q.offer(point);
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			visited[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[nx][ny]) {
					if(map[nx][ny] == 'L') {
						q.offer(new int [] {nx, ny});
						visited[nx][ny] = true;
						check[nx][ny] = check[x][y] + 1;
					}
				}
			}
		}
		
		int t = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(t < check[i][j]) {
					t = check[i][j];
				}
			}
		}
		cnt = t;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char [R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				
				if(map[i][j] == 'L') {
					visited = new boolean [R][C];
					bfs(new int [] {i, j});
					
					if(max < cnt) {
						max = cnt;
					}
				}
			}
		}
		System.out.println(max);
	}

}
