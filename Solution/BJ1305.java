package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1305 {
	
	private static int[] makeTable(String input) {
		int inputSize = input.length();
		int[] table = new int [inputSize];
		int index = 0;
		
		for(int i=1; i<inputSize; i++) {
			while(index > 0 && input.charAt(i) != input.charAt(index)) {
				index = table[index-1];
			}
			
			if(input.charAt(i) == input.charAt(index)) {
				table[i] = ++index;
			}
		}
		
		return table;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		int[] table = makeTable(input);
		System.out.println(L - table[L-1]);
	}

}
