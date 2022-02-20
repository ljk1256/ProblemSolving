package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1476 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int e = 0, s = 0, m = 0, year = 0;
		while(true) { // 나누는 방법과 나머지를 이용하는 방법을 생각해야한다
			year++;
			e = year % 15;
			s = year % 28;
			m = year % 19;
			
			if(e == 0) e = 15;
	        if (s == 0) s = 28;
	        if (m == 0) m = 19;
			
			if(e == E && s == S && m == M) break;
		}
		
		System.out.println(year);
	}

}
