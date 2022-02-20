package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15652 {
	
	static int[] selected;
	static int N;
	static int M;
	
	private static void comb(int idx, int cnt) {
		
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<selected.length; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			return;
		}
		
		for(int i=idx; i<N+1; i++) {
			if(cnt != 0) {
				if(selected[cnt-1] <= i) {
					selected[cnt] = i;
					comb(idx, cnt+1);
				}
			}
			else {
				selected[cnt] = i;
				comb(idx, cnt+1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		selected = new int [M];
		comb(1, 0);
	}

}
