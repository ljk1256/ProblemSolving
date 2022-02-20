package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10157 {
	
	static int[][] deltas;
	static boolean[][] visited;
	static int R;
	static int C;
	static int count;
	
	private static void search(int x, int y) { // cnt 0 이 된 시점에 x, y 좌표 출력, 모두 탐색 해도 0이 아닌경우 0 출력
		int dir = 0;
		int nx = x; 
		int ny = y; 
		
		while(!visited[nx][ny]) { // 자리가 예약 안됐을 경우
			visited[nx][ny] = true; // 예약으로 변경
			
			nx += deltas[dir % 4][0];  // 다음자리 인덱스로 변경
			ny += deltas[dir % 4][1];
			
			if(nx < 1 || ny < 1 || nx > R || ny > C || visited[nx][ny]) {
				nx -= deltas[dir % 4][0];  // 원래자리로 되돌린 후
				ny -= deltas[dir % 4][1];
				
				dir++;
				
				nx += deltas[dir % 4][0];  // 방향 전환 후 인덱스 변경
				ny += deltas[dir % 4][1];
			}
			count--;
			if(count == 0) {
				nx -= deltas[dir % 4][0];  // 원래자리로 되돌린 후
				ny -= deltas[dir % 4][1];
				
				System.out.println(nx + " " + ny);
			}
		}
		
		if(count != 0) {
			System.out.println(0);
		}
		
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		count = Integer.parseInt(br.readLine());
		
		deltas = new int [][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		visited = new boolean [R+1][C+1];
		
		search(1, 1);
	}
}
