package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503 {
	
	static class Robot {
		
		int x;
		int y;
		int dir;
		
		public Robot(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static Robot robot;
	static boolean flag;
	static int answer;
	
	private static boolean isMove(int row, int column, int dir) {
		switch(dir) {
		case 0 : {
			if(map[row+1][column] != 1) return true;
			else return false;
		}
		case 1 : {
			if(map[row][column-1] != 1) return true;
			else return false;
		}
		case 2 :{
			if(map[row-1][column] != 1) return true;
			else return false;
		}
		case 3 :{
			if(map[row][column+1] != 1) return true;
			else return false;
		}
		}
		return true;
	}
	
	private static void Clean() {
		if(!visited[robot.x][robot.y]) {
			visited[robot.x][robot.y] = true;
			answer++;
		}

		int cnt = 0;
		while(true) {
			
			int nx = robot.x + dx[robot.dir];
			int ny = robot.y + dy[robot.dir];
			cnt++;
			
			if(map[nx][ny] != 1 && !visited[nx][ny]) {
				robot.x = nx;
				robot.y = ny;
				robot.dir = (robot.dir + 3) % 4;
				return;
			}
			
			if(cnt == 4) {
				robot.dir = (robot.dir + 3) % 4;
				if(isMove(robot.x, robot.y, robot.dir)) {
					if(robot.dir == 0) robot.x += 1;
					else if(robot.dir == 1) robot.y -= 1;
					else if(robot.dir == 2) robot.x -= 1;
					else robot.y += 1;
					
					cnt = 0;
					continue;
				}
				else flag = false;
				
				return;
			}
			
			if(map[nx][ny] == 1 || visited[nx][ny]) {
				robot.dir = (robot.dir + 3) % 4;
				continue;
			}
			
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		visited = new boolean [N][M];
		
		st = new StringTokenizer(br.readLine());
		robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		flag = true;
		answer = 0;
		while(flag) {
			Clean();
		}
		
		System.out.println(answer);
	}

}
