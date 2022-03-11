package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17779 {
	
	static int[][] map;
	static int N;
	static int minAns;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	private static void comb() {
		int x, y, d1, d2;
		int[][] section = new int [N][N];
		boolean[][] visited = new boolean [N][N];
		int one, two, three, four, five;
		
		for(int i=0; i<N; i++) { // x, y 좌표
			for(int j=0; j<N; j++) {
				
				for(int k=0; k<N; k++) { // d1, d2
					for(int t=0; t<N; t++) {
						x = i; 
						y = j;
						d1 = k;
						d2 = t;
						
						if(x >= x+d1+d2 || x+d1+d2 > N-1 || y-d1 < 0 || y-d1 >= y || y >= y+d2 || y+d2 > N-1) continue;
						
						for(int q=0; q<N; q++) { // 구역 초기화
							Arrays.fill(section[q], 0);
							Arrays.fill(visited[q], false);
						}
						
						for(int q=x, w=y; q<=x+d1; q++, w--) section[q][w] = 5; // 경계선 표기
						for(int q=x, w=y; q<=x+d2; q++, w++) section[q][w] = 5;
						for(int q=x+d1, w=y-d1; q<=x+d1+d2; q++, w++) section[q][w] = 5;
						for(int q=x+d2, w=y+d2; q<=x+d1+d2; q++, w--) section[q][w] = 5;
						
						Queue<int []> queue = new LinkedList<>();
						queue.offer(new int [] {0, 0}); // 5번 경게선 꼭지점을 바람개비 모양으로 탐색을 하는게 맞다
						visited[0][0] = true;
						
						while(!queue.isEmpty()) {
							int[] temp = queue.poll();
							int r = temp[0];
							int c = temp[1];
							
							if(0 <= r && r < x+d1 && 0 <= c && c <= y) section[r][c] = 1;
							else if(0 <= r && r <= x+d2 && y < c && c <= N-1) section[r][c] = 2;
							else if(x+d1 <= r && r <= N-1 && 0 <= c && c < y-d1+d2) section[r][c] = 3;
							else if(x+d2 < r && r <= N-1 && y-d1+d2 <= c && c <= N-1) section[r][c] = 4;
							
							for(int e=0; e<4; e++) {
								int nr = r + dx[e];
								int nc = c + dy[e];
								
								if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
								
								if(section[nr][nc] == 0) {
									queue.offer(new int [] {nr, nc});
									visited[nr][nc] = true;
								}
							}
						}
						
						one = two = three = four = five = 0; // 인구수 누적 초기화
						
						for(int e=0; e<N; e++) {
							for(int r=0; r<N; r++) {
								if(section[e][r] == 0 || section[e][r] == 5) five += map[e][r];
								else if(section[e][r] == 1) one += map[e][r];
								else if(section[e][r] == 2) two += map[e][r];
								else if(section[e][r] == 3) three += map[e][r];
								else four += map[e][r];
							}
						}
						
						int min = Math.min(one, Math.min(two, Math.min(three, Math.min(four, five))));
						int max = Math.max(one, Math.max(two, Math.max(three, Math.max(four, five))));
						
						minAns = Math.min(minAns, max-min);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minAns = Integer.MAX_VALUE;
		comb();
		
		System.out.println(minAns);
	}

}
