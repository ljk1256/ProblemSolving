package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4963 {
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) {
				return;
			}
			
			int[][] map = new int [h][w];
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			Queue<Island> q = new LinkedList<>();
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j] != 0) {
						q.offer(new Island(i, j));
						answer++;
						
						while(!q.isEmpty()) {
							Island temp = q.poll();
							int x = temp.x;
							int y = temp.y;
							
							for(int k=0; k<8; k++) {
								int nx = x + dx[k];
								int ny = y + dy[k];
								
								if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 0) {
									continue;
								}
								
								if(map[nx][ny] != 0) {
									q.offer(new Island(nx, ny));
									map[nx][ny] = 0;
								}
							}
						}
					}
				}
			}
			System.out.println(answer);
		}
	}		
}

class Island {
	
	int x;
	int y;
	
	public Island(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
