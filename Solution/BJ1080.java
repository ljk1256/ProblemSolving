package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1080 {
	
	static int[][] A;
	static boolean[][] visited;
	static int answer;
	
	private static void reverse(int row, int column) {
		for(int i=row; i<row+3; i++) {
			for(int j=column; j<column+3; j++) {
				if(A[i][j] == 0) A[i][j] = 1;
				else A[i][j] = 0;
				visited[i][j] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		A = new int [N][M];
		int[][] B = new int [N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				A[i][j] = s.charAt(j) -'0';
			}
		}
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				B[i][j] = s.charAt(j) -'0';
			}
		}
		
		visited = new boolean [N][M];
		answer = 0;
		
		for(int i=0; i<=B.length-3; i++) {
		cp : for(int j=0; j<=B[i].length-3; j++) {
				
				if(A[i][j] != B[i][j]) { // 두번 뒤집으면 같은 결과가 나오기때문에 한번만 뒤집으면서 확인한다
					for(int k=i; k<i+3; k++) {
						for(int t=j; t<j+3; t++) {
							if(!visited[k][t]) {
								reverse(i, j);
								answer++;
								continue cp;
							}
						}
					}
				}
				
			}
		}
		
		bp : for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(A[i][j] != B[i][j]) {
						answer = -1;
						break bp;
					}
				}
			}
		
		System.out.println(answer);
	}

}
