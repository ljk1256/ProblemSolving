package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2293 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dp = new int [K+1];
		int[] coins = new int [N];
		
		for(int i=0; i<N; i++) coins[i] = Integer.parseInt(br.readLine());
		
		dp[0] = 1; // 어떠한 동전이든지 0원을 만드는 방법은 한가지
		
		for(int i=0; i<N; i++) { // 동전의 종류를 하나씩 늘려가며 경우의 수를 구한다
			
			for(int j=1; j<K+1; j++) { // 예를 들어 2원으로 4원을 만드는 방법은 2원만드는 방법과 같다 왜냐? 2원의 갯수만 더 늘려줄 뿐이니까
				if(coins[i] <= j) dp[j] += dp[j - coins[i]];
			}
			
		}
		
		System.out.println(dp[K]);
	}

}
