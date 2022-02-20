package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3085 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] candys;
	static int N;
	static int maxAns;
	
	private static void counting(int row, int column) { // 일직선 같이 탐색
		int rowCnt = 0;
		int nx = row, ny = column;
		while(candys[nx][ny] == candys[row][column]) {
			nx += dx[0];
			ny += dy[0];
			rowCnt++;
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
		}
		
		nx = row; 
		ny = column;
		while(candys[nx][ny] == candys[row][column]) {
			nx += dx[1];
			ny += dy[1];
			rowCnt++;
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
		}
		rowCnt -= 1; // 처음 자리 중복 제거
		maxAns = Math.max(maxAns, rowCnt);
		
		int columnCnt = 0;
		nx = row; 
		ny = column;
		while(candys[nx][ny] == candys[row][column]) {
			nx += dx[2];
			ny += dy[2];
			columnCnt++;
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
		}
		
		nx = row; 
		ny = column;
		while(candys[nx][ny] == candys[row][column]) {
			nx += dx[3];
			ny += dy[3];
			columnCnt++;
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
		}
		columnCnt -= 1; // 처음 자리 중복 제거
		maxAns = Math.max(maxAns, columnCnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		candys = new char [N][N];
		
		for(int i=0; i<N; i++) {
			candys[i] = br.readLine().toCharArray();
		}
		
		maxAns = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				counting(i, j);
				
				for(int k=0; k<4; k++) {
					int ni = i + dx[k];
					int nj = j + dy[k];
					
					if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
					
					if(candys[i][j] != candys[ni][nj]) {
						char origin = candys[i][j];
						candys[i][j] = candys[ni][nj];
						candys[ni][nj] = origin;
						counting(i, j);
						
						candys[ni][nj] = candys[i][j];
						candys[i][j] = origin;
					}
				}
			}
		}
		
		System.out.println(maxAns);
	}

}
