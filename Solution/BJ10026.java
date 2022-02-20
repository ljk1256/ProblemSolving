package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ10026 {
	
	static int[][] delta;
	static boolean[][] visited;
	static String[][] matrix;
	static int N;
	static int count;
	
	private static void bfs(int a, int b) {
		
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		
		q.offer(new Integer[] {a, b});
		while(!q.isEmpty()) {
			Integer[] temp = q.poll();    //
			int x = temp[0];
			int y = temp[1];
			String s = matrix[x][y];
			
			for(int j=0; j<4; j++) {
				int nx = x + delta[j][0];
				int ny = y + delta[j][1];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {  // 조건을 무의미 하게 탐색 하지않게 조건문안에서 방문처리 - 박예진 교수님
	
					if(!visited[nx][ny] && s.equals(matrix[nx][ny])) {  // 큐를 초기화 한다. - 박예진 교수님
						q.offer(new Integer[] {nx, ny}); // 큐에 넣는 순간 방문처리 해야함
						visited[nx][ny] = true;
					}
				}
			}	
		}
		count++;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		delta = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		N = Integer.parseInt(br.readLine());
		matrix = new String [N][N];
		visited = new boolean [N][N];
		count = 0;
		
		for(int i=0; i<N; i++) {	
			matrix[i] = br.readLine().split("");
		}
		
		for(int i=0; i<N; i++) { // 다음 그룹 탐색
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		sb.append(count);
		sb.append(" ");
			
		count = 0;
		visited = new boolean [N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(matrix[i][j].equals("G")) {
					matrix[i][j] = "R";
				}
			}
		}
		
		for(int i=0; i<N; i++) { // 다음 그룹 탐색
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		sb.append(count);
		System.out.println(sb.toString());
	}
	
}
