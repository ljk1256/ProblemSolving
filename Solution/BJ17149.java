package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		int answer = 0; // K & -K 는 이진수에서 가장 뒤에 있는 1의 위치를 의미
		
		for(int i=0; i<N; i++) {
			if(input.charAt(i) == '1') answer++;
		}
		
		System.out.println(answer);
	}

}
