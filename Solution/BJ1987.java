package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1987 {
	
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer;
	static int R;
	static int C;
	
	private static void dfs(int x, int y, boolean[] check, int cnt) {
		
		int r = x;
		int c = y;
		int count = cnt;
		boolean[] v = check;
		answer = Math.max(answer, count); // 함수 호출시 방문횟수 갱신
		
		for(int i=0; i<4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
				continue;
			}
			
			if(!v[map[nr][nc] - 'A']) {
				v[map[nr][nc] - 'A'] = true; // 지나온 알파벳 체크
				dfs(nr, nc, v, cnt+1); // 다음 경로 탐색
				v[map[nr][nc] - 'A'] = false; // 백트래킹
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char [R][C];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		answer = 0;
		boolean[] check = new boolean [26];
		check[map[0][0] - 'A'] = true;
		dfs(0, 0, check, 1);
		
		System.out.println(answer);
	}

}
