package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2156 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] wine = new int [N+1];
		int[] dp = new int [N+1];
		
		for(int i=1; i<N+1; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		if(N == 1) {
			dp[1] = wine[1];
		}else if(N == 2) {
			dp[1] = wine[1];
			dp[2] = wine[1] + wine[2];
		}
		else {
			dp[1] = wine[1];
			dp[2] = wine[1] + wine[2];
			for(int i=3; i<N+1; i++) {
				dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i])); // 포도주를 2번 연속 안마시는 경우도 있고
			}
		}
		
		System.out.println(dp[N]); // dp를 현 시점에서 가장 최대 값을 얻고 싶으면 항상 이전 항과도 비교 해야 하는 경우를 생각해야한다.
	}

}
