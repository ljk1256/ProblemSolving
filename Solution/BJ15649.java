package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ15649 {
	
	static int[] selected;
	static boolean[] isUsed;
	static int N;
	static int M;
	static BufferedWriter bw;
	
	private static void permutation(int cnt) throws IOException {
		if(cnt == M) {
			for(int i=0; i<selected.length; i++) {
				bw.write(selected[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		selected = new int [M];
		isUsed = new boolean [N+1];
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		permutation(0);
		
		bw.flush();
		br.close();
		bw.close();
	}

}
