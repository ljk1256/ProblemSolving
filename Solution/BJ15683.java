package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15683 {
	
	static class CCTV {
		
		int x;
		int y;
		int kind;
		
		public CCTV(int x, int y, int kind) {
			super();
			this.x = x;
			this.y = y;
			this.kind = kind;
		}
		
	}
	
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] d3 = {{0, 3}, {0, 2}, {1, 2}, {1, 3}};
	static int[][] d4 = {{0, 2, 3}, {0, 1, 2}, {1, 2, 3}, {0, 1, 3}};
	static ArrayList<CCTV> cctvList;
	static int answer;
	
	// 배열 복사를 하고 싶지 않다면, 백트래킹을 이용해야한다. >> 모든 상황마다 복사가 아닌 한번 진행했으면 다시 원래 대로 돌려놓고 다시 다음 케이스 수행
	
	private static void observe(int cnt, int[][] checked) {
		if(cnt == cctvList.size()) {
			int blindSpot = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(checked[i][j] == 0) blindSpot++;
				}
			}
			answer = Math.min(answer, blindSpot);
			return;
		}
		
		CCTV tempcctv = cctvList.get(cnt);
		
		switch(tempcctv.kind) { // tempSum 감시 방향이 바뀔때 마다 초기화
			case 1 : {
				for(int i=0; i<4; i++) {
					int[][] newmap = new int [N][M];
					for(int j=0; j<N; j++) {
						System.arraycopy(checked[j], 0, newmap[j], 0, checked[j].length);
					}
					
					int nx = tempcctv.x;
					int ny = tempcctv.y;
					while(true) {
						nx += dx[i];
						ny += dy[i];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					observe(cnt+1, newmap);
				}
				break;
			}
			
			case 2 : {
				int idx = 0;
				for(int i=0; i<2; i++) { // 0 상하, 1 좌우
					int[][] newmap = new int [N][M];
					for(int j=0; j<N; j++) {
						System.arraycopy(checked[j], 0, newmap[j], 0, checked[j].length);
					}
					
					int nx = tempcctv.x;
					int ny = tempcctv.y;
					
					while(true) { // 상
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					idx++;
					nx = tempcctv.x;
					ny = tempcctv.y;
					while(true) { // 하
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					observe(cnt+1, newmap);
					idx++; // 좌 우 탐색을 위해 증가
				}
				break;
			}
			
			case 3 : {
				for(int i=0; i<4; i++) { // 0 상우, 1 상좌, 2 하좌, 3 하우
					int[][] newmap = new int [N][M];
					for(int j=0; j<N; j++) {
						System.arraycopy(checked[j], 0, newmap[j], 0, checked[j].length);
					}
					
					int idx = d3[i][0];
					int nx = tempcctv.x;
					int ny = tempcctv.y;
					
					while(true) { // 상
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					idx = d3[i][1];
					nx = tempcctv.x;
					ny = tempcctv.y;
					while(true) { // 하
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					observe(cnt+1, newmap);
				}
				break;
			}
			
			case 4 : {
				for(int i=0; i<4; i++) {
					int[][] newmap = new int [N][M];
					for(int j=0; j<N; j++) {
						System.arraycopy(checked[j], 0, newmap[j], 0, checked[j].length);
					}
					
					int idx = d4[i][0];
					int nx = tempcctv.x;
					int ny = tempcctv.y;
					
					while(true) { // 상
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					idx = d4[i][1];
					nx = tempcctv.x;
					ny = tempcctv.y;
					while(true) { // 하
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					idx = d4[i][2];
					nx = tempcctv.x;
					ny = tempcctv.y;
					while(true) { // 하
						nx += dx[idx];
						ny += dy[idx];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
					
					observe(cnt+1, newmap);
				}
				break;
			}
			
			case 5 : {
				int[][] newmap = new int [N][M];
				for(int i=0; i<N; i++) {
					System.arraycopy(checked[i], 0, newmap[i], 0, checked[i].length);
				}
				
				for(int i=0; i<4; i++) {
					int nx = tempcctv.x;
					int ny = tempcctv.y;
					
					while(true) { // 상
						nx += dx[i];
						ny += dy[i];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || newmap[nx][ny] == 6) break;
						
						if(1 <= newmap[nx][ny] && newmap[nx][ny] <= 5) continue;
						else newmap[nx][ny] = 7;
					}
				}
				
				observe(cnt+1, newmap);
			}
			break;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		cctvList = new ArrayList<>();
		answer = N*M;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					if(map[i][j] == 6) {
						answer--;
						continue;
					}
					cctvList.add(new CCTV(i, j, map[i][j]));
					answer--;
				}
			}
		}
		
		observe(0, map);
		System.out.println(answer);
	}

}
