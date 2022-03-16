package Solution;

import java.io.*;

public class BJ11718 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerBuilder = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			
			if(input == null || input.equals("")) break;
			
			answerBuilder.append(input).append("\n");
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
