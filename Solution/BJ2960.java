package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2960 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		boolean[] isPrime = new boolean [N+1];
		
		// 에라토스 테네스의 체 소수만 남기는 방법
		// 1~N 까지 소수를 구한다면
		// 2부터 아직 지우지 않는 수를 고른다 >> 이 수는 소수다.
		// 고른 수의 배수는 모두 지운다
		// N 까지 반복하며 소수가 아닌 수들은 모두 지운다
		
	bp : for(int i=2; i<N+1; i++) {
			if(!isPrime[i]) {
				K--;
				isPrime[i] = true;
				if(K == 0) {
					ans = i;
					break bp;
				}
				for(int j=i; j<N+1; j+=i) {
					if(!isPrime[j]) {
						K--;
						isPrime[j] = true;
						if(K == 0) {
							ans = j;
							break bp;
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}
