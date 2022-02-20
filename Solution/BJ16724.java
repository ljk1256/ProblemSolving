package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16724 {
	
	static int N;
	static int M;
	static int[][] marking;
	static char[][] map;
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		else {
			marking[bRoot/M][bRoot%M] = aRoot;
			return true;
		}
	}
	
	private static int find(int n) {
		if(marking[n/M][n%M] == n) return n;
		else return marking[n/M][n%M] = find(marking[n/M][n%M]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char [N][];
		marking = new int [N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				marking[i][j] = M*i + j; // 모든 배열을 0~N-1로 마킹 뒤에 j 기준으로 반드시 해야한다 row column이 모두 영향없는 값으로 선정
			}
		}
		
		int safezoneCnt = N * M;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				switch(map[i][j]) {
					case 'U' : {
						if(union(marking[i][j], marking[i-1][j])) safezoneCnt--;
						break;
					}
					case 'D' : {
						if(union(marking[i][j], marking[i+1][j])) safezoneCnt--;
						break;
					}
					case 'L' : {
						if(union(marking[i][j], marking[i][j-1])) safezoneCnt--;
						break;
					}
					case 'R' : {
						if(union(marking[i][j], marking[i][j+1])) safezoneCnt--;
						break;
					}
				}
			}
		}
		
		System.out.println(safezoneCnt);
	}

}
