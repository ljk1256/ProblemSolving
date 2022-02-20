package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2579 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int S = Integer.parseInt(br.readLine());
		int[] arr = new int [S+1];
		int[] dp = new int [S+1];
		
		for(int i=1; i<S+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if(S == 1) {
			dp[1] = arr[1];
		}
		else if(S == 2) {
			dp[1] = arr[1];
			dp[2] = arr[1] + arr[2];
		}
		else {
			dp[1] = arr[1];
			dp[2] = arr[1] + arr[2];
			dp[3] = Math.max(arr[1], arr[2]) + arr[3];
			for(int i=4; i<S+1; i++) { // n 번째 계단을 밟을때 최대 값은  n-3번째 계단을 밟고 n-1번째 계단을 밟고 n번째 계단을 밟는것과, n-2를 밟고 n을 밟는 2가지 경우 밖에없다.
				dp[i] = Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]);
			}
		}
		
		System.out.println(dp[S]);
	}

}
