package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ2407 {
	
	static BigInteger[] fac = new BigInteger[101];
	
	private static void factorial() {
		
		fac[1] = new BigInteger("1");
		for(int i=2; i<101; i++) {
			fac[i] = fac[i-1].multiply(new BigInteger(Integer.toString(i)));
		}
	}
	
	private static BigInteger nCr(int n, int r) {
		return fac[n].divide(fac[n-r].multiply(fac[r]));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		factorial();
		System.out.println(nCr(n, m));
	}

}
