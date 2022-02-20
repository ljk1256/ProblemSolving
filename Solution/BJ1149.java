package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int [N+1][3];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + R;
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + G;
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + B;
		}
		int result = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}

}
