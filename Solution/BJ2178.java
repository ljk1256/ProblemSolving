package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2178 {
	
	static int[][] maze;
	static boolean[][] visited;
	static int N;
	static int M;
	static int[] delta_x;
	static int[] delta_y;
	static int count = 0;
	
	private static void bfs(int[] idx) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.offer(idx);
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			
			/*if(x == N-1 && y == M-1) {
				break;
			}*/
			
			for(int i=0; i<4; i++) {
				int nx = x + delta_x[i];
				int ny = y + delta_y[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(!visited[nx][ny] && maze[nx][ny] == 1) {
						q.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
						maze[nx][ny] = maze[x][y] + 1; // 현재까지 최단 경로탐색을 저장 
					}
				}
				
			}
		}
		System.out.println(maze[N-1][M-1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int [N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = s.charAt(j) - '0';
			}
		}
		
		int[] start = new int[] {0, 0};
		visited = new boolean [N][M];
		delta_x = new int[] {-1, 0, 1, 0};
		delta_y = new int[] {0, 1, 0, -1};
		bfs(start);
	}

}
