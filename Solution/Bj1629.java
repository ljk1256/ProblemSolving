package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1629 {
	
	private static long divide(long a, long n, long div) {
		
		if(a == 0) return 0;
		if(n == 0) return 1;
		if(n == 1) return a % div;
		
		long y = 1;
		if(n % 2 == 0) {
			y = divide(a, n/2, div) % div;
			return (y * y % div) % div;
		}
		else {
			y = divide(a, (n-1)/2, div) % div;
			return ((y * y % div) % div * a) % div;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		System.out.println(divide(A, B, C));
	}

}
