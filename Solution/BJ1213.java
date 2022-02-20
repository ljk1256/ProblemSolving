package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1213 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		int size = s.length(), cnt = 0;
		int[] arr = new int [26];
		
		for(int i=0; i<size; i++) {
			arr[s.charAt(i) - 'A']++;
		}
		
		boolean isOdd = false;
		int idx = 0;
		for(int i=0; i<26; i++) {
			if(arr[i] != 0) {
				cnt++;
			}
			
			if(arr[i] % 2 == 1) { // 홀수 개의 문자가 있다면
				isOdd = true;
				idx = i;
			}
		}
		
		if(size % 2 == 0) { // 짝수 일 경우
			for(int i=0; i<26; i++) {
				if(arr[i] != 0 && arr[i] % 2 != 0) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
			}
		}
		
		else { // 홀수 일 경우 (한개의 홀수, 나머지 짝수)
			boolean flag = false;
			for(int i=0; i<26; i++) {
				if(arr[i] != 0) {
					if(arr[i] % 2 == 0) {
						continue;
					}
					else {
						if(!flag) {
							flag = true;
							continue;
						}
						else {
							System.out.println("I'm Sorry Hansoo");
							return;
						}
					}
				}
			}
		}
		
		for(int i=0; i<26; i++) {
			if(arr[i] != 0 && cnt != 0) { // 남은 문자가 한개가 아닌경우
				for(int j=0; j<arr[i]/2; j++) {
					sb.append((char)(i+'A'));
				}
				cnt--;
			}
		}
		
		String origin = sb.toString();
		String reverse = sb.reverse().toString(); // 스트링 빌더 reverse 하면 같은 객체를 나타내서 값이 바뀔수 있다
		StringBuilder answer = new StringBuilder();
		
		if(isOdd) answer.append(origin).append((char)(idx+'A')).append(reverse);
		else answer.append(origin).append(reverse);
		
		System.out.println(answer.toString());
	}

}
