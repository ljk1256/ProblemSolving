package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1194 {
	
	static int N;
	static int M;
	static int ans = -1;
	static char[][] maze;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static boolean[][][] visited;
	static Queue<Minsik> q = new LinkedList<Minsik>();

	private static void bfs() {
		
		while(!q.isEmpty()) {
			Minsik temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int mask = temp.mask;
			
			if(maze[x][y] == '1') {
				ans = temp.dis;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][mask] || maze[nx][ny] == '#') { 
					continue;
				}
				
				if(maze[nx][ny] == 'a' || maze[nx][ny] == 'b' || maze[nx][ny] == 'c' || maze[nx][ny] == 'd' || maze[nx][ny] == 'e' || maze[nx][ny] == 'f') {
					q.offer(new Minsik(nx, ny, temp.dis+1, mask | 1 << maze[nx][ny] - 'a'));
					visited[nx][ny][mask | 1 << maze[nx][ny] - 'a'] = true;
				}
				else if(maze[nx][ny] == 'A' || maze[nx][ny] == 'B' || maze[nx][ny] == 'C' || maze[nx][ny] == 'D' || maze[nx][ny] == 'E' || maze[nx][ny] == 'F') {
					if((mask & (1 << maze[nx][ny] - 'A')) != 0) {
						q.offer(new Minsik(nx, ny, temp.dis+1, mask));
						visited[nx][ny][mask] = true;
					}
				}
				else { // 0 이나 . 이나 모든경우를 적어야 하는데 그게 귀찮으면 else
					q.offer(new Minsik(nx, ny, temp.dis+1, mask));
					visited[nx][ny][mask] = true;
				}
			}
		}
	}	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char [N][M];
		visited = new boolean [N][M][64];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = s.charAt(j);
				if(maze[i][j] == '0') {
					q.offer(new Minsik(i, j, 0, 0));
				}
				
			}
		}
		bfs();
		System.out.println(ans);
	}

}

class Minsik {
	int x;
	int y;
	int dis;
	int mask;
			
	public Minsik(int x, int y, int dis, int mask) {
		super();
		this.x = x;
		this.y = y;
		this.dis = dis;
		this.mask = mask;
	}
	
}
