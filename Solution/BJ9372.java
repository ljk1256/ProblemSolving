package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9372 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 국가 수
			int M = Integer.parseInt(st.nextToken()); // 비행기 종류
			
			int[][] nation = new int [N][N];
			for(int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				nation[a-1][b-1] = 1; // 국가 번호 와 인덱스 일치
				nation[b-1][a-1] = 1;
			}
			boolean[] visited = new boolean[N];
			int count = 0;
			visited[0] = true;
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(nation[j][k] == 1 && !visited[k]) {
						visited[k] = true;
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}

}
