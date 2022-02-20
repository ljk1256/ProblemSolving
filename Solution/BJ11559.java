package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ11559 {
	
	static class Puyo {
		
		int x;
		int y;
		char c;
		
		public Puyo(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
		
	}
	
	static int reaction;
	static char[][] field;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Puyo> puyo_q; // 터트려야할 그룹 저장
	
	private static void isPuyo(Puyo puyo) {
		Queue<Puyo> q = new LinkedList<>();
		q.offer(new Puyo(puyo.x, puyo.y, puyo.c));
		visited[puyo.x][puyo.y] = true;
		
		int cnt = 1; // 이미 하난 탐색큐에 넣었으니
		while(!q.isEmpty()) {
			Puyo temp = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6 || visited[nx][ny]) continue;
				
				if(field[nx][ny] == temp.c) { // 같은 뿌요라면
					q.offer(new Puyo(nx, ny, temp.c));
					visited[nx][ny] = true;
					cnt++;
				}
			}
		}
		
		if(cnt >= 4) { // 4개 이상 연결되어 있다면
			puyo_q.offer(puyo);
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		field = new char [12][6];
		
		for(int i=0; i<12; i++) {
			String s = br.readLine();
			for(int j=0; j<6; j++) {
				field[i][j] = s.charAt(j);
			}
		}
		
		puyo_q = new LinkedList<>();
		int answer = 0;
		while(true) {
			visited = new boolean [12][6]; // while 문 돌때마다 초기화 시켜줘야한다
			
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(field[i][j] != '.' && !visited[i][j]) { // 뿌요가 있다면(중복체크 제외)
						isPuyo(new Puyo(i, j, field[i][j]));
					}
				}
			}
			
			if(puyo_q.isEmpty()) break; // 터트려야 할 뿌요가 없다면
			
			else { // 있다면
				while(!puyo_q.isEmpty()) { // 만약 무한루프 돈다면 방문체크 넣어야한다
					Puyo next = puyo_q.poll();
					Queue<Puyo> q = new LinkedList<>();
					q.offer(next);
					
					while(!q.isEmpty()) {
						Puyo temp = q.poll();
						for(int i=0; i<4; i++) {
							int nx = temp.x + dx[i];
							int ny = temp.y + dy[i];
							
							if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
							
							if(field[nx][ny] == temp.c) {
								field[nx][ny] = '.';
								q.offer(new Puyo(nx, ny, temp.c));
							}
						}
					}
				}
				answer++; // 모든 뿌요를 다 부셨다면 연쇄 하나 추가
			}
			
			for(int i=0; i<6; i++) { // 제일 밑부터 떨어질 뿌요가 있는지 탐색
				for(int j=11; j>=0; j--) {
					if(field[j][i] != '.') {
						if(j+1 > 11) continue; // 제일 밑에 있다면
						else {
							char temp = field[j][i];
							int idx = j;
							while(true) {
								if(idx < 11 && field[idx+1][i] == '.') idx++;
								else break;
							}
							if(idx > j) { // 내려갈 곳이 있다면
								field[idx][i] = temp; // 뿌요를 내리고
								field[j][i] = '.'; // 원래 자리는 빈공간으로 갱신
							}
							else continue; // 내려갈곳이 없다면 다음탐색
						}
					}
				}
			}
			
		}
		
		System.out.println(answer);
	}

}
