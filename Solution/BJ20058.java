package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ20058 {
	
	static int[][] map;
	static int size; // 전체 맵의 크기
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	private static void fireStorm(int level) {
		if(level != 0) {
			int gridSize = (int)Math.pow(2, level); // 격자 크기
			
			for(int i=0; i<size; i+=gridSize) {
				for(int j=0; j<size; j+=gridSize) {
					rotate(i, j, gridSize);
				}
			}
		}
		
		boolean[][] meltCheck = new boolean [size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				int isMelt = 0;
				
				for(int k=0; k<4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
					
					if(map[nx][ny] > 0) isMelt++;
				}
				
				if(isMelt < 3) meltCheck[i][j] = true; // 인접한 얼음칸이 3칸 미만이라면 녹는체크 
			}
		}
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(meltCheck[i][j]) {
					map[i][j]--;
					if(map[i][j] < 0) map[i][j] = 0; // 얼음은 마이너스가 될 수 없다
				}
			}
		}
	}
	
	private static void rotate(int row, int column, int gridSize) {
		// 배열 복사
		int[][] copy = new int [gridSize][gridSize];
		for(int i=0; i<gridSize; i++) {
			for(int j=0; j<gridSize; j++) {
				copy[i][j] = map[row+i][column+j];
			}
		}
		// 복사한 배열로 시계방향 90도 회전
		int[][] rotateArr = new int [gridSize][gridSize];
		for(int i=0; i<gridSize/2; i++) {
			int startX = i, startY = i; // 시작 꼭지점
			int repeatCnt = gridSize - (2*i);
			// 위
			int tempIdx = startY;
			for(int j=startY; j<startY+repeatCnt; j++) {
				rotateArr[tempIdx++][startY+repeatCnt-1] = copy[startX][j];
			}
			// 오른쪽
			tempIdx = startX+repeatCnt-1;
			for(int j=startY; j<startY+repeatCnt; j++) {
				rotateArr[startX+repeatCnt-1][tempIdx--] = copy[j][startX+repeatCnt-1];
			}
			// 아래
			tempIdx = startY+repeatCnt-1;
			for(int j=startY+repeatCnt-1; j>=startY; j--) {
				rotateArr[tempIdx--][startY] = copy[startY+repeatCnt-1][j];
			}
			// 왼쪽
			tempIdx = startX;
			for(int j=startX+repeatCnt-1; j>=startX; j--) {
				rotateArr[startX][tempIdx++] = copy[j][startX];
			}
		}
		
		// 원본 배열에 위치 맞게 갱신
		for(int i=0; i<gridSize; i++) {
			for(int j=0; j<gridSize; j++) {
				map[row+i][column+j] = rotateArr[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 격자 크기 지수
		int Q = Integer.parseInt(st.nextToken()); // 스톰 횟수
		
		size = (int)Math.pow(2, N);
		
		map = new int [size][size];
		ArrayList<Integer> levelList = new ArrayList<>();
		
		for(int i=0; i<size; i++) { // map 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) { // 단계 입력
			levelList.add(Integer.parseInt(st.nextToken()));
		}
		
		int cnt = 0;
		while(Q-- > 0) fireStorm(levelList.get(cnt++));
		
		StringBuilder sb = new StringBuilder();
		
		int totalIce = 0;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				totalIce += map[i][j];
			}
		}
		
		sb.append(totalIce).append("\n");
		
		int maxGroup = 0;
		boolean[][] visited = new boolean [size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(!visited[i][j] && map[i][j] > 0) {
					int tempGroup = 0; // 자기 자신 포함
					
					Queue<int []> q = new LinkedList<>();
					q.offer(new int [] {i, j});
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] temp = q.poll();
						int x = temp[0];
						int y = temp[1];
						tempGroup++; // 덩어리 갱신
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny]) continue;
							
							if(map[nx][ny] > 0) {
								q.offer(new int [] {nx, ny});
								visited[nx][ny] = true;
							}
						}
					}
					
					maxGroup = Math.max(maxGroup, tempGroup);
				}
			}
		}
		
		sb.append(maxGroup);
		System.out.println(sb.toString());
	}

}
