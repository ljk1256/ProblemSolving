package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ice {
	int x;
	int y;
	
	public Ice(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}

public class BJ2573 {
	
	static int N;
	static int M;
	static int year = 0;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static int[][] map;
	static int[][] copy_map;
	static boolean[][] surround;
	static boolean[][] visited;
	
	private static void melt() {
		year = year + 1;
		surround = new boolean [N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					surround[i][j] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) { // true 일때 4방 탐색 후, false 갯수 만큼 map에서 차감, 하는 0 이하로 갈 순없음
				if(surround[i][j] == true) {
					int cnt = 0;
					int di = 0;
					int dj = 0;
					for(int k=0; k<4; k++) {
							di = i + dx[k];
							dj = j + dy[k];
						
						if(di >= 0 && dj >= 0 && di < N && dj < M) {
							if(surround[di][dj] == false) {
								cnt = cnt + 1;
							}
						}
					}
					if(map[i][j] < cnt) {
						map[i][j] = 0;
					}
					else {
						map[i][j] = map[i][j] - cnt;
					}
				}
			}
		}
	}
	
	private static void isDivision() {
		int fragment = 0;
		copy_map = new int [N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy_map[i][j] != 0) {
					bfs(new Ice(i, j));
					fragment = fragment + 1;
				}
			}
		}
		
		if(fragment >= 2) {
			return;
		}
		else if(fragment == 0){
			year = 0;
			return;
		}
		else {
			melt();
			isDivision();
		}
	}
	
	private static void bfs(Ice ice) {
		Queue<Ice> q = new LinkedList<Ice>();
		visited = new boolean [N][M];
		q.offer(ice);
		visited[ice.x][ice.y] = true;
		
		while(!q.isEmpty()) {
			Ice temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
					if(copy_map[nx][ny] != 0) {
						q.offer(new Ice(nx, ny));
						copy_map[nx][ny] = 0;
						visited[nx][ny] = true;
					}
				}
			}
		}
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
		
		isDivision();
		System.out.println(year);
	}

}
