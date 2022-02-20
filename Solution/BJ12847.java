package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12847 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] salarys = new int [n+1];
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=n; i++) {
			salarys[i] = Integer.parseInt(st.nextToken());
		}
		
		long maxSum = 0, tempSum = 0;
		int windowSize = m;
		
		for(int i=1; i<=n; i++) {
			if(i > windowSize) tempSum -= salarys[i-windowSize];
			tempSum += salarys[i];
			
			maxSum = Math.max(maxSum, tempSum);
		}
		
		System.out.println(maxSum);
	}

}
