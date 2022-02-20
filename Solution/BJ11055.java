package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11055 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int [N+1];
		int[] dp = new int [N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = input[1];
		for(int i=2; i<N+1; i++) {
			dp[i] = input[i];
			
			for(int j=1; j<i; j++) {
				if(input[j] < input[i]) {
					dp[i] = Math.max(dp[i], dp[j] + input[i]); // dp가 아니라 input 으로 해야 중간에 값이 바뀌지 않고 비교가능
				}		                                       // 현재 값 기준으로 수열의 합을 저장
			}
		}
		
		int max = -1;
		for(int i=1; i<N+1; i++) {
			if(max < dp[i]) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}

}
