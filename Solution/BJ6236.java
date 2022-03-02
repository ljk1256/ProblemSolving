package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6236 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int maxMoney = 0;
		
		for(int i=0; i<N; i++) {
			maxMoney = Math.max(maxMoney, Integer.parseInt(br.readLine()));
		}
		
		System.out.println(maxMoney);
	}

}
