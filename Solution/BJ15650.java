package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15650 {
	
	static boolean[] visited;
	static int M;
	
	private static void perm(int idx, int cnt) {
		
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=1; i<visited.length; i++) {
				if(visited[i]) {
					sb.append(i).append(" ");
				}
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			return;
		}
		
		for(int i=idx; i<visited.length; i++) {
			visited[i] = true;
			perm(i+1, cnt+1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean [N+1];
		perm(1, 0);
	}

}
