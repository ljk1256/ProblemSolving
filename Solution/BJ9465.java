package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9465 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int [2][n+1];
			
			for(int j=0; j<2; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<n+1; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int [2][n+1];
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			for(int j=2; j<n+1; j++) {
				dp[0][j] = Math.max(dp[1][j-2], dp[1][j-1]) + arr[0][j]; // j점에 올 수 있는 경우는 대각선 전과 전전중에 큰 값을 선택하여 오는것이다.
				dp[1][j] = Math.max(dp[0][j-2], dp[0][j-1]) + arr[1][j];
			}
			
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
		
	}

}
