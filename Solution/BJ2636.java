package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2636 {
	
	static int R;
	static int C;
	static int time = 0;
	static int cheese = 0;
	static boolean flag;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] copy_map;
	static boolean[][] visited;
	
	private static void bfs(Cheese point) {
		Queue<Cheese> q = new LinkedList<Cheese>();
		Queue<Cheese> cq = new LinkedList<Cheese>();
		
		q.offer(point);
		visited = new boolean[R][C]; 
		while(!q.isEmpty()) {
			Cheese temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			visited[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[nx][ny]) {
					if(map[nx][ny] == 1) {  // 목적 영역 탐색으로 안된다면, 목적이 아닌 영역을 탐색 하는 역발상이 이 문제의 핵심!
						cq.offer(new Cheese(nx, ny));
						visited[nx][ny] = true;
					}
					else if(map[nx][ny] == 0) {
						q.offer(new Cheese(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		if(!cq.isEmpty()) {
			time = time + 1;
			cheese = cq.size();
			while(!cq.isEmpty()) {
				Cheese temp = cq.poll();
				map[temp.x][temp.y] = 0;
			}
		}
		else {
			flag = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		flag = true;
		while(flag) {
			bfs(new Cheese(0, 0));
		}
		System.out.println(time);
		System.out.println(cheese);
	}

}

class Cheese {
	int x;
	int y;
	
	public Cheese(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
