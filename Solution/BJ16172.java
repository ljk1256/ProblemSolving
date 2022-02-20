package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16172 {
	
	static StringBuilder sb;
	
	private static int[] makeTable(String pattern) {
		int patternSize = pattern.length();
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) index = table[index-1];
			if(pattern.charAt(index) == pattern.charAt(i)) table[i] = ++index;
		}
		
		return table;
	}
	
	private static void KMP(String input, String pattern) {
		int inputSize = input.length();
		int patternSize = pattern.length();
		int[] table = makeTable(pattern);
		int index = 0;
		
		for(int i=0; i<inputSize; i++) {
			char c = input.charAt(i);
			if(isNumber(c)) continue;
			
			while(index > 0 && input.charAt(i) != pattern.charAt(index)) index = table[index-1];
			
			if(input.charAt(i) == pattern.charAt(index)) {
				if(index == patternSize-1) {
					System.out.println(1);
					return;
				}
				
				index++;
			}
		}
		
		System.out.println(0);
	}
	
	private static boolean isNumber(char c) {
		if('0' <= c && c <= '9') return true;
		else return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String pattern = br.readLine();
		
		KMP(input, pattern);
	}
	
}
