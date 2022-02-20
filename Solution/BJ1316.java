package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1316 {
	
	private static int isGroup(String input) {
		String s = input;
		int[] atoz = new int [26];
		
		for(int i=0; i<s.length(); i++) {
			int idx = s.charAt(i) - 'a';
			
			if(i == 0) { // 문자열 제일 앞자리라면
				atoz[idx]++;
			}
			
			else {
				if(atoz[idx] != 0) { // 이미 체크했던 알파벳이 나왔다면
					if(idx == s.charAt(i-1) - 'a') atoz[idx]++; // 직전 알파벳과 같다면 연속된것
					else return 0; // 아니면 그룹 단어가 아니다
				}
				else {
					atoz[idx]++; // 처음보는 단어라면
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		while(N-- > 0) {
			answer += isGroup(br.readLine());
		}
		
		System.out.println(answer);
	}

}
