package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16916 {
	
	private static int[] makeTable(char[] S) {
		int patternSize = S.length;
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && S[i] != S[index]) index = table[index-1];
			if(S[i] == S[index]) table[i] = ++index;
		}
		
		return table;
	}
	
	private static void KMP(char[] P, char[] S) {
		int parentSize = P.length;
		int patternSize = S.length;
		int[] table = makeTable(S);
		int index = 0;
		
		for(int i=0; i<parentSize; i++) {
			while(index > 0 && P[i] != S[index]) index = table[index-1];
			if(P[i] == S[index]) {
				if(index == patternSize-1) {
					System.out.println(1);
					return;
				}
				else index++;
			}
		}
		
		System.out.println(0);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] P = br.readLine().toCharArray();
		char[] S = br.readLine().toCharArray();
		
		KMP(P, S);
	}

}
