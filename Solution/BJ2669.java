package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2669 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean[][] rectangular = new boolean [101][101];
		int area = 0;
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<y2-y1; j++) {
				for(int k=0; k<x2-x1; k++) {
					rectangular[x1+k][y1+j] = true;
				}
			}
		}
		
		for(int i=0; i<rectangular.length; i++) {
			for(int j=0; j<rectangular.length; j++) {
				if(rectangular[i][j])
					area++;
			}
		}
		System.out.println(area);
	}

}
