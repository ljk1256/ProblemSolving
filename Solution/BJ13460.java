package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13460 {
	
	static int N;
	static int M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][][][] visited; // 동시에 발생한다면 모든 경우를 동시에 생각해야한다.
	static int[] hole;
	static int answer;
	
	static class Marble {
		
		int rx;
		int ry;
		int bx;
		int by;
		int cnt;
		
		public Marble(int rx, int ry, int bx, int by, int cnt) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
		
	}
	
	private static Marble move(Marble temp, int dir) {
		Marble now = temp;
		boolean flag = true; // 파란공이 먼저 움직이면 true, 아니면 false
		switch (dir) {
		case 0 : { // 상
			if(now.bx < now.rx) flag = true;
			else flag = false;
			break;
		}
		case 1 : { // 하
			if(now.bx < now.rx) flag = false;
			else flag = true;
			break;
		}
		case 2 : { // 좌
			if(now.by < now.ry) flag = true;
			else flag = false;
			break;
		}
		case 3 : { // 우
			if(now.by < now.ry) flag = false;
			else flag = true;
			break;
		}
		
		}
		
		int nbx = 0, nby = 0, nrx = 0, nry = 0;
		if(flag) { // 파란구슬이 먼저 움직일 경우
			nbx = now.bx + dx[dir];
			nby = now.by + dy[dir];
			while(map[nbx][nby] == '.') { // 벽이거나, 구멍이거나 다른색상 공일수 있기에 빈공간일때만 움직이게 하는 조건이 제일 깔끔하다.
				nbx += dx[dir];
				nby += dy[dir];
			}
			
			if(map[nbx][nby] == '#') { // 벽을 만난경우 한칸뒤로
				nbx -= dx[dir];
				nby -= dy[dir];
			}
			
			nrx = now.rx + dx[dir]; // 빨간구슬 이동
			nry = now.ry + dy[dir];
			while(map[nrx][nry] == '.') {
				if(nrx == nbx && nry == nby) break; // 구슬은 겹칠수 없음
				nrx += dx[dir];
				nry += dy[dir];
			}
			
			if(map[nrx][nry] != 'O') { // 구멍이 아니라면, 파란구슬과 겹치거나, 벽을 만난경우 이므로 한칸뒤로
				nrx -= dx[dir];
				nry -= dy[dir];
			}
				
		}
		else { // 빨간구슬이 먼저 움직이는 경우
			nrx = now.rx + dx[dir];
			nry = now.ry + dy[dir];
			while(map[nrx][nry] == '.') { // 벽이거나, 구멍이거나 다른색상 공일수 있기에 빈공간일때만 움직이게 하는 조건이 제일 깔끔하다.
				nrx += dx[dir];
				nry += dy[dir];
			}
			
			if(map[nrx][nry] == '#') { // 벽을 만난경우 한칸뒤로
				nrx -= dx[dir];
				nry -= dy[dir];
			}

			nbx = now.bx + dx[dir]; // 파란구슬 이동
			nby = now.by + dy[dir];
			while(map[nbx][nby] == '.') {
				if(nbx == nrx && nby == nry) break; // 구슬은 겹칠수 없음
				nbx += dx[dir];
				nby += dy[dir];
			}
			
			if(map[nbx][nby] != 'O') { // 구멍이 아니라면, 파란구슬과 겹치거나, 벽을 만난경우 이므로 한칸뒤로
				nbx -= dx[dir];
				nby -= dy[dir];
			}
		}
		
		return new Marble(nrx, nry, nbx, nby, now.cnt);
	}
	
	private static void bfs(int r_x, int r_y, int b_x, int b_y, int cnt) {
		PriorityQueue<Marble> q = new PriorityQueue<>(new Comparator<Marble>() {

			@Override
			public int compare(Marble o1, Marble o2) {
				return o1.cnt - o2.cnt;
			}
		});
		q.offer(new Marble(r_x, r_y, b_x, b_y, cnt));
		visited[r_x][r_y][b_x][b_y] = true;
		
		while(!q.isEmpty()) {
			Marble temp = q.poll();
			int rx = temp.rx;
			int ry = temp.ry;
			int bx = temp.bx;
			int by = temp.by;
			int count = temp.cnt;
			
			if(count > 10) { // 우선순위 큐를 사용해서 탐색 했으므로 이 다음 10회이하인 탐색은 없음
				return;
			}
			
			if(bx == hole[0] && by == hole[1]) { // 동시에 도착하거나 파란구슬이 구멍으로 빠진다면
				continue;
			}
			
			if(rx == hole[0] && ry == hole[1]) { 
				answer = count;
				return;
			}
			
			for(int i=0; i<4; i++) { // 4방향으로 굴려보기
				Marble next = move(temp, i);
				if(!visited[next.rx][next.ry][next.bx][next.by]) {
					q.offer(new Marble(next.rx, next.ry, next.bx, next.by, next.cnt+1));
					visited[next.rx][next.ry][next.bx][next.by] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char [N][M];
		visited = new boolean [N][M][N][M]; // [빨간][구슬][파란][구슬]
		
		int bx = 0, by = 0, rx = 0, ry = 0;
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'B') { // 탐색을 위해 좌표만 저장한 뒤 빈공간으로 초기화
					map[i][j] = '.';
					bx = i;
					by = j;
				}
				
				if(map[i][j] == 'R') {
					map[i][j] = '.';
					rx = i;
					ry = j;
				}
				
				if(map[i][j] == 'O') {
					hole = new int[] {i, j};
				}
			}
		}
		answer = -1;
		bfs(rx, ry, bx, by, 0);
		System.out.println(answer);
	}

}
