package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1012 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			boolean[][] map = new boolean [r][c];
			boolean[][] visited = new boolean [r][c];
			
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				int cabbage_y = Integer.parseInt(st.nextToken());
				int cabbage_x = Integer.parseInt(st.nextToken());
				
				map[cabbage_x][cabbage_y] = true;
			}
			
			int cnt = 0;
			for(int j=0; j<r; j++) {
				for(int k=0; k<c; k++) {
					if(map[j][k] && !visited[j][k]) {
						Queue<int[]> q = new LinkedList<>();
						q.offer(new int[] {j, k});
						visited[j][k] = true;
						
						while(!q.isEmpty()) {
							int[] temp = q.poll();
							int x = temp[0];
							int y = temp[1];
							
							for(int t=0; t<4; t++) {
								int nx = x + dx[t];
								int ny = y + dy[t];
								
								if(nx < 0 || nx > r-1 || ny < 0 || ny > c-1 || visited[nx][ny]) continue;
								
								if(map[nx][ny]) {
									q.offer(new int [] {nx, ny});
									visited[nx][ny] = true;
								}
							}
						}
						cnt++;
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
