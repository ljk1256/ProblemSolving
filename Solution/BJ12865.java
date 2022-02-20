package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] data = new int [N+1][2];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken()); // 물건의 무게
			data[i][1] = Integer.parseInt(st.nextToken()); // 물건의 가치
		}
		
		int[][] dp = new int [N+1][K+1]; // 물건의 인덱스 와 데이터 인덱스 일치, 무게도 인덱스와 일치
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<K+1; j++) {
				
				if(data[i][0] > j) {
					dp[i][j] = dp[i-1][j]; // 허용 무게가 더 작아 해당 물건을 담지 못할때 >> 동일한 허용 무게와 현재 물건을 고려하지 않던 시점과 가치는 같음
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-data[i][0]] + data[i][1]); // 현재 물건을 담을 수 있을때 : 나를 담지 않을 경우, 나를 담을 경우의 최대를 비교
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
