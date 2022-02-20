package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ19237 {
	
	static class Shark {
		
		int x;
		int y;
		int number; // 상어번호
		int dir; // 상어가 현재 바라보는 방향
		
		public Shark(int x, int y, int number, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
			this.dir = dir;
		}
		
	}
	
	static class Smell {
		
		int x;
		int y;
		int sharkNumber; // 냄새를 남긴 상어 번호
		int time; // 냄새를 남긴 시점
		
		public Smell(int x, int y, int sharkNumber, int time) {
			super();
			this.x = x;
			this.y = y;
			this.sharkNumber = sharkNumber;
			this.time = time;
		}
		
	}
	
	static PriorityQueue<Shark> pq;
	static int nowTime;
	static int N;
	static int k;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static int[][][] dirPriority; // [상어번호][상어가 현재바라 보는 방향][우선순위]
	static Shark[][] sharkmap;
	static Smell[][] smellmap;
	
	private static void moveShark() {
		
		Shark[][] newSharkmap = new Shark [N][N]; // 객체가 움직일때 맘대로 null을 하면 안된다 >> 동시에 움직이는 경우 그 자리에 다른 객체가 올 수 있기에 배열을 복사 하는것이 좋다
		
		while(!pq.isEmpty()) { // 상어번호가 높은 순서부터 뽑는다
			Shark temp = pq.poll();
			int x = temp.x;
			int y = temp.y;
			int number = temp.number;
			int dir = temp.dir;
			boolean flag = false;
			
			for(int i=1; i<=4; i++) { // 냄새가 없는곳을 발견 했다면
				int nx = x + dx[dirPriority[number][dir][i]];
				int ny = y + dy[dirPriority[number][dir][i]];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(smellmap[nx][ny] == null) {
					newSharkmap[nx][ny] = new Shark(nx, ny, temp.number, dirPriority[number][dir][i]);
					flag = true;
					break;
				}
			}
			
			if(flag) continue; // 상어가 냄새 없는곳을 찾아서 움직였다면 아래 코드(자신의 냄새가 있는곳 탐색)할 필요없다
			
			for(int i=1; i<=4; i++) { // 냄새가 없는곳이 없어, 자신의 냄새가 있는곳을 발견했다면
				int nx = x + dx[dirPriority[number][dir][i]];
				int ny = y + dy[dirPriority[number][dir][i]];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(smellmap[nx][ny] != null && smellmap[nx][ny].sharkNumber == number) {
					newSharkmap[nx][ny] = new Shark(nx, ny, temp.number, dirPriority[number][dir][i]);
					break;
				}
			}
		}
		
		for(int i=0; i<N; i++) { // 상어 모두 움직임이 완료했다면, 원래 맵에 갱신시킨다
			for(int j=0; j<N; j++) {
				sharkmap[i][j] = null;
				if(newSharkmap[i][j] != null) {
					sharkmap[i][j] = new Shark(newSharkmap[i][j].x, newSharkmap[i][j].y, newSharkmap[i][j].number, newSharkmap[i][j].dir);
				}
			}
		}
	}
	
	private static void makeSmell() {
		
		for(int i=0; i<N; i++) { // 새로 움직인 상어 위치 탐색
			for(int j=0; j<N; j++) {
				if(sharkmap[i][j] != null) {
					smellmap[i][j] = new Smell(i, j, sharkmap[i][j].number, nowTime);
					pq.offer(new Shark(i, j, sharkmap[i][j].number, sharkmap[i][j].dir));
				}
			}
		}
		
		for(int i=0; i<N; i++) { // 냄새가 지워질 시간이 됐다면
			for(int j=0; j<N; j++) {
				if(smellmap[i][j] != null && smellmap[i][j].time+k == nowTime) {
					smellmap[i][j] = null;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		int M = Integer.parseInt(st.nextToken()); // 상어 
		k = Integer.parseInt(st.nextToken()); // 냄새가 사라지는 시간
		
		Shark[] list = new Shark [M+1];
		sharkmap = new Shark [N][N];
		smellmap = new Smell [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				if(n != 0) {
					list[n] = new Shark(i, j, n, 0);
					smellmap[i][j] = new Smell(i, j, n, 0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.number, o1.number));
		
		for(int i=1; i<=M; i++) {
			Shark temp = list[i]; // 데이터, 인덱스 잘 보고 일치 및 초기화 시켜야한다
			temp.dir = Integer.parseInt(st.nextToken());
			sharkmap[temp.x][temp.y] = new Shark(temp.x, temp.y, temp.number, temp.dir);
			pq.offer(new Shark(temp.x, temp.y, temp.number, temp.dir));
		}
		
		dirPriority = new int [M+1][5][5];
		
		for(int i=1; i<=M; i++) { // 상어 번호는 인덱스 일치, 방향 번호도 인덱스 일치, 우선순위도 인덱스 일치
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int t=1; t<=4; t++) {
					dirPriority[i][j][t] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		nowTime = 0;
		while(nowTime < 1000) { // 최대 1000초 동안 수행
			if(pq.size() == 1) break; // 우선 순위큐로 인해 번호가 작은 상어가 제일 마지막에 남기때문에 큐 사이즈로 판단가능
			moveShark();
			nowTime++;
			makeSmell();
		}
		
		if(nowTime == 1000 && pq.size() != 1) nowTime = -1; // 1000초가 지나도 다른 상어가 있다면
		System.out.println(nowTime);
		
	}

}
