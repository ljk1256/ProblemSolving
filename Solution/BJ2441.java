package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2441 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=i; j>0; j--) {
				builder.append(" ");
			}
			
			for(int j=i; j<N; j++) {
				builder.append("*");
			}
			builder.append("\n");
		}
		
		builder.setLength(builder.length()-1);
		System.out.println(builder.toString());
	}

}
