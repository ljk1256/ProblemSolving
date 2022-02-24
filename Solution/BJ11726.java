package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11726 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int [N+1];
		
		if(N == 1) {
			System.out.println(1);
			System.exit(0);
		}
		else {
			dp[1] = 1;
			dp[2] = 2;
			
			for(int i=3; i<N+1; i++) {
				dp[i] = ((dp[i-2] % 10007) + (dp[i-1] % 10007)) % 10007;
			}
			
			System.out.println(dp[N]);
		}
	}

}
