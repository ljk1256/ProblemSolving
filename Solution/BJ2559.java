package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		int[] degree = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			degree[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N-K+1; i++) {
			int sum = 0;
			for(int j=0; j<K; j++) {
				sum += degree[i+j];
			}
			if(max < sum) {
				max = sum;
			}
		}
		
		System.out.println(max);
	}

}
