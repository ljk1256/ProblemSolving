package Solution;

import java.util.Scanner;


public class BJ5904 {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	static StringBuilder Moo(int n) {
		
		if(n == 0) {
			sb.append("moo");
			return sb;
		}
		
		else if(n >= 1) {
			Moo(n-1);
			sb.append("m");
			for(int i=0; i<n+2; i++) {
				sb.append("o");
			}
			Moo(n-1);
		}
		return null;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int cnt;
		
		if(N > 5) {
			cnt = N / 5;
		}
		else {
			cnt = N;
		}
		
		Moo(10);
		System.out.println(sb.toString());
		System.out.println(sb.charAt(N-1));
	}

}
