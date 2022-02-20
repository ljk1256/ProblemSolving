package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2933 {
	
	static int R;
	static int C;
	static char[][] cave;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<int[]> cluster = new ArrayList<>();
	
	private static void move() { // 클러스터 이동이 전부 끝났다면 클러스터를 비워줘야 한다.
		
		for(int i=0; i<cluster.size(); i++) {
			int[] temp = cluster.get(i);
			cave[temp[0]][temp[1]] = '.';
		}
		
		int size = 0;
	bp:	for(int i=1; i<R; i++) {
			for(int j=0; j<cluster.size(); j++) { // 클러스터 완전 탐색
				int[] temp = cluster.get(j);
				int x = temp[0];
				int y = temp[1];
				
				if(x - i < 1 || cave[x - i][y] == 'x') {
					break bp;
				}
			}
			size++;
		}
		
		for(int i=0; i<cluster.size(); i++) {
			int[] temp = cluster.get(i);
			cave[temp[0] - size][temp[1]] = 'x';
		}
		
		cluster.clear();
	}
	
	private static boolean isCluster() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean [R+1][C+1];
		
		for(int i=1; i<C+1; i++) { // 1층에 있는 모든 클러스터 저장
			if(cave[1][i] == 'x') {
				q.offer(new int[] {1, i});
				visited[1][i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				
				if(nx < 1 || nx >= R+1 || ny < 1 || ny >= C+1 || visited[nx][ny]) {
					continue;
				}
				
				if(cave[nx][ny] == 'x') {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		for(int i=1; i<R+1; i++) {
			for(int j=1; j<C+1; j++) {
				if(cave[i][j] == 'x' && !visited[i][j]) {
					cluster.add(new int[] {i, j});
				}
			}
		}
		
		if(cluster.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private static void stick(boolean dir, int row) { // 한번 던질때마다 체크하고 있다면 클러스터 이동
		
		boolean flag = dir;
		if(flag) { // true 왼쪽
			int c = 1;
			while(c < C){
				if(cave[row][c] != 'x') { // 미네랄 만날때까지
					c++;
				}
				if(cave[row][c] == 'x') {
					cave[row][c] = '.';
					break;
				}
			}
		}
		else {  // false 오른쪽
			int c = C;
			while(c > 1){
				if(cave[row][c] != 'x') {
					c--;
				}
				if(cave[row][c] == 'x') {
					cave[row][c] = '.';
					break;
				}
			}
		}
		
		if(isCluster()) { // 클러스터가 새로생겼는지 확인
			move();
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cave = new char [R+1][C+1];
		for(int i=R; i>=1; i--) {
			String s = br.readLine();
			for(int j=1; j<=C; j++) {
				cave[i][j] = s.charAt(j-1);
			}
		}
	
		int N = Integer.parseInt(br.readLine());
		boolean dir = true;
		st = new StringTokenizer(br.readLine());
		while(N > 0) {
			int row = Integer.parseInt(st.nextToken());
			stick(dir, row);
			dir = !dir; // 방향전환
			N--; // 던지는 횟수
		}
		
		for(int i=R; i>=1; i--) { // 배열 반전시켜서 출력
			for(int j=1; j<=C; j++) {
				System.out.print(cave[i][j]);
			}
			System.out.println();
		}
	}

}
