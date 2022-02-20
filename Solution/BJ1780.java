package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1780 {
	
	static int[][] arr;
	static int N;
	static int zero; // 0 종이
	static int minus; // -1 종이
	static int plus; // 1 종이
	
	private static void cut(int r, int c, int N) {
		
		if(N == 1) {
			if(arr[r][c] == -1) minus++;
			else if(arr[r][c] == 0) zero++;
			else plus++;
		}
		
		else {
			boolean flag = false;
			int temp = arr[r][c]; // 초기 대조군
		bp : for(int i=r; i<r+N; i++) {
				for(int j=c; j<c+N; j++) {
					if(arr[i][j] != temp) { // 모두 같은 종이가 아니라면
						flag = true;
						break bp;
					}
				}
			}
			
			if(flag) { // 위에서 브레이크 일어났다면
				int div = N/3;
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						cut(r+(div*i), c+(div*j), div);
					}
				}
			}
			
			else { // 모두 같은 종이라면
				if(temp == -1) minus++;
				else if(temp == 0) zero++;
				else plus++;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minus = 0; zero = 0; plus = 0;
		cut(0, 0, N);
		
		sb.append(minus).append("\n").append(zero).append("\n").append(plus);
		System.out.println(sb.toString());
	}

}
