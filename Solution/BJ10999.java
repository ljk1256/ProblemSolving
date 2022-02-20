package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10999 {
	
	static long[] segment;
	
	private static void update(int nodeLeft, int nodeRight, int queryLeft, int queryRight, int nodeIdx, int plus) {
		if()
	}
	
	private static long prefixSum(int nodeLeft, int nodeRight, int queryLeft, int queryRight, int nodeIdx) {
		if()
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		segment = new long [N+1];
		for(int i=1; i<N+1; i++) {
			segment[i] = Long.parseLong(br.readLine());
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int plus = Integer.parseInt(st.nextToken());
				update(1, N, start, end, 1, plus);
			}
			else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				sb.append(prefixSum(1, N, start, end, 1)).append("\n");
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
