package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17144 {
	
	static int R;
	static int C;
	static int[][][] air;
	static int cleaner;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	
	private static void dust(int time) {
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(air[i][j][time-1] == -1) { // 원래 기준 배열에서 공기청정기 위치 다음 시간 배열에 복사
					air[i][j][time] = -1;
					continue;
				}
				else if(air[i][j][time-1] == 0) { // 0일 경우는 시간을 줄이기 위해 건너뛰기
					continue;
				}
				else { // 먼지가 있을 경우
					int div = air[i][j][time-1] / 5;  // 확산될 먼지 양
					int cnt = 0;  // 확산 방향 카운트
					
					for(int k=0; k<4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						
						if(ni >= 0 && nj >= 0 && ni < R && nj < C && air[ni][nj][time-1] != -1) { // 배열의 범위를 벗어나거나, 공기청정기를 제외한 곳만 확산
							air[ni][nj][time] += div;
							cnt = cnt + 1;
						}
					}
					
					air[i][j][time] += air[i][j][time-1] - (div * cnt); // 원래 양 - 확산된 양을 빼준 뒤 새로운 시간 배열에 갱신
				}
			}
		}
	}
	
	private static void clean(int time) { // 꺾는걸 고려하지 않고 점의 좌표를 리스트에 다 넣고 일자로 밀어버리기는 방법도 있다.
		
		//위쪽 공기 순환
		int temp = air[cleaner-1][C-1][time]; // 우측 아랫변
		for(int i=C-2; i>0; i--) {
			air[cleaner-1][i+1][time] = air[cleaner-1][i][time];
		}
		air[cleaner-1][1][time] = 0; // 공기 청정기에서 나오는 바람은 깨끗한 바람
		
		int temp2 = air[0][C-1][time]; // 우측 모서리
		for(int i=0; i<cleaner-2; i++) { // 하나 더 줄여야함
			air[i][C-1][time] = air[i+1][C-1][time];
		}
		air[cleaner-2][C-1][time] = temp;
		
		temp = air[0][0][time];
		for(int i=1; i<C-1; i++) {
			air[0][i-1][time] = air[0][i][time];
		}
		air[0][C-2][time] = temp2;
		
		for(int i=cleaner-2; i>1; i--) {
			air[i][0][time] = air[i-1][0][time];
		}
		air[1][0][time] = temp;
		
		//아래 공기 순환
		int temp3 = air[cleaner][C-1][time];
		for(int i=C-1; i>1; i--) {
			air[cleaner][i][time] = air[cleaner][i-1][time];
		}
		air[cleaner][1][time] = 0;
		
		int temp4 = air[R-1][C-1][time];
		for(int i=R-1; i>cleaner+1; i--) {
			air[i][C-1][time] = air[i-1][C-1][time];
		}
		air[cleaner+1][C-1][time] = temp3;
		
		temp3 = air[R-1][0][time];
		for(int i=0; i<C-2; i++) {
			air[R-1][i][time] = air[R-1][i+1][time];
		}
		air[R-1][C-2][time] = temp4;
		
		for(int i=cleaner+1; i<R-2; i++) {
			air[i][0][time] = air[i+1][0][time];
		}
		air[R-2][0][time] = temp3;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		air = new int [R][C][T+1];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				air[i][j][0] = Integer.parseInt(st.nextToken());
				
				if(air[i][j][0] == -1) {
					cleaner = i;  // 공기청정기 좌표 위치 저장 (-1이 2개가 있기때문에 마지막 좌표(시계방향 좌표만 저장됨))
				}
			}
		}
		int flag = 0; // 3차원 배열의 시간이면서 최종 T시간 까지 동작
		while(flag < T) {
			flag++;
			dust(flag);
			clean(flag);
		}
		
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(air[i][j][T] != -1) {
					sum += air[i][j][T];
				}
			}
		}
		System.out.println(sum);
	}

}
