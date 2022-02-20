package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ14499 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] A;
		char[] B;
		
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		
		int[][] dp = new int [A.length+1][B.length+1];
		
		for(int i=1; i<A.length+1; i++) {
			for(int j=1; j<B.length+1; j++) {
				
				if(A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[A.length][B.length]);
	}

}
