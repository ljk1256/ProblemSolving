package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ4354 {
	
	static boolean flag;
	static StringBuilder sb;
	
	private static void Solve(String input) {
		int[] table = makeTable(input);
		
		if(input.length() % (input.length() - table[input.length()-1]) != 0) {
			sb.append(1).append("\n");
		}
		else {
			sb.append(input.length() / (input.length() - table[input.length()-1])).append("\n");
		}
	}
	
	private static int[] makeTable(String pattern) {
		int patternSize = pattern.length();
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) {
				index = table[index-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(index)) {
				table[i] = ++index;
			}
		}
		
		return table;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		sb = new StringBuilder();
		while(!input.equals(".")) {
			Solve(input); 
			input = br.readLine(); // 다음 루프를 위해 초기화
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
