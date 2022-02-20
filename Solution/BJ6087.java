package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ6087 {
	
	static int W;
	static int H;
	static int answer = 99999999;
	static char[][] map;
	static int[][] dp;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Laser start, end;
	
	private static void bfs() {
		
		Queue<Laser> q = new LinkedList<Laser>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			Laser temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int dir = temp.dir;
			int mirror = temp.mirror;
			
			if(temp.x == end.x && temp.y == end.y) {
				answer = Math.min(answer, mirror); // 먼저 도착하는게 가장 적지 않을 수 있음
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*') {
					continue;
				}
				
				int nm = mirror;
				if(dir != -1 && dir != i) {  // 처음 나가는 방향 -1 이 아니고 원래 진행 방향과 달라졌을때
					nm++;
				}
				
				if(dp[nx][ny] == 0) {
					dp[nx][ny] = nm;
					q.offer(new Laser(nx, ny, i, nm));
				}
				else if(dp[nx][ny] >= nm) { // 방문 했던 곳이더라도 만약 더 작은 거울이 있을경우 갱신
					dp[nx][ny] = nm;        // 같은 경우도 다시 방문 하는 이유는 그 시점에서 거울 갯수는 같을 수 있으나 현재 진행 방향이 서로 다를 수 있기때문에 다음 정점에서 거울 갯수가 달라질 수 있다. 
					q.offer(new Laser(nx, ny, i, nm));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char [H][W];
		dp = new int [H][W];
		ArrayList<Laser> list = new ArrayList<>();
		
		for(int i=0; i<H; i++) {
			String s = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'C') {
					list.add(new Laser(i, j, -1, 0));
				}
			}
		}
		start = list.get(0);
		end = list.get(1);
		
		bfs();
		System.out.println(answer);
	}

}
class Laser {
	int x;
	int y;
	int dir;
	int mirror;
	
	public Laser(int x, int y, int dir, int mirror) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.mirror = mirror;
	}

}
