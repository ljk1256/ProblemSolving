package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17472ing {
	
	static int N;
	static int M;
	static int total;
	static int minlen = 99999999;
	static int[][] island;
	static boolean[][] copy;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static List<List<Nation>> list = new ArrayList<>();
	
	private static int cntisland() {
		int cnt = 0;
		boolean[][] visited = new boolean [N][M];
		Queue<Nation> cq = new LinkedList<Nation>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy[i][j]) {
					cq.offer(new Nation(i, j));
					cnt = cnt + 1;
					
					while(!cq.isEmpty()) {
						Nation temp = cq.poll();
						int x = temp.x;
						int y = temp.y;
						visited[x][y] = true;
						copy[x][y] = false;
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
								if(!visited[nx][ny] && copy[nx][ny]) {
									cq.offer(new Nation(nx, ny));
									visited[nx][ny] = true;
								}
							}
						}
					}
				}
			}
		}
		return cnt;
	}
	
	private static void bridge(int bridcnt, int lencnt) {
			
		if(bridcnt == total) {
			minlen = Math.min(lencnt, minlen);
			return;
		}
		int cnt = 0;	
		while(cnt < list.size()) {
			List<Nation> temp = list.get(cnt);
			int x = temp.x;
			int y = temp.y;
			
			for(int i=0; i<4; i++) {
				int nx = x;
				int ny = y;
				int count = 0;
						
				while(true) {
					nx = nx + dx[i];
					ny = ny + dy[i];
						
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
						count = 0;
						break;
					}
						
					if(island[nx][ny] == 1) {
						break;
					}
						
					count = count + 1;
				}
					
				if(count <= 1) {
					cnt++;
					continue;
				}
				else {
					int fx = x;
					int fy = y;
					for(int j=0; j<count; j++) { // count +1
						fx = fx + dx[i];
						fy = fy + dy[i];
						island[fx][fy] = 2;
					}
					/*for(int k=0; k<N; k++) {
						System.out.println();
						for(int t=0; t<M; t++) {
							System.out.print(island[k][t] + " ");
						}
					}
					System.out.println();*/
					bridge(bridcnt+1, lencnt+count);
					
					fx = x;
					fy = y;
					for(int j=0; j<count; j++) {
						fx = fx + dx[i];
						fy = fy + dy[i];
						island[fx][fy] = 0;
					}
				}
				
			}
			cnt++;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		island = new int [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				island[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		int rabel = 0;
		boolean[][] visited = new boolean [N][M];
		Queue<Nation> cq = new LinkedList<Nation>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(island[i][j] != 0 && island[i][j] != rabel) {
					rabel++;
				}

				if(island[i][j] != 0) {
					cq.offer(new Nation(i, j));
					
					while(!cq.isEmpty()) {
						Nation temp = cq.poll();
						int x = temp.x;
						int y = temp.y;
						visited[x][y] = true;
						island[x][y] = rabel;
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
								if(!visited[nx][ny] && island[nx][ny] != 0) {
									cq.offer(new Nation(nx, ny));
									visited[nx][ny] = true;
								}
							}
						}
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(island[i][j] + " ");
			}
			System.out.println();
		}
	}

}

class Nation {
	int x;
	int y;
	
	public Nation(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}