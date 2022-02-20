package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2477 {
	// 점을 한칸씩 밀어가면서 반례찾기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		int[][] data = new int [6][2];
		int row = 0, column = 0;
		
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<6; i++) {
			if(data[i][0] == 3 || data[i][0] == 4) {
				if(column < data[i][1]) {
					column = data[i][1];
				}
			}
			
			if(data[i][0] == 1 || data[i][0] == 2) {
				if(row < data[i][1]) {
					row = data[i][1];
				}
			}
		}
		
		int area = 0;
		
		
		
		System.out.println(K * ((row * column) - area));
	}

}
