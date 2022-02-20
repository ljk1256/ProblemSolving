package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1600 {
	
	static int K;
	static int W;
	static int H;
	static int count = Integer.MAX_VALUE;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static int[] horse_x = new int [] {-2, -2, -1, 1, 2, 2, 1, -1};
	static int[] horse_y = new int [] {-1, 1, 2, 2, 1, -1, -2, -2};
	static int[][] map;
	static boolean[][][] visited;
	
	private static void move() {
		Queue<Monkey> q = new LinkedList<Monkey>();
		q.offer(new Monkey(0, 0, 0, 0));
		
		for(int i=0; i<K+1; i++) {
			visited[0][0][i] = true; // 혼동을 피하기 위해선 첫 번째 큐 방문 처리는 밖에다 해주는것이 좋음
		}
		
		while(!q.isEmpty()) {
			Monkey temp = q.poll();
			
			if(temp.x == H-1 && temp.y == W-1) {
				count = temp.dis;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < H && ny < W) {
					if(map[nx][ny] == 0 && !visited[nx][ny][temp.jump]) {
						q.offer(new Monkey(nx, ny, temp.dis+1, temp.jump));
						visited[nx][ny][temp.jump] = true;
					}
				}
			}
			
			if(temp.jump < K) {
				for(int i=0; i<8; i++) {
					int hx = temp.x + horse_x[i];
					int hy = temp.y + horse_y[i];
					
					if(hx >= 0 && hy >= 0 && hx < H && hy < W) {
						if(map[hx][hy] == 0 && !visited[hx][hy][temp.jump+1]) {
							q.offer(new Monkey(hx, hy, temp.dis+1, temp.jump+1));
							visited[hx][hy][temp.jump+1] = true;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int [H][W];
		visited = new boolean [H][W][K+1];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move();
		if(count == Integer.MAX_VALUE) { // 모두 탐색 했는데 초기값 Integer.MAX_VALUE 라면 모든 경우에 대해 도달 할 수 없다는 상황이므로 -1 출력
			System.out.println(-1);
		}
		else {
			System.out.println(count);
		}
	}
}

class Monkey {
	int x;
	int y;
	int dis;
	int jump;
	
	public Monkey(int x, int y, int dis, int jump) {
		super();
		this.x = x;
		this.y = y;
		this.dis = dis;
		this.jump = jump;
	}

}
