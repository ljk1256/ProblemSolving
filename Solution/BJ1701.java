package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1701 {
	
	static char[] input;
	
	private static int makeTable(int startpoint) {
		int inputSize = input.length;
		int[] table = new int [inputSize-startpoint];
		int index = 0;
		int max = 0;
		
		for(int i=startpoint+1; i<inputSize; i++) {
			while(index > 0 && input[i] != input[index+startpoint]) index = table[index-1];
			if(input[i] == input[index+startpoint]) {
				table[i-startpoint] = ++index;
				max = Math.max(max, table[i-startpoint]);
			}
		}
		
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		int answer = 0;
		for(int i=0; i<input.length; i++) {
			answer = Math.max(answer, makeTable(i));
		}
		
		System.out.println(answer);
	}

}
