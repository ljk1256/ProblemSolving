package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2580 {
	
	static int[][] map;
	static ArrayList<Zero> list = new ArrayList<>();
	
	private static void solve(int cnt) {
		if(cnt == list.size()) {
			for(int i=0; i<9; i++) { // 출력
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		Zero temp = list.get(cnt);
		for(int i=1; i<=9; i++) {
			if(r_check(temp.x, i) && c_check(temp.y, i) && sq_check(temp.x, temp.y, i)) {
				map[temp.x][temp.y] = i;
				solve(cnt+1);
				map[temp.x][temp.y] = 0;
			}
		}
	}
	
	private static boolean r_check(int r, int i) { // 가로 체크
		for(int j=0; j<9; j++) {
			if(map[r][j] == i) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean c_check(int c, int i) { // 세로 체크
		for(int j=0; j<9; j++) {
			if(map[j][c] == i) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean sq_check(int r, int c, int i) { // 3x3 체크
		if(0 <= r && r <= 2 && 0 <= c && c <= 2) { // 1사분면
			for(int j=0; j<3; j++) {
				for(int k=0; k<3; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(0 <= r && r <= 2 && 2 < c && c <= 5) { // 2
			for(int j=0; j<3; j++) {
				for(int k=3; k<6; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(0 <= r && r <= 2 && 5 < c && c <= 8) { // 3
			for(int j=0; j<3; j++) {
				for(int k=6; k<9; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(2 < r && r <= 5 && 0 <= c && c <= 2) { // 4
			for(int j=3; j<6; j++) {
				for(int k=0; k<3; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(2 < r && r <= 5 && 2 < c && c <= 5) { // 5
			for(int j=3; j<6; j++) {
				for(int k=3; k<6; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(2 < r && r <= 5 && 5 < c && c <= 8) { // 6
			for(int j=3; j<6; j++) {
				for(int k=6; k<9; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(5 < r && r <= 8 && 0 <= c && c <= 2) { // 7
			for(int j=6; j<9; j++) {
				for(int k=0; k<3; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(5 < r && r <= 8 && 2 < c && c <= 5) { // 8
			for(int j=6; j<9; j++) {
				for(int k=3; k<6; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		else if(5 < r && r <= 8 && 5 < c && c <= 8) { // 9
			for(int j=6; j<9; j++) {
				for(int k=6; k<9; k++) {
					if(map[j][k] == i) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int [9][9];
		for(int i=0; i<9; i++) { // 스도쿠 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<9; i++) { // 빈칸 탐색
			for(int j=0; j<9; j++) {
				if(map[i][j] == 0) {
					list.add(new Zero(i, j));
				}
			}
		}
		
		solve(0);
	}

}

/*class Zero {
	
	int x;
	int y;
	
	public Zero(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}*/
