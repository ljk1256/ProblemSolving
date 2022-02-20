package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2096 {
	
	static int[] dx = {-1, -1, -1};
	static int[] dy = {0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] maxDp = new int [2][3];
		int[][] minDp = new int [2][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				int n = Integer.parseInt(st.nextToken());
				maxDp[1][j] = n;
				minDp[1][j] = n;
			}
			
			for(int j=0; j<3; j++) {
				int tempMin = Integer.MAX_VALUE, tempMax = Integer.MIN_VALUE;
				
				for(int k=0; k<3; k++) {
					int nx = 1 + dx[k];
					int ny = j + dy[k];
					
					if(nx < 0 || ny < 0 || ny >= 3) continue;
					
					tempMin = Math.min(tempMin, minDp[nx][ny] + minDp[1][j]);
					tempMax = Math.max(tempMax, maxDp[nx][ny] + maxDp[1][j]);
				}
				
				minDp[1][j] = tempMin;
				maxDp[1][j] = tempMax;
			}
			
			for(int j=0; j<3; j++) {
				minDp[0][j] = minDp[1][j];
				maxDp[0][j] = maxDp[1][j];
			}
		}
		
		int minAns = Integer.MAX_VALUE, maxAns = Integer.MIN_VALUE;
		for(int i=0; i<3; i++) {
			if(minDp[0][i] < minAns) minAns = minDp[0][i];
			if(maxDp[0][i] > maxAns) maxAns = maxDp[0][i];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(maxAns).append(" ").append(minAns);
		System.out.println(sb.toString());
	}

}
