package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074 {
	
	static int answer;
	static double r;
	static double c;
	
	private static void z(int n, double r, double c) {
		double x = Math.pow(2, n-1) - 1; // 중점 좌표
		double y = Math.pow(2, n-1) - 1;
		
		if(n == 0) {
			return;
		}
		
		if(r <= x && c <= y) { // 1사분면
			z(n-1, r, c);
		}
		else if(r <= x && c > y) {
			answer += ((Math.pow(2, n-1) * Math.pow(2, n-1)));
			z(n-1, r, c-Math.pow(2, n-1));
		}
		else if(r > x && c <= y) {
			answer += (((Math.pow(2, n-1) * Math.pow(2, n-1))) * 2);
			z(n-1, r-Math.pow(2, n-1), c);
		}
		else if(r > x && c > y) {
			answer += (((Math.pow(2, n-1) * Math.pow(2, n-1))) * 3);
			z(n-1, r-Math.pow(2, n-1), c-Math.pow(2, n-1));
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		answer = 0;
		z(N, r, c);
		System.out.println(answer);
	}

}
