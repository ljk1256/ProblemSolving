package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7569 {
	
	static class Tomato {
		
		int x;
		int y;
		int z;
		int day;
		
		public Tomato(int x, int y, int z, int day) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
		
	}
	
	static int M;
	static int N;
	static int H;
	static int[] dx = {0, 0, -1, 1, 0, 0}; // 위층, 아래층 + 4방탐색
	static int[] dy = {0, 0, 0, 0, -1, 1};
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[][][] box;
	
	static boolean isGrown() {
		for(int i=H-1; i>=0; i--) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) { // 익지 않은 토마토가 한개라도 있다면
					if(box[j][k][i] == 0) return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // column
		N = Integer.parseInt(st.nextToken()); // row
		H = Integer.parseInt(st.nextToken());
		
		Queue<Tomato> q = new LinkedList<>();
		boolean flag = true; // 처음부터 모두 익었는지 체크
		box = new int [N][M][H]; // 토마토 격자
		
		for(int i=H-1; i>=0; i--) { // 아래층 부터
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<M; k++) {
					box[j][k][i] = Integer.parseInt(st.nextToken());
					if(box[j][k][i] == 0) {
						flag = false;
					}
					else if(box[j][k][i] == 1) {
						q.offer(new Tomato(j, k, i, 0)); // 익은 토마토 큐 추가
					}
				}
			}
		}
		
		if(flag) { // 처음부터 모두 익었다면
			System.out.println(0);
			return;
		}
		else {
			int day = 0;
			while(!q.isEmpty()) {
				Tomato temp = q.poll();
				if(temp.day > day) day = temp.day; // 최대일수 갱신
				
				for(int i=0; i<6; i++) {
					int nx = temp.x + dx[i];
					int ny = temp.y + dy[i];
					int nz = temp.z + dz[i];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= M || nz < 0 || nz >= H || box[nx][ny][nz] == 1) continue; // 범위를 벗어나거나 이미 익은 토마토라면
					
					if(box[nx][ny][nz] == -1) continue; // 빈 공간이라면
					
					else if(box[nx][ny][nz] == 0) { // 익지 않는 토마토가 있다면
						box[nx][ny][nz] = 1;
						q.offer(new Tomato(nx, ny, nz, temp.day+1));
					}
				}
			}
			
			if(isGrown()) { // 모두 익었다면
				System.out.println(day);
				return;
			}
			else { // 모두 익지 않았다면
				System.out.println(-1);
				return;
			}
		}
	}

}
