package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ3025 {
	
	static int R;
	static int C;
	static int N;
	static char[][] map;
	static Stack<Point>[] dp; // 해당 인덱스 = 열 에서 떨어지는 돌은 몇번째 행부터 탐색 하면 되는지 저장
	
	static class Point {
		
		int x;
		int y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static void play(int column) {
		
		if(map[0][column] != '.') { // 제일 위칸이 빈칸이 아니라면 돌을 던질 수 없다
			return;
		}
		
		int x = 0;
		int y = column;
		
		if(!dp[column].isEmpty()) { // 이미 지나갔던 경로가 있다면
			int size = dp[column].size();
			for(int i=0; i<size; i++) {
				Point temp = dp[column].pop(); // 마지막 경로 부터 탐색한다
				if(map[temp.x][temp.y] == '.') { // 해당 경로에 빈공간이라면
					x = temp.x;
					y = temp.y; // 해당 좌표부터 탐색 시작
					dp[column].push(new Point(x, y)); // 마지막 경로에 다시 넣어준다
					break;
				}
				else continue; // 돌이 있다면 다음 마지막 경로 탐색
			}
		}
		
		while(true) { // 더 이상 움직이지 못할때까지 수행
			
			if(x < R-1) {
				
				if(map[x+1][y] == 'X') { // 벽이라서 이동을 못한다면
					dp[column].push(new Point(x, y));
					map[x][y] = 'O'; // 현재칸에 돌을 놔둔뒤 탐색을 끝낸다
					return;
				}
				
				if(map[x+1][y] == '.') { // 빈 곳이라 굴러갈수 있다면
										// 돌을 한칸 내리고 나서 다시 탐색한다
					dp[column].push(new Point(x, y));
					x += 1;
					continue;
				}
				
				if(map[x+1][y] == 'O') { // 밑에 돌이 있다면
					
					if(y-1 >= 0 && map[x][y-1] == '.' && map[x+1][y-1] == '.') { // 왼쪽 과 왼쪽 아래가 비어 있다면
						dp[column].push(new Point(x, y)); // 경로에 추가해준다
						x += 1;
						y -= 1;
						continue;
					}
					
					if(y+1 <= C-1 && map[x][y+1] == '.' && map[x+1][y+1] == '.') { // 오른쪽 과 오른쪽 아래가 비어 있다면
						dp[column].push(new Point(x, y)); // 경로에 추가해준다
						x += 1;
						y += 1;
						continue;
					}
					
					map[x][y] = 'O'; // 굴러 갈 수 없다면 그 자리에 돌 놔두고 멈춘다
					dp[column].push(new Point(x, y));
					return;
					
				}
			}
			
			else if(x == R-1) { // 제일 아래까지 내려왔다면
				map[x][y] = 'O'; // 돌을 놔두고 멈춘다
				dp[column].push(new Point(x, y));
				return;
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char [R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		N = Integer.parseInt(br.readLine()); // 던지는 돌의 개수
		dp = new Stack [C];
		for(int i=0; i<C; i++) {
			dp[i] = new Stack<>();
		}
		
		while(N > 0) {
			play(Integer.parseInt(br.readLine()) - 1); // 열 입력 받은뒤 실행 인덱스 맞추기 위해 -1 
			N--;
		}
		
		StringBuilder sb = new StringBuilder(); // print 호출은 
		for(int i=0; i<R; i++) { // 결과 출력
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
