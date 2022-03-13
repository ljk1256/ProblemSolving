package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2908 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input1 = st.nextToken();
		String input2 = st.nextToken();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(input1);
		sb.reverse();
		
		int rever1 = Integer.parseInt(sb.toString());
		
		sb.setLength(0);
		sb.append(input2);
		sb.reverse();
		
		int rever2 = Integer.parseInt(sb.toString());
		
		if(rever1 > rever2) System.out.println(rever1);
		else System.out.println(rever2);
	}

}
