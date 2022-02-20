package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10809 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		int[] check = new int [26];
		boolean[] isUsed = new boolean [26];
		
		for(int i=0; i<input.length; i++) {
			if(!isUsed[input[i] - 'a']) {
				check[input[i] - 'a'] = i;
				isUsed[input[i] - 'a'] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<26; i++) {
			if(isUsed[i]) sb.append(check[i]).append(" ");
			else sb.append(-1).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
