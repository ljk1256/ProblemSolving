package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15654 {
	
	static int[] set;
	static int[] selected;
	static boolean[] visited;
	static int N;
	static int M;
	
	private static void comb(int cnt) {
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<M; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(cnt != 0) {
				if(selected[cnt-1] <= set[i]) {
					selected[cnt] = set[i];
					comb(cnt+1);
				}
			}
			else {
				selected[cnt] = set[i];
				comb(cnt+1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		set = new int [N];
		for(int i=0; i<N; i++) {
			set[i] = Integer.parseInt(st.nextToken());
		}
		
		selected = new int [M];
		visited = new boolean [N];
		Arrays.sort(set);
		comb(0);
	}

}
