package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ1938 {
	
	static class tree {
		
		int x;
		int y;
		int time;
		boolean stat; // true 세로, false 가로
		
		public tree(int x, int y, int time, boolean stat) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.stat = stat;
		}
		
	}
	
	static int N;
	static tree B_center;
	static tree E_center;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static char[][] map;
	
	private static boolean check(int x, int y) { // 8방향 보면서 회전을 못하면 경우 false 리턴
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) return false;
			
			if(map[nx][ny] == '1') { // 나무가 있다면
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char [N][N];
		
		ArrayList<tree> B_list = new ArrayList<>();
		ArrayList<tree> E_list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'B') {
					B_list.add(new tree(i, j, 0, false));
				}
				
				else if(map[i][j] == 'E') {
					E_list.add(new tree(i, j, 0, false));
				}
			}
		}
		
		if(B_list.get(0).x == B_list.get(1).x) { // 가로로 된것
			B_center = new tree(B_list.get(1).x, B_list.get(1).y, 0, false);
		}
		else {
			B_center = new tree(B_list.get(1).x, B_list.get(1).y, 0, true);
		}
		
		if(E_list.get(0).x == E_list.get(1).x) { // 가로로 된것
			E_center = new tree(E_list.get(1).x, E_list.get(1).y, 0, false);
		}
		else {
			E_center = new tree(E_list.get(1).x, E_list.get(1).y, 0, true);
		}
		
		PriorityQueue<tree> pq = new PriorityQueue<>(new Comparator<tree>() {

			@Override
			public int compare(tree o1, tree o2) {
				return o1.time - o2.time;
			}
		});
		
		pq.offer(B_center);
		boolean[][][] visited = new boolean [N][N][2]; // [0]은 세로일때 방문, [1]은 가로일때 방문 체크
		while(!pq.isEmpty()) { // 모든 이동은 중심점을 기준으로 이동(회전하거나 이동시킬때 중심을 기준으로 확인하는 아이디어)
			tree temp = pq.poll();
			
			if(temp.x == E_center.x && temp.y == E_center.y && temp.stat == E_center.stat) { // E 자리로 이동했다면
				System.out.println(temp.time);
				return;
			}
			
			if(temp.stat) { // 지금 상태가 세로일때
				// 상
				if(temp.x-2 >= 0 && map[temp.x-2][temp.y] != '1' && !visited[temp.x-1][temp.y][0]) {
					pq.offer(new tree(temp.x-1, temp.y, temp.time+1, temp.stat));
					visited[temp.x-1][temp.y][0] = true;
				}
				// 하
				if(temp.x+2 <= N-1 && map[temp.x+2][temp.y] != '1' && !visited[temp.x+1][temp.y][0]) {
					pq.offer(new tree(temp.x+1, temp.y, temp.time+1, temp.stat));
					visited[temp.x+1][temp.y][0] = true;
				}
				// 좌
				if(temp.y-1 >= 0 && map[temp.x-1][temp.y-1] != '1' && map[temp.x+1][temp.y-1] != '1' && map[temp.x][temp.y-1] != '1' && !visited[temp.x][temp.y-1][0]) {
					pq.offer(new tree(temp.x, temp.y-1, temp.time+1, temp.stat));
					visited[temp.x][temp.y-1][0] = true;
				}
				// 우
				if(temp.y+1 <= N-1 && map[temp.x-1][temp.y+1] != '1' && map[temp.x+1][temp.y+1] != '1' && map[temp.x][temp.y+1] != '1' && !visited[temp.x][temp.y+1][0]) {
					pq.offer(new tree(temp.x, temp.y+1, temp.time+1, temp.stat));
					visited[temp.x][temp.y+1][0] = true;
				}
				// 회전
				if(check(temp.x, temp.y) && !visited[temp.x][temp.y][1]) {
					pq.offer(new tree(temp.x, temp.y, temp.time+1, !temp.stat));
					visited[temp.x][temp.y][1] = true;
				}
			}
			
			else { // 가로 일때
				// 상
				if(temp.x-1 >= 0 && map[temp.x-1][temp.y-1] != '1' && map[temp.x-1][temp.y] != '1' && map[temp.x-1][temp.y+1] != '1' && !visited[temp.x-1][temp.y][1]) {
					pq.offer(new tree(temp.x-1, temp.y, temp.time+1, temp.stat));
					visited[temp.x-1][temp.y][1] = true;
				}
				// 하
				if(temp.x+1 <= N-1 && map[temp.x+1][temp.y-1] != '1' && map[temp.x+1][temp.y] != '1' && map[temp.x+1][temp.y+1] != '1' && !visited[temp.x+1][temp.y][1]) {
					pq.offer(new tree(temp.x+1, temp.y, temp.time+1, temp.stat));
					visited[temp.x+1][temp.y][1] = true;
				}
				// 좌
				if(temp.y-2 >= 0 && map[temp.x][temp.y-2] != '1' && !visited[temp.x][temp.y-1][1]) {
					pq.offer(new tree(temp.x, temp.y-1, temp.time+1, temp.stat));
					visited[temp.x][temp.y-1][1] = true;
				}
				// 우
				if(temp.y+2 <= N-1 && map[temp.x][temp.y+2] != '1' && !visited[temp.x][temp.y+1][1]) {
					pq.offer(new tree(temp.x, temp.y+1, temp.time+1, temp.stat));
					visited[temp.x][temp.y+1][1] = true;
				}
				// 회전
				if(check(temp.x, temp.y) && !visited[temp.x][temp.y][0]) {
					pq.offer(new tree(temp.x, temp.y, temp.time+1, !temp.stat));
					visited[temp.x][temp.y][0] = true;
				}
			}
			
		}
		
		System.out.println(0);
	}

}
