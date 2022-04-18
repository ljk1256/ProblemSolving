package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1259 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			
			if("0".equals(input)) break;
			
			boolean flag = true;
			
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i) != input.charAt(input.length()-1-i)) {
					flag = false;
					break;
				}
			}
			
			if(flag) sb.append("yes").append("\n");
			else sb.append("no").append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
