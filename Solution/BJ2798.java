package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2798 {
	
	static int N;
	static int M;
	static int[] arr;
	static int answer;
	
	private static void comb(int cnt, int idx, int[] selected) {
		if(cnt == 3) {
			int sum = 0;
			for(int i=0; i<3; i++) {
				sum += arr[selected[i]];
			}
			
			if(sum <= M) answer = Math.max(answer, sum);
			return;
		}
		
		for(int i=idx; i<N; i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1, selected);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = 0;
		int[] selected = new int [3];
		comb(0, 0, selected);
		
		System.out.println(answer);
	}

}
