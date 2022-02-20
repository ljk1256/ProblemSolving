package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ20057 {
	
	static class Rate {
		
		int dx;
		int dy;
		double ratio;
		
		public Rate(int dx, int dy, double ratio) {
			super();
			this.dx = dx;
			this.dy = dy;
			this.ratio = ratio;
		}
		
	}
	
	static int N;
	static int[] dx = {0, 1, 0, -1}; // 토네이도 이동 방향
	static int[] dy = {-1, 0, 1, 0};
	static int[][] map;
	static int outSand;
	static ArrayList<ArrayList<Rate>> propotions;
	
	private static void moveTornado() {
		int x = N/2, y = N/2;
		int dir = 0, cnt = 0, repeat = 1; // 토네이도 방향 인덱스, 2번 진행하면 반복횟수가 1씩 증가
		
		while(true) { 
			if(x == 0 && y == 0) break; // 0, 0 도탁 할때까지 계속 움직인다
			
			if(cnt == 2) {
				cnt = 0; // 진행횟수 초기화
				repeat++; // 반복횟수 1증가
			}
			
			for(int i=0; i<repeat; i++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				spreadSand(x, y, dir);
				
				x = nx;
				y = ny;
				
				if(x == 0 && y == 0) break;
			}
			
			dir = (dir + 1) % 4; // 방향 전환
			cnt++;
		}
	}
	
	private static void spreadSand(int startX, int startY, int dir) {
		int pointX = startX + dx[dir], pointY = startY + dy[dir]; // 움직일 모래 좌표
		
		// 확산 하는 모래를 구한다 (밖으로 나가는 모래 체크) 한번에 갱신
		ArrayList<Rate> list = propotions.get(dir);
		int origin = map[pointX][pointY];
		int tempSand = map[pointX][pointY];
		
		ArrayList<int []> removeList = new ArrayList<>(); // 확산되는 모래 리스트
		for(int i=0; i<list.size(); i++) {
			Rate temp = list.get(i);
			int nx = startX + temp.dx;
			int ny = startY + temp.dy;
			
			removeList.add(new int [] {nx, ny, (int)(tempSand * temp.ratio)});
		}
		
		for(int i=0; i<removeList.size(); i++) {
			int[] temp = removeList.get(i);
			int x = temp[0];
			int y = temp[1];
			int diff = temp[2];
			
			tempSand -= diff; // 확산되는 모래양을 빼준다
			
			if(x < 0 || x >= N || y < 0 || y >= N) { // 격자 밖을 벗어나는 모래가 있다면
				outSand += diff;
				continue;
			}
			
			map[x][y] += diff; // 범위 내라면 확산시켜준다
		}
		
		int nextX = pointX + dx[dir], nextY = pointY + dy[dir]; // 알파 자리로 남은 모래 이동
		
		if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
			outSand += tempSand; // 알파 자리가 격자 밖을 벗어난다면 나가는 모래 추가
			map[pointX][pointY] -= origin; // 원래 자리 모래는 날라간만큼 사라짐
		}
		else {
			map[nextX][nextY] += tempSand; // 격자 범위 안이라면 모래를 이동시켜 준다
			map[pointX][pointY] -= origin; // 원래 자리 모래는 날라간만큼 사라짐
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int [N][N];
		
		for(int i=0; i<N; i++) { // 초기 모래 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		propotions = new ArrayList<>();
		for(int i=0; i<4; i++) {
			propotions.add(new ArrayList<>());
		}
		
		ArrayList<Rate> list = propotions.get(0); // dir 0 입력
		list.add(new Rate(-1, 0, 0.01));
		list.add(new Rate(1, 0, 0.01));
		list.add(new Rate(-2, -1, 0.02));
		list.add(new Rate(-1, -1, 0.07));
		list.add(new Rate(1, -1, 0.07));
		list.add(new Rate(2, -1, 0.02));
		list.add(new Rate(-1, -2, 0.1));
		list.add(new Rate(1, -2, 0.1));
		list.add(new Rate(0, -3, 0.05));
		
		list = propotions.get(1); // dir 1 입력
		list.add(new Rate(0, -1, 0.01));
		list.add(new Rate(0, 1, 0.01));
		list.add(new Rate(1, -1, 0.07));
		list.add(new Rate(1, 1, 0.07));
		list.add(new Rate(1, -2, 0.02));
		list.add(new Rate(1, 2, 0.02));
		list.add(new Rate(2, -1, 0.1));
		list.add(new Rate(2, 1, 0.1));
		list.add(new Rate(3, 0, 0.05));
		
		list = propotions.get(2); // dir 2 입력
		list.add(new Rate(-1, 0, 0.01));
		list.add(new Rate(1, 0, 0.01));
		list.add(new Rate(-1, 1, 0.07));
		list.add(new Rate(1, 1, 0.07));
		list.add(new Rate(-2, 1, 0.02));
		list.add(new Rate(2, 1, 0.02));
		list.add(new Rate(-1, 2, 0.1));
		list.add(new Rate(1, 2, 0.1));
		list.add(new Rate(0, 3, 0.05));
		
		list = propotions.get(3); // dir 3 입력
		list.add(new Rate(0, -1, 0.01));
		list.add(new Rate(0, 1, 0.01));
		list.add(new Rate(-1, -1, 0.07));
		list.add(new Rate(-1, 1, 0.07));
		list.add(new Rate(-1, -2, 0.02));
		list.add(new Rate(-1, 2, 0.02));
		list.add(new Rate(-2, -1, 0.1));
		list.add(new Rate(-2, 1, 0.1));
		list.add(new Rate(-3, 0, 0.05));
		
		outSand = 0;
		moveTornado();
		
		System.out.println(outSand);
	}

}
