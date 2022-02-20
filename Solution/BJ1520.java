package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1520 {
	
	static int M;
	static int N;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static int[][] map;
	static int[][] dp;
	
	private static int dfs(int x, int y) {
		
		if(x == M && y == N) {
			return 1;
		}
		
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		else {
			dp[x][y] = 0; // 중요함 -1에서 연산되면 값이 달라 질 수 있음
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 1 && ny >= 1 && nx < M+1 && ny < N+1) {
					if(map[x][y] > map[nx][ny]) {
						dp[x][y] += dfs(nx, ny);
					}
				}
			}
		}
		return dp[x][y];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int [M+1][N+1];
		dp = new int [M+1][N+1];
		
		for(int i=1; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<M+1; i++) { // 0 이 아닌 -1로 초기화를 하는 이유 : 0은 그 길로 갈 곳이 없는 의미인데, 탐색을 안했다는 의미가 겹쳐 중복탐색 발생할 수 있음
			for(int j=1; j<N+1; j++) {
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(1, 1));
	}

}
