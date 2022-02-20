package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] site = new int [N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) { // 고사장 인원 초기화
			site[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken()); // 총 감독
		int C = Integer.parseInt(st.nextToken()); // 부 감독
		
		long sum = 0; // 감독관 수
		for(int i=1; i<N+1; i++) {
			site[i] -= B;
			sum++;
		}
		
		for(int i=1; i<N+1; i++) {
			if(site[i] <= 0) continue;
			
			if(site[i] < C) {
				site[i] -= C;
				sum++;
			}
			else {
				long temp = 0;
				if(site[i] % C == 0) {
					temp = (site[i] / C);
				}
				else {
					temp = (site[i] / C) + 1;
				}
				sum += temp;
			}
		}
		System.out.println(sum);
	}

}
