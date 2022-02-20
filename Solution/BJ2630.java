package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2630 {
	
	static int white;
	static int blue;
	static int[][] map;
	
	private static void Solve(int row, int column, int size) {
		if(size == 1) {
			if(map[row][column] == 0) white++;
			else blue++;
			return;
		}
		
		int standard = map[row][column];
		boolean flag = false;
		
		bp:for(int i=row; i<row+size; i++) {
			for(int j=column; j<column+size; j++) {
				if(map[i][j] != standard) {
					flag = true;
					break bp;
				}
			}
		}
		
		if(flag) {
			Solve(row, column, size/2);
			Solve(row, column+size/2, size/2);
			Solve(row+size/2, column, size/2);
			Solve(row+size/2, column+size/2, size/2);
		}
		
		else {
			if(standard == 0) white++;
			else blue++;
			return;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Solve(0, 0, N);
		StringBuilder sb = new StringBuilder();
		sb.append(white).append("\n").append(blue);
		System.out.println(sb.toString());
	}

}
