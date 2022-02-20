package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ21610 {
	
	static class Command {
		
		int dir;
		int dis;
		
		public Command(int dir, int dis) {
			this.dir = dir;
			this.dis = dis;
		}
	}
	
	static class Cloud {
		
		int x;
		int y;
		
		public Cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int N;
	static int[][] map; // 바구니 물의 양
	static ArrayList<Command> cmList; // 이동 명령 리스트
	static ArrayList<Cloud> clList; // 구름 리스트
	static boolean[][] isUsed; // 구름이 있다가 사라진 자리인지 체크
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}; // 구름의 이동 방향
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] copydx = {-1, -1, 1, 1}; // 대각선 물이 있는지 체크하는 방향
	static int[] copydy = {-1, 1, -1, 1};
	
	private static int conversion(int number) {
		if(number < 0) { // 음수면 양수 좌표가 나올때까지
			while(number < 0) number += N;
			return number;
		}
		else if(number >= N) return number % N; // 배열 인덱스 범위를 넘어갔다면 모듈러 연산
		else return number;
	}
	
	private static void cloudMove(int cnt) {
		Command tempCmd = cmList.get(cnt);
		int cmdDir = tempCmd.dir;
		int cmdDis = tempCmd.dis;
		
		for(int i=0; i<clList.size(); i++) {
			Cloud tempCld = clList.get(i);
			int x = tempCld.x;
			int y = tempCld.y;
			
			x += dx[cmdDir] * cmdDis;
			y += dy[cmdDir] * cmdDis;
			
			tempCld.x = conversion(x); // 범위 벗어난 숫자들 변환 해서 다시 넣어준다
			tempCld.y = conversion(y);
			
			map[tempCld.x][tempCld.y]++; // 구름이 있는 자리 물 1 증가
			isUsed[tempCld.x][tempCld.y] = true; // 구름이 있던 자리체크
		}
	}
	
	private static void makeCloud() {
		ArrayList<Integer> plusList = new ArrayList<>(); // 구름이 있던 바구니에 물복사증가량 저장
		
		for(int i=0; i<clList.size(); i++) {
			Cloud tempCld = clList.get(i);
			int x = tempCld.x;
			int y = tempCld.y;
			int plus = 0;
			
			for(int j=0; j<4; j++) {
				int nx = x + copydx[j];
				int ny = y + copydy[j];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(map[nx][ny] != 0) plus++;
			}
			
			plusList.add(plus); 
		}
		
		for(int i=0; i<plusList.size(); i++) {
			Cloud tempCld = clList.get(i);
			int x = tempCld.x;
			int y = tempCld.y;
			
			map[x][y] += plusList.get(i); // 물복사 진행
		}
		
		clList.clear(); // 구름 리스트 초기화
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 2 && !isUsed[i][j]) { // 물의 양이 2이상이고 지난 구름이 있던 자리가 아니라면
					map[i][j] -= 2; // 물의 양을 2 감소시키고
					clList.add(new Cloud(i, j)); // 새로운 구름 추가
				}
			}
		}
		
		for(int i=0; i<N; i++) { // 구름이 있던 자리 초기화
			Arrays.fill(isUsed[i], false);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		int M = Integer.parseInt(st.nextToken()); // 이동 횟수
		
		map = new int [N][N];
		for(int i=0; i<N; i++) { // 최초 바구니 물의 양 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cmList = new ArrayList<>(); // 이동 명령 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			cmList.add(new Command(dir-1, dis)); // 방향 배열 인덱스는 0부터 시작하니 -1 해서 입력
		}
		
		clList = new ArrayList<>(); // 최초 비바라기 구름 생성
		clList.add(new Cloud(N-1, 0));
		clList.add(new Cloud(N-1, 1));
		clList.add(new Cloud(N-2, 0));
		clList.add(new Cloud(N-2, 1));
		
		int cnt = 0;
		isUsed = new boolean [N][N];
		while(M-- > 0) {
			cloudMove(cnt++); // 구름 이동, 구름이 있는 곳 물 1증가, 구름 삭제
			makeCloud(); // 대각선방향 물 있는지 체크, 물이 2 이상인 곳 구름생성, isUsed 초기화
		}
		
		long waterSum = 0L;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				waterSum += map[i][j];
			}
		}
		System.out.println(waterSum);
	}

}
