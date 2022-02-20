package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16953 {
	
	static long A;
	static long B;
	static long answer;
	static boolean flag;
	
	private static void Solve(long cnt, long number) {
		if(number == B) {
			flag = true;
			answer = Math.min(answer, cnt);
			return;
		}
		
		else if(number > B) return;
		
		Solve(cnt+1, number*2);
		Solve(cnt+1, number*10 + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		answer = Long.MAX_VALUE;
		flag = false;
		
		Solve(0, A);
		
		if(answer == Long.MAX_VALUE && !flag) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(answer+1);
	}

}
