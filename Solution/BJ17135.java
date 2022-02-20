package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17135 {
	
	static class Enemy {
		
		int x;
		int y;
		int dis;
		
		public Enemy(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		
	}
	
	static int N;
	static int M;
	static int D;
	static int[][] map;
	static int answer;
	
	private static void comb(int cnt, int idx, int[] selected) {
		if(cnt == 3) {
			int[][] copymap = new int [N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, copymap[i], 0, map[i].length);
			}
			
			playDefence(copymap, selected);
			return;
		}
		
		for(int i=idx; i<M; i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1, selected);
		}
	}
	
	private static void playDefence(int[][] startmap, int[] archerIdxs) {
		ArrayList<Enemy> archers = new ArrayList<>();
		for(int i=0; i<archerIdxs.length; i++) {
			archers.add(new Enemy(N, archerIdxs[i], 0));
		}
		
		ArrayList<Enemy> enemys = new ArrayList<>();
		
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				if(startmap[i][j] == 1) enemys.add(new Enemy(i, j, 0));
			}
		}
		
		PriorityQueue<Enemy>[] killLists = new PriorityQueue [3];
		for(int i=0; i<3; i++) {
			killLists[i] = new PriorityQueue<>(new Comparator<Enemy>() {

				@Override
				public int compare(Enemy o1, Enemy o2) {
					if(o1.dis == o2.dis) {
						return o1.y - o2.y;
					}
					return o1.dis - o2.dis;
				}
			});
		}
		
		int tempDied = 0;
		while(!enemys.isEmpty()) { // 턴이 끝날때마다 리스트 큐 초기화 필수 다음 단계 맵까지 그려놓고 다음 턴 진행
			
			for(int i=0; i<3; i++) {
				Enemy archer = archers.get(i);
				int archer_x = archer.x;
				int archer_y = archer.y;
				
				for(int j=0; j<enemys.size(); j++) {
					Enemy enemy = enemys.get(j);
					int enemy_x = enemy.x;
					int enemy_y = enemy.y;
					int distance = Math.abs(archer_x - enemy_x) + Math.abs(archer_y - enemy_y);
					
					if(distance <= D) {
						killLists[i].offer(new Enemy(enemy_x, enemy_y, distance));
					}
				}
			}
			
			for(int i=0; i<3; i++) {
				if(!killLists[i].isEmpty()) {
					Enemy diedEnemy = killLists[i].poll();
					startmap[diedEnemy.x][diedEnemy.y] = 0;
				}
			}
			
			for(int i=0; i<enemys.size(); i++) {
				Enemy moveEnemy = enemys.get(i);
				if(startmap[moveEnemy.x][moveEnemy.y] == 0) {
					tempDied++;
					continue;
				}
				else if(moveEnemy.x+1 == N) {
					startmap[moveEnemy.x][moveEnemy.y] = 0;
					continue;
				}
				else {
					startmap[moveEnemy.x][moveEnemy.y] = 0;
					startmap[moveEnemy.x+1][moveEnemy.y] = 1;
				}
			}
			
			enemys.clear(); // 모든 자료구조 초기화
			for(int i=0; i<3; i++) {
				killLists[i].clear();
			}
			
			for(int i=N-1; i>=0; i--) {
				for(int j=0; j<M; j++) {
					if(startmap[i][j] == 1) enemys.add(new Enemy(i, j, 0));
				}
			}
		}
		
		answer = Math.max(tempDied, answer);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // row
		M = Integer.parseInt(st.nextToken()); // column
		D = Integer.parseInt(st.nextToken()); // 거리
		
		map = new int [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		comb(0, 0, new int [3]);
		
		System.out.println(answer);
	}

}
