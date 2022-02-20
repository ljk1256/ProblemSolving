package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ12104 {
	
	static int answer;
	
	private static int[] makeTable(char[] input) {
		int inputSize = input.length;
		int[] table = new int [inputSize];
		int index = 0;
		
		for(int i=1; i<inputSize; i++) {
			while(index > 0 && input[i] != input[index]) index = table[index-1];
			if(input[i] == input[index]) table[i] = ++index;
		}
		
		return table;
	}
	
	private static void KMP(char[] parent, char[] pattern) {
		int parentSize = parent.length;
		int patternSize = pattern.length;
		int[] table = makeTable(pattern);
		int index = 0;
		
		for(int i=0; i<parentSize-1; i++) { // 순환되는 구조는 2배로 한뒤 제일 마지막 인덱스만 확인하지 않으면된다
			while(index > 0 && parent[i] != pattern[index]) index = table[index-1];
			if(parent[i] == pattern[index]) {
				if(index == patternSize-1) {
					answer++;
					index = table[index];
				}
				else index++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		char[] mulitple  = new char [pattern.length*2];
		System.arraycopy(pattern, 0, mulitple, 0, pattern.length);
		System.arraycopy(pattern, 0, mulitple, pattern.length, pattern.length);
		
		answer = 0;
		KMP(mulitple, input);
		System.out.println(answer);
	}

}
