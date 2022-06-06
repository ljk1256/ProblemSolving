package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ16120 {
	
	static int[] ppapTable;
	
	private static int[] makeTable(String pattern) {
		int patternSize = pattern.length();
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) index = table[index-1];
			
			if(pattern.charAt(i) == pattern.charAt(index)) table[i] = ++index;
		}
		
		return table;
	}
	
	private static int kmp(String query, String ppap) {
		int querySize = query.length();
		int tableSize = ppap.length();
		int index = 0;
		
		for(int i=0; i<querySize; i++) {
			while(index > 0 && query.charAt(i) != ppap.charAt(index)) index = ppapTable[index-1];
			
			if(query.charAt(i) == ppap.charAt(index)) {
				if(index == tableSize-1) {
					return i - tableSize + 1;
				}
				else index++;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		/*ppapTable = makeTable("PPAP");
		
		boolean flag = true;
		
		while(flag) {
			int index = kmp(input, "PPAP");
			
			if(index != -1) {
				String left = input.substring(0, index);
				String right = "";
				
				if(index + 4 < input.length()) {
					right = input.substring(index+4, input.length());
				}
				
				input = left + "P" + right;
			}
			else break;
		}
		
		if(input.equals("P")) System.out.println("PPAP");
		else System.out.println("NP");*/
		
		Stack<Character> stack = new Stack<>(); // 특정 시점에 순서나 관계가 있다면 스택을 사용하면 유용하다
		
		for(int i=0; i<input.length(); i++) {
			char tempChar = input.charAt(i);
			
			if(tempChar == 'P') stack.push(tempChar);
			
			else if(tempChar == 'A') {
				if(stack.size() >= 2 && i < input.length()-1 && input.charAt(i+1) == 'P') {
					stack.pop();
					stack.pop();
				}
				else {
					System.out.println("NP");
					return;
				}
			}
		}
		
		if(stack.size() == 1) System.out.println("PPAP");
		else System.out.println("NP");
	}

}
