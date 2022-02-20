package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502 {
	
	static int N;
	static int M;
	static int SAFE = 0;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	static int[][] lab;
	static int[][] copy_lab;
	
	private static void safe() {
		
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy_lab[i][j] == 0) {
					count++;
				}
			}
		}
		SAFE = Math.max(SAFE, count);
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy_lab[i][j] == 2) {
					q.offer(new int [] {i, j});
					
					while(!q.isEmpty()) {
						int[] temp = q.poll();
						int x = temp[0];
						int y = temp[1];
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
								if(copy_lab[nx][ny] == 0) {
									copy_lab[nx][ny] = 2;
									q.offer(new int [] {nx, ny});
								}
							}
						}
					}
				}
			}
		}
		safe();
	}
	
	private static void wall(int cnt) {
		
		if(cnt == 3) {
			copy_lab = new int [N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					copy_lab[i][j] = lab[i][j];
				}
			}
			bfs();
		}
		else {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					
					if(lab[i][j] == 0) {
						lab[i][j] = 1;
						wall(cnt + 1);
						lab[i][j] = 0;
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
		
		lab = new int [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		wall(0);
		System.out.println(SAFE);
	}

}
