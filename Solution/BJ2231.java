package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2231 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int answer = 0;
		
		for(int i=1; i<=N; i++) {
			int len = String.valueOf(i).length();
			
			int temp = i;
			for(int j=0; j<len; j++) {
				temp += (String.valueOf(i).charAt(j) - '0');
			}
			
			if(temp == N) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}

}
