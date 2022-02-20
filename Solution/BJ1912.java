package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] input = new int [N];
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int [N];
		dp[0] = input[0];
		
		int ans = dp[0];
		for(int i=1; i<N; i++) { // 현재값 부터 시작하는것과 누적된 수 중에서 더 큰 값을 선택한다
			dp[i] = Math.max(input[i], dp[i-1] + input[i]);
			ans = Math.max(ans, dp[i]); // 하지만 이것이 dp[N-1] 이 모든 경우의 최대 연속합을 보장하진 않는다
		}
		
		System.out.println(ans);
	}

}
