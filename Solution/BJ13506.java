package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ13506 {
	
	static int ans;
	
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
	
	private static int kmp(String parent, String pattern) {
		int parentSize = parent.length();
		int patternSize = pattern.length();
		int[] table = makeTable(pattern);
		int index = 0, unionCnt = 0;
		
		for(int i=0; i<parentSize; i++) {
			while(index > 0 && parent.charAt(i) != pattern.charAt(index)) index = table[index-1];
			if(parent.charAt(i) == pattern.charAt(index)) {
				if(index == patternSize-1) {
					unionCnt++;
					index = table[index];
				}
				else index++;
			}
		}
		return unionCnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		ans = Integer.MIN_VALUE;
		int[] table = makeTable(input);
		
		String pattern = input.substring(0, table[table.length-1]); // 후보군이 될 수 있는 것은 접미사의 최대 길이부터 후보군이 된다
		for(int i=0; i<pattern.length(); i++) { // 하나씩 줄여가면서 접두사 와 접미사가 모두 같으면서 중간에 반복된다면
			String suffix = input.substring(input.length()-pattern.length()+i, input.length()); // sub string은 마지막인자는 길이를 넣어준다 알아서-1해줌
			String prefix = input.substring(0, pattern.length()-i);
			
			if(suffix.equals(prefix) && kmp(input, suffix) >= 3) {
				bw.write(suffix);
				bw.flush();
				bw.close();
				return;
			}
		}
		
		bw.write("-1");
		bw.flush();
		bw.close();
	}

}
