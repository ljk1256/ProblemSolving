package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ23290 {
	
	static class Shark {
		int x;
		int y;
		
		public Shark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Fish {
		int x;
		int y;
		int dir;
		
		public Fish(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	static class Smell {
		int x;
		int y;
		int when;
		
		public Smell(int x, int y, int when) {
			super();
			this.x = x;
			this.y = y;
			this.when = when;
		}
		
	}
	
	static class Point {
		int x;
		int y;
		int kill;
		String index;
		
		public Point(int x, int y, int kill, String index) {
			super();
			this.x = x;
			this.y = y;
			this.kill = kill;
			this.index = index;
		}
		
	}
	
	static int S;
	static Shark location;
	static int[] fish_dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fish_dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] shark_dx = {-1, 0, 1, 0};
	static int[] shark_dy = {0, -1, 0, 1};
	static ArrayList<Fish>[][] before; // 복제전 배열
	static ArrayList<Fish>[][] after; // 복제후 배열
	static ArrayList<Smell>[][] fish_smell; // 냄새
	static PriorityQueue<Point> pq;
	
	static void move_fish() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(!before[i][j].isEmpty()) {
					int size = before[i][j].size();
					for(int k=0; k<size; k++) {
						Fish temp = before[i][j].get(k);
						int dir = temp.dir;
						int cnt = 0;
						
						while(cnt < 9) {
							if(cnt == 8) {
								after[temp.x][temp.y].add(new Fish(temp.x, temp.y, temp.dir));
								break;
							}
							
							int nx = temp.x + fish_dx[dir];
							int ny = temp.y + fish_dy[dir];
							
							if(nx < 0 || nx > 3 || ny < 0 || ny > 3) { // 범위를 벗어난다면
								dir = (dir + 7) % 8;
								cnt++;
								continue;
							}
							
							if(nx == location.x && ny == location.y) { // 상어가 있다면
								dir = (dir + 7) % 8;
								cnt++;
								continue;
							}
							
							if(!fish_smell[nx][ny].isEmpty()) { // 냄새가 있다면
								dir = (dir + 7) % 8;
								cnt++;
								continue;
							}
							
							after[nx][ny].add(new Fish(nx, ny, dir)); // 이동 했다면
							break;
						}
					}
				}
			}
		}
	}
	
	static void move_shark() {
		pq = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if(o1.kill != o2.kill) {
					return o2.kill - o1.kill;
				}
				else {
					return o1.index.compareTo(o2.index);
				}
				
			}
		});
		
		Shark temp = location;
		Point next = new Point(temp.x, temp.y, 0, "");
		isMove(0, next, new boolean[4][4]);
		
		Point max = pq.poll();
		int nx = temp.x;
		int ny = temp.y;
		for(int i=0; i<3; i++) {
			int dir = max.index.charAt(i) - '0';
			nx += shark_dx[dir];
			ny += shark_dy[dir];
			
			if(!after[nx][ny].isEmpty()) { // 이동하는 곳에 물고기가 있다면
				after[nx][ny].clear(); // 해당 격자 물고기 삭제
				fish_smell[nx][ny].add(new Smell(nx, ny, S)); // 해당 격자 현재 시점 냄새추가
			}
		}
		
		location = new Shark(nx, ny); // 상어 위치 갱신
	}
	
	static void isMove(int cnt, Point point, boolean[][] visited) {
		if(cnt == 3) {
			pq.offer(point);
			return;
		}
		
		Point temp = point;
		for(int i=0; i<4; i++) { // 움직일 수 있는 지 탐색
			int nx = temp.x + shark_dx[i];
			int ny = temp.y + shark_dy[i];
			
			if(nx < 0 || nx > 3 || ny < 0 || ny > 3) continue; // 범위를 벗어 난다면
			
			if(!visited[nx][ny]) { // 방문 하지 않았던 곳이라면, 문제가 생긴다면 수정필요
				visited[nx][ny] = true;
				isMove(cnt+1, new Point(nx, ny, temp.kill+after[nx][ny].size(), temp.index+String.valueOf(i)), visited); // 다음 움직임 탐색
				visited[nx][ny] = false;
			}
			else { // 방문 했던 곳이라면
				isMove(cnt+1, new Point(nx, ny, temp.kill, temp.index+String.valueOf(i)), visited); // 다음 움직임 탐색
			}
		}
	}
	
	static void smell_delete() {
		for(int i=0; i<4; i++) {
			outer : for(int j=0; j<4; j++) {
				if(!fish_smell[i][j].isEmpty()) {
					int size = fish_smell[i][j].size();
					for(int k=0; k<size; k++) {
						Smell temp = fish_smell[i][j].get(k);
						if(temp.when == S+2) {
							fish_smell[i][j].remove(k);
							continue outer;
						}
					}
				}
			}
		}
	}
	
	static void copy() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(!after[i][j].isEmpty()) {
					for(int k=0; k<after[i][j].size(); k++) {
						Fish temp = after[i][j].get(k);
						before[i][j].add(new Fish(temp.x, temp.y, temp.dir));
					}
				}
				after[i][j].clear();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken()); // 초기 물고기 수
		S = Integer.parseInt(st.nextToken()); // 연습 횟수
		
		before = new ArrayList [4][4];
		after = new ArrayList [4][4];
		fish_smell = new ArrayList [4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				before[i][j] = new ArrayList<>();
				after[i][j] = new ArrayList<>();
				fish_smell[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			before[x-1][y-1].add(new Fish(x-1, y-1, dir-1)); // 인덱스를 0부터 맞추기 위해 -1
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		location = new Shark(x-1, y-1);
		
		while(S > 0) { // 연습 횟수 만큼 반복
			move_fish();
			move_shark();
			smell_delete();
			copy();
			S--;
		}
		
		int answer = 0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(!before[i][j].isEmpty()) {
					answer += before[i][j].size();
				}
			}
		}
		System.out.println(answer);
	}
}
