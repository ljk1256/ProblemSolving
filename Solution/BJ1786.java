package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1786 {
	
	static StringBuilder sb;
	
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
	
	private static void KMP(String input, String pattern) {
		StringBuilder builder = new StringBuilder();
		int inputSize = input.length();
		int patternSize = pattern.length();
		int[] table = makeTable(pattern);
		int cnt = 0, index = 0;
		
		for(int i=0; i<inputSize; i++) {
			while(index > 0 && input.charAt(i) != pattern.charAt(index)) {
				index = table[index-1];
			}
			
			if(input.charAt(i) == pattern.charAt(index)) {
				if(index == patternSize-1) {
					cnt++;
					builder.append(i-patternSize+2).append(" ");
					index = table[index];
				}
				else index++;
			}
		}
		
		sb.append(cnt).append("\n");
		if(builder.length() != 0) {
			builder.setLength(builder.length()-1);
		}
		sb.append(builder.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String pattern = br.readLine();
		
		sb = new StringBuilder();
		KMP(input, pattern);
		
		System.out.println(sb.toString());
	}

}
