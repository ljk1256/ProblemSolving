package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16234 {
	
	static int N;
	static int L;
	static int R;
	static boolean[][] visited;
	static int[][] nation;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static List<int[]> list;
	
	private static boolean isMove() {
		
		boolean flag = false;
		visited = new boolean [N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(new int [] {i, j});
					
					if(list.size() > 1) {
						int sum = 0;
						flag = true;
						
						for(int[] temp : list) {
							sum += nation[temp[0]][temp[1]];
						}
						
						sum = sum / list.size();
						
						for(int k=0; k<list.size(); k++) {
							int[] temp = list.get(k);
							nation[temp[0]][temp[1]] = sum;
						}
					}
				}
			}
		}
		return flag;
	}
	
	private static void bfs(int[] point) {
		Queue<int[]> q = new LinkedList<int[]>();
		list = new ArrayList<>();
		
		q.offer(point);
		list.add(point);
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			visited[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
					if(L <= Math.abs(nation[x][y] - nation[nx][ny]) && Math.abs(nation[x][y] - nation[nx][ny]) <= R) {
						q.offer(new int [] {nx, ny});
						list.add(new int [] {nx, ny});
						visited[nx][ny] = true;
					}
				}	
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		nation = new int [N][N];
		visited = new boolean [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				nation[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean flag = true;
		int day = 0;
		while(flag) {
			if(!isMove()) {
				flag = false;
			}
			else {
				day++;
			}
		}
		
		System.out.println(day);
	}

}
