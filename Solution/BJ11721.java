package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11721 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length; i++) {
			sb.append(input[i]);
			if(i % 10 == 9) sb.append("\n");
		}
		
		if(input.length % 10 == 0) sb.setLength(sb.length()-1); // 이런 조건을 잘 생각해야한다 실수도 실력
		System.out.println(sb.toString()); // if문을 사용시 else 를 항상 생각해야한다.
	}

}
