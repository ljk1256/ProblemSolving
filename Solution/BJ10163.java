package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10163 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int [1001][1001];
		int[] count = new int [N + 1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int column = Integer.parseInt(st.nextToken());
			int garo = Integer.parseInt(st.nextToken());
			int sero = Integer.parseInt(st.nextToken());
				
			for(int k=0; k<sero; k++) {
				for(int t=0; t<garo; t++) {
					map[row + k][column + t] = i;
				}
			}
		}
		
		for(int i=0; i<1001; i++) {
			for(int j=0; j<1001; j++) {
				count[map[i][j]]++;
			}
		}
		
		for(int i=1; i<N+1; i++) {
			System.out.println(count[i]);
		}
	}

}
