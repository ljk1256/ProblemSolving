package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ9663 {
	
	static int N;
	static int answer;
	static int[][] chess;
	static int[] column;
	/*static ArrayList<Queen> list = new ArrayList<>();*/
	
	/*static class Queen {
		
		int x;
		int y;
		
		public Queen(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}*/
	
	private static void solve(int r) {
		if(r == N) {
			answer++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			chess[r][i] = 1; // 먼저 놓아보고
			
			if(check(r, i)) { // 놓은곳이 유효하다면 다음 열로 이동
				column[r] = i; // 이전 퀸의 열 저장
				solve(r+1); // 재귀 호출
				chess[r][i] = 0; // 백트래킹
				column[r] = 0; // 백트래킹
			}
			
			chess[r][i] = 0; // 해당열에 놓으면 안된다.
		}
	}
	
	private static boolean check(int r, int c) {
		for(int i=0; i<r; i++) {
			
			if(column[i] == c) { // 이전 퀸들과 같은 직선에 있는지 확인
				return false;
			}
			if(Math.abs(i - r) == Math.abs(column[i] - c)) { // 이전 퀸들과 대각선에 있는지 확인
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		chess = new int [N][N];
		column = new int [N];
		
		answer = 0;
		solve(0);
		System.out.println(answer);
	}

}
