package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2447 {
	
	static char[][] star; // 만약 찍고 안찍는 여부만 필요하다면 4바이트 짜리 char보다 boolean형 배열로 선언하여 메모리를 줄일 수 있다
	
	private static void makeStar(int r, int c, int N) {
		
		if(N == 1) { // 더이상 분할 할 수 없다면
			star[r][c] = '*';
		}
		
		else {
			int div = N / 3;
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(i == 1 && j == 1) continue; // 정 중앙 빈공간 (1,1)은 안찍는다
					else {
						makeStar(r + (i*div), c + (j*div), div);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		star = new char [N][N];
		
		for(int i=0; i<N; i++) {
			Arrays.fill(star[i], ' '); // 디폴트를 빈공간
		}
		
		makeStar(0, 0, N);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(star[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
