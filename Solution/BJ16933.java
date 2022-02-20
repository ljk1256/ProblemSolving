package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16933 {
	
	static class Human {
		
		int x;
		int y;
		int breakCnt;
		int time;
		boolean isDay;
		
		public Human(int x, int y, int breakCnt, int time, boolean isDay) {
			super();
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
			this.time = time;
			this.isDay = isDay;
		}
		
	}
	
	static int N;
	static int M;
	static int K;
	static int answer;
	static int[][] map;
	static int[][][] checkTimes; // 왜 꼭 3차원이어야 하는가?? 그냥 부신횟수만 체크해서 최소 시간만 구하면되는게 아닌가?
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Human> q; // 시간은 다 똑같이 흐르기때문에 정렬할 필요가없다
	
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Human temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int breakcnt = temp.breakCnt;
			int time = temp.time;
			boolean isDay = temp.isDay;
			
			if(x == N-1 && y == M-1) { // 도착했다면
				answer = temp.time;
				return;
			}
			
			for(int j=0; j<4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if(map[nx][ny] == 0) { // 빈 공간이라면
					if(checkTimes[nx][ny][breakcnt] == 0 || checkTimes[nx][ny][breakcnt] > time+1) { // 처음 방문 하는 곳이거나, 현재 시간이 더 짧을경우만 탐색
						checkTimes[nx][ny][breakcnt] = time+1;
						q.offer(new Human(nx, ny, breakcnt, time+1, !isDay));
					}
				}
				
				else { // 벽 이라면
					if(breakcnt < K) { // 벽을 부실수 있어야 진행가능
						if(isDay && (checkTimes[nx][ny][breakcnt+1] > time+1 || checkTimes[nx][ny][breakcnt+1] == 0)) { // 낮에는 부시고 이동
							checkTimes[nx][ny][breakcnt+1] = time+1;
							q.offer(new Human(nx, ny, breakcnt+1, time+1, !isDay)); 
						}
						else if(!isDay) {
							q.offer(new Human(x, y, breakcnt, time+1, !isDay)); // 밤에는 제자리에 카운트만 증가
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		answer = Integer.MAX_VALUE;
		checkTimes = new int [N][M][K+1]; // 최소 시간 입력하면서 진행
		checkTimes[0][0][0] = 1; // 다른 조건들에 비해 한 없이 크기가 다른 값이 있다면, 그것이 힌트가 될 수 있다
		q = new LinkedList<>();
		q.offer(new Human(0, 0, 0, 1, true));
		
		bfs();
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer); // 도착을 못한다면 -1출력
	}

}
