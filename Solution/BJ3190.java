package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ3190 {
	
	static int N;
	static int[][] apple;
	static int[][] way= {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	
	static class Command {
		
		int time;
		char dir;
		
		public Command(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
		
	}
	
	static class Snake {
		
		int x;
		int y;
		int dir;
		
		public Snake(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	private static int turn(int dir, char c) {
		int ndir = 0;
		switch(dir) {
		case 0 : {
			if(c == 'L') ndir = 3;
			else ndir = 2;
			break;
		}
		case 1 : {
			if(c == 'L') ndir = 2;
			else ndir = 3;
			break;
		}
		case 2 : {
			if(c == 'L') ndir = 0;
			else ndir = 1;
			break;
		}
		case 3 : {
			if(c == 'L') ndir = 1;
			else ndir = 0;
			break;
		}
		}
		return ndir;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 맵의 크기
		int K = Integer.parseInt(br.readLine()); // 사과 개수
		
		apple = new int [N+1][N+1];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			apple[x][y] = 1; // 사과는 1, 빈공간은 0, 뱀은 2
		}
		
		int L = Integer.parseInt(br.readLine()); // 방향 전환 개수
		Command[] list = new Command [10001]; // 시간과 인덱스 일치
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			list[time] = new Command(time, c);
		}
		
		Deque<Snake> dq = new LinkedList<>();
		dq.offerFirst(new Snake(1, 1, 1));
		apple[1][1] = 2; // 앞으로 뱀의 위치는 2로 표현
		int time = 0;
		while(true) {
			time++;
			
			Snake temp = dq.getFirst();
			int x = temp.x;
			int y = temp.y;
			int dir = temp.dir;
			
			int nx = x + way[dir][0];
			int ny = y + way[dir][1];
			
			if(nx < 1 || nx > N || ny < 1 || ny > N || apple[nx][ny] == 2) break; // 벽을 만나거나 몸통을 만났을경우
			
			if(apple[nx][ny] == 0) { // 사과가 없는 경우  꼬리를 당겨 준다
				if(list[time] != null) { // 방향 전환 정보가 있다면
					int ndir = turn(dir, list[time].dir);
					dq.offerFirst(new Snake(nx, ny, ndir)); // 머리의 위치와 방향 갱신후 덱 앞에 넣기
					Snake tail = dq.pollLast(); // 꼬리부분 빼기
					apple[tail.x][tail.y] = 0;
					apple[nx][ny] = 2; // 뱀이 자리를 차지함
				}
				else { // 방향 전환 정보가 없다면
					dq.offerFirst(new Snake(nx, ny, dir)); // 머리의 위치 덱 앞에 넣기
					Snake tail = dq.pollLast(); // 꼬리부분 빼기
					apple[tail.x][tail.y] = 0;
					apple[nx][ny] = 2; // 뱀이 자리를 차지함
				}
			}
			
			else if(apple[nx][ny] == 1) { // 사과가 있다면
				if(list[time] != null) { // 방향 전환 정보가 있다면
					int ndir = turn(dir, list[time].dir);
					dq.offerFirst(new Snake(nx, ny, ndir)); // 머리의 위치와 방향 갱신후 덱 앞에 넣기
					apple[nx][ny] = 2; // 뱀이 자리를 차지함
				}
				else { // 방향 전환 정보가 없다면
					dq.offerFirst(new Snake(nx, ny, dir)); // 머리의 위치 덱 앞에 넣기
					apple[nx][ny] = 2; // 뱀이 자리를 차지함
				}
			}
		}
		
		System.out.println(time);
	}

}
