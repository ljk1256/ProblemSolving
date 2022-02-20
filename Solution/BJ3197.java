package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3197 {
	
	static class Ice {
		
		int x;
		int y;
		
		public Ice(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static boolean flag;
	static int R;
	static int C;
	static int day;
	static char[][] lake;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Ice[] swan;
	static Queue<Ice> waterq;
	static Queue<Ice> swanq;
	static boolean[][] swan_visited;
	
	static void melt() {
		int size = waterq.size(); // 다음에 녹는 얼음까지 넣어주기에 초기 큐 사이즈 만큼만 돌아야한다
		
		for(int i=0; i<size; i++) {
			Ice temp = waterq.poll();
			
			for(int j=0; j<4; j++) {
				int nx = temp.x + dx[j];
				int ny = temp.y + dy[j];
				
				if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				
				if(lake[nx][ny] == 'X') { // 물과 인접한 빙하가 있다면 녹인다
					lake[nx][ny] = '.';
					waterq.offer(new Ice(nx, ny)); // 다음 녹일 빙하 탐색 큐에 추가
				}
			}
		}
	}
	
	static void isMeet() {
		Queue<Ice> next_swan = new LinkedList<>();
		
		while(!swanq.isEmpty()) {
			Ice temp = swanq.poll();
			
			if(temp.x == swan[1].x && temp.y == swan[1].y) { // 다른 백조를 만난다면
				flag = false;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx < 0 || nx >= R || ny < 0 || ny >= C || swan_visited[nx][ny]) continue; // 범위를 벗어나거나 방문을 했다면
				
				if(lake[nx][ny] == '.') { // 물이라면 계속 탐색 진행
					swanq.offer(new Ice(nx, ny));
					swan_visited[nx][ny] = true;
				}
				else if(lake[nx][ny] == 'X') { // 얼음을 만났다면 지금은 못가지만 다음 탐색가능
					next_swan.offer(new Ice(nx, ny));
					swan_visited[nx][ny] = true;
				}
			}
		}
		swanq = next_swan; // 이전 탐색 위치 이후 위치부터 탐색 하기위해 큐 갱신
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lake = new char [R][C];
		swan = new Ice [2]; // 백조 위치 저장
		waterq = new LinkedList<>();
		swanq = new LinkedList<>();
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				lake[i][j] = s.charAt(j);
				
				if(lake[i][j] != 'X') { // 얼음이 아니라면 물이므로 녹여야 할 빙하 탐색 큐에 추가
					waterq.offer(new Ice(i, j));
				}
				
				if(lake[i][j] == 'L') {
					if(swan[0] != null) {
						swan[1] = new Ice(i, j);
						lake[i][j] = '.'; // 백조가 있는 위치도 물이기에 위치 저장만하고 물로 바꿔준다
					}
					else {
						swan[0] = new Ice(i, j);
						lake[i][j] = '.'; 
					}
				}
			}
		}
		
		flag = true;
		day = 0;
		swan_visited = new boolean [R][C];
		swanq.offer(new Ice(swan[0].x, swan[0].y));
		swan_visited[swan[0].x][swan[0].y] = true;
		
		while(flag) { // 먼저 만날수 있는지 판단하고 day를 증가 시킨뒤 한 사이클이 종료되기 때문에 만약, 바로 만날수 있어도 day는 +1이 된다. 즉,정답에는 -1 헤야한다.
			isMeet();
			melt();
			day++;
		}
		
		System.out.println(day-1);
	}
}
