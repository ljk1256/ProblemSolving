package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7453 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		long[] A = new long [n];
		long[] B = new long [n];
		long[] C = new long [n];
		long[] D = new long [n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			D[i] = Long.parseLong(st.nextToken());
		}
		
		long[] ABSum = new long [n*n];
		int idx = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ABSum[idx] = A[i] + B[j];
				idx++;
			}
		}
		
		long[] CDSum = new long [n*n];
		idx = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				CDSum[idx] = C[i] + D[j];
				idx++;
			}
		}
		
		Arrays.sort(ABSum);
		Arrays.sort(CDSum);
		
		int left = 0, right = CDSum.length-1;
		long answer = 0L;
		while(left < ABSum.length && right >= 0) {
			long tempAB = ABSum[left];
			long tempCD = CDSum[right];
			
			if(tempAB + tempCD == 0) {
				long ABcnt = 0L;
				while(left < ABSum.length && ABSum[left] == tempAB) {
					left++;
					ABcnt++;
				}
				
				long CDcnt = 0L;
				while(right >= 0 && CDSum[right] == tempCD) {
					right--;
					CDcnt++;
				}
				
				answer += ABcnt * CDcnt;
			}
			
			else if(tempAB + tempCD < 0) left++;
			else right--;
		}
		
		System.out.println(answer);
	}

}
