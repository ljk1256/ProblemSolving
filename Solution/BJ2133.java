package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2133 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int [N+1];
		
		if(N == 1) { // 분명히 안되는 조건인데 범위에 포함되어 있다면 그냥 0을 출력하는 건지 생각해 봐야한다
			System.out.println(0);
			System.exit(0);
		}
		
		dp[0] = 1; // 규칙을 만들기 위해 적어준다
		dp[2] = 3;
		
		for(int i=4; i<N+1; i++) {
			if(i%2 != 0) continue;
			
			dp[i] = dp[i-2] * 3;
			
			for(int j=4; j<=i; j+=2) { // 길이가 4부터 새로운 유형이 있다
				dp[i] += dp[i-j] * 2; // 이전 길이의 새로운 유형이 4x2 와 2x4 모양으로 되는 경우 모두 고려해야 한다
			}
		}
		
		System.out.println(dp[N]);
	}

}
