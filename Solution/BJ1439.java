package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1439 {
	
	static int ans;
	static char[] input;
	
	private static void reverse(int start, int end, char diff) {
		for(int i=start; i<=end; i++) {
			input[i] = diff;
		}
		ans++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// token을 0 과 1 로 각각 나눠보고 적은 갯수를 뒤집어도 된다
		
		input = br.readLine().toCharArray();
		
		char prev = input[0];
		boolean flag = false;
		int start = 0, end = 0;
		ans = 0;
		
		for(int i=0; i<input.length; i++) {
			if(input[i] != prev) {
				if(flag) {
					end = i-1;
					prev = input[i];
					reverse(start, end, prev);
					flag = false;
				}
				else {
					flag = true;
					prev = input[i];
					start = i;
				}
			}
		}
		
		if(flag) ans++;
		
		System.out.println(ans);
	}

}
