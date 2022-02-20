package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1193 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		int n = 1;
		int sub = 0;
		
		while(n*n+n < 2*X) n++;
		
		boolean flag = false;
		sub = n-1;
		
		if(n % 2 == 0) flag = true; // 짝수면  1/n 부터 시작
									// 홀수면 n/1 부터 시작
		
		int startNum = (sub*(sub+1))/2 + 1;
		int numerator = 0, denominator = 0;
		
		if(flag) {
			numerator = 1;
			denominator = n;
		}
		
		else {
			numerator = n;
			denominator = 1;
		}
		
		
		while(true) {
			
			if(X == startNum) break;
			
			if(flag) {
				numerator++;
				denominator--;
				
			}
			else {
				numerator--;
				denominator++;
			}
			startNum++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(numerator).append("/").append(denominator);
		System.out.println(sb.toString());
	}

}
