package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10870 {
	
	private static int fibo(int cnt) {
		if(cnt <= 1) {
			return cnt;
		}
		
		return fibo(cnt-2) + fibo(cnt-1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(fibo(N));
	}

}
