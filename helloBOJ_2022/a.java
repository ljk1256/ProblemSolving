package helloBOJ_2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class a {
	
	static boolean flag;
	static int answer;
	static int d;
	
	private static boolean isContain(int decimalNum) {
		
		String converNum = conversion(decimalNum, d);
		char[] Numchar = converNum.toCharArray();
		boolean[] isUsed = new boolean [d];
		
		if(Numchar.length > d || d > Numchar.length) {
			answer = -1;
			flag = false;
			return false;
		}
		
		for(int i=0; i<Numchar.length; i++) {
			if(!isUsed[Numchar[i] - '0']) isUsed[Numchar[i] - '0'] = true; // 한번도 사용하지 않았다면, 사용체크
			else return false; // 중복된거면 더 이상 볼필요 없다
		}
		
		return true;
	}
	
	public static String conversion(int decimal, int K) { // 2~9 진법 변환
		
		StringBuilder sb = new StringBuilder();
		int current = decimal;
		while(current > 0) {
		sb.append(current % K);
		current /= K;
		}
		
		return sb.reverse().toString();
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		flag = true;
		answer = 0;
		
		if(N < (int)Math.pow(d, d-1)) N = (int)Math.pow(d, d-1);
		else N++;
		
		while(flag) {
			if(isContain(N)) {
				answer = N;
				break;
			}
			N++;
		}
		
		System.out.println(answer);
	}

}
