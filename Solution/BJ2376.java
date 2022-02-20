package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2376 { // 반드시 문을 열어주고 탐색하는것이 좋은 방법은 아니다. bfs, dfs 모두 생각해 봐야한다.
	
	static int answer;
	static int w;
	static int h;
	static char[][] jail;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	private static int[][] bfs(Prisoner p) {
		
		int[][] minDoor = new int [h+2][w+2];
		for(int i=0; i<h+2; i++) {
			for(int j=0; j<w+2; j++) { // 방문 안한 경우를 0으로 둘 경우 무한루프 돌게된다.
				minDoor[i][j] = -1; 
			}
		}
		
		Queue<Prisoner> q = new LinkedList<Prisoner>();
		q.offer(p);
		
		while(!q.isEmpty()) {
			Prisoner temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int open = temp.open;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= h+2 || ny < 0 || ny >= w+2 || jail[nx][ny] == '*') continue; // 범위를 벗어 나거나 벽을 만날경우 무시
				
				if(jail[nx][ny] == '#') { // 문을 만난 경우
					
					if(minDoor[nx][ny] == -1) { // 한번도 방문 안한 경우
						q.offer(new Prisoner(nx, ny, open+1)); // 문 열고 방문
						minDoor[nx][ny] = open+1;
					}
					
					else if(minDoor[nx][ny] != -1 && minDoor[nx][ny] > open+1) { // 방문했더라도 더 적게 열고 방문 한 경우 갱신
						q.offer(new Prisoner(nx, ny, open+1));
						minDoor[nx][ny] = open+1;
					}
				}
				
				else { // 빈 공간 또는 죄수가 있는 경우
					if(minDoor[nx][ny] == -1) { 
						q.offer(new Prisoner(nx, ny, open)); 
						minDoor[nx][ny] = open;
					}
					
					else if(minDoor[nx][ny] != -1 && minDoor[nx][ny] > open) { 
						q.offer(new Prisoner(nx, ny, open));
						minDoor[nx][ny] = open;
					}
				}
			}
		}
		return minDoor; // 각 자리에 저장된 값은 해당 위치까지 가는데 가장 적게 연 문의 횟수
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			jail = new char [h+2][w+2]; // 감옥 지도
			
			for(int i=0; i<h+2; i++) {
				for(int j=0; j<w+2; j++) {
					jail[i][j] = '.'; // 테두리 포함 모든 공간 . 초기화
				}
			}
			
			ArrayList<Prisoner> list = new ArrayList<>();
			for(int i=1; i<=h; i++) {
				String s = br.readLine();
				for(int j=1; j<=w; j++) {
					jail[i][j] = s.charAt(j-1);
					
					if(jail[i][j] == '$') {
						list.add(new Prisoner(i, j, 0));
					}
				}
			}
			
			answer = 9999999;
			int[][] one = bfs(new Prisoner(0, 0, 0)); // 상근이 출발
			int[][] two = bfs(list.get(0)); // 죄수 1번 출발
			int[][] three = bfs(list.get(1)); // 죄수 2번 출발
			int[][] result = new int [h+2][w+2]; // 서로 다른 지점에서 출발하여 한 점에서 만난다면, 각자 최소의 문을 열고 왔기때문에 두개를 더하면 최소 값(비용)이 된다.
			
			for(int i=0; i<h+2; i++) {
				for(int j=0; j<w+2; j++) {
					if(jail[i][j] == '*') continue;
					
					if(jail[i][j] == '#') { // 문은 세명이 열었기에 겹치는 두명분은 빼준다.
						result[i][j] = one[i][j] + two[i][j] + three[i][j] - 2;
					}
					else if(jail[i][j] == '.' || jail[i][j] == '$'){
						result[i][j] = one[i][j] + two[i][j] + three[i][j];
						
						if(result[i][j] < 0) { // 빈 공간이지만 모두 벽으로 둘러쌓여 가지 못하는 곳이 있다. 그런곳은 음수의 값으로 초기화 되었기에 값계산시 최대로 변환
							result[i][j] = 9999999;
						}
					}
					answer = Math.min(answer, result[i][j]);
				}
			}
			System.out.println(answer);
		}
	}

}
class Prisoner {
	
	int x;
	int y;
	int open;
	
	public Prisoner(int x, int y, int open) {
		super();
		this.x = x;
		this.y = y;
		this.open = open;
	}
	
}
