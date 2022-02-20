package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1932 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] dp = new int [N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				if(!st.hasMoreTokens()) {
					continue;
				}
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0;
		if(N == 1) {
			max = dp[1][1];
		}
		else {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dp[i+1][j+1] = Math.max(dp[i][j] + dp[i+1][j+1], dp[i][j+1] + dp[i+1][j+1]);
				}
			}
			
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(max < dp[i][j]) {
						max = dp[i][j];
					}
				}
			
			}
		
		}
		
		System.out.println(max);
	}

}
