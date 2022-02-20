package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2448 {
	
	static char[][] stars;
	
	private static void makeStar(int row, int column, int size) {
		if(size == 3) {
			stars[row][column] = '*';
			stars[row+1][column-1] = '*';
			stars[row+1][column+1] = '*';
			
			for(int i=0; i<5; i++) {
				stars[row+2][column-i+2] = '*';
			}
			return;
		}
		
		int div = size / 2;
		makeStar(row, column, div);
		makeStar(row+div, column-div, div);
		makeStar(row+div, column+div, div);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		stars = new char [N][2*N];
		
		for(int i=0; i<N; i++) {
			Arrays.fill(stars[i], ' ');
		}
		
		makeStar(0, N-1, N); // 꼭지점 기준
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<2*N; j++) {
				sb.append(stars[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
