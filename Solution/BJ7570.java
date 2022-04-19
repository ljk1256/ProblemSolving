package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7570 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int [N+1];
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) { // 반드시 처음부터 가야 증가하면서 연속된 수열의 최대 길이를 찾을 수 있다
			int tempStudent = Integer.parseInt(st.nextToken());
			
			dp[tempStudent] = dp[tempStudent-1] + 1; // 만약 연속 됐다면, 직전 수에서 더 이어서 할 수 있다
			max = Math.max(dp[tempStudent],max);
		}
		
		System.out.println(N - max);
	}

}
