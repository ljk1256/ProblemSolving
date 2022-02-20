package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1789 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long S = Long.parseLong(br.readLine());
		
		long m = (long)Math.sqrt(2*S);
		
		while(m*m+m > 2*S) m--; // 마지막 수에 1씩 더해가며 찾아간다 >> 즉 n*(n+1)/2 가 커지는 시점만 알면된다
		
		System.out.println(m);
	}

}
