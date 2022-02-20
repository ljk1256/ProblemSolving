package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ12904 {
	
	static String S;
	static int Slen;
	static boolean flag;
	
	private static void Solve(String s) {
		if(s.length() == Slen) {
			if(s.equals(S)) flag = true;
			return;
		}
		
		if(s.charAt(s.length()-1) == 'B') {
			String subS = s.substring(0, s.length()-1);
			StringBuilder sb = new StringBuilder();
			sb.append(subS).reverse();
			Solve(sb.toString());
		}
		else {
			Solve(s.substring(0, s.length()-1));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		String T = br.readLine(); // 원본 문자열
		Slen = S.length();
		flag = false;
		
		Solve(T);
		
		if(flag) System.out.println(1);
		else System.out.println(0);
	}

}
