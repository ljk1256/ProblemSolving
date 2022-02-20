package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1941 {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] girl;
	static int answer;
	//static boolean[][] visited;
	
	private static void comb(int[][] arr, int idx, int cnt, int y_cnt) {
		
		if(y_cnt >= 4) return; // 임도연파가 4이상이면 더이상 볼 필요가 없다.
		
		if(cnt == 7) {
			if(check(arr, (idx-1) / 5, (idx-1) % 5)) { // 이다솜파 4명 이상 이면서 한붓그리기 가능하면 조합가능
				answer++;
				return;
			}
			else {
				return;
			}
		}
		
		for(int i=idx; i<25; i++) {
			int x = i / 5;
			int y = i % 5;
			
			if(arr[x][y] == 0) {
				arr[x][y] = -1;
				if(girl[x][y] == 'Y') {
					comb(arr, i+1, cnt+1, y_cnt+1);
				}
				else {
					comb(arr, i+1, cnt+1, y_cnt);
				}
				arr[x][y] = 0;
			}
		}
	}
	
	private static boolean check(int[][] arr, int x, int y) {
		int count = 1;
		Queue<int []> q = new LinkedList<>();
		boolean[][] visited = new boolean [5][5];
		q.offer(new int [] {x, y});
		
		while(!q.isEmpty()) {	
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			visited[r][c] = true;
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc]) continue;
				
				if(arr[nr][nc] == -1) {
					q.offer(new int [] {nr, nc});
					visited[nr][nc] = true;
					count++;
				}
			}
		}
		
		if(count == 7) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		girl = new char [5][5];
		int[][] arr = new int [5][5];
		for(int i=0; i<5; i++) {
			String s = br.readLine();
			for(int j=0; j<5; j++) {
				girl[i][j] = s.charAt(j);
			}
		}
		
		answer = 0;
		comb(arr, 0, 0, 0);
		System.out.println(answer);
	}

}
class Seven {
	
	int x;
	int y;
	
	public Seven(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
