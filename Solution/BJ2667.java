package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BJ2667 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int [N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		int cnt = 1;
		boolean[][] visited = new boolean [N][N];
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					cnt = 1;
					Queue<int[]> q = new LinkedList<>();
					q.offer(new int [] {i, j});
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] temp = q.poll();
						int x = temp[0];
						int y = temp[1];
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
							
							if(map[nx][ny] == 1) {
								q.offer(new int [] {nx, ny});
								visited[nx][ny] = true;
								cnt++;
							}
						}
					}
					
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		sb.append(list.size()).append("\n");
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i)).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
}
