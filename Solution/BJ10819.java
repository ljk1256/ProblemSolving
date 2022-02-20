package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10819 {
	
	static int N;
	static int[] origin;
	static int maxAns;
	static int[] selected;
	static boolean[] isUsed;
	
	private static void permutation(int cnt) {
		if(cnt == N) {
			int tempSum = 0;
			for(int i=0; i<N-1; i++) {
				tempSum += Math.abs(origin[selected[i]] - origin[selected[i+1]]);
			}
			maxAns = Math.max(tempSum, maxAns);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				selected[cnt] = i;
				permutation(cnt+1);
				isUsed[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		origin = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			origin[i] = Integer.parseInt(st.nextToken());
		}
		
		maxAns = Integer.MIN_VALUE;
		selected = new int [N];
		isUsed = new boolean [N];
		permutation(0);
		
		System.out.println(maxAns);
	}

}
