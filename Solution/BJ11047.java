package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11047 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int [N];
		for(int i=0; i<N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coins);
		
		int idx = coins.length-1, coinCnt = 0;
		while(K > 0) {
			for(int i=idx; i>=0; i--) {
				if(K/coins[i] != 0) {
					idx = i-1;
					coinCnt += (K/coins[i]);
					K -= (K/coins[i])*coins[i];
					break;
				}
			}
		}
		
		System.out.println(coinCnt);
	}

}
